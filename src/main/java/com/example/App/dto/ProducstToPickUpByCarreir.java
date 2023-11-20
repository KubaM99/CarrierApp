package com.example.App.dto;

import java.util.List;

public class ProducstToPickUpByCarreir {
    
    
    	private Long orderId;
	private List<ProductDeliveryDTO> productDeliveryDTOs;
	
	public Long getOrderId() {
	    return orderId;
	}
	public void setOrderId(Long orderId) {
	    this.orderId = orderId;
	}
	public List<ProductDeliveryDTO> getProductDeliveryDTOs() {
	    return productDeliveryDTOs;
	}
	public void setProductDeliveryDTOs(List<ProductDeliveryDTO> productDeliveryDTOs) {
	    this.productDeliveryDTOs = productDeliveryDTOs;
	}
	
	

}
