package com.wci.android.anyrun.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.wci.android.anyrun.R;
import com.wci.android.anyrun.adapters.FirebaseAdapter;
import com.wci.android.anyrun.models.OpenOrder;

/**
 * Created on 15-02-07.
 *
 * @author phil
 */
public class PendingOrderFragment extends ListFragment {

	private Object mOpenOrderId = "-JhVsLxhhUf061lZsvil";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.activity_orders_list,
				container,
				false
		);

		Firebase.setAndroidContext(getActivity());

		Firebase myFirebaseRef = new Firebase(
				String.format(
						"%s%s%s",
						getString(R.string.firebase_url),
						"OpenOrders/",
						mOpenOrderId,
				        "/people_ids"
				)
		);

		//TODO:
		// skip group selection
		//create group

		// get people in group
		//update list

		//
		setListAdapter(new PendingOrderAdapter(getActivity(), myFirebaseRef));
		return view;
	}

	private class PendingOrderAdapter extends FirebaseAdapter<OpenOrder> {

		public PendingOrderAdapter(Activity context, Query mRef) {
			super(mRef, OpenOrder.class, R.layout.view_item_list_order_people, context);
		}

		@Override protected void populateView(View v, OpenOrder model) {
//			((TextView) v.findViewById(R.id.text1)).setText(model.get);
			v.findViewById(R.id.text1);
		}
	}
}
