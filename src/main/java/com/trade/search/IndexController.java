package com.trade.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/index")
public class IndexController {
	private final IndexService indexService;

	@Autowired
	public IndexController(IndexService indexService) {
		this.indexService = indexService;
	}

	@PostMapping
	public ResponseEntity indexWebSite(@RequestParam String webUrl) {
		return new ResponseEntity(OK);
	}
}
