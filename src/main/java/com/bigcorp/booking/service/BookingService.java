package com.bigcorp.booking.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import com.bigcorp.booking.dao.BookingDAO;
import com.bigcorp.booking.model.Booking;

@Stateless
public class BookingService {

	@Inject
	protected BookingDAO bookingDao;

	@TransactionAttribute
	public Booking findById(long id) {
		return this.bookingDao.findById(Booking.class, id);
	}

	@TransactionAttribute
	public Booking save(Booking booking) {
		return this.bookingDao.merge(booking);
	}

	@TransactionAttribute
	public void delete(Booking booking) {
		Booking persistedBooking = this.bookingDao.findById(Booking.class, booking.getId());
		this.bookingDao.remove(persistedBooking);
	}

	public List<Booking> findByRestaurantAndDate(Long restaurantId, LocalDate date) {
		return this.bookingDao.findByRestaurantAndDate(restaurantId, date);
	}

}
