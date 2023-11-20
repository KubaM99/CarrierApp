package com.example.App.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProdacutToCardDTO {
    
    Long sku;

    public Long getSku() {
        return sku;
    }

    public void setProductName(Long productSku) {
        this.sku = productSku;
    }
    
    
    
    

}
