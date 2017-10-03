package com.trade.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
	private final IndexService indexService;

	@Autowired
	public IndexController(IndexService indexService) {
		this.indexService = indexService;
	}

	@PostMapping(value = "/index")
	public List<String> indexWebSite(@RequestParam String webUrl) {
		return indexService.getLinks(webUrl);
	}

	@GetMapping(value = "/hello")
	public String hello() {
		return "hello";
	}
}
