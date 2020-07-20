package com.techouts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.techouts.dao.ClusterConfigDAO;
import com.techouts.dao.NodeConfigDAO;
import com.techouts.document.ClusterConfig;
import com.techouts.document.NodeConfig;

@Service
public class ClusterNodeServiceImpl implements ClusterNodeService {

	@Autowired
	private Environment env;

	@Autowired
	private ClusterConfigDAO clusterDao;

	@Autowired
	private NodeConfigDAO nodeDao;

	@Autowired
	private KafkaTemplate<String, Object> template;

	@Override
	public ClusterConfig getClusterConfigById(String id) {
		return clusterDao.getClusterById(id).get(0);
	}

	@Override
	public List<NodeConfig> getNodeConfigByIp(String ip) {
		return nodeDao.getNodebyIp(ip);
	}

	@Override
	public List<ClusterConfig> getAllClusters() {
		return clusterDao.getAllClusters();
	}

	@Override
	public List<NodeConfig> getAllNodes() {
		return nodeDao.getAllNodes();
	}

	@Override
	public String insertRecord(NodeConfig config) {
		String result = nodeDao.addNodeConfig(config);
		if (nodeDao.addNodeConfig(config).contains(("Added Successfully"))) {
			template.send(env.getProperty("listener.topic"), "Your Property Added Successfully");
		}
		else {
			template.send(env.getProperty("listener.topic"), "Your Property Added Failed");
		}
		return result;
	}

	@Override
	public NodeConfig getNodeConfigById(String id) {
		return nodeDao.getNodeById(id).get(0);
	}

}
