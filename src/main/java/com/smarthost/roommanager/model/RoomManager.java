package com.smarthost.roommanager.model;

import java.util.Arrays;
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
	
	public RoomManager() {
		super();
		customers = Arrays.asList( new Integer[0]  );
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

		int premiumOccupancy;
		int pricePremium;
		
		List<Integer> bookedPremiumCustomers = customers.stream()
				.filter(p -> p >= 100) //
				.sorted(Comparator.reverseOrder()) //
				.limit(premiumRooms) //
				.collect(Collectors.toList());

		List<Integer> allEconomyCustomers = customers.stream() //
				.filter(p -> p < 100) //
				.collect(Collectors.toList());

		List<Integer> bookedEconomyCustomers;
		if (premiumRooms > bookedPremiumCustomers.size() && allEconomyCustomers.size() > economyRooms) {

			int restOfPremiumRooms = premiumRooms - bookedPremiumCustomers.size();
			int overbookedEconomyUsers = allEconomyCustomers.size() - economyRooms;

			int min = restOfPremiumRooms < overbookedEconomyUsers ? restOfPremiumRooms : overbookedEconomyUsers;

			List<Integer> bookedEconomyCustomersInPremium = customers.stream()
					.filter(p -> p < 100) //
					.sorted(Comparator.reverseOrder())//
					.limit(min)//
					.collect(Collectors.toList());

			bookedEconomyCustomers = customers.stream() //
					.filter(p -> p < 100)//
					.filter(x -> !bookedEconomyCustomersInPremium.contains(x))//
					.sorted(Comparator.reverseOrder())//
 					.limit(economyRooms) //
					.collect(Collectors.toList());

			premiumOccupancy = bookedPremiumCustomers.size() + bookedEconomyCustomersInPremium.size();
			pricePremium = bookedPremiumCustomers.stream() //
					.mapToInt(Integer::valueOf) //
					.sum() // 
					+ //
					 bookedEconomyCustomersInPremium.stream() //
					.mapToInt(Integer::valueOf) //
					.sum();
			
			
		} else {

			bookedEconomyCustomers = customers.stream() //
					.filter(p -> p < 100) //
					.sorted(Comparator.reverseOrder())//
					.limit(economyRooms)//
					.collect(Collectors.toList());

			
			premiumOccupancy = bookedPremiumCustomers.size();
			pricePremium = bookedPremiumCustomers.stream().mapToInt(Integer::valueOf).sum();
			
		}

		int priceEconomy = bookedEconomyCustomers.stream().mapToInt(Integer::valueOf).sum();

		Occupancy occupancy = new Occupancy();
		occupancy.setUsagePremium(premiumOccupancy);
		occupancy.setUsageEconomy(bookedEconomyCustomers.size());
		occupancy.setPricePremium(pricePremium);
		occupancy.setPriceEconomy(priceEconomy);

		return occupancy;
	}
}
