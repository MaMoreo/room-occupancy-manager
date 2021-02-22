package com.smarthost.roommanager.model;

import org.hibernate.validator.constraints.Currency;
import org.javamoney.moneta.FastMoney;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smarthost.roommanager.config.CustomerDeserializer;
import com.smarthost.roommanager.config.CustomerSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = CustomerSerializer.class)
@JsonDeserialize(using = CustomerDeserializer.class)
public class Customer {

	@Currency("EUR")
	private FastMoney amount;
	
	/*public Customer(Double amountD ) {
		CurrencyUnit usd = Monetary.getCurrency("EUR");
		amount = Monetary.getDefaultAmountFactory()
	      .setCurrency(usd).setNumber(amountD).create();
	}*/

}
