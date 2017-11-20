package com.trade.domain.order;

import com.trade.web.user.LoggedUser;

public interface OrderService {

	Long create(Long adId, LoggedUser customer);

	Order findOne(Long orderId);
}
