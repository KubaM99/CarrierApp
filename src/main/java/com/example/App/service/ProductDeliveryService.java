package com.example.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDeliveris;
import com.example.App.model.Product;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.repo.ProductRepo;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class ProductDeliveryService {

	@Autowired
	private ProductDeliveryRepo productDeliveryRepo;

	@Autowired
	private ProductRepo productRepo;

	public List<ProductDeliveris> getAllDeliveryProducts() {
		return productDeliveryRepo.findAll();
	}

	public List<ProductDeliveris> saveAll(List<ProductDeliveris> dp) {
		return productDeliveryRepo.saveAll(dp);
	}

	public List<ProductDeliveris> getProduct(List<ProductDeliveris> dleiveruProductList, Delivery dleivery) {
        
		List<ProductDeliveris> dp = new ArrayList<>();
        
		for (ProductDeliveris x : dleiveruProductList) {
        
			Optional<Product> product = productRepo.findBySku(x.getSku());
        
			if (product.isPresent()) {
				Long sku = product.get().getSku();
				String productName = product.get().getName();
				double price = product.get().getPrice();
				
        
				dp.add(new ProductDeliveris(sku, productName, price, 1, dleivery));
			}
			
			dleivery.setTotalPris(getTotalprice(dp));
        
		}
		return dp;
        
	}
	
	public double getTotalprice(List<ProductDeliveris> dp) {
		
		double sum = 0;
		for(ProductDeliveris x : dp) {
			
			sum += x.getPrice();
		}
		
		return sum;
	}
	
	//public void deleteProductDeliveryByDeliveryId(Long id) {
	//	productDeliveryRepo.deleteProductDeliveryByDeliveryId(id);
	//}
	
	
	public List<ProductDeliveris> addProductToCart(AddProdacutToCardDTO productName) {

		List<ProductDeliveris> dp = new ArrayList<>();

		

			Product product = (productRepo.findBySku(productName.getSku())).get();
			
				//.orElseThrow(()->new NoSuchElementException("Produst dont exsist")));

			//if (product.isPresent()) {
				Long sku = product.getSku();
				String name = product.getName();
				double price = product.getPrice();
				

				dp.add(new ProductDeliveris(sku, name, price, 1));
			//}
			
			//dleivery.setTotalPris(getTotalprice(dp));
			

		
		return dp;

	}
	
	public List<ProductDeliveryDTO> getProduct(Delivery dleivery) {
	        
	    List<ProductDeliveris> deliveries = productDeliveryRepo.
		    		findProductDeliveryByDeliveryId(dleivery.getId()).orElseThrow();
	    
	    List<ProductDeliveryDTO> dp = new ArrayList<>();
    
		for (ProductDeliveris x : deliveries) {
    
			Optional<Product> product = productRepo.findBySku(x.getSku());
    
			if (product.isPresent()) {
				Long sku = product.get().getSku();
				String productName = product.get().getName();
				double price = product.get().getPrice();
				
    
				dp.add(new ProductDeliveryDTO(sku, productName, price));
			}
			
    
		}
		return dp;
    
	}
	
	
	
	
}
