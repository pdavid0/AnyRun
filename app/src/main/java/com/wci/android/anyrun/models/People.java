package com.wci.android.anyrun.models;

/**
 *
 * Created by phil on 15-02-07.
 *
 */
public class People {
	String email;
	String firebase_id;
	String ballista_id;

	public People() {
	}

	public People(String email, String firebase_id, String ballista_id) {
		this.email = email;
		this.firebase_id = firebase_id;
		this.ballista_id = ballista_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirebase_id() {
		return firebase_id;
	}

	public void setFirebase_id(String firebase_id) {
		this.firebase_id = firebase_id;
	}

	public String getBallista_id() {
		return ballista_id;
	}

	public void setBallista_id(String ballista_id) {
		this.ballista_id = ballista_id;
	}
}
