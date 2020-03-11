package com.techouts.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cluster_config")
public class ClusterConfig {

	@Id
	private String id;
	private String name;
	private String ip;
	private List<String> nodes;

}
