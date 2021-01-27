package com.smarthost.roommanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smarthost.roommanager.model.RoomManager.Occupancy;

class RoomManagerTest {

	private RoomManager manager;

	@BeforeEach
	void setUp() throws Exception {
		manager = new RoomManager();
	}

	@Test
	void calculateOccupancyPerfectMatch() {
		List<Integer> customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22, 99, 100, 101, 115, 209} );
		
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
	void calculateOccupancyPremiumEmpty() {
		List<Integer> customers = Arrays.asList( new Integer[] {23, 45, 155, 374, 22, 99, 100, 101, 115, 209} );
		
		int premium = 7;
		int economy = 5;
		manager.setCustomers(customers);
		
		Occupancy occupancy = manager.calculateOccupancy(premium, economy);
		assertEquals(6, occupancy.getUsagePremium());
		assertEquals(4, occupancy.getUsageEconomy());
		assertEquals(1054, occupancy.getPricePremium());
		assertEquals(189, occupancy.getPriceEconomy());
	}
	

}
