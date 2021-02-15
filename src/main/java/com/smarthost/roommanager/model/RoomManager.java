package com.smarthost.roommanager.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class RoomManager {

	private List<Double> customers;
	private int premiumRooms;
	private int economyRooms;
	private Occupancy occupancy;
	private List<Double> allCustomers;
	private List<Double> bookedPremium;
	private List<Double> bookedEconomy;

	/**
	 * Version 1: Calculate Occupancy
	 * 
	 * @param premiumRooms Amount of available premium Rooms
	 * @param economyRooms Amount of available economy Rooms
	 * @return The Occupancy statistics
	 */
	public Occupancy calculateOccupancy(int premiumRooms, int economyRooms) {
		
		if (customers == null || customers.isEmpty()) {
			return new Occupancy(0,0, 0, 0);
		}

		occupancyCalculator(premiumRooms, economyRooms);
		return fillOccupancyData();
	}

	
	/**
	 * Version 2: Calculate Occupancy
	 * 
	 * @param premiumRooms Amount of available premium Rooms
	 * @param economyRooms Amount of available economy Rooms
	 * @return The Occupancy statistics
	 * 
	 * */
	public Occupancy calculateOccupancyV2(int premiumRooms, int economyRooms) {

		this.premiumRooms = premiumRooms;
		this.economyRooms = economyRooms;
		
		if (customers == null || customers.isEmpty()) {
			return new Occupancy(0,0, 0, 0);
		}

		List<Double> bookedPremiumCustomers = customers.stream()//
				.filter(p -> p >= 100) //
				.sorted(Comparator.reverseOrder()) //
				.limit(premiumRooms) //
				.collect(Collectors.toList());

		List<Double> allEconomyCustomers = customers.stream() //
				.filter(p -> p < 100) //
				.filter(p -> p > 0)
				.collect(Collectors.toList());

		List<Double> bookedEconomyCustomersInPremium = calculateOverbookingEconomyRoooms( //
				bookedPremiumCustomers.size(),//
				allEconomyCustomers.size());

		List<Double> bookedEconomyCustomers = customers.stream() //
				.filter(p -> p < 100) //
				.filter(p -> p > 0)
				.filter(x -> !bookedEconomyCustomersInPremium.contains(x))//
				.sorted(Comparator.reverseOrder())//
				.limit(economyRooms)//
				.collect(Collectors.toList());

		return calculateOccupancy(bookedPremiumCustomers, bookedEconomyCustomersInPremium, bookedEconomyCustomers);
	}

	private List<Double> calculateOverbookingEconomyRoooms(int bookedPremiumCustomers, int allEconomyCustomers) {

		if (premiumRooms > bookedPremiumCustomers && allEconomyCustomers > economyRooms) {

			int restOfPremiumRooms = premiumRooms - bookedPremiumCustomers;
			int overbookedEconomyUsers = allEconomyCustomers - economyRooms;
			int min = restOfPremiumRooms < overbookedEconomyUsers ? restOfPremiumRooms : overbookedEconomyUsers;

			return customers.stream().filter(p -> p < 100) //
					.sorted(Comparator.reverseOrder())//
					.limit(min)//
					.collect(Collectors.toList());
		} else
			return new ArrayList<>();
	}
	
	private Occupancy calculateOccupancy(List<Double> bookedPremiumCustomers,
			List<Double> bookedEconomyCustomersInPremium, List<Double> bookedEconomyCustomers) {

		int premiumOccupancy = bookedPremiumCustomers.size() + bookedEconomyCustomersInPremium.size();
		double pricePremium = bookedPremiumCustomers.stream() //
				.mapToDouble(Double::valueOf) //
				.sum() //
				+ //
				bookedEconomyCustomersInPremium.stream() //
					.mapToDouble(Double::valueOf) //
						.sum();


		double priceEconomy = bookedEconomyCustomers.stream() //
				.mapToDouble(Double::valueOf) //
				.sum();
		
		return new Occupancy(premiumOccupancy, bookedEconomyCustomers.size(), pricePremium, priceEconomy);
	}
	
	
	private void occupancyCalculator(int premiumRooms, int economyRooms) {
		
		this.premiumRooms = premiumRooms;
		this.economyRooms = economyRooms;

		allCustomers = customers.stream()//
				.sorted(Comparator.reverseOrder()) //
				.collect(Collectors.toList());

		bookedPremium = new ArrayList<>();
		bookedEconomy = new ArrayList<>();

		int counter = 0;
		for (Double customer : allCustomers) {

			if (!bookPremium(customer) && !bookEconomy(customer, counter)) {
				break;
			}
			counter++;
		}
	}

	private Occupancy fillOccupancyData() {
		return new Occupancy(bookedPremium.size(), //
				bookedEconomy.size(), //
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

	public List<Double> getCustomers() {
		if (customers == null) {
			customers = new ArrayList<>();
		}

		return customers;
	}
}
