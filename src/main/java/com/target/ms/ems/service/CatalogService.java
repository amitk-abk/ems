package com.target.ms.ems.service;

import org.springframework.transaction.annotation.Transactional;

import com.target.ms.ems.response.CatalogResponse;

public interface CatalogService {

	@Transactional
	String createCatalog(CatalogResponse catalogBean);

}
