package com.bigcorp.booking.faces.facebean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bigcorp.booking.faces.formbean.RestaurantTypeFormBean;
import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantTypeService;

@Named("restaurantTypeFaceBean")
@RequestScoped
public class RestaurantTypeFaceBean {

	@Inject
	private RestaurantTypeService restaurantTypeService;

	@Inject
	private RestaurantTypeFormBean restaurantTypeFormBean;

	private Long loadId;

	public String displayCreate() {
		// initialisation d'un restaurant type

		// remplissage des champs

		return "hello";

	}

	public Long getLoadId() {
		return loadId;
	}

	public void setLoadId(Long loadId) {
		this.loadId = loadId;
	}

	public void onLoad() {
		RestaurantType restaurantTypeWithId = this.restaurantTypeService.find(this.loadId);
		if (restaurantTypeWithId == null) {
			return;
		}
		fromEntity(restaurantTypeWithId);
	}

	/*
	 * Transfers entity attributes to this.restaurantFormBean attributes.
	 * 
	 * @param enitty
	 */
	private void fromEntity(RestaurantType entity) {
		this.restaurantTypeFormBean.setId(entity.getId());
		this.restaurantTypeFormBean.setName(entity.getName());
	}

	public String getmaValeur() {
		String maValeur = "Coucou";
		System.out.println(maValeur);
		return maValeur;

	}

	public String save() {
		RestaurantType entity = toEntity();
		// String name = restaurantTypeFormBean.getName();
		RestaurantType savedEntity = restaurantTypeService.save(entity);
		// System.out.println(name);
		return "restaurant-type?faces-redirect=true&id=" + savedEntity.getId();
	}

	public String cancel() {
		return "hello";
	}

	private RestaurantType toEntity() {
		RestaurantType entity = new RestaurantType();
		entity.setId(restaurantTypeFormBean.getId());
		entity.setName(restaurantTypeFormBean.getName());
		return entity;
	}

}