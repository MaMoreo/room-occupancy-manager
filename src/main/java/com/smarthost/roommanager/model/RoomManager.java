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

	private List<Integer> customers;
	private int premiumRooms;
	private int economyRooms;
	private Occupancy occupancy;

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
		private int pricePremium;
		private int priceEconomy;
	}

	public Occupancy calculateOccupancy(int premiumRooms, int economyRooms) {
		
		List<Integer> allCustomers = customers.stream()//
				.sorted(Comparator.reverseOrder()) //
				.collect(Collectors.toList());
		
		List<Integer> bookedPremium = new ArrayList<>();
		List<Integer> bookedEconomy = new ArrayList<>();
		
		int counter = 0;
		for (Integer customer : allCustomers) {
			if (customer >= 100) {
				if (bookedPremium.size() < premiumRooms) {
					bookedPremium.add(customer);
					counter ++;
				}
				continue;
			}
			
			if (bookedPremium.size() < premiumRooms && (allCustomers.size() - counter) > economyRooms){
				bookedPremium.add(customer);
				counter ++;
				continue;
			}
			
			if (bookedEconomy.size() < economyRooms) {
				bookedEconomy.add(customer);
				counter ++;
				continue;
			} 
				break;
		}		
		return new Occupancy(bookedPremium.size(),
						bookedEconomy.size(), 
							bookedPremium.stream() //
								.mapToInt(Integer::valueOf)//
								.sum(),
						bookedEconomy.stream() //
								.mapToInt(Integer::valueOf)//
								.sum());
	}
	
	public Occupancy calculateOccupancyOld(int premiumRooms, int economyRooms) {

		this.premiumRooms = premiumRooms;
		this.economyRooms = economyRooms;

		List<Integer> bookedPremiumCustomers = customers.stream()//
				.filter(p -> p >= 100) //
				.sorted(Comparator.reverseOrder()) //
				.limit(premiumRooms) //
				.collect(Collectors.toList());

		List<Integer> allEconomyCustomers = customers.stream() //
				.filter(p -> p < 100) //
				.filter(p -> p > 0)
				.collect(Collectors.toList());

		List<Integer> bookedEconomyCustomersInPremium = calculateOverbookingEconomyRoooms( //
				bookedPremiumCustomers.size(),//
				allEconomyCustomers.size());

		List<Integer> bookedEconomyCustomers = customers.stream() //
				.filter(p -> p < 100) //
				.filter(p -> p > 0)
				.filter(x -> !bookedEconomyCustomersInPremium.contains(x))//
				.sorted(Comparator.reverseOrder())//
				.limit(economyRooms)//
				.collect(Collectors.toList());

		return calculateOccupancy(bookedPremiumCustomers, bookedEconomyCustomersInPremium, bookedEconomyCustomers);
	}

	private Occupancy calculateOccupancy(List<Integer> bookedPremiumCustomers,
			List<Integer> bookedEconomyCustomersInPremium, List<Integer> bookedEconomyCustomers) {

		int premiumOccupancy = bookedPremiumCustomers.size() + bookedEconomyCustomersInPremium.size();
		int pricePremium = bookedPremiumCustomers.stream() //
				.mapToInt(Integer::valueOf) //
				.sum() //
				+ //
				bookedEconomyCustomersInPremium.stream() //
						.mapToInt(Integer::valueOf) //
						.sum();


		int priceEconomy = bookedEconomyCustomers.stream() //
				.mapToInt(Integer::valueOf)//
				.sum();
		
		return new Occupancy(premiumOccupancy, bookedEconomyCustomers.size(), pricePremium, priceEconomy);
	}

	private List<Integer> calculateOverbookingEconomyRoooms(int bookedPremiumCustomers, int allEconomyCustomers) {

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

	public List<Integer> addCustomers(List<Integer> customersToAdd) {

		this.customers.addAll(customersToAdd);
		return customers;
	}
}
