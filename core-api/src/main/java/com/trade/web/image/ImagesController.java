package com.trade.web.image;

import com.trade.core.domain.image.ImageStorage;
import com.trade.exception.BaseExceptionReason;
import com.trade.exception.CoreAPIException;
import com.trade.exception.client.ApiExceptionDetails;
import com.trade.exception.client.ExceptionReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.trade.exception.BaseExceptionReason.SERVER_ERROR;
import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
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

	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity upload(@PathVariable Long productId,
	                             @RequestParam("image") MultipartFile file) {
		try {
			imageStorage.store(productId, file.getBytes(), file.getOriginalFilename());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new CoreAPIException(SERVER_ERROR, exceptionDetails("image.store.error"));
		}

		return ok().build();
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImagesResource>> images(@PathVariable Long productId) {
		List<Long> images = imageStorage.findImagesIds(productId);
		List<ImagesResource> resources = images.stream().map(id -> new ImagesResource(id, productId)).collect(toList());
		return ok(resources);
	}

	@GetMapping(path = "/{imageId}")
	public ResponseEntity<byte[]> image(@PathVariable Long productId, @PathVariable Long imageId) {
		byte[] image = imageStorage.findImage(productId, imageId);
		return ok().contentType(IMAGE_JPEG).body(image);
	}
}
