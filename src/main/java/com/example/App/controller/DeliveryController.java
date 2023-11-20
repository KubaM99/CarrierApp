package com.example.App.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.dto.DeliveryResponseDTO;
import com.example.App.dto.OrderDTO;
import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.dto.dalivery.DeliveryForCarrierDTO;
import com.example.App.maper.DeliveryMaper;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;
import com.example.App.model.Product;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.DeliveryRepo;
import com.example.App.service.CustomerService;
import com.example.App.service.DeliveryService;
import com.example.App.service.ProductDeliveryService;
import com.example.App.service.ProductService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private DeliveryRepo deliveryRepo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDeliveryService productDeliveryService;
	
	
	
	
	
	
	
	@GetMapping("/x")
	public ResponseEntity<List<Delivery>> getAllDelivery(){
		List<Delivery> deliveris = deliveryRepo.findAll();
		return ResponseEntity.ok(deliveris);
			
	}
	
	
	

	@PostMapping("/add")
	public ResponseEntity<DeliveryResponseDTO> pleaseDelivery(@RequestBody OrderDTO orderDTO) {

	    // Customer customerDTOCusto= new
	    // Customer(orderDTO.getCustomerName(),orderDTO.getCustomerEmail());

	    // customer.setId(1L);

	    // Optional<Customer> customerOptional =
	    // customerService.isCustomerPresent(customerDTO);
	    Customer customer = new Customer();

	    Optional<Customer> customerOptional = customerService.findByEmail(orderDTO.getCustomerEmail());

	    if (customerOptional.isPresent()) {

		customer = customerOptional.get();

	    } else {
		customer = customerService
			.saveCustomer(new Customer(orderDTO.getCustomerName(), orderDTO.getCustomerEmail()));

	    }

	    // Customer customer = customerOptional.orElse(customerService.saveCustomer(new
	    // Customer(orderDTO.getCustomerName(),orderDTO.getCustomerEmail())));

	    //Delivery delivery = deliveryService.saveDelivery(new Delivery(customer));

	    //List<ProductDelivery> pList = productDeliveryService.getProduct(orderDTO.getProductDeliveris(), delivery);
	    //productDeliveryService.saveAll(pList);
            //
	    //DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO();
	    //deliveryResponseDTO.setAmount(delivery.getTotalPris());
	    //deliveryResponseDTO.setOrderId(delivery.getId());
	    //deliveryResponseDTO.setData(delivery.getCreatedDate());
	    //deliveryResponseDTO.setProductDeliveryDTOs(deliveryMaper.toDTO(pList));

	    return ResponseEntity.ok(new DeliveryResponseDTO());

	}
	
	@PostMapping("/addProductToCard")
	public ResponseEntity<List<ProductDelivery>> pleaseDelivery(@RequestBody AddProdacutToCardDTO dto) {
	    
	  
	    
	    return ResponseEntity.ok(productDeliveryService.addProductToCart(dto));
	    
	   
	}
	
	@GetMapping("/addDelivery")
	public ResponseEntity<DeliveryResponseDTO> addDeliver() {
	    
	    return ResponseEntity.ok(deliveryService.addDelivery());
	}
	
	@GetMapping("/forCarriers")
	public ResponseEntity<List<DeliveryForCarrierDTO>> deliveryForCarriers() {
	    
	    List<DeliveryForCarrierDTO> deliveris =  deliveryService.deliverisForCarriers();
	    
	    return ResponseEntity.ok(deliveris);
	}
	
	
	
	
	
}
