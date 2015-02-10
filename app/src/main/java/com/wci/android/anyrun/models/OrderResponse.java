package com.wci.android.anyrun.models;

/**
 *
 * Created by phil on 15-02-07.
 *
 */
public class OrderResponse {
	boolean response;
	boolean is_special;

	String special;

	public OrderResponse() {
	}

	public OrderResponse(boolean response, boolean is_special, String special) {
		this.response = response;
		this.is_special = is_special;
		this.special = special;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public boolean isIs_special() {
		return is_special;
	}

	public void setIs_special(boolean is_special) {
		this.is_special = is_special;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}
}
