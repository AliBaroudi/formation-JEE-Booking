package com.bigcorp.booking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import com.bigcorp.booking.dao.RestaurantDAO;
import com.bigcorp.booking.dao.RestaurantTypeDAO;
import com.bigcorp.booking.model.Restaurant;
import com.bigcorp.booking.model.RestaurantType;

@Stateless
public class RestaurantService {

	@Inject
	protected RestaurantDAO restaurantDAO;

	@Inject
	protected RestaurantTypeDAO restaurantTypeDAO;

	public Restaurant find(Long id) {
		return this.restaurantDAO.findById(Restaurant.class, id);
	}

	@TransactionAttribute
	public void removeById(Long id) {
		this.restaurantDAO.removeById(Restaurant.class, id);
	}

	@TransactionAttribute
	public void delete(Restaurant restaurant) {
		Restaurant persistedRestaurant = this.restaurantDAO.findById(Restaurant.class, restaurant.getId());
		this.restaurantDAO.remove(persistedRestaurant);
	}

	@TransactionAttribute
	public void remove(Restaurant restaurant) {
		restaurant = this.find(restaurant.getId());
		this.restaurantDAO.remove(restaurant);
	}

	/*
	 * public RestaurantType merge(RestaurantType object) { return
	 * this.restaurantDAO.merge(object); }
	 */

	@TransactionAttribute
	public Restaurant save(Restaurant restaurant) {
		return this.restaurantDAO.merge(restaurant);
	}

	public List<Restaurant> findByName(String name) {
		return this.restaurantDAO.findByName(name);
	}

	public RestaurantType save(RestaurantType restaurantType) {
		return this.restaurantTypeDAO.merge(restaurantType);

	}

	public RestaurantDAO getRestaurantDAO() {
		return restaurantDAO;
	}

	public void setRestaurantDAO(RestaurantDAO restaurantDAO) {
		this.restaurantDAO = restaurantDAO;
	}

	public Restaurant findById(long id) {
		Restaurant findById = this.restaurantDAO.findById(Restaurant.class, id);
		return findById;

	}

	/**
	 * 
	 * @param id
	 * @return Restaurant, attention peut être null.
	 */
	public Restaurant findWithTypeById(Long id) {
		return this.restaurantDAO.findWithTypeById(id);
	}

	public List<Restaurant> findWithTypeByName(String name) {
		return this.restaurantDAO.findWithTypeByName(name);
	}

}
