package com.techouts.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

import lombok.Data;

@Data
@Document("node_config")
public class NodeConfig {

	@Id
	private String id;
	private String ip;
	private String config;
	private String thread_count;
	private String path;
	private BasicDBObject property;
}