package com.example.menschapp;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
	ArrayList<Games> gamesArray = new ArrayList<Games>();
	private TextView spieler1;
	private TextView spieler2;
	private TextView spieler3;
	private TextView spieler4;
	
	private SharedPreferences prefs;
	private MenschApplication obsApp;
	
	Games gameDetail;
	private GameDetailTask gameTask = null;
	private SpectateGameTask specTask = null;
	private JoinGameTask joinTask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
		setContentView(R.layout.activity_game_detail);
		
		spieler1 = (TextView) findViewById(R.id.spieler1);
		spieler2 = (TextView) findViewById(R.id.spieler2);
		spieler3 = (TextView) findViewById(R.id.spieler3);
		spieler4 = (TextView) findViewById(R.id.spieler4);
		
		
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
		
		
		int delay = 1000; // delay for 1 sec. 
		int period = 10000; // repeat every 1 sec. 
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(new TimerTask() 
		    { 
		        public void run() 
		        { 
		        	gameTask = new GameDetailTask();
		            gameTask.execute();  // display the data
		        } 
		    }, delay, period);
		
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

//		    gameDetail = GameDetailActivity.this.obsApp.getObsStub().getGameDetails(gameid);
			gamesArray = GameDetailActivity.this.obsApp.getObsStub().getGames();
			for(Games game : gamesArray) {
				if(game.getId()==gameid) {
					gameDetail = game;
				}
			}
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
			spieler1.setText(gameDetail.getSpieler1());
			if(gameDetail.getSpieler2()!=null) spieler2.setText(gameDetail.getSpieler2());
			if(gameDetail.getSpieler3()!=null) spieler3.setText(gameDetail.getSpieler3());
			if(gameDetail.getSpieler4()!=null) spieler4.setText(gameDetail.getSpieler4());
		}

		@Override
		protected void onCancelled() {

		}
	}

	public class JoinGameTask extends AsyncTask<String, Void, Boolean> {
			
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

	public class SpectateGameTask extends AsyncTask<String, Void, Boolean> {
		
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
