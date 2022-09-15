package com.mmit.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mmit.model.entities.Batch;
import com.mmit.model.entities.Bootcamper;
import com.mmit.model.services.BatchService;
import com.mmit.model.services.BootcamperService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class BootcampBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Bootcamper> bootcampers;
	private Bootcamper bootcamper;
	
	private List<Batch> batchList;
	
	private int chosen_level_id;
	
	@Inject
	private BootcamperService service;
	@Inject
	private BatchService batchService;
	
	@RequestParameterMap
	@Inject
	private Map<String, String> params;
	@PostConstruct
	private void init() {
		String id = params.get("id");
		bootcamper = id == null  ? new Bootcamper() : service.findById(Integer.parseInt(id));
		batchList = new ArrayList<Batch>();
	}

	// ajax method
	public void retrieveBatchList() {
		System.out.println("Choose level id: " + chosen_level_id);
		batchList = batchService.findByLevelId(chosen_level_id);
	}
	public List<Bootcamper> getBootcampers() {
		bootcampers = service.findAll();
		return bootcampers;
	}

	
	public String save() {
		
		service.save(bootcamper);
		return "/bootcamper-list?faces-redirect=true";
	}
	
	public String delete(int id) {
		service.deleteById(id);
		return "/bootcamper-list?faces-redirect=true";
	}
	
	public List<Batch> getBatchList() {
		return batchList;
	}

	public Bootcamper getBootcamper() {
		return bootcamper;
	}

	public void setBootcamper(Bootcamper bootcamper) {
		this.bootcamper = bootcamper;
	}

	public int getChosen_level_id() {
		return chosen_level_id;
	}

	public void setChosen_level_id(int chosen_level_id) {
		this.chosen_level_id = chosen_level_id;
	}
	
	
	

}