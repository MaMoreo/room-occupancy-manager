package com.smarthost.roommanager.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RoomManager {

	private List<Integer> customers;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	 class Occupancy {
		
		private int usagePremium;
		private int usageEconomy;
		private int pricePremium;
		private int priceEconomy;
	}
	
	
	
	
	public Occupancy calculateOccupancy(int premium, int economy) {
		
		List<Integer> usePremium = customers
				.stream()
				.filter(p -> p >= 100)
				.sorted(Comparator.reverseOrder())
				.limit(premium)
				.collect(Collectors.toList());
		
		
		List<Integer> useEconomy = customers
				.stream()
				.filter(p -> p < 100)
				.sorted(Comparator.reverseOrder())
				.limit(economy)
				.collect(Collectors.toList());
		
		/*IntStream
			    .iterate( premium - 1,  n -> n - 1)
			    .takeWhile(n -> n > 0)
			    .mapToObj(pene::get)
			    .forEach(result::add);
			*/    
		
		if (usePremium.size() < premium && (useEconomy.size() + usePremium.size() < customers.size())) {
			// premium free
			int premiumFree = premium - usePremium.size();
			// rest of cheap
			 List<Integer> restOfCheap = customers
				.stream()
				.filter(p -> p < 100)
				.sorted(Comparator.reverseOrder())
				//.limit(economy)
				.collect(Collectors.toList());
			
			restOfCheap.removeIf(x -> useEconomy.contains(x));
			
		}
		
		int pricePremium = usePremium.stream().mapToInt(Integer::valueOf).sum();
		int priceEconomy = useEconomy.stream().mapToInt(Integer::valueOf).sum();
		
		Occupancy occupancy = new Occupancy();
		occupancy.setUsagePremium(usePremium.size());
		occupancy.setUsageEconomy(useEconomy.size());
		occupancy.setPricePremium(pricePremium);
		occupancy.setPriceEconomy(priceEconomy);
		
		return occupancy;
	}
	
	public void addCustomers(List<Integer> customers) {
		this.customers = customers;
	}
}
