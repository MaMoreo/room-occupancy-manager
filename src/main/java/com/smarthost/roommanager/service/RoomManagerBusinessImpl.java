package com.smarthost.roommanager.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smarthost.roommanager.model.Occupancy;
import com.smarthost.roommanager.model.RoomManager;

@Component
public class RoomManagerBusinessImpl implements RoomManagerBusiness {

		
	private final RoomManager roomManager = new RoomManager();
	
	
	@Override
	public Occupancy calculateOccupancy(Integer premiumRooms, Integer economyRooms) {
		return roomManager.calculateOccupancy(premiumRooms,economyRooms);
	}


	@Override
	public List<Double> addCustomers(List<Double> customers) {
		return  roomManager.addCustomers(customers);
	}


	@Override
	public void setCustomers(List<Double> customers) {
		roomManager.setCustomers(customers);
	}


	@Override
	public List<Double> getCustomers() {
		return roomManager.getCustomers();
	}
}
