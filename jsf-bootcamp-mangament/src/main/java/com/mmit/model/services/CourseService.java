package com.mmit.model.services;

import java.util.List;

import com.mmit.model.entities.Course;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class CourseService {
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Course> findAll() {
		
		return em.createNamedQuery("getAllCourse", Course.class)
				.getResultList();
	}

	public List<Course> findBycId(int id) {
		return em.createNamedQuery("getCourseDetail", Course.class)
				.getResultList();
		
	}
	
	public Course findById(int id) {
		
		return em.find(Course.class, id);
	}

	public void save(Course course) {
		
		if(course.getId() == 0)
			em.persist(course);
		else
			em.merge(course);
		
		
	}

	public List<Course> findByLevelId(int lvlId) {
		
		return em.createNamedQuery("getCourseByLevelId",Course.class).setParameter("LevelId", lvlId).getResultList();
	}

	public void deleteById(int id) {
		
		var course = em.find(Course.class,id);
		em.remove(course);
		
		
	}
}