package com.example.App.model;


import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery")
@AllArgsConstructor
public class Delivery  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer appUser;
    
    
    @ManyToOne
    @JoinColumn(name = "carrier")
    private Carrier carrier;

    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    private double totalPris;

  
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true, fetch =FetchType.LAZY)
    private List<ProductDeliveris> productDelivery;

    public Delivery(Customer appUser) {
	super();
	this.appUser = appUser;
	this.delivered = false;
	this.took = false;

    }
    
    public Delivery(Long id) {
   	super();
   	this.id =id;
   	this.delivered = false;
   	this.took = false;

       }
    

    public Delivery(Customer appUser, Date createdDate, double totalPris, List<ProductDeliveris> productDelivery) {
	super();
	this.appUser = appUser;
	//this.productDelivery = productDelivery;
    }

    private boolean delivered;

    private boolean took;

    public Delivery() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Customer getCustomer() {
	return appUser;
    }

    public void setCustomer(Customer appUser) {
	this.appUser = appUser;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public double getTotalPris() {
	return totalPris;
    }

    public void setTotalPris(Double totalPris) {
	this.totalPris = totalPris;
    }

    //public List<ProductDelivery> getProductDelivery() {
//	return productDelivery;
    //}
    //
    //public void setProductDelivery(List<ProductDelivery> productDelivery) {
//	this.productDelivery = productDelivery;
    //}

    public boolean isDelivered() {
	return delivered;
    }

    public void setDelivered(boolean delivered) {
	this.delivered = delivered;
    }

    public void setTotalPris(double totalPris) {
	this.totalPris = totalPris;
    }

    public boolean isTook() {
	return took;
    }

    public void setTook(boolean took) {
	this.took = took;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }
    
    

 

}
