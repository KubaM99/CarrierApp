package com.example.App.maper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import org.hibernate.type.descriptor.java.VersionJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;
import com.example.App.model.Product;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.ProductRepo;
import com.example.App.service.ProductDeliveryService;
import com.example.App.service.ProductService;

import lombok.experimental.var;

@Component
public class DeliveryMaper {

	// nie dział optional, 500 Internal Server Error
	public static List<ProductDeliveryDTO> toDTO(List<ProductDelivery> productDelivery) {

		List<ProductDeliveryDTO> dto = new ArrayList<>();

		for (ProductDelivery x : productDelivery) {

			dto.add(new ProductDeliveryDTO(x.getSku(), x.getProductName(), x.getPrice()));

		}

		return dto;

	}

}
