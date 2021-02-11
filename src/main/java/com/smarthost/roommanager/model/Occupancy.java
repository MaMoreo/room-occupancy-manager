package com.smarthost.roommanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Occupancy {

	private int usagePremium;
	private int usageEconomy;
	private double pricePremium;
	private double priceEconomy;
}
