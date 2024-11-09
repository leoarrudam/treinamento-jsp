package com.indracompany.treinamento.util.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;


public class ServiceInstante<T extends Object> {
	
	
	@SuppressWarnings("unchecked")
	public T get(String nome, HttpServletRequest request){
		
		ApplicationContext aC = RequestContextUtils.findWebApplicationContext(request);
		
		if (aC != null) {
			return  (T) aC.getBean(nome);
		} 
		
		return null;
		
	}

}
