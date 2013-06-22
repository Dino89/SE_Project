package com.example.menschapp;

import java.util.Timer;
import java.util.TimerTask;

import com.example.menschapp.GameDetailActivity.GameDetailTask;
import com.example.menschapp.util.Games;
import com.example.menschapp.util.MenschSystemStub;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

public class GameFieldActivity extends Activity {

	private int gameid;
	
	private SharedPreferences prefs;
	private MenschApplication obsApp;
	
	private RefreshViewTask refreshView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
		setContentView(R.layout.activity_game_field);
//		
//		int delay = 1000; // delay for 1 sec. 
//		int period = 10000; // repeat every 1 sec. 
//		Timer timer = new Timer(); 
//		timer.scheduleAtFixedRate(new TimerTask() 
//		    { 
//		        public void run() 
//		        { 
//		        	refreshView = new RefreshViewTask();
//		        	refreshView.execute();  // display the data
//		        } 
//		    }, delay, period);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_field, menu);
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
	
	public class RefreshViewTask extends AsyncTask<String, Void, Boolean> {
		
	    @Override
		protected Boolean doInBackground(String... params) {

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

		}

		@Override
		protected void onCancelled() {

		}
	}



}
