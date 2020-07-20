package com.techouts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.techouts.document.ClusterConfig;

@Repository
public class ClusterConfigDAOImpl implements ClusterConfigDAO {

	@Autowired
	private MongoTemplate template;

	@Override
	public List<ClusterConfig> getAllClusters() {

		return template.findAll(ClusterConfig.class);
	}

	@Override
	public List<ClusterConfig> getClusterById(String id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		return template.find(query, ClusterConfig.class);
	}

}
