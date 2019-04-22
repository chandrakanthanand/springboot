package com.ck.springboot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class TopicController {
	@Autowired
	private TopicsService topicService;

	@RequestMapping("/topics")
	public List<Topic> listOfTopic() {
		return topicService.getAllTopics();
	}
    @RequestMapping("/topic/{id}")
	public Topic getTopicById(@PathVariable("id") int id) {
    	Topic topic= topicService.getTopicById(id);
    	if(topic == null) {
    		throw new TopicNotFoundException("Topic Not found ");
    	}
    	return topic;
	}
    @RequestMapping(value="/newTopic",method=RequestMethod.POST)
    public ResponseEntity addTopic(@RequestBody Topic topic) {
    Topic savedTopic= topicService.addTopic(topic);    
     URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTopic.getId()).toUri();
     return ResponseEntity.created(location).build();
    }
    @RequestMapping(value="/topic/{id}",method=RequestMethod.DELETE)
    public void deleteTopicById(@PathVariable int id) {
    	topicService.deleteTopicByID(id);
    }		
}
