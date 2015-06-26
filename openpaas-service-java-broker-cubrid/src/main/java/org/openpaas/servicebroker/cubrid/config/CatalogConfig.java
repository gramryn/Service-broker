package org.openpaas.servicebroker.cubrid.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openpaas.servicebroker.model.Catalog;
import org.openpaas.servicebroker.model.Plan;
import org.openpaas.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

	@Bean
	public Catalog catalog() {		
		return new Catalog( Arrays.asList(
				new ServiceDefinition(
						"cubrid", 
						"Cubrid DB", 
						"A simple cubrid implementation", 
						true, 
						true,
						getPlans(),
						Arrays.asList("cubrid", "document"),
						getServiceDefinitionMetadata(),
						null,
						null)));
	}

	/* Used by Pivotal CF console */	

	private Map<String,Object> getServiceDefinitionMetadata() {
		Map<String,Object> sdMetadata = new HashMap<String,Object>();
		sdMetadata.put("displayName", "CubridDB");
		sdMetadata.put("imageUrl","http://www.openpaas.org/rs/cubrid/images/CubridDB_Logo_Full.png");
		sdMetadata.put("longDescription","CubridDB Service");
		sdMetadata.put("providerDisplayName","OpenPaas");
		sdMetadata.put("documentationUrl","http://www.openpaas.org");
		sdMetadata.put("supportUrl","http://www.openpaas.org");
		return sdMetadata;
	}

	private Map<String,Object> getPlanMetadata(String vol, String charset) {		
		Map<String,Object> planMetadata = new HashMap<String,Object>();
		planMetadata.put("costs", getCosts());
		planMetadata.put("bullets", getBullets(vol, charset));
		return planMetadata;
	}

	private List<Map<String,Object>> getCosts() {
		Map<String,Object> costsMap = new HashMap<String,Object>();

		Map<String,Object> amount = new HashMap<String,Object>();
		amount.put("usd", new Double(0.0));

		costsMap.put("amount", amount);
		costsMap.put("unit", "MONTHLY");

		return Arrays.asList(costsMap);
	}

	private List<String> getBullets(String vol, String charset) {
		return Arrays.asList("Shared CubridDB server", 
				vol+" Storage", 
				charset+" Character Set");
	}

	private List<Plan> getPlans() {
		
		List<Plan> plans = Arrays.asList(
				new Plan("cubrid-plan-A", 
						"Cubrid Plan A", 
						"This is a Cubrid plan A. 100 MB Database volume size and UTF-8 Charactor set.",
						getPlanMetadata("100 MB", "UTF-8"),
						true),
				new Plan("cubrid-plan-B", 
						"Cubrid Plan B", 
						"This is a Cubrid plan B. 200 MB Database volume size and UTF-8 Charactor set.",
						getPlanMetadata("200 MB", "UTF-8"),
						true),
				new Plan("cubrid-plan-C", 
						"Cubrid Plan C", 
						"This is a Cubrid plan C. 100 MB Database volume size and EUC-KR Charactor set.",
						getPlanMetadata("100 MB", "EUC-KR"),
						true),
				new Plan("cubrid-plan-D", 
						"Cubrid Plan D", 
						"This is a Cubrid plan D. 200 MB Database volume size and EUC-KR Charactor set.",
						getPlanMetadata("200 MB", "EUC-KR"),
						true));
		
		return plans;
	}

}