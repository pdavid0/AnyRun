package com.wci.android.anyrun.http.requests;

import android.content.Context;

import com.wci.android.anyrun.R;
import com.wci.android.anyrun.models.LoginResponse;

/**
 * Created by phil on 15-02-07.
 */
public class LoginRequest extends AbstractRequest {
	private final String email;
	private final String password;
	private       Object reqObject;

	public LoginRequest(String email, String password, Context c) {
		super(c.getString(R.string.base_url) + "/login", LoginResponse.class, c);
		this.email = email;
		this.password = password;
	}

	@Override public Object loadDataFromNetwork() throws Exception {
		return getRestTemplate().postForObject(this.mUrl, getReqObject(), this.getResultType());
	}

	@Override public int compareTo(Object another) {
		return 0;
	}

	public Object getReqObject() {
		return new LoginObject(email, password);
	}

	private static final class LoginObject {
		public String email;
		public String pwd;

		private LoginObject(String email, String pwd) {
			this.email = email;
			this.pwd = pwd;
		}
	}

}
