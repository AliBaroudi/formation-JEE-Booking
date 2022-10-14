package com.bigcorp.booking.dao;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bigcorp.booking.model.RestaurantType;

@Singleton
public class RestaurantTypeDAO extends AbstractDao<RestaurantType> {

	@PersistenceContext
	protected EntityManager entityManager;

	public RestaurantType save(RestaurantType object) {
		return this.entityManager.merge(object);
	}

	public RestaurantType find(Long id) {
		return this.entityManager.find(RestaurantType.class, id);
	}

	public RestaurantType merge(RestaurantType restaurantType) {
		return this.entityManager.merge(restaurantType);

	}

	/**
	 * Renvoie le restauranttype et les restaurants dont l'id du restaurant vaut id.
	 *
	 * @param id
	 * @return Restaurant, peut être null
	 */
	public RestaurantType findWithRestaurantsById(Long id) {
		List<RestaurantType> resultList = this.entityManager.createQuery(
				"SELECT distinct rt FROM RestaurantType rt left outer join fetch rt.restaurants where rt.id = :idParam ",
				RestaurantType.class).setParameter("idParam", id).getResultList();
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

}
