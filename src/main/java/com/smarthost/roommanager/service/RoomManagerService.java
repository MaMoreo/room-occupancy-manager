package com.smarthost.roommanager.service;

import com.smarthost.roommanager.model.RoomManager.Occupancy;

public interface RoomManagerService {

	Occupancy getOccupancy(String premiumRooms, String economyRooms);

	Double[] setCustomers(Double[] customers);

	Double[] getCustomers();

	Double[] addCustomers(Double[] customers);

}
