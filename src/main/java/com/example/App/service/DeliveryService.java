package com.example.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.App.dto.DeliveryResponseDTO;
import com.example.App.dto.dalivery.CustomerForCarrierDTO;
import com.example.App.dto.dalivery.DeliveryForCarrierDTO;
import com.example.App.maper.DeliveryMaper;
import com.example.App.model.AppUser;
import com.example.App.model.Card;
import com.example.App.model.Carrier;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;
import com.example.App.model.Product;
import com.example.App.repo.CardRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.repo.ProductRepo;
import com.example.App.repo.UserRepo;
import com.example.App.security.AuthentService;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class DeliveryService {

    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AuthentService authentService;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private ProductRepo productService;
    @Autowired
    private ProductDeliveryService productDeliveryService;
    @Autowired
    private UserRepo userRepo;
    
    
    

    public Delivery saveDelivery(Delivery delivery) {
	return deliveryRepo.save(delivery);
    }
    
    public double getTotalPrise(List<ProductDelivery> dp) {

	double totalprise = 0;

	for (ProductDelivery product : dp) {

	    totalprise += product.getPrice();
	}

	return totalprise;
    }

    //public void deleteDeliveryByCustomerId(Long id) {
//	deliveryRepo.deleteDeliveryByCustomerId(id);
    //
    //}

    //public List<Delivery> findDeliveryByCustomerId(Long id) {
    //
//	List<Delivery> deliveries = deliveryRepo.findAllDeliveryByCustomerId(id);
    //
//	// return deliveryRepo.findAllById(delivery.stream().map(Delivery ::
//	// getId).collect(Collectors.toList()));
//	return deliveries;
    //}

    public Optional<Delivery> findById(Long id) {

	Optional<Delivery> dleivery = deliveryRepo.findById(id);

	return Optional.ofNullable(dleivery.get());

    }

   public DeliveryResponseDTO addDelivery() {

	//var  customer = userRepo.findByEmail(authentService.findUser()).orElseThrow();
	var  customer = customerRepo.findByEmail(authentService.findUser()).orElseThrow();

	List<Card> productsCard = cardRepo.findByCustomerId(customer.getId()).orElseThrow();

	List<ProductDelivery> dp = new ArrayList<>();

	for(Card p : productsCard ) {
	    
	    Product product = productService.findBySku(p.getSku()).orElseThrow();
	        
		
		Long sku = product.getSku();
		String productName = product.getName();
		double price = product.getPrice();
			
   
		dp.add(new ProductDelivery(sku, productName, price, 1));
	    
	}

	Delivery delivery = saveDelivery(new Delivery(customer));
        
        
	List<ProductDelivery> pList = productDeliveryService.getProduct(dp, delivery);
	productDeliveryService.saveAll(pList);
        
	cardRepo.deleteByCustomerId(customer.getId());
        
	DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO();
	deliveryResponseDTO.setAmount(delivery.getTotalPris());
	deliveryResponseDTO.setOrderId(delivery.getId());
	deliveryResponseDTO.setData(delivery.getCreatedDate());
	deliveryResponseDTO.setProductDeliveryDTOs(DeliveryMaper.toDTO(pList));

	return deliveryResponseDTO;
   }
   
    
    
    public List<DeliveryForCarrierDTO> deliverisForCarriers(){
	
	List<Delivery> deliveris = deliveryRepo.findAllDeliveryForCarriers();
	
	List<DeliveryForCarrierDTO> dtoList  = new ArrayList<>();
	
	
	for(Delivery delivery : deliveris) {
	
	    DeliveryForCarrierDTO dto = new DeliveryForCarrierDTO();
	    
	    dto.setOrderId(delivery.getId());
	    dto.setAmount(delivery.getTotalPris());
	    dto.setData(delivery.getCreatedDate());
	    
	    var customer = delivery.getCustomer();
	    
	    dto.setCustomer(new CustomerForCarrierDTO(
		    customer.getFirstName(),
		    customer.getLastName(),
		    customer.getPhone(),
		    customer.getAddress(),
		    customer.getCity(),
		    customer.getZipCode(),
		    customer.getEmail()
		    ));
	    
	    
	    dtoList.add(dto);
	    
	    
	    }
	

	return dtoList;
	
    }
}
