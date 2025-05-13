package com.example.master.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.master.service.KafkaProducerService;

@Controller
public class BusinessCaseController {
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	private AtomicInteger flag = new AtomicInteger(0);
	
	@GetMapping("/name/{name}")
	@ResponseBody
	public String addressCase(@PathVariable String name) {
		return "Hello " + name;
	}
	
	@PostMapping("/saga/success")
	@ResponseBody
	public String sagaSuccess() {
		kafkaProducerService.send("saga-events", "START_SAGA_SUCCESS : " + flag.getAndIncrement());
		return "success";
	}
	
	@PostMapping("/saga/failure")
	@ResponseBody
	public String sagaFailure() {
		return "failed"; 
	}
}
