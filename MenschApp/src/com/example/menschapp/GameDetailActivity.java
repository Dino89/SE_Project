package com.example.menschapp;

import com.example.menschapp.util.Games;
import com.example.menschapp.util.MenschSystemStub;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class GameDetailActivity extends Activity {

	private int gameid;
	private TextView TextView03;
	
	private SharedPreferences prefs;
	private MenschApplication obsApp;
	
	private GameDetailTask gameTask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
		setContentView(R.layout.activity_game_detail);
		
		TextView03 = (TextView) findViewById(R.id.spielleiter);
		
		
		gameTask = new GameDetailTask();
		gameTask.execute();
		
		findViewById(R.id.mitspielen).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
//						mitspielen();
					}
				});
		
		findViewById(R.id.zuschauen).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
//						zuschauen();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_detail, menu);
		return true;
	}
	
	/**
	 * Diese Methode wird vom Container aufgerufen, wenn ein Menue-Eintrag ausgewaehlt wird.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.itemPrefs :
				startActivity(new Intent(this, SettingsActivity.class));
				break;
		}
			
		return true;
	}

	public class GameDetailTask extends AsyncTask<String, Void, Boolean> {
		
	    @Override
		protected Boolean doInBackground(String... params) {
			// TODO: attempt authentication against a network service.
			Intent myIntent = getIntent();
			gameid = myIntent.getExtras().getInt("gameid");

		    Games gameDetail = GameDetailActivity.this.obsApp.getObsStub().getGameDetails(gameid);

	        try {
				// Simulate network access.
				Thread.sleep(250);
			} catch (InterruptedException e) {
				return false;
			}

			return true;
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
	        TextView03.setText(Integer.toString(gameid));
		}

		@Override
		protected void onCancelled() {

		}
	}
	
	
}
