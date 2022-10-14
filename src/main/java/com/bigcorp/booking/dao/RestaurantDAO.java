package com.bigcorp.booking.dao;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bigcorp.booking.model.Restaurant;

@Singleton
public class RestaurantDAO extends AbstractDao<Restaurant> {

	@PersistenceContext
	protected EntityManager entityManager;

	/*
	 * public List<Restaurant> findByName(String name) { return this.entityManager
	 * .createQuery("select distinct r from Restaurant r where r.name like :name",
	 * Restaurant.class) .setParameter("name", name).getResultList(); }
	 */

	public List<Restaurant> findByName(String name) {
		if (name == null) {
			name = "";
		} else {
			name = name.toUpperCase();
		}
		return this.entityManager
				.createQuery("select distinct r " + "	from Restaurant r " + " where upper(r.name) like :name",
						Restaurant.class)
				.setParameter("name", "%" + name + "%").getResultList();
	}

	/**
	 * Renvoie le restaurant et son restaurantType (s'il existe) dont l'id du
	 * restaurant vaut id.
	 *
	 * @param id
	 * @return Restaurant, peut être null
	 */
	public Restaurant findWithTypeById(Long id) {
		TypedQuery<Restaurant> query = this.entityManager.createQuery("SELECT r " + " FROM Restaurant r"
				+ "    left join fetch r.restaurantType " + " 	where r.id = :idParam ", Restaurant.class);
		query.setParameter("idParam", id);
		List<Restaurant> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	public List<Restaurant> findWithTypeByName(String name) {
		return this.entityManager.createQuery("select distinct r " + "	from Restaurant r "
				+ "	left outer join fetch r.restaurantType " + " where r.name = :name", Restaurant.class)
				.setParameter("name", name).getResultList();
	}

	/*
	 * private RestaurantService restaurantService;
	 * 
	 * public Restaurant merge(Restaurant restaurant) { return
	 * this.entityManager.merge(restaurant); }
	 * 
	 * public Restaurant find(Long id) { return
	 * this.entityManager.find(Restaurant.class, id); }
	 * 
	 * public void remove(Restaurant restaurant) {
	 * this.entityManager.remove(restaurant); }
	 * 
	 * public void removeById(Long id) {
	 * this.entityManager.createQuery("delete from Restaurant e where e.id = :id",
	 * Restaurant.class) .setParameter("id", id).executeUpdate(); }
	 */

}
