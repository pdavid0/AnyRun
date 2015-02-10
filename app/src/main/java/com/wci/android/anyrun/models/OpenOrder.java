package com.wci.android.anyrun.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by phil on 15-02-07.
 *
 */
public class OpenOrder {
	String orderId;
	boolean specials = true;
	int     timeout  = 8; //mins
	List<People> people_ids;
	List<OrderResponse> responses = new ArrayList<>();

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isSpecials() {
		return specials;
	}

	public void setSpecials(boolean specials) {
		this.specials = specials;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public List<People> getPeople_ids() {
		return people_ids;
	}

	public void setPeople_ids(List<People> people_ids) {
		this.people_ids = people_ids;
	}

	public List<OrderResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<OrderResponse> responses) {
		this.responses = responses;
	}

	public OpenOrder(String orderId, boolean specials, int timeout, List<People> peopleInOrder, List<OrderResponse> responses) {

		this.orderId = orderId;
		this.specials = specials;
		this.timeout = timeout;
		this.people_ids = peopleInOrder;
		this.responses = responses;
	}

	public OpenOrder() {

	}
}
