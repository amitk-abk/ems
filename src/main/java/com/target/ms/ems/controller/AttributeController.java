package com.target.ms.ems.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.target.ms.ems.response.AttributeResponse;
import com.target.ms.ems.service.AttributeService;

@RestController
@RequestMapping("/api")
public class AttributeController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttributeController.class);

	private AttributeService attributeService;
	
	@Autowired
	public AttributeController(AttributeService service) {
		this.attributeService = service;
	}
	
	@PostMapping("/attribute")
	@ResponseStatus(HttpStatus.CREATED)
	public String createAttribute(@RequestBody AttributeResponse attributeResponse) {
		String response = attributeService.createAttribute(attributeResponse);
		logger.info("Created attribute {}", attributeResponse.getName());
		return response;
	}
	
	@GetMapping("/attribute/{attributeName}")
	@ResponseStatus(HttpStatus.OK)
	public AttributeResponse getAttribute(@PathVariable String attributeName) {
		AttributeResponse response = attributeService.getAttributeFor(attributeName);
		logger.info("Retrieving the attribute details for attribute {}", attributeName);
		return response;
	}
	
	@PutMapping("/attribute")
	@ResponseStatus(HttpStatus.OK)
	public String updateAttribute(@RequestBody AttributeResponse attributeResponse) {
		String response = attributeService.updateAttributeFor(attributeResponse);
		logger.info("Updating the attribute {}", attributeResponse.getName());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String operationException(Exception e) {
		return "Something went wrong in processing the request, kindly check the payload or request";
	}
	
	@GetMapping("/attribute/all")
	@ResponseStatus(HttpStatus.OK)
	public List<AttributeResponse> getAllAttributes() {
		List<AttributeResponse> allAttributes = attributeService.getAllAttributes();
		logger.info("Fetching all the attributes");
		return allAttributes;
	}
}
