package com.bigcorp.booking.rest.exceptionmapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantService;
import com.bigcorp.booking.service.RestaurantTypeService;

@Named
@Path("/restaurant-types")
@Produces("application/json")
public class RestaurantTypeRestService {

	@Inject
	RestaurantService restaurantService;

	@Inject
	RestaurantTypeService restaurantTypeService;

	@GET
	@Path("/{id}")
	public RestaurantType getRestaurantById(@PathParam("id") Long id) {
		// le path de ce truc est
		// http://path-vers-le-serveur/rest/v1/restaurant-types/12
		return this.restaurantTypeService.find(id);
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public RestaurantType postRestaurantById(@PathParam("monParamId") Long id) {
		RestaurantType restaurantType = this.restaurantTypeService.find(id);
		if (restaurantType == null) {
			throw new NotFoundException("Hey! je connais pas ce resteau!");
		}
		// restaurantType.getId();
		return restaurantType;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	private RestaurantType postRestaurant(RestaurantType restaurantType) {
		if (restaurantType == null) {
			return null;
		}
		return this.restaurantTypeService.save(restaurantType);

	}

	@DELETE
	@Path("/{id}")
	public void deleteRestaurantById(@PathParam("id") Long id) {
		// le path de ce truc est
		// http://path-vers-le-serveur/rest/v1/restaurant-types/12
		this.restaurantTypeService.delete(id);
	}
}
