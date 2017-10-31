package com.trade.domain.buyer;

import com.trade.domain.buyer.settings.BuyerSettingsEntity;
import com.trade.domain.buyer.settings.BuyerSettingsRepo;
import com.trade.web.buyer.BuyerDto;
import com.trade.web.buyer.BuyerMapper;
import com.trade.web.buyer.BuyerSettingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyerServiceImpl implements BuyerService {
	private final BuyerMapper mapper;
	private final BuyerRepo buyerRepo;
	private final BuyerSettingsRepo buyerSettingsRepo;

	@Autowired
	public BuyerServiceImpl(BuyerMapper mapper, BuyerRepo buyerRepo, BuyerSettingsRepo buyerSettingsRepo) {
		this.mapper = mapper;
		this.buyerRepo = buyerRepo;
		this.buyerSettingsRepo = buyerSettingsRepo;
	}

	@Override
	public BuyerEntity create(BuyerDto dto) {
		return buyerRepo.save(mapper.map(dto));
	}

	@Override
	public BuyerSettingsEntity create(BuyerSettingsDto dto) {
		return buyerSettingsRepo.save(mapper.map(dto));
	}
}
