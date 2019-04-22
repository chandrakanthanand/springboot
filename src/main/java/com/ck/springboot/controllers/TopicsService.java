package com.ck.springboot.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicsService {
private List<Topic> topics=new ArrayList<Topic>(Arrays.asList(new Topic(1, "Spring", "Dependecy Injection"),
		new Topic(2,"Java","Platform Independent")
		));

		

public List<Topic> getAllTopics(){
	return topics;
}
public Topic getTopicById(int id) {
	Topic returnTopic = null;
	for(Topic topic:topics) {
		if(topic.getId() == id) {
			returnTopic=topic;
		}
		
	}
	return returnTopic;
}
public Topic addTopic(Topic topic) {
	topics.add(topic);
	return topic;	
}
public void deleteTopicByID(int id) {
	Topic t=null;
	for(Topic topic:topics) {
		if(topic.getId() == id) {
			System.out.println("I'm here");
			t=topic;
		}
	}
	if(topics.remove(t)) {
	System.out.println(topics);
	}
}

}
