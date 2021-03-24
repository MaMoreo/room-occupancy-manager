package com.smarthost.roommanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smarthost.roommanager.model.Occupancy;
import com.smarthost.roommanager.model.RoomManager;

@Service
public class RoomManagerServiceImpl implements RoomManagerService {

	private final RoomManager roomManager;
	
	public RoomManagerServiceImpl(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	@Override
	public Occupancy getOccupancy(String premiumRooms, String economyRooms, int version) {
		if (version == 1)
			return roomManager.calculateOccupancy(Integer.valueOf(premiumRooms), Integer.valueOf(economyRooms));
		else
			return roomManager.calculateOccupancyV2(Integer.valueOf(premiumRooms), Integer.valueOf(economyRooms));
	}

	@Override
	public Double[] setCustomers(Double[] customers) {
		roomManager.setCustomers(new ArrayList<>(Arrays.asList(customers)));
		return customers;
	}

	@Override
	public Double[] getCustomers() {
		return roomManager.getCustomers().toArray(new Double[0]);
	}

	@Override
	public Double[] addCustomers(Double[] customers) {
		List<Double> allCustomers = roomManager.addCustomers(Arrays.asList(customers));
		return allCustomers.toArray(new Double[0]);
	}
}
