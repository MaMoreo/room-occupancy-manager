package com.smarthost.roommanager.service;

import com.smarthost.roommanager.model.RoomManager.Occupancy;

public interface RoomManagerService {

	Occupancy getOccupancy(String premiumRooms, String economyRooms);

	Integer[] setCustomers(Integer[] customers);

	Integer[] getCustomers();

}
