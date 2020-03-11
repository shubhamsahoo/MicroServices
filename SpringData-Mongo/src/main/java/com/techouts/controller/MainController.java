package com.techouts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mongodb.BasicDBObject;
import com.techouts.command.NodeCommand;
import com.techouts.document.ClusterConfig;
import com.techouts.document.NodeConfig;
import com.techouts.service.ClusterNodeService;
import com.techouts.service.NodeList;

@Controller
@SessionAttributes("nodeCommand")
public class MainController {
	private static BasicDBObject property;

	@Autowired
	private Environment env;

	@Autowired
	private NodeList nodeList;

	@Autowired
	private KafkaTemplate<String, Object> template;

	@Autowired
	private ClusterNodeService service;

	private int getActiveThreadCount() {
		int threadCount = 0;
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for (Thread t : threadSet) {
			if (t.getThreadGroup() == Thread.currentThread().getThreadGroup()
					&& t.getState() == Thread.State.RUNNABLE) {
				++threadCount;
			}
		}
		return threadCount;
	}

	@ModelAttribute("nodeCommand")
	public NodeCommand createNC() {
		return new NodeCommand();
	}

	@PostMapping("/url")
	@ResponseBody
	public String ajaxCall() throws IOException {
		int status = 0;
		status = nodeList.getStatus("http://localhost:8181/");
		return String.valueOf(status);
	}

	@RequestMapping("/cluster.htm")
	public String getClusters(Map<String, Object> map) {
		List<ClusterConfig> allClusters = null;
		System.out.println("MainController.getClusters()");
		// get all clusters list
		allClusters = service.getAllClusters();
		// put it into model data
		map.put("cluster_configs", allClusters);
		// get all nodes
		return "cluster_configs";
	}

	@RequestMapping("/nodes.htm")
	public String showNodes(HttpServletRequest req, Map<String, Object> map) {
		List<ClusterConfig> allClusters = null;
		List<NodeConfig> allNodes = new ArrayList<>();
		System.out.println("MainController.showNodes()");
		ClusterConfig config = null;
		// get all clusters
		allClusters = service.getAllClusters();
		// get the Cluster obj by giving id
		config = service.getClusterConfigById(req.getParameter("id"));
		map.put("css", "active");
		// get all nodes by using cluster
		config.getNodes().forEach(ip -> {
			service.getNodeConfigByIp(ip).forEach(nconfig -> {
				nconfig.setThread_count(String.valueOf(getActiveThreadCount()));
				allNodes.add(nconfig);
			});
		});
		// add clusters and nodes to map
		map.put("cluster_configs", allClusters);
		map.put("node_configs", allNodes);
		return "cluster_configs";
	}

	@GetMapping("/search_property.htm")
	public String lunchSearchedForm(@ModelAttribute("nodeCommand") NodeCommand cmd, HttpServletRequest req,
			Map<String, Object> map) {
		NodeConfig config = null;
		Set<String> keys = null;
		List<String> propertyKey = new ArrayList<>();
		List<String> propertyValue = new ArrayList<>();
		System.out.println("MainController.lunchSearchedForm()");
		Map<String, String> entry = new HashMap<String, String>();
		// read form data
		String searchedProperty = req.getParameter("searchstring");
		System.out.println(req.getParameter("id"));
		// use Service
		config = service.getNodeConfigById(req.getParameter("id"));
		// copy node config object data to node command
		BeanUtils.copyProperties(config, cmd);
		BasicDBObject property = cmd.getProperty();
		keys = property.keySet();
		keys.forEach(key -> {
			if (key.contains(searchedProperty)) {
				propertyKey.add(key);
			}
		});
		propertyKey.forEach(key -> {
			propertyValue.add((String) property.get(key));
		});
		for (int i = 0; i < propertyKey.size(); i++) {
			entry.put(propertyKey.get(i), propertyValue.get(i));
		}
		map.put("property", entry);
		return "search_property";
	}

