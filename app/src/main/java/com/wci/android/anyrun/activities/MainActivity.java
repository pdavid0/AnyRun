package com.wci.android.anyrun.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.wci.android.anyrun.fragments.DoRunFragment;
import com.wci.android.anyrun.R;
import com.wci.android.anyrun.fragments.PendingOrderFragment;


public class MainActivity extends ActionBarActivity implements DoRunFragment.onStartRun {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {

			Fragment fragment = DoRunFragment.instantiate(this, "");
			getSupportFragmentManager().beginTransaction()
			                           .add(R.id.container, fragment)
			                           .commit();

		}
		Firebase.setAndroidContext(this);

		Firebase mMyGroupRef = new Firebase(
				String.format(
						"%s%s",
						getString(R.string.firebase_url),
						"PeopleGroup/-SRDFGJHKJKHGJFT"
				)
		);

		mMyGroupRef.addValueEventListener(
				new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
//TODO:
					}

					@Override
					public void onCancelled(FirebaseError firebaseError) {
						firebaseError.toException().printStackTrace();
						Toast.makeText(
								MainActivity.this,
								"" + firebaseError.getDetails(),
								Toast.LENGTH_SHORT
						).show();
					}
				}
		);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override public void onStartRun() {
		// Create and commit a new fragment transaction that adds the fragment for the back of
		// the card, uses custom animations, and is part of the fragment manager's back stack.

		getFragmentManager()
				.beginTransaction()

						// Replace the default fragment animations with animator resources representing
						// rotations when switching to the back of the card, as well as animator
						// resources representing rotations when flipping back to the front (e.g. when
						// the system Back button is pressed).
				.setCustomAnimations(
						R.animator.card_flip_right_in, R.animator.card_flip_right_out,
						R.animator.card_flip_left_in, R.animator.card_flip_left_out
				)

						// Replace any fragments currently in the container view with a fragment
						// representing the next page (indicated by the just-incremented currentPage
						// variable).
				.replace(R.id.container, new PendingOrderFragment())

						// Add this transaction to the back stack, allowing users to press Back
						// to get to the front of the card.
				.addToBackStack(null)

						// Commit the transaction.
				.commit();
//		startActivity(new Intent(this, PendingOrderFragment.class));
	}
}
