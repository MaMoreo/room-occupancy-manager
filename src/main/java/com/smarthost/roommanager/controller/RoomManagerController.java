package com.smarthost.roommanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smarthost.roommanager.model.RoomManager;
import com.smarthost.roommanager.service.RoomManagerService;


@RestController
public class RoomManagerController {
	
	private final RoomManagerService roomManagerService;
	
	
	public RoomManagerController(RoomManagerService roomManagerService) {
		super();
		this.roomManagerService = roomManagerService;
	}

	@GetMapping("/occupancy/{premiumRooms}/{economyRooms}")
	public RoomManager.Occupancy getStatisticsForInstrument(@PathVariable("premiumRooms") String premiumRooms,
			@PathVariable("economyRooms") String economyRooms) {  
		
		return roomManagerService.getOccupancy(premiumRooms, economyRooms);
		
	}
}
