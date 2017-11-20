package com.trade.common;

import com.trade.common.exception.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

import static java.util.Optional.ofNullable;

public class RepoUtils {

	public static <T> T findEntity(Long id, CrudRepository<T, Long> repo) {
		return ofNullable(repo.findOne(id)).orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
