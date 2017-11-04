package com.trade.domain.ad;

import com.trade.web.ad.AdDto;

public interface AdService {
	AdEntity create(Long groupId, AdDto dto);
}
