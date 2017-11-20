package com.trade.domain.ad;

import com.trade.web.ad.AdDto;

public interface AdService {
	Ad create(Long groupId, AdDto dto);
}
