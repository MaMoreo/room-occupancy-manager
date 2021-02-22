package com.smarthost.roommanager.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthost.roommanager.model.Customer;
import com.smarthost.roommanager.model.Occupancy;
import com.smarthost.roommanager.service.RoomManagerService;

@RestController
@RequestMapping("room-manager")
public class RoomManagerController {

	private final RoomManagerService roomManagerService;

	public RoomManagerController(RoomManagerService roomManagerService) {
		super();
		this.roomManagerService = roomManagerService;
	}


	@GetMapping("v1/occupancy")
	public Occupancy getAccomodation(   //
			@RequestParam("premiumRooms") String premiumRooms,  //
			@RequestParam("economyRooms") String economyRooms) {

		return roomManagerService.getOccupancy(premiumRooms, economyRooms, 1);
	}
	
	@GetMapping("v2/occupancy")
	public Occupancy getAccomodationV2(   //
			@RequestParam("premiumRooms") String premiumRooms,  //
			@RequestParam("economyRooms") String economyRooms) {

		return roomManagerService.getOccupancy(premiumRooms, economyRooms, 2);
	}

	@PostMapping("v1/customers")
	public ResponseEntity<Double[]> createCustomers(@RequestBody Double[] customers) {

		return ResponseEntity //
				.ok(roomManagerService.setCustomers(customers));
	}
	
	@PostMapping("v2/customers")
	public ResponseEntity<Customer> createCustomersV2(@Valid @RequestBody Customer customers, BindingResult bindingResult) {

		if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                //log.debug(objectError.toString());
            });

            //return ResponseEntity.badRequest();
         	return null;
        }
		
		return ResponseEntity 
				.ok(customers );  //roomManagerService.setCustomers(customers));
	}

	@GetMapping("v1/customers")
	public ResponseEntity<Double[]> getCustomers() {

		return ResponseEntity //
				.ok(roomManagerService.getCustomers());
	}

	@PutMapping("v1/customers")
	public ResponseEntity<Double[]> addCustomers(@RequestBody Double[] customers) {

		return ResponseEntity //
				.ok(roomManagerService.addCustomers(customers));
	}
}
