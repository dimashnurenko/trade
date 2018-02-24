package com.trade.web.order.recipient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientDto {
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String phone;
	private String email;
	private String country;
	private String zip;
}
