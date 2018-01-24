package com.trade.web.image;

import com.trade.common.exception.ServerException;
import com.trade.domain.image.ImageStorage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/products/{productId}/images")
public class UploadImageController {
	private final static Logger log = Logger.getLogger(UploadImageController.class);

	private final ImageStorage imageStorage;

	@Autowired
	public UploadImageController(ImageStorage imageStorage) {
		this.imageStorage = imageStorage;
	}

	@PostMapping
	public ResponseEntity upload(@PathVariable Long productId,
	                             @RequestParam("image") MultipartFile file) {
		try {
			imageStorage.store(productId, file.getBytes(), file.getOriginalFilename());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new ServerException(e.getMessage());
		}

		return ok().build();
	}
}
