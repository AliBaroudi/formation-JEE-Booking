package com.bigcorp.booking.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.bigcorp.booking.dao.ExampleDao;
import com.bigcorp.booking.model.Example;

@Stateless
public class ExampleService {

	@Inject
	private ExampleDao exampleDao;

	@TransactionAttribute
	public Example save(Example example) {
		return this.exampleDao.merge(example);
	}
	
	@Transactional
	public void removeById(Long id) {
		this.exampleDao.remove(id);
	}

	public Example findById(Long id) {
		return this.exampleDao.findById(id);
	}

}