	@PostMapping("/search_property.htm")
	public String finalFpdate(@ModelAttribute("nodeCommand") NodeCommand cmd, Map<String, Object> map)
			throws IOException, InterruptedException {
		NodeConfig config = null;
		System.out.println("MainController.finalFpdate()");
		List<NodeConfig> allNodes = null;
		File file1 = null;
		String result = null;
		BasicDBObject propertyOld = null;
		// convert cmd to config
		config = new NodeConfig();
		// initialize old property
		propertyOld = cmd.getProperty();
		BeanUtils.copyProperties(cmd, config);
		propertyOld.put(cmd.getKey(), cmd.getValue());
		config.setProperty(propertyOld);
		// check the given tomcat path is Exists or not
		if (!(new File(config.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name")).exists()))
			throw new IOException(
					"Invalid Tomcat Location" + config.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name"));

		if (!(new File(config.getPath() + "/" + env.getProperty("tomcat.starting-file-name")).exists()))
			throw new IOException(
					"Invalid Tomcat Location" + config.getPath() + "/" + env.getProperty("tomcat.starting-file-name"));

		result = service.insertRecord(config);
		if (result.contains("Added Successfully")) {
			file1 = new File(config.getPath());
			ProcessBuilder builder = new ProcessBuilder(env.getProperty("tomcat.shutdown-file-name"));
			builder.directory(file1);
			builder.start();
			Thread.sleep(1000);
			ProcessBuilder builder1 = new ProcessBuilder(env.getProperty("tomcat.starting-file-name"));
			builder1.directory(file1);
			builder1.start();
			Thread.sleep(5000);
			template.send(env.getProperty("listener.topic"), "Server Restarted");
		}
		else {
			return "cluster_config";
		}
		allNodes = service.getAllNodes();
		for (NodeConfig conf : allNodes) {
			if (!(conf.getId().equals(config.getId()))) {

				conf.setProperty(config.getProperty());
				result = service.insertRecord(conf);
				if (result.contains("Added Successfully")) {
					if (conf.getPath() != null) {
						file1 = new File(conf.getPath());

						if (!(new File(conf.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name")).exists()))
							throw new IOException("Invalid Tomcat Location" + conf.getPath() + "/"
									+ env.getProperty("tomcat.shutdown-file-name"));

						if (!(new File(conf.getPath() + "/" + env.getProperty("tomcat.starting-file-name")).exists()))
							throw new IOException("Invalid Tomcat Location" + conf.getPath() + "/"
									+ env.getProperty("tomcat.starting-file-name"));

						ProcessBuilder builder = new ProcessBuilder(env.getProperty("tomcat.shutdown-file-name"));
						builder.directory(file1);
						builder.start();
						Thread.sleep(1000);
						ProcessBuilder builder1 = new ProcessBuilder(env.getProperty("tomcat.starting-file-name"));
						builder1.directory(file1);
						builder1.start();
						Thread.sleep(5000);
						template.send(env.getProperty("listener.topic"), "Server Restarted");
					}
				}
			}
			allNodes = service.getAllNodes();
		}
		map.put("result", result);
		return "cluster_configs";
	}

	@GetMapping("/add_property.htm")
	public String addPropertyForm(HttpServletRequest req, @ModelAttribute("nodeCommand") NodeCommand cmd) {
		NodeConfig config = null;
		// use Service
		config = service.getNodeConfigById(req.getParameter("id"));
		// copy node config object data to node command
		BeanUtils.copyProperties(config, cmd);
		System.out.println("MainController.addPropertyForm()");
		return "add_property";
	}

	@PostMapping("/add_property.htm")
	public String addPropertyFormProcess(@ModelAttribute("nodeCommand") NodeCommand cmd, Map<String, Object> map)
			throws IOException, InterruptedException {
		NodeConfig config = null;
		String result = null;
		System.out.println("MainController.addPropertyFormProcess()");
		BasicDBObject obj = cmd.getProperty();
		File file1 = null;
		// convert command to config
		config = new NodeConfig();
		BeanUtils.copyProperties(cmd, config);
		obj.put(cmd.getKey(), cmd.getValue());
		property = config.getProperty();
		config.setProperty(obj);
		// check the given tomcat path
		if (!(new File(config.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name")).exists()))
			throw new IOException(
					"Invalid Tomcat Location" + config.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name"));

		if (!(new File(config.getPath() + "/" + env.getProperty("tomcat.starting-file-name")).exists()))
			throw new IOException(
					"Invalid Tomcat Location" + config.getPath() + "/" + env.getProperty("tomcat.starting-file-name"));

		// use service
		result = service.insertRecord(config);
		if (result.contains("Added Successfully")) {
			file1 = new File(config.getPath());
			ProcessBuilder builder = new ProcessBuilder(env.getProperty("tomcat.shutdown-file-name"));
			builder.directory(file1);
			builder.start();
			Thread.sleep(1000);
			ProcessBuilder builder1 = new ProcessBuilder(env.getProperty("tomcat.starting-file-name"));
			builder1.directory(file1);
			builder1.start();
			Thread.sleep(5000);
			template.send(env.getProperty("listener.topic"), "Server Restarted");
		}

		return "cluster_configs";
	}

	@RequestMapping(value = "/ajaxrecursive", method = RequestMethod.POST)
	public String updateCommonPropertyInAllNodes(HttpServletRequest req, Map<String, Object> map)
			throws IOException, InterruptedException {
		NodeConfig config = null;
		File file = null;
		String result = null;
		// use Service
		config = service.getNodeConfigById(req.getParameter("nodeval"));
		// update the property with new property
		config.setProperty(property);
		// check the given tomcat path
		if (!(new File(config.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name")).exists()))
			throw new IOException(
					"Invalid Tomcat Location" + config.getPath() + "/" + env.getProperty("tomcat.shutdown-file-name"));

		if (!(new File(config.getPath() + "/" + env.getProperty("tomcat.starting-file-name")).exists()))
			throw new IOException(
					"Invalid Tomcat Location" + config.getPath() + "/" + env.getProperty("tomcat.starting-file-name"));

		result = service.insertRecord(config);
		map.put("result", result);
		if (result.contains("Added Successfully")) {
			file = new File(config.getPath());
			ProcessBuilder builder = new ProcessBuilder(env.getProperty("tomcat.shutdown-file-name"));
			builder.directory(file);
			builder.start();
			Thread.sleep(1000);
			ProcessBuilder builder1 = new ProcessBuilder(env.getProperty("tomcat.starting-file-name"));
			builder1.directory(file);
			builder1.start();
			Thread.sleep(5000);
			template.send(env.getProperty("listener.topic"), "Server Restarted");
		}
		return "cluster_configs";
	}

}// class
