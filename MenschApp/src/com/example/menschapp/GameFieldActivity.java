package com.example.menschapp;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.menschapp.GameDetailActivity.GameDetailTask;
import com.example.menschapp.GameDetailActivity.JoinGameTask;
import com.example.menschapp.util.Games;
import com.example.menschapp.util.MenschSystemStub;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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
	private Games gameDetail;
	private ImageView field_blue_1;
	private ImageView field_blue_2;
	private ImageView field_blue_3;
	private ImageView field_blue_4;
	private ImageView field_blue_house_1;
	private ImageView field_blue_house_2;
	private ImageView field_blue_house_3;
	private ImageView field_blue_house_4;
	private ImageView field_green_1;
	private ImageView field_green_2;
	private ImageView field_green_3;
	private ImageView field_green_4;
	private ImageView field_green_house_1;
	private ImageView field_green_house_2;
	private ImageView field_green_house_3;
	private ImageView field_green_house_4;
	private ImageView field_red_1;
	private ImageView field_red_2;
	private ImageView field_red_3;
	private ImageView field_red_4;
	private ImageView field_red_house_1;
	private ImageView field_red_house_2;
	private ImageView field_red_house_3;
	private ImageView field_red_house_4;
	private ImageView field_yellow_1;
	private ImageView field_yellow_2;
	private ImageView field_yellow_3;
	private ImageView field_yellow_4;
	private ImageView field_yellow_house_1;
	private ImageView field_yellow_house_2;
	private ImageView field_yellow_house_3;
	private ImageView field_yellow_house_4;
	
	private TextView stateMessage;
	MediaPlayer mp;
	
	private boolean gewurfelt;
	
	ArrayList<ImageView> spielfeld = new ArrayList<ImageView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent myIntent = getIntent();
		gameid = myIntent.getExtras().getInt("gameid");
		
		super.onCreate(savedInstanceState);
		wuerfelButton = (Button) findViewById(R.id.wuerfeln);
		
