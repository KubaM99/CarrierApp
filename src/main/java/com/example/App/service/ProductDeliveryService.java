package com.example.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;
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

	public List<ProductDelivery> getAllDeliveryProducts() {
		return productDeliveryRepo.findAll();
	}

	public List<ProductDelivery> saveAll(List<ProductDelivery> dp) {
		return productDeliveryRepo.saveAll(dp);
	}

	public List<ProductDelivery> getProduct(List<ProductDelivery> dleiveruProductList, Delivery dleivery) {
        
		List<ProductDelivery> dp = new ArrayList<>();
        
		for (ProductDelivery x : dleiveruProductList) {
        
			Optional<Product> product = productRepo.findBySku(x.getSku());
        
			if (product.isPresent()) {
				Long sku = product.get().getSku();
				String productName = product.get().getName();
				double price = product.get().getPrice();
				
        
				dp.add(new ProductDelivery(sku, productName, price, 1, dleivery));
			}
			
			dleivery.setTotalPris(getTotalprice(dp));
        
		}
		return dp;
        
	}
	
	public double getTotalprice(List<ProductDelivery> dp) {
		
		double sum = 0;
		for(ProductDelivery x : dp) {
			
			sum += x.getPrice();
		}
		
		return sum;
	}
	
	//public void deleteProductDeliveryByDeliveryId(Long id) {
	//	productDeliveryRepo.deleteProductDeliveryByDeliveryId(id);
	//}
	
	
	public List<ProductDelivery> addProductToCart(AddProdacutToCardDTO productName) {

		List<ProductDelivery> dp = new ArrayList<>();

		

			Product product = (productRepo.findBySku(productName.getSku())).get();
			
				//.orElseThrow(()->new NoSuchElementException("Produst dont exsist")));

			//if (product.isPresent()) {
				Long sku = product.getSku();
				String name = product.getName();
				double price = product.getPrice();
				

				dp.add(new ProductDelivery(sku, name, price, 1));
			//}
			
			//dleivery.setTotalPris(getTotalprice(dp));
			

		
		return dp;

	}
	
	
	
	
}
