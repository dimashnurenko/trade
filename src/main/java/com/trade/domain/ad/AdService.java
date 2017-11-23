package com.trade.domain.ad;

import com.trade.web.ad.AdDto;
import org.springframework.security.access.annotation.Secured;

public interface AdService {
	@Secured({"ROLE_BUYER", "ROLE_ADMIN"})
	Ad create(Long groupId, Long userId, AdDto dto);
}
