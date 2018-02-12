package com.target.ms.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.target.ms.ems.response.CatalogResponse;
import com.target.ms.ems.service.CatalogService;

@RestController
@RequestMapping("/api")
public class CatalogController {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
	
	private CatalogService catalogService;
	
	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@PostMapping("/catalog")
	@ResponseStatus(HttpStatus.CREATED)
	public String createCatalog(@RequestBody CatalogResponse catalogBean) {
		String response = catalogService.createCatalog(catalogBean);
		logger.info("Created catalog {}", catalogBean.getCatalogName());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String operationException(Exception e) {
		logger.error("Error occured in processing the request. Message : {}", e.getMessage(), e);
		return "Something went wrong in processing the request, kindly check the payload or request";
	}
}
