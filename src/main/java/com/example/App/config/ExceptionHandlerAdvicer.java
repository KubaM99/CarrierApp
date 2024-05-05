package com.example.App.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvicer {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> invalidHandler(MethodArgumentNotValidException e) {

		Map<String, String> invlid = new HashMap<>();

		e.getBindingResult().getFieldErrors().forEach(i -> {
			invlid.put(i.getField(), i.getDefaultMessage());

		});

		return invlid;

	}
}
