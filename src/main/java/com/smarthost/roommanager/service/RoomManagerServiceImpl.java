package com.smarthost.roommanager.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.smarthost.roommanager.model.RoomManager;
import com.smarthost.roommanager.model.RoomManager.Occupancy;

@Service
public class RoomManagerServiceImpl implements RoomManagerService{

	private final RoomManager roomManager;
	
	
	public RoomManagerServiceImpl(RoomManager roomManager) {
		super();
		this.roomManager = roomManager;
	}

	@Override
	public Occupancy getOccupancy(String premiumRooms, String economyRooms) {
		
		return roomManager.calculateOccupancy(Integer.valueOf(premiumRooms), Integer.valueOf(economyRooms));
	}

	@Override
	public Integer[] setCustomers(Integer[] customers) {
		roomManager.setCustomers(Arrays.asList(customers));
		return customers;
	}

	@Override
	public Integer[] getCustomers() {
		
		return roomManager.getCustomers().toArray(new Integer[0]);
	}
}
