package com.techouts.service;

import java.util.List;

import com.techouts.document.ClusterConfig;
import com.techouts.document.NodeConfig;

public interface ClusterNodeService {

	ClusterConfig getClusterConfigById(String name);

	List<NodeConfig> getAllNodes();

	List<NodeConfig> getNodeConfigByIp(String ip);

	NodeConfig getNodeConfigById(String id);

	List<ClusterConfig> getAllClusters();

	String insertRecord(NodeConfig config);

}
