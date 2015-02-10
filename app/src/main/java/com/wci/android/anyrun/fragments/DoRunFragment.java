package com.wci.android.anyrun.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wci.android.anyrun.R;

/**
 * Created on 15-02-07.
 *
 * @author phil
 */
public class DoRunFragment extends Fragment implements View.OnClickListener {

	private Button     mDoRunBtn;
	private onStartRun callback;

	public interface onStartRun {
		void onStartRun();
	}

	@Override public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			callback = (onStartRun) activity;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		mDoRunBtn = (Button) rootView.findViewById(R.id.mainBtn);
		mDoRunBtn.setOnClickListener(this);
		return rootView;
	}

	@Override public void onClick(View v) {
		callback.onStartRun();
	}

	@Override public void onDetach() {
		super.onDetach();
		callback = null;
	}
}
