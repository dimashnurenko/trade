package com.trade.domain.buyer.settings;

import com.trade.common.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "buyer_settings")
public class BuyerSettingsEntity {
	private Long id;
	private Long buyerId;
	private BigDecimal exchangeRate;
	private Currency currency;
	private double percent;
}
