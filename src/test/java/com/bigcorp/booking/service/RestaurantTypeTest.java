package com.bigcorp.booking.service;

import javax.inject.Inject;

import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bigcorp.booking.model.Restaurant;
import com.bigcorp.booking.model.RestaurantType;

@RunWith(SingleApplicationComposerRunner.class)
public class RestaurantTypeTest {

	@Inject
	private RestaurantTypeService restaurantTypeService;

	@Inject
	private RestaurantService restaurantService;

	RestaurantType restaurantType = new RestaurantType();

	@Test
	public void testget() throws Exception {
		restaurantType = this.restaurantTypeService.find(12L);
		Assert.assertNull(restaurantType);
	}

	@Test
	public void testsave() throws Exception {
		RestaurantType restaurantType = new RestaurantType();
		restaurantType = this.restaurantTypeService.save(restaurantType);
		Assert.assertNotNull(restaurantType.getId());
		restaurantType.setName("toto");
		restaurantType.setDate();
		restaurantType.setActive(true);
		restaurantType = this.restaurantTypeService.save(restaurantType);
		Assert.assertNotNull(restaurantType.getId());
	}

	@Test
	public void testfindWithTypeById() throws Exception {

		// Création du type
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("Restaurant local");
		restaurantType = this.restaurantTypeService.save(restaurantType);

		// Création d'un premier restaurant rattaché au type
		Restaurant restaurant1 = new Restaurant();
		restaurant1.setName("Mc Do avec cadeaux 1");
		restaurant1.setRestaurantType(restaurantType);
		restaurant1 = this.restaurantService.save(restaurant1);
		// restaurantType.getRestaurants().add(restaurant1); ça ne vas pas marcher -> le
		// maitre c'est restaurant

		// Création d'un deuxième restaurant rattaché au type
		Restaurant restaurant2 = new Restaurant();
		restaurant2.setName("Mc Do sans cadeaux 2");
		restaurant2.setRestaurantType(restaurantType);
		restaurant2 = this.restaurantService.save(restaurant2);
		// restaurantType.getRestaurants().add(restaurant2); ça ne vas pas marcher -> le
		// maitre c'est restaurant

		// Récupération du graphe d'objets RestaurantType -> Restaurant à partir de
		// l'identifiant
		// du restaurantType
		RestaurantType restaurantTypeWithRestaurants = this.restaurantTypeService
				.findWithRestaurantsById(restaurantType.getId());
		Assert.assertNotNull(restaurantTypeWithRestaurants);
		Assert.assertEquals(2, restaurantTypeWithRestaurants.getRestaurants().size());

	}

}
