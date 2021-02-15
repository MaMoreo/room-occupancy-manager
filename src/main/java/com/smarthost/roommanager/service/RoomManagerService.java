package com.smarthost.roommanager.service;

import com.smarthost.roommanager.model.Occupancy;

public interface RoomManagerService {

	Occupancy getOccupancy(String premiumRooms, String economyRooms, int version);

	Double[] setCustomers(Double[] customers);

	Double[] getCustomers();

	Double[] addCustomers(Double[] customers);

}
