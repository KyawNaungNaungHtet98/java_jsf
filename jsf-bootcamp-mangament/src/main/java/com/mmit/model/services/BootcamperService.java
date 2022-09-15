package com.mmit.model.services;

import java.util.List;

import com.mmit.model.entities.Bootcamper;
import com.mmit.model.entities.Course;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Stateless
public class BootcamperService {

	@PersistenceContext
	private EntityManager em;

	public List<Bootcamper> findAll() {
		
		return em.createNamedQuery("findAllBootcampers", Bootcamper.class).getResultList();
	}

	public void save(Bootcamper bootcamper) {
		if(bootcamper.getId() == 0)
			em.persist(bootcamper);
		else
			em.merge(bootcamper);
		
	}

	public Bootcamper findById(int parseInt) {
		
		return em.find(Bootcamper.class, parseInt);
	}

	public void deleteById(int id) {
		var bootcamper = em.find(Bootcamper.class,id);
		em.remove(bootcamper);
		
	}

	
}