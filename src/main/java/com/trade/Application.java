package com.trade;

import com.trade.search.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);

		ElasticSearchService service = new ElasticSearchService();
		service.callService();
	}
}
