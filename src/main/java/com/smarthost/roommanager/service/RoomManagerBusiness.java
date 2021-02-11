package com.smarthost.roommanager.service;

import java.util.List;

import com.smarthost.roommanager.model.Occupancy;

public interface RoomManagerBusiness {

	Occupancy calculateOccupancy(Integer premiumRooms, Integer economyRooms);

	List<Double> addCustomers(List<Double> customers);

	void setCustomers(List<Double> customers);

	List<Double> getCustomers();

}
