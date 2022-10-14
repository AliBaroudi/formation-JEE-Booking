package com.bigcorp.booking.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bigcorp.booking.model.Restaurant;
import com.bigcorp.booking.model.RestaurantType;

@RunWith(SingleApplicationComposerRunner.class)
public class RestaurantServiceTest {

	@Inject
	private RestaurantService restaurantService;

	@Inject
	private RestaurantService restaurantTypeService;

	Restaurant restaurant = new Restaurant();

	@Test
	public void testget() throws Exception {
		restaurant = this.restaurantService.find(12L);
		Assert.assertNull(restaurant);
	}

	@Test
	public void testsave() throws Exception {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("toto");
		restaurant = this.restaurantService.save(restaurant);
		Assert.assertNotNull(restaurant.getId());
		// this.restaurantService.removeById(restaurant.getId());
		// restaurant = this.restaurantService.find(restaurant.getId());
		// Assert.assertNull(restaurant);

		// this.restaurantService.remove(restaurant);
		// restaurant = this.restaurantService.find(restaurant.getId());
		// Assert.assertNull(restaurant);
	}

	@Test
	public void testfindByName() throws Exception {
		Restaurant restaurant = new Restaurant();
		String name = "Mon Fameux Restaurant";
		restaurant.setName(name);
		restaurant = this.restaurantService.save(restaurant);
		List<Restaurant> maList = this.restaurantService.findByName("fameux");
		System.out.println(maList);
		Assert.assertTrue(maList.isEmpty());
	}

	@Test
	public void testFindWithType() {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("Mc Do avec cadeaux");

		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("Restaurant local");
		restaurantType = this.restaurantTypeService.save(restaurantType);

		restaurant.setRestaurantType(restaurantType);
		restaurant = this.restaurantService.save(restaurant);

		List<Restaurant> loadedRestaurants = this.restaurantService.findWithTypeByName(restaurant.getName());
		Restaurant restaurantWithType = loadedRestaurants.get(0);
		Assert.assertEquals(restaurantType.getName(), restaurantWithType.getRestaurantType().getName());
	}

	@Test
	public void testfindWithTypeById() throws Exception {
		// création des entités
		Restaurant restaurant = new Restaurant();
		restaurant.setName("Mc Do avec cadeaux");

		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("Restaurant local");
		restaurantType = this.restaurantTypeService.save(restaurantType);

		restaurant.setRestaurantType(restaurantType);
		restaurant = this.restaurantService.save(restaurant);

		// Récupération du graphe d"objets Restaurant -> Restaurant à partir de
		// l'identifiant du restaurant
		Restaurant restaurantWithRestaurantType = this.restaurantService.findWithTypeById(restaurant.getId());
		Assert.assertNotNull(restaurantWithRestaurantType);
		Assert.assertNotNull(restaurantWithRestaurantType.getRestaurantType());
		Assert.assertNotNull("Restaurant local", restaurantWithRestaurantType.getRestaurantType().getName());

	}

}