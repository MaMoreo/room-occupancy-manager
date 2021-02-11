package com.smarthost.roommanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


	@GetMapping("/occupancy")
	public RoomManager.Occupancy getAccomodation(   //
			@RequestParam("premiumRooms") String premiumRooms,  //
			@RequestParam("economyRooms") String economyRooms) {

		return roomManagerService.getOccupancy(premiumRooms, economyRooms);
	}

	@PostMapping("/customers")
	public ResponseEntity<Double[]> createCustomers(@RequestBody Double[] customers) {

		return ResponseEntity //
				.ok(roomManagerService.setCustomers(customers));
	}

	@GetMapping("/customers")
	public ResponseEntity<Double[]> getCustomers() {

		return ResponseEntity //
				.ok(roomManagerService.getCustomers());
	}

	@PutMapping("/customers")
	public ResponseEntity<Double[]> addCustomers(@RequestBody Double[] customers) {

		return ResponseEntity //
				.ok(roomManagerService.addCustomers(customers));
	}
}
