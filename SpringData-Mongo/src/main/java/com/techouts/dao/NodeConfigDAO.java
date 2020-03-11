package com.techouts.dao;

import java.util.List;

import com.techouts.document.NodeConfig;

public interface NodeConfigDAO {
	public List<NodeConfig> getAllNodes();

	public String addNodeConfig(NodeConfig nodeConfig);

	public List<NodeConfig> getNodebyIp(String ip);

	public List<NodeConfig> getNodeById(String id);

	public int updateNode(NodeConfig config);

}
