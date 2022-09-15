package com.mmit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mmit.model.entities.Level;
import com.mmit.model.services.LevelService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class LevelBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private LevelService service;
	
	private List<Level> levels;
	private Level level;
	
	@RequestParameterMap
	@Inject
	private Map<String, String> params;
	
	@PostConstruct
	private void init() {
		String id = params.get("levelId");
		level = id == null ? new Level() : service.findById(Integer.parseInt(id));
	}
	
	public String save() {
		service.save(level);
		return "/index?faces-redirect=true";
	}
	public List<Level> getLevels() {
		levels = service.findAll();
		return levels;
	}
	public Level getLevel() {
		
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	
	
}