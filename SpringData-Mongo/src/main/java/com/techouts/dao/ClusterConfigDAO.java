package com.techouts.dao;

import java.util.List;

import com.techouts.document.ClusterConfig;

public interface ClusterConfigDAO {
	public List<ClusterConfig> getAllClusters() ;

	public List<ClusterConfig> getClusterById(String id);
}
