package com.example.menschapp;

import java.util.Timer;
import java.util.TimerTask;

import com.example.menschapp.GameDetailActivity.GameDetailTask;
import com.example.menschapp.GameDetailActivity.JoinGameTask;
import com.example.menschapp.util.Games;
import com.example.menschapp.util.MenschSystemStub;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameFieldActivity extends Activity {

	private int gameid;
	private int diceNumber;
	private int diceId;
	private int spielfigurfeld;
	private SharedPreferences prefs;
	private MenschApplication obsApp;
	private SpielenTask spielenTask = null;
	private WuerfelTask wuerfelTask = null;
	private RefreshViewTask refreshView = null;
	TextView wuerfelergebnis;
	private Button wuerfelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent myIntent = getIntent();
		gameid = myIntent.getExtras().getInt("gameid");
		
		super.onCreate(savedInstanceState);
		wuerfelButton = (Button) findViewById(R.id.wuerfeln);
		
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
		setContentView(R.layout.activity_game_field);
		
		findViewById(R.id.wuerfeln).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {					
					    wuerfelTask = new WuerfelTask();
					    wuerfelTask.execute();
					}
				});
		
		findViewById(R.id.field_1).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=1; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_2).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=2; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_3).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=3; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_4).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=4; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_5).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=5; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_6).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=6; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_7).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=7; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_8).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=8; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_9).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=9; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_10).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=10; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		int delay = 1000; // delay for 1 sec. 
		int period = 10000; // repeat every 1 sec. 
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(new TimerTask() 
		    { 
		        public void run() 
		        { 
		        	refreshView = new RefreshViewTask();
		        	refreshView.execute();  // display the data
		        } 
		    }, delay, period);
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

	    	GameFieldActivity.this.obsApp.getObsStub().getGameFields(gameid);
	    	
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

	public class WuerfelTask extends AsyncTask<String, Void, Boolean> {
		
	    @Override
		protected Boolean doInBackground(String... params) {
	
	    	diceNumber = GameFieldActivity.this.obsApp.getObsStub().diceNumber(gameid);
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
		wuerfelergebnis = (TextView) findViewById(R.id.wuerfelergebnis);
		wuerfelergebnis.setText(diceNumber + " gewürfelt");
		}
	
		@Override
		protected void onCancelled() {
	
		}
	}

	public class SpielenTask extends AsyncTask<String, Void, Boolean> {
		
	    @Override
		protected Boolean doInBackground(String... params) {
	
	    	GameFieldActivity.this.obsApp.getObsStub().spielen(gameid, spielfigurfeld, diceNumber);
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
