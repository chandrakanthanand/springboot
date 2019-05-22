package com.ck.springboot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class TopicController {
	@Autowired
	private TopicsService topicService;

	@RequestMapping(value="/topics",headers="version=1")
	public MappingJacksonValue listOfTopic() {
		List<Topic> list = topicService.getAllTopics();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id");
		FilterProvider filters= new SimpleFilterProvider().addFilter("TopicFilter", filter);
		MappingJacksonValue jacksonValue=new MappingJacksonValue(list);
		jacksonValue.setFilters(filters);
		return jacksonValue;
	}

	@RequestMapping("/topic/{id}")
	public Resource<Topic> getTopicById(@PathVariable("id") int id) {
		Topic topic = topicService.getTopicById(id);
		if (topic == null) {
			throw new TopicNotFoundException("Topic Not found ");
		}
		Resource<Topic> resource = new Resource<Topic>(topic);
		Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listOfTopic())
				.withRel("users");
		resource.add(link);
		return resource;
	}

	@RequestMapping("/topics/{id}")
	public void getTopicByString(@PathVariable("id") String id) {
		System.out.println(id);
	}

	@RequestMapping(value = "/newTopic", method = RequestMethod.POST)
	public ResponseEntity addTopic(@RequestBody Topic topic) {
		Topic savedTopic = topicService.addTopic(topic);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTopic.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
	public void deleteTopicById(@PathVariable int id) {
		topicService.deleteTopicByID(id);
	}
}
