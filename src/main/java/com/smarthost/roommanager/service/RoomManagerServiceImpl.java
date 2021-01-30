package com.smarthost.roommanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<Integer> supportedTypes = new ArrayList<Integer>(Arrays.asList(customers));
		roomManager.setCustomers(supportedTypes);
		return customers;
	}

	@Override
	public Integer[] getCustomers() {
		
		return roomManager.getCustomers().toArray(new Integer[0]);
	}

	@Override
	public Integer[] addCustomers(Integer[] customers) {
		List<Integer> allCustomers =  roomManager.addCustomers(Arrays.asList(customers));
		return allCustomers.toArray(new Integer[0]);
	}
}
