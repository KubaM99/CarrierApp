package com.example.App.exceptation;

import java.util.List;
import java.util.function.Supplier;

import com.example.App.model.Card;

public class NotFoundProducts extends RuntimeException {

    public NotFoundProducts(String message) {
	super(message);
	// TODO Auto-generated constructor stub
    }

}
