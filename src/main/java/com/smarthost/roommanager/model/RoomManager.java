package com.smarthost.roommanager.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
public class RoomManager {

	private List<Double> customers;
	private int premiumRooms;
	private int economyRooms;
	private Occupancy occupancy;
	private List<Double> allCustomers;
	private List<Double> bookedPremium;
	private List<Double> bookedEconomy;

	public RoomManager() {
		super();
		customers = new ArrayList<>();

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class Occupancy {

		private int usagePremium;
		private int usageEconomy;
		private double pricePremium;
		private double priceEconomy;
	}

	public Occupancy calculateOccupancy(int premiumRooms, int economyRooms) {

		this.premiumRooms = premiumRooms;
		this.economyRooms = economyRooms;

		allCustomers = customers.stream()//
				.sorted(Comparator.reverseOrder()) //
				.collect(Collectors.toList());

		bookedPremium = new ArrayList<>();
		bookedEconomy = new ArrayList<>();

		int counter = 0;
		for (Double customer : allCustomers) {

			if ( !bookPremium(customer) && !bookEconomy(customer, counter)) {
					break;
			}
			counter++;
		}

		return new Occupancy(bookedPremium.size(), //
				bookedEconomy.size(),  //
				bookedPremium.stream() //
					.mapToDouble(Double::valueOf)//
					.sum(),
				bookedEconomy.stream() //
					.mapToDouble(Double::valueOf)//
					.sum());

	}

	private boolean bookPremium(double customer) {
		if (customer >= 100) {
			if (bookedPremium.size() < premiumRooms) {
				bookedPremium.add(customer);
			}
			return true;
		}
		return false;
	}

	private boolean bookEconomy(double customer, int counter) {

		if (bookedPremium.size() < premiumRooms && (allCustomers.size() - counter) > economyRooms) {
			bookedPremium.add(customer);
			return true;
		}

		if (bookedEconomy.size() < economyRooms) {
			bookedEconomy.add(customer);
			return true;
		}

		return false;
	}

	public List<Double> addCustomers(List<Double> customersToAdd) {

		this.customers.addAll(customersToAdd);
		return customers;
	}
}
