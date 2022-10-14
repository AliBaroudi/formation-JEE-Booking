package com.bigcorp.booking.rest.pojo;

public class RestaurantPojo {

	private Long id;
	private String name;
	private Long restaurantTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRestaurantTypeId() {
		return restaurantTypeId;
	}

	public void setRestaurantTypeId(Long restaurantTypeId) {
		this.restaurantTypeId = restaurantTypeId;
	}

}
