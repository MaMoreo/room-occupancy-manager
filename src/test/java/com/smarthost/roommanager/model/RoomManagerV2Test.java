package com.smarthost.roommanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomManagerV2Test {

	private RoomManager manager;
	private List<Double> customers;
	private List<Double> providedCustomers;

	@BeforeEach
	void setUp() throws Exception {
		manager = new RoomManager();
		customers = Arrays.asList(new Double[] { 300.0, 200.0, 100.0, 30.0, 20.0, 10.0 });
		providedCustomers = Arrays
				.asList(new Double[] { 23.0, 45.0, 155.0, 374.0, 22.0, 99.0, 100.0, 101.0, 115.0, 209.0 });
	}

	@Test
	void calculateOccupancyV2PerfectMatchAll() {

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2V2MorePremiumCustomers() {

		int premium = 2;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(500, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2V2MoreEconomyCustomers() {

		int premium = 3;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(2, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(50, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2MorePremiumAndMoreEconomyCustomers() {

		int premium = 2;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(premium, occupancy.getUsagePremium());
		assertEquals(economy, occupancy.getUsageEconomy());
		assertEquals(500, occupancy.getPricePremium());
		assertEquals(50, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2MoreRoomrsThanCustomers() {

		int premium = 7;
		int economy = 10;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2MoveSeveralEconomyToPremium() {

		int premium = 5;
		int economy = 1;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(premium, occupancy.getUsagePremium());
		assertEquals(economy, occupancy.getUsageEconomy());
		assertEquals(600 + 30 + 20, occupancy.getPricePremium());
		assertEquals(10, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2MoveSeveralEconomyToPremiumEmptyPremium() {
		customers = Arrays.asList(new Double[] { 400.0, 300.0, 200.0, 100.0, 30.0, 20.0, 10.0 });

		int premium = 10;
		int economy = 1;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(6, occupancy.getUsagePremium());
		assertEquals(economy, occupancy.getUsageEconomy());
		assertEquals(1000 + 30 + 20, occupancy.getPricePremium());
		assertEquals(10, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancNoPremiumCustomers() {
		customers = Arrays.asList(new Double[] { 30.0, 20.0, 10.0 });

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(0, occupancy.getUsagePremium());
		assertEquals(economy, occupancy.getUsageEconomy());
		assertEquals(0, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancNoEconomymCustomers() {
		customers = Arrays.asList(new Double[] { 300.0, 200.0, 100.0 });

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(premium, occupancy.getUsagePremium());
		assertEquals(0, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(0, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancNoCustomers() {
		customers = Arrays.asList(new Double[0]);

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(0, occupancy.getUsagePremium());
		assertEquals(0, occupancy.getUsageEconomy());
		assertEquals(0, occupancy.getPricePremium());
		assertEquals(0, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2PerfectMatch() {
		customers = Arrays.asList(new Double[] { 23.0, 45.0, 155.0, 374.0, 22.0, 99.0, 100.0, 101.0, 115.0, 209.0 });

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(738, occupancy.getPricePremium());
		assertEquals(167, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2PremiumOneEmptyRoom() {
		// customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22, 99, 100, 101,
		// 115, 209} );
		customers = providedCustomers;

		int premium = 7;
		int economy = 5;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(6, occupancy.getUsagePremium());
		assertEquals(4, occupancy.getUsageEconomy());
		assertEquals(1054, occupancy.getPricePremium());
		assertEquals(189, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2EconomyEmpty() {
		// List<Integer> customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22,
		// 99, 100, 101, 115, 209} );
		customers = providedCustomers;

		int premium = 2;
		int economy = 7;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(4, occupancy.getUsageEconomy());
		assertEquals(583, occupancy.getPricePremium());
		assertEquals(189, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyV2MoveEconomyToPremium() {
		// List<Integer> customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22,
		// 99, 100, 101, 115, 209} );
		customers = providedCustomers;

		int premium = 7;
		int economy = 1;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(7, occupancy.getUsagePremium());
		assertEquals(1, occupancy.getUsageEconomy());
		assertEquals(1153, occupancy.getPricePremium());
		assertEquals(45, occupancy.getPriceEconomy());
	}

	@Test
	void calculateInvalidPrices() {
	   customers = Arrays.asList(new Double[] { -1.0, -2.0, 0.0, 15.0, 25.0, 220.0, 15.0, 15.0, -1.0 });

		int premium = 2;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancyV2(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(2, occupancy.getUsageEconomy());
		assertEquals(245, occupancy.getPricePremium());
		assertEquals(30, occupancy.getPriceEconomy());
	}

}
