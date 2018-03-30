package com.trade.web.image;

import com.trade.core.domain.image.ImageStorage;
import com.trade.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/products/{productId}/images")
public class ImagesController {
	private final static Logger log = LoggerFactory.getLogger(ImagesController.class);

	private final ImageStorage imageStorage;

	@Autowired
	public ImagesController(ImageStorage imageStorage) {
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

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImagesResource>> images(@PathVariable Long productId) {
		List<Long> images = imageStorage.findImagesIds(productId);
		List<ImagesResource> resources = images.stream().map(id -> new ImagesResource(id, productId)).collect(toList());
		return ok(resources);
	}

	@GetMapping(path = "/{imageId}", produces = IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> image(@PathVariable Long productId, @PathVariable Long imageId) {
		return ok(imageStorage.findImage(productId, imageId));
	}
}
