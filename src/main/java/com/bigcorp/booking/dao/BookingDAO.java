package com.bigcorp.booking.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bigcorp.booking.model.Booking;

@Singleton
public class BookingDAO extends AbstractDao<Booking> {

	@PersistenceContext
	protected EntityManager entityManager;

	public List<Booking> findByRestaurantAndDate(Long restaurantId, LocalDate date) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
		Root<Booking> queryRoot = criteriaQuery.from(Booking.class);
		List<Predicate> predicates = new ArrayList<>();
		if (restaurantId != null) {
			predicates.add(criteriaBuilder.equal(queryRoot.get("restaurant").get("id"), restaurantId));
		}
		if (date != null) {
			predicates.add(criteriaBuilder.equal(queryRoot.get("date"), date));
		}
		Predicate andPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		criteriaQuery.where(andPredicate);

		return this.entityManager.createQuery(criteriaQuery).getResultList();
	}

}
