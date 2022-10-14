package com.bigcorp.booking.rest.exceptionmapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bigcorp.booking.model.Booking;
import com.bigcorp.booking.model.Restaurant;
import com.bigcorp.booking.rest.pojo.BookingPojo;
import com.bigcorp.booking.service.BookingService;
import com.bigcorp.booking.service.RestaurantService;

@Named
@Path("/bookings")
public class BookingRestService {

	@Inject
	private RestaurantService restaurantService;

	@Inject
	private BookingService bookingService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public BookingPojo post(BookingPojo bookingPojo) {
		if (bookingPojo == null) {
			throw new NotFoundException();
		}
		Booking booking = toEntity(bookingPojo);
		booking = this.bookingService.save(booking);
		return toPojo(booking);
	}

	private Booking toEntity(BookingPojo bookingPojo) {
		Booking booking = new Booking();
		booking.setDate(bookingPojo.getDate());
		booking.setId(bookingPojo.getId());
		booking.setName(bookingPojo.getName());
		booking.setSize(bookingPojo.getSize());
		if (bookingPojo.getRestaurantId() != null) {
			Restaurant restaurant = this.restaurantService.findById(bookingPojo.getRestaurantId());
			booking.setRestaurant(restaurant);
		}
		return booking;
	}

	private BookingPojo toPojo(Booking booking) {
		BookingPojo bookingPojo = new BookingPojo();
		bookingPojo.setDate(booking.getDate());
		bookingPojo.setId(booking.getId());
		bookingPojo.setName(booking.getName());
		bookingPojo.setSize(booking.getSize());
		if (booking.getRestaurant() != null) {
			bookingPojo.setRestaurantId(booking.getRestaurant().getId());
		}
		return bookingPojo;
	}

}
