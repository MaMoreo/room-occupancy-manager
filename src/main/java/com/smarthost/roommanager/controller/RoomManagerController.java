package com.smarthost.roommanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthost.roommanager.model.RoomManager;
import com.smarthost.roommanager.service.RoomManagerService;


@RestController
@RequestMapping("room-manager")
public class RoomManagerController {
	
	private final RoomManagerService roomManagerService;
	
	
	public RoomManagerController(RoomManagerService roomManagerService) {
		super();
		this.roomManagerService = roomManagerService;
	}

	@GetMapping("/occupancy/{premiumRooms}/{economyRooms}")
	public RoomManager.Occupancy getAccomodation(@PathVariable("premiumRooms") String premiumRooms,
			@PathVariable("economyRooms") String economyRooms) {  
		
		return roomManagerService.getOccupancy(premiumRooms, economyRooms);
		
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Integer[]> createCustomers(@RequestBody Integer[] customers ) {  
		
		  return ResponseEntity //
			   .ok(roomManagerService.setCustomers(customers));
	}
	
	@GetMapping("/customers")
	public ResponseEntity<Integer[]> getCustomers( ) {  
		
		  return ResponseEntity //
			   .ok(roomManagerService.getCustomers());
	}
}
