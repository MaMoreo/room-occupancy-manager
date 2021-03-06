package com.smarthost.roommanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomManagerTest {

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
	void calculateOccupancyPerfectMatchAll() {

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMorePremiumCustomers() {

		int premium = 2;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(500, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMoreEconomyCustomers() {

		int premium = 3;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(2, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(50, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMorePremiumAndMoreEconomyCustomers() {

		int premium = 2;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(premium, occupancy.getUsagePremium());
		assertEquals(economy, occupancy.getUsageEconomy());
		assertEquals(500, occupancy.getPricePremium());
		assertEquals(50, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMoreRoomrsThanCustomers() {

		int premium = 7;
		int economy = 10;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(600, occupancy.getPricePremium());
		assertEquals(60, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMoveSeveralEconomyToPremium() {

		int premium = 5;
		int economy = 1;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(premium, occupancy.getUsagePremium());
		assertEquals(economy, occupancy.getUsageEconomy());
		assertEquals(600 + 30 + 20, occupancy.getPricePremium());
		assertEquals(10, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMoveSeveralEconomyToPremiumEmptyPremium() {
		customers = Arrays.asList(new Double[] { 400.0, 300.0, 200.0, 100.0, 30.0, 20.0, 10.0 });

		int premium = 10;
		int economy = 1;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
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

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
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

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
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

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(0, occupancy.getUsagePremium());
		assertEquals(0, occupancy.getUsageEconomy());
		assertEquals(0, occupancy.getPricePremium());
		assertEquals(0, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyPerfectMatch() {
		customers = Arrays.asList(new Double[] { 23.0, 45.0, 155.0, 374.0, 22.0, 99.0, 100.0, 101.0, 115.0, 209.0 });

		int premium = 3;
		int economy = 3;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(3, occupancy.getUsagePremium());
		assertEquals(3, occupancy.getUsageEconomy());
		assertEquals(738, occupancy.getPricePremium());
		assertEquals(167, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyPremiumOneEmptyRoom() {
		// customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22, 99, 100, 101,
		// 115, 209} );
		customers = providedCustomers;

		int premium = 7;
		int economy = 5;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(6, occupancy.getUsagePremium());
		assertEquals(4, occupancy.getUsageEconomy());
		assertEquals(1054, occupancy.getPricePremium());
		assertEquals(189, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyEconomyEmpty() {
		// List<Integer> customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22,
		// 99, 100, 101, 115, 209} );
		customers = providedCustomers;

		int premium = 2;
		int economy = 7;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(4, occupancy.getUsageEconomy());
		assertEquals(583, occupancy.getPricePremium());
		assertEquals(189, occupancy.getPriceEconomy());
	}

	@Test
	void calculateOccupancyMoveEconomyToPremium() {
		// List<Integer> customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22,
		// 99, 100, 101, 115, 209} );
		customers = providedCustomers;

		int premium = 7;
		int economy = 1;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
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

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(2, occupancy.getUsageEconomy());
		assertEquals(245, occupancy.getPricePremium());
		assertEquals(30, occupancy.getPriceEconomy());
	}
	
	@Test
	void calculateInvalidPricesNoEconomy() {
	   customers = Arrays.asList(new Double[] { -1.0, 220.0, 100.0});

		int premium = 2;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(0, occupancy.getUsageEconomy());
		assertEquals(320, occupancy.getPricePremium());
		assertEquals(0, occupancy.getPriceEconomy());
	}
	
	@Test
	void calculateInvalidPricesNoPremium() {
	   customers = Arrays.asList(new Double[] { -1.0, 22.0, 10.0});

		int premium = 2;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(0, occupancy.getUsagePremium());
		assertEquals(2, occupancy.getUsageEconomy());
		assertEquals(0, occupancy.getPricePremium());
		assertEquals(32, occupancy.getPriceEconomy());
	}
	
	@Test
	void calculateRepeatedPrices() {
	   customers = Arrays.asList(new Double[] { 22.0, 22.0, 100.0, 100.0 });

		int premium = 2;
		int economy = 2;
		manager.setCustomers(customers);

		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(2, occupancy.getUsagePremium());
		assertEquals(2, occupancy.getUsageEconomy());
		assertEquals(200, occupancy.getPricePremium());
		assertEquals(44, occupancy.getPriceEconomy());
	}

}
