package com.target.ms.ems.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.ms.ems.model.Catalog;
import com.target.ms.ems.repository.CatalogRepository;
import com.target.ms.ems.response.CatalogResponse;
import com.target.ms.ems.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {
	
	@Autowired
	private CatalogRepository catalogRepository;	
	

	@Override
	public String createCatalog(CatalogResponse catalogBean) {
		Catalog catalog = new Catalog();
		catalog.setCatalogName(catalogBean.getCatalogName());
		catalog.setCatalogCode(catalogBean.getCatalogCode());
		catalog.setDescription(catalogBean.getDescription());
		catalog.setDisplayName(catalogBean.getDisplayName());
		catalog = catalogRepository.save(catalog);
		return "created catalog:" + catalog.getCatalogName();
	}

}
