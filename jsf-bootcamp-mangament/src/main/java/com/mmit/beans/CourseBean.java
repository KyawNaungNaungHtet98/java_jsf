package com.mmit.beans;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import com.mmit.model.entities.Batch;
import com.mmit.model.entities.Course;
import com.mmit.model.entities.Level;
import com.mmit.model.services.BatchService;
import com.mmit.model.services.CourseService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CourseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CourseService service;
	

	private List<Course> courses;
	private Course course;
	
	
	@RequestParameterMap
	@Inject
	private Map<String, String> params;
	

	@PostConstruct
	private void init() {
		String id = params.get("cId");
		
		course = (id == null) ? new Course() : service.findById(Integer.parseInt(id));
		
		courses =  service.findAll();
		
	}
	public String save() {
		
		service.save(course);
		
		return "/course-list?faces-redirect=true";
	}
	public String delete(int id) {
		//System.out.println("delete method" + id);
		service.deleteById(id);
		//System.out.println("After delete method");
		return "/course-list?faces-redirect=true";
		
		
		
	}
//	public String searchDetail(int id) {
//		service.findById(id);
//		courses = service.findAll();
//		return "/course-detail?faces-redirect=true";
//		
//		
//	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Course> getCourses() {
		courses = service.findAll();
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	


	
}
