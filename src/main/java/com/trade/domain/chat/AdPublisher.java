package com.trade.domain.chat;

import com.trade.domain.ad.Ad;
import com.trade.domain.ad.AdMapper;
import com.trade.domain.ad.AdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.trade.domain.ad.AdStatus.PUBLISHED;

@Component
public class AdPublisher {

	private final AdRepo repo;
	private final AdMapper mapper;
	private final SimpMessagingTemplate broker;

	@Autowired
	public AdPublisher(AdRepo repo, AdMapper mapper, SimpMessagingTemplate broker) {
		this.repo = repo;
		this.mapper = mapper;
		this.broker = broker;
	}

	@Transactional
	public void publish(Long adId) {
		Ad adEntity = repo.findOne(adId);
		AdMessage adMessage = mapper.mapToMessage(adEntity);

		broker.convertAndSend("/ws-channel/ad-publishing/" + adEntity.getGroupId(), adMessage);

		adEntity.setStatus(PUBLISHED);
		repo.save(adEntity);
	}
}
