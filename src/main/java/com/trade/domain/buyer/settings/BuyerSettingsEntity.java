package com.trade.domain.buyer.settings;

import com.trade.common.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "buyer_settings")
public class BuyerSettingsEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "buyer_id")
	private Long buyerId;
	@Column(name = "exchange_rate")
	private BigDecimal exchangeRate;
	@Enumerated(value = STRING)
	@Column(name = "currency")
	private Currency currency;
	private double percent;
}
