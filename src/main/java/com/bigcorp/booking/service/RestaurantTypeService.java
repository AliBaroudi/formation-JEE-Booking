package com.bigcorp.booking.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import com.bigcorp.booking.dao.RestaurantTypeDAO;
import com.bigcorp.booking.model.RestaurantType;

@Stateless
public class RestaurantTypeService {

	@Inject
	protected RestaurantTypeDAO restaurantDAO;

	public RestaurantType find(Long id) {
		return this.restaurantDAO.find(id);
	}

	/*
	 * public RestaurantType merge(RestaurantType object) { return
	 * this.restaurantDAO.merge(object); }
	 */

	@TransactionAttribute
	public RestaurantType save(RestaurantType restaurantType) {
		return this.restaurantDAO.merge(restaurantType);
	}

	public RestaurantType findWithRestaurantsById(Long id) {
		return this.restaurantDAO.findWithRestaurantsById(id);

	}

	public void delete(Long id) {
		RestaurantType restaurantType = this.restaurantDAO.find(id);
		this.restaurantDAO.remove(restaurantType);
	}

}
