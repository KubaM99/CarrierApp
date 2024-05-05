package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.App.dto.ProducstToPickUpByCarreir;
import com.example.App.model.Carrier;

import com.example.App.service.CarrierService;


@RestController
@RequestMapping("/carrier")
public class CarrierController {

    
    @Autowired
    private CarrierService carrierService;

   

    @GetMapping("/getAllCarrier")
    public ResponseEntity<List<Carrier>> getAllCarrier() {

	List<Carrier> carriers = carrierService.findAllCarrier();

	return ResponseEntity.ok(carriers);

    }

    @GetMapping("/bookingDelivery/{id}")
    public ResponseEntity<String> bookingDelivery(@PathVariable Long id) {

	String booking = carrierService.bookingDelivery(id);

	return ResponseEntity.ok(booking);
    }

    @GetMapping("/delivered/{id}")
    public ResponseEntity<String> delivered(@PathVariable Long id) {

	Long delivery = carrierService.delivered(id);

	return ResponseEntity.ok("Order id:" + delivery + "delivered");
    }

    @GetMapping("/listOfProdustsForCarreir")
    public ResponseEntity<List<ProducstToPickUpByCarreir>> listOfProdustsForCarreir() {

	List<ProducstToPickUpByCarreir> delivery = carrierService.listOfProdustsForCarreir();

	return ResponseEntity.ok(delivery);
    }

}
