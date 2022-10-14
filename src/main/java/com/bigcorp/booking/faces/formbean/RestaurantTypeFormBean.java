package com.bigcorp.booking.faces.formbean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RestaurantTypeFormBean implements Serializable {

	// public static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String ajaxName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// @javax.validation.constraints.Email
	public String getName() {
		return name;
	}

	public String getAjaxName() {
		return "AJAX" + this.name;
	}

	public void setAjaxName(String ajaxName) {
		this.ajaxName = ajaxName;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public static long getSerialversionuid() { return serialVersionUID; }
	 */

}