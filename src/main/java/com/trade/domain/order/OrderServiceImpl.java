package com.trade.domain.order;

import com.trade.common.exception.ResourceNotFoundException;
import com.trade.common.exception.ServerException;
import com.trade.domain.ad.Ad;
import com.trade.domain.ad.AdRepo;
import com.trade.domain.user.UserRepo;
import com.trade.web.user.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.trade.common.RepoUtils.findEntity;
import static com.trade.common.errorcode.ErrorCodes.SECURITY_ERROR;
import static com.trade.domain.order.Order.builder;
import static com.trade.domain.order.Status.NEW;
import static java.util.Optional.ofNullable;

@Component
public class OrderServiceImpl implements OrderService {
	private final AdRepo adRepo;
	private final OrderRepo orderRepo;
	private final UserRepo userRepo;

	@Autowired
	public OrderServiceImpl(AdRepo adRepo, OrderRepo orderRepo, UserRepo userRepo) {
		this.adRepo = adRepo;
		this.orderRepo = orderRepo;
		this.userRepo = userRepo;
	}

	@Override
	@Transactional
	public Long create(Long adId, LoggedUser customer) {
		Long customerId = ofNullable(customer).map(LoggedUser::getId).orElseThrow(() -> new ServerException(SECURITY_ERROR, "Customer can't be defined"));

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setAdId(adId);
		orderEntity.setCreatorId(customerId);
		orderEntity.setStatus(NEW);
		orderEntity.setTotal(findEntity(adId, adRepo).getPrice());

		return orderRepo.save(orderEntity).getId();
	}

	@Override
	@Transactional(readOnly = true)
	public Order findOne(Long orderId) {
		OrderEntity orderEntity = ofNullable(orderRepo.findOne(orderId)).orElseThrow(() -> new ResourceNotFoundException(orderId));
		Ad ad = findEntity(orderEntity.getAdId(), adRepo);

		return builder().orderId(orderEntity.getId())
		                .adId(orderEntity.getAdId())
		                .adTitle(ad.getTitle())
		                .total(orderEntity.getTotal())
		                .groupId(ad.getGroupId())
		                .status(orderEntity.getStatus())
		                .customerName(findEntity(orderEntity.getCreatorId(), userRepo).getName())
		                .build();
	}
}
