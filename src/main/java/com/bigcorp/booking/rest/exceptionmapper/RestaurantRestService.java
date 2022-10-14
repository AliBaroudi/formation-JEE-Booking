package com.bigcorp.booking.rest.exceptionmapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.bigcorp.booking.model.Restaurant;
import com.bigcorp.booking.rest.pojo.RestaurantPojo;
import com.bigcorp.booking.service.RestaurantService;
import com.bigcorp.booking.service.RestaurantTypeService;

@Named
@Path("/restaurants")
public class RestaurantRestService {

	@Inject
	private RestaurantService restaurantService;

	@Inject
	private RestaurantTypeService restaurantTypeService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public RestaurantPojo post(RestaurantPojo restaurantPojo) {
		if (restaurantPojo == null) {
			throw new NotFoundException();
		}
		Restaurant restaurant = toEntity(restaurantPojo);
		restaurant = this.restaurantService.save(restaurant);
		return toPojo(restaurant);
	}

	@GET
	@Produces("application/json")
	public List<RestaurantPojo> findByName(@QueryParam("name") String name) {
		return (List<RestaurantPojo>) this.restaurantService.findByName(name).stream().map(r -> toPojo(r));
	}

	private Restaurant toEntity(RestaurantPojo restaurantPojo) {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(restaurantPojo.getId());
		restaurant.setName(restaurantPojo.getName());
		restaurant.setRestaurantType(this.restaurantTypeService.find(restaurantPojo.getRestaurantTypeId()));
		return restaurant;
	}

	private RestaurantPojo toPojo(Restaurant restaurant) {
		RestaurantPojo restaurantPojo = new RestaurantPojo();
		restaurantPojo.setId(restaurant.getId());
		restaurantPojo.setName(restaurant.getName());
		if (restaurant.getRestaurantType() != null) {
			restaurantPojo.setRestaurantTypeId(restaurant.getRestaurantType().getId());
		}
		return restaurantPojo;
	}

}