//		mp = MediaPlayer.create(GameFieldActivity.this, R.);
		
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
		setContentView(R.layout.activity_game_field);
		
		
		
		stateMessage = (TextView) findViewById(R.id.textView2);
		
		 field_blue_1 = (ImageView) findViewById(R.id.field_blue_1);
		 field_blue_2 = (ImageView) findViewById(R.id.field_blue_2);
		 field_blue_3 = (ImageView) findViewById(R.id.field_blue_3);
		 field_blue_4 = (ImageView) findViewById(R.id.field_blue_4);
		 field_blue_house_1 = (ImageView) findViewById(R.id.field_blue_house_1);
		 field_blue_house_2 = (ImageView) findViewById(R.id.field_blue_house_2);
		 field_blue_house_3 = (ImageView) findViewById(R.id.field_blue_house_3);
		 field_blue_house_4 = (ImageView) findViewById(R.id.field_blue_house_4);
		 field_green_1 = (ImageView) findViewById(R.id.field_green_1);
		 field_green_2 = (ImageView) findViewById(R.id.field_green_2);
		 field_green_3 = (ImageView) findViewById(R.id.field_green_3);
		 field_green_4 = (ImageView) findViewById(R.id.field_green_4);
		 field_green_house_1 = (ImageView) findViewById(R.id.field_green_house_1);
		 field_green_house_2 = (ImageView) findViewById(R.id.field_green_house_2);
		 field_green_house_3 = (ImageView) findViewById(R.id.field_green_house_3);
		 field_green_house_4 = (ImageView) findViewById(R.id.field_green_house_4);
		 field_red_1 = (ImageView) findViewById(R.id.field_red_1);
		 field_red_2 = (ImageView) findViewById(R.id.field_red_2);
		 field_red_3 = (ImageView) findViewById(R.id.field_red_3);
		 field_red_4 = (ImageView) findViewById(R.id.field_red_4);
		 field_red_house_1 = (ImageView) findViewById(R.id.field_red_house_1);
		 field_red_house_2 = (ImageView) findViewById(R.id.field_red_house_2);
		 field_red_house_3 = (ImageView) findViewById(R.id.field_red_house_3);
		 field_red_house_4 = (ImageView) findViewById(R.id.field_red_house_4);
		 field_yellow_1 = (ImageView) findViewById(R.id.field_yellow_1);
		 field_yellow_2 = (ImageView) findViewById(R.id.field_yellow_2);
		 field_yellow_3 = (ImageView) findViewById(R.id.field_yellow_3);
		 field_yellow_4 = (ImageView) findViewById(R.id.field_yellow_4);
		 field_yellow_house_1 = (ImageView) findViewById(R.id.field_yellow_house_1);
		 field_yellow_house_2 = (ImageView) findViewById(R.id.field_yellow_house_2);
		 field_yellow_house_3 = (ImageView) findViewById(R.id.field_yellow_house_3);
		 field_yellow_house_4 = (ImageView) findViewById(R.id.field_yellow_house_4);
		
		
		
		
		
		findViewById(R.id.wuerfeln).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						findViewById(R.id.wuerfeln).setEnabled(false);
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
		findViewById(R.id.field_11).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=11; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_12).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=12; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_13).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=13; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_14).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=14; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_15).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=15; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_16).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=16; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_17).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=17; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_18).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=18; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_19).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=19; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_20).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=20; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_21).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=21; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_22).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=22; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_23).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=23; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_24).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=24; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_25).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=25; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_26).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=26; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_27).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=27; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_28).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=28; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_29).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=29; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_30).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=30; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_31).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=31; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_32).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=32; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_33).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=33; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_34).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=34; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_35).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=35; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_36).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=36; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_37).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=37; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_38).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=38; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_39).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=39; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		findViewById(R.id.field_40).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=40; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_blue_1).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=101; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_blue_2).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=102; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_blue_3).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=103; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_blue_4).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=104; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_red_1).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=105; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_red_2).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=106; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_red_3).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=107; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_red_4).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=108; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_green_1).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=109; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_green_2).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=110; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_green_3).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=111; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_green_4).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=112; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_yellow_1).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=113; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_yellow_2).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=114; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_yellow_3).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=115; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		findViewById(R.id.field_yellow_4).setOnClickListener(
				new View.OnClickListener() { 
					@Override
					public void onClick(View view) { spielfigurfeld=116; spielenTask = new SpielenTask(); spielenTask.execute(); } 
		});
		
		spielfeld.add((ImageView) findViewById(R.id.field_1));
		spielfeld.add((ImageView) findViewById(R.id.field_2));
		spielfeld.add((ImageView) findViewById(R.id.field_3));
		spielfeld.add((ImageView) findViewById(R.id.field_4));
		spielfeld.add((ImageView) findViewById(R.id.field_5));
		spielfeld.add((ImageView) findViewById(R.id.field_6));
		spielfeld.add((ImageView) findViewById(R.id.field_7));
		spielfeld.add((ImageView) findViewById(R.id.field_8));
		spielfeld.add((ImageView) findViewById(R.id.field_9));
		spielfeld.add((ImageView) findViewById(R.id.field_10));
		spielfeld.add((ImageView) findViewById(R.id.field_11));
		spielfeld.add((ImageView) findViewById(R.id.field_12));
		spielfeld.add((ImageView) findViewById(R.id.field_13));
		spielfeld.add((ImageView) findViewById(R.id.field_14));
		spielfeld.add((ImageView) findViewById(R.id.field_15));
		spielfeld.add((ImageView) findViewById(R.id.field_16));
		spielfeld.add((ImageView) findViewById(R.id.field_17));
		spielfeld.add((ImageView) findViewById(R.id.field_18));
		spielfeld.add((ImageView) findViewById(R.id.field_19));
		spielfeld.add((ImageView) findViewById(R.id.field_20));
		spielfeld.add((ImageView) findViewById(R.id.field_21));
		spielfeld.add((ImageView) findViewById(R.id.field_22));
		spielfeld.add((ImageView) findViewById(R.id.field_23));
		spielfeld.add((ImageView) findViewById(R.id.field_24));
		spielfeld.add((ImageView) findViewById(R.id.field_25));
		spielfeld.add((ImageView) findViewById(R.id.field_26));
		spielfeld.add((ImageView) findViewById(R.id.field_27));
		spielfeld.add((ImageView) findViewById(R.id.field_28));
		spielfeld.add((ImageView) findViewById(R.id.field_29));
		spielfeld.add((ImageView) findViewById(R.id.field_30));
		spielfeld.add((ImageView) findViewById(R.id.field_31));
		spielfeld.add((ImageView) findViewById(R.id.field_32));
		spielfeld.add((ImageView) findViewById(R.id.field_33));
		spielfeld.add((ImageView) findViewById(R.id.field_34));
		spielfeld.add((ImageView) findViewById(R.id.field_35));
		spielfeld.add((ImageView) findViewById(R.id.field_36));
		spielfeld.add((ImageView) findViewById(R.id.field_37));
		spielfeld.add((ImageView) findViewById(R.id.field_38));
		spielfeld.add((ImageView) findViewById(R.id.field_39));
		spielfeld.add((ImageView) findViewById(R.id.field_40));
		
