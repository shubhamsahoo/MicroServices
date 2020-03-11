package com.techouts.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

	@Autowired
	private NodeList nodeList;
	
	@Scheduled(fixedDelay = 100)
	public void jobScheduling() throws IOException {

		Map<String, Integer> nodelist = nodeList.getAllNodes();

		for (Map.Entry<String, Integer> entry : nodelist.entrySet()) {

			String url = entry.getKey();
			int code = nodeList.getStatus(url);
			nodelist.put(url, code);
		}
		/*for (Map.Entry<String, Integer> entry : nodelist.entrySet()) {
		
			System.out.println(entry.getKey()+" "+ entry.getValue());
		
		}
		System.out.println("****************************");
		*/
	}
}