package com.bigcorp.booking.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bigcorp.booking.model.Example;

@Singleton
public class ExampleDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public Example merge(Example object) {
		return this.entityManager.merge(object);
	}

	public Example findById(Long id) {
		return this.entityManager.find(Example.class, id);
	}

	public void remove(Long id) {
		this.entityManager.createQuery("delete from Example e where e.id = :id", Example.class).setParameter("id", id)
				.executeUpdate();
	}

}