//		spielfeld.get(0).setImageResource(R.drawable.circle_low2_green_filled);
//		spielfeld.get(1).setImageResource(R.drawable.circle_low2_green_filled);
//		spielfeld.get(2).setImageResource(R.drawable.circle_low2_green_filled);
//		spielfeld.get(3).setImageResource(R.drawable.circle_low2_green_filled);
		
		int delay = 1000; // delay for 1 sec. 
		int period = 5000; // repeat every 1 sec. 
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
	    	Log.d("SessionID->","SessionID"+GameFieldActivity.this.obsApp.getObsStub().getSessionId());
	    	
	    	 ArrayList<Games> gamesArray = GameFieldActivity.this.obsApp.getObsStub().getGames();
	    	
	    	
			
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

			stateMessage.setText(gameDetail.getStateMessage());
			if(gameDetail.getStateMessage().contains("Warte auf") && gameDetail.getStateMessage().contains(GameFieldActivity.this.obsApp.getObsStub().getUserName())){
				wuerfelButton = (Button) findViewById(R.id.wuerfeln);
				wuerfelButton.setEnabled(true);
				stateMessage.setText("Sie sind dran");
			}else{
				wuerfelButton = (Button) findViewById(R.id.wuerfeln);
				wuerfelButton.setEnabled(false);
			}
			
			for(int i=0;i<spielfeld.size();i++){
				if(gameDetail.getSpielfeld().get(i) ==0)
				spielfeld.get(i).setImageResource(R.drawable.circle_low2);
				
				if(gameDetail.getSpielfeld().get(i) ==1)
					spielfeld.get(i).setImageResource(R.drawable.circle_low2_blue_filled);
				
				if(gameDetail.getSpielfeld().get(i) ==2)
					spielfeld.get(i).setImageResource(R.drawable.circle_low2_red_filled);
				
				if(gameDetail.getSpielfeld().get(i) ==3)
					spielfeld.get(i).setImageResource(R.drawable.circle_low2_green_filled);
				
				if(gameDetail.getSpielfeld().get(i) ==4)
					spielfeld.get(i).setImageResource(R.drawable.circle_low2_yellow_filled);
				
			}
			
			if(gameDetail.getField_blue_1() == 0){
				field_blue_1.setImageResource(R.drawable.circle_low2_blue);
			}
			if(gameDetail.getField_blue_2() == 0){
				field_blue_2.setImageResource(R.drawable.circle_low2_blue);
			}
			if(gameDetail.getField_blue_3() == 0){
				field_blue_3.setImageResource(R.drawable.circle_low2_blue);
			}
			if(gameDetail.getField_blue_4() == 0){
				field_blue_4.setImageResource(R.drawable.circle_low2_blue);
			}
			
			if(gameDetail.getField_blue_1() == 1){
				field_blue_1.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			if(gameDetail.getField_blue_2() == 1){
				field_blue_2.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			if(gameDetail.getField_blue_3() == 1){
				field_blue_3.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			if(gameDetail.getField_blue_4() == 1){
				field_blue_4.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			
			if(gameDetail.getField_blue_house_1() ==0){
				field_blue_house_1.setImageResource(R.drawable.circle_low2_blue);
			}
			if(gameDetail.getField_blue_house_2() ==0){
				field_blue_house_2.setImageResource(R.drawable.circle_low2_blue);
			}
			if(gameDetail.getField_blue_house_3() ==0){
				field_blue_house_3.setImageResource(R.drawable.circle_low2_blue);
			}
			if(gameDetail.getField_blue_house_4() ==0){
				field_blue_house_4.setImageResource(R.drawable.circle_low2_blue);
			}
			
			if(gameDetail.getField_blue_house_1() ==1){
				field_blue_house_1.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			if(gameDetail.getField_blue_house_2() ==1){
				field_blue_house_2.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			if(gameDetail.getField_blue_house_3() ==1){
				field_blue_house_3.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			if(gameDetail.getField_blue_house_4() ==1){
				field_blue_house_4.setImageResource(R.drawable.circle_low2_blue_filled);
			}
			
			
			if(gameDetail.getField_red_1() == 0){
				field_red_1.setImageResource(R.drawable.circle_low2_red);
			}
			if(gameDetail.getField_red_2() == 0){
				field_red_2.setImageResource(R.drawable.circle_low2_red);
			}
			if(gameDetail.getField_red_3() == 0){
				field_red_3.setImageResource(R.drawable.circle_low2_red);
			}
			if(gameDetail.getField_red_4() == 0){
				field_red_4.setImageResource(R.drawable.circle_low2_red);
			}
			
			if(gameDetail.getField_red_1() == 2){
				field_red_1.setImageResource(R.drawable.circle_low2_red_filled);
			}
			if(gameDetail.getField_red_2() == 2){
				field_red_2.setImageResource(R.drawable.circle_low2_red_filled);
			}
			if(gameDetail.getField_red_3() == 2){
				field_red_3.setImageResource(R.drawable.circle_low2_red_filled);
			}
			if(gameDetail.getField_red_4() == 2){
				field_red_4.setImageResource(R.drawable.circle_low2_red_filled);
			}
			
			if(gameDetail.getField_red_house_1() ==0){
				field_red_house_1.setImageResource(R.drawable.circle_low2_red);
			}
			if(gameDetail.getField_red_house_2() ==0){
				field_red_house_2.setImageResource(R.drawable.circle_low2_red);
			}
			if(gameDetail.getField_red_house_3() ==0){
				field_red_house_3.setImageResource(R.drawable.circle_low2_red);
			}
			if(gameDetail.getField_red_house_4() ==0){
				field_red_house_4.setImageResource(R.drawable.circle_low2_red);
			}
			
			if(gameDetail.getField_red_house_1() ==2){
				field_red_house_1.setImageResource(R.drawable.circle_low2_red_filled);
			}
			if(gameDetail.getField_red_house_2() ==2){
				field_red_house_2.setImageResource(R.drawable.circle_low2_red_filled);
			}
			if(gameDetail.getField_red_house_3() ==2){
				field_red_house_3.setImageResource(R.drawable.circle_low2_red_filled);
			}
			if(gameDetail.getField_red_house_4() ==2){
				field_red_house_4.setImageResource(R.drawable.circle_low2_red_filled);
			}
			
			
			if(gameDetail.getField_green_1() == 0){
				field_green_1.setImageResource(R.drawable.circle_low2_green);
			}
			if(gameDetail.getField_green_2() == 0){
				field_green_2.setImageResource(R.drawable.circle_low2_green);
			}
			if(gameDetail.getField_green_3() == 0){
				field_green_3.setImageResource(R.drawable.circle_low2_green);
			}
			if(gameDetail.getField_green_4() == 0){
				field_green_4.setImageResource(R.drawable.circle_low2_green);
			}
			
			if(gameDetail.getField_green_1() == 3){
				field_green_1.setImageResource(R.drawable.circle_low2_green_filled);
			}
			if(gameDetail.getField_green_2() == 3){
				field_green_2.setImageResource(R.drawable.circle_low2_green_filled);
			}
			if(gameDetail.getField_green_3() == 3){
				field_green_3.setImageResource(R.drawable.circle_low2_green_filled);
			}
			if(gameDetail.getField_green_4() == 3){
				field_green_4.setImageResource(R.drawable.circle_low2_green_filled);
			}
			
			if(gameDetail.getField_green_house_1() ==0){
				field_green_house_1.setImageResource(R.drawable.circle_low2_green);
			}
			if(gameDetail.getField_green_house_2() ==0){
				field_green_house_2.setImageResource(R.drawable.circle_low2_green);
			}
			if(gameDetail.getField_green_house_3() ==0){
				field_green_house_3.setImageResource(R.drawable.circle_low2_green);
			}
			if(gameDetail.getField_green_house_4() ==0){
				field_green_house_4.setImageResource(R.drawable.circle_low2_green);
			}
			
			if(gameDetail.getField_green_house_1() ==3){
				field_green_house_1.setImageResource(R.drawable.circle_low2_green_filled);
			}
			if(gameDetail.getField_green_house_2() ==3){
				field_green_house_2.setImageResource(R.drawable.circle_low2_green_filled);
			}
			if(gameDetail.getField_green_house_3() ==3){
				field_green_house_3.setImageResource(R.drawable.circle_low2_green_filled);
			}
			if(gameDetail.getField_green_house_4() ==3){
				field_green_house_4.setImageResource(R.drawable.circle_low2_green_filled);
			}
			
			
			if(gameDetail.getField_yellow_1() == 0){
				field_yellow_1.setImageResource(R.drawable.circle_low2_yellow);
			}
			if(gameDetail.getField_yellow_2() == 0){
				field_yellow_2.setImageResource(R.drawable.circle_low2_yellow);
			}
			if(gameDetail.getField_yellow_3() == 0){
				field_yellow_3.setImageResource(R.drawable.circle_low2_yellow);
			}
			if(gameDetail.getField_yellow_4() == 0){
				field_yellow_4.setImageResource(R.drawable.circle_low2_yellow);
			}
			
			if(gameDetail.getField_yellow_1() == 4){
				field_yellow_1.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			if(gameDetail.getField_yellow_2() == 4){
				field_yellow_2.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			if(gameDetail.getField_yellow_3() == 4){
				field_yellow_3.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			if(gameDetail.getField_yellow_4() == 4){
				field_yellow_4.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			
			if(gameDetail.getField_yellow_house_1() ==0){
				field_yellow_house_1.setImageResource(R.drawable.circle_low2_yellow);
			}
			if(gameDetail.getField_yellow_house_2() ==0){
				field_yellow_house_2.setImageResource(R.drawable.circle_low2_yellow);
			}
			if(gameDetail.getField_yellow_house_3() ==0){
				field_yellow_house_3.setImageResource(R.drawable.circle_low2_yellow);
			}
			if(gameDetail.getField_yellow_house_4() ==0){
				field_yellow_house_4.setImageResource(R.drawable.circle_low2_yellow);
			}
			
			if(gameDetail.getField_yellow_house_1() ==4){
				field_yellow_house_1.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			if(gameDetail.getField_yellow_house_2() ==4){
				field_yellow_house_2.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			if(gameDetail.getField_yellow_house_3() ==4){
				field_yellow_house_3.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			if(gameDetail.getField_yellow_house_4() ==4){
				field_yellow_house_4.setImageResource(R.drawable.circle_low2_yellow_filled);
			}
			
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
		findViewById(R.id.wuerfeln).setEnabled(true);
		gewurfelt=true;
		}
	
		@Override
		protected void onCancelled() {
	
		}
	}

	public class SpielenTask extends AsyncTask<String, Void, Boolean> {
		
	    @Override
		protected Boolean doInBackground(String... params) {
	    	
	    	if(gewurfelt ==true)
	    	GameFieldActivity.this.obsApp.getObsStub().spielen(gameid, spielfigurfeld);
	    	
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
