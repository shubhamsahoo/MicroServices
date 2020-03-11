package com.techouts.command;

import com.mongodb.BasicDBObject;

import lombok.Data;

@Data
public class NodeCommand {
	private String id;
	private String ip;
	private String config;
	private String thread_count;
	private String path;
	private BasicDBObject property;
	private String key;
	private String value;

}
