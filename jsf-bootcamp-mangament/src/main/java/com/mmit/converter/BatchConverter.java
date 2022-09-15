package com.mmit.converter;

import com.mmit.model.entities.Batch;
import com.mmit.model.services.BatchService;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class BatchConverter implements Converter<Batch>{
	@Inject
	private BatchService service;
	

	@Override
	public Batch getAsObject(FacesContext context, UIComponent component, String id) {
		return service.findById(Integer.parseInt(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Batch value) {
		return String.valueOf(value.getId());
	}

}

	
