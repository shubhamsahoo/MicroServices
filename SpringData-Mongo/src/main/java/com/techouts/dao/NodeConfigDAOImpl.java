package com.techouts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;
import com.techouts.document.NodeConfig;

@Repository
public class NodeConfigDAOImpl implements NodeConfigDAO {

	@Autowired
	private MongoTemplate template;

	@Override
	public List<NodeConfig> getAllNodes() {
		return template.findAll(NodeConfig.class);
	}

	@Override
	public List<NodeConfig> getNodebyIp(String ip) {
		Query query = new Query();
		query.addCriteria(Criteria.where("ip").is(ip));
		return template.find(query, NodeConfig.class);
	}

	@Override
	public String addNodeConfig(NodeConfig nodeConfig) throws NullPointerException {
		if (nodeConfig == null)
			throw new NullPointerException("Property must not be empty");
		template.save(nodeConfig);
		return "Your Data Added Successfully";
	}

	@Override
	public List<NodeConfig> getNodeById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return template.find(query, NodeConfig.class);
	}

	@Override
	public int updateNode(NodeConfig config) {
		Query query = null;
		Update update = null;
		// create a Query object and add your criteria
		query = new Query();
		query.addCriteria(Criteria.where("id").is(config.getId()));
		// create a update object and add our new values
		update = new Update();
		update.set("ip", config.getIp());
		update.set("config", config.getConfig());
		update.set("thread_count", config.getThread_count());
		UpdateResult res = template.updateFirst(query, update, NodeConfig.class);
		return (int) res.getModifiedCount();
	}

}