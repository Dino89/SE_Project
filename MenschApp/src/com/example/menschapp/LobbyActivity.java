package com.example.menschapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.menschapp.LoginActivity.UserLoginTask;
import com.example.menschapp.util.Games;
import com.example.menschapp.util.MenschSystemStub;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LobbyActivity extends Activity {
	
	
    ListView gameListView;
    ArrayAdapter arrayAdapter;
	String[] valueList;
	private GameListTask listTask = null;
	ArrayList<Games> gamesArray = new ArrayList<Games>();
	ArrayList<String> games = new ArrayList<String>();
	
	
	private SharedPreferences prefs;
	private MenschApplication obsApp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lobby);
		
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
	    // Initialize the array

	    // Declare the UI components
	
	        // Initialize the UI components
        gameListView = (ListView) findViewById(R.id.lobbylist);
	        // For this moment, you have ListView where you can display a list.
	        // But how can we put this data set to the list?
	        // This is where you need an Adapter
	 
	        // context - The current context.
	        // resource - The resource ID for a layout file containing a layout 
	                // to use when instantiating views.
	        // From the third parameter, you plugged the data set to adapter

	    arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, games);
//	 
//	        // By using setAdapter method, you plugged the ListView with adapter
	    gameListView.setAdapter(arrayAdapter);
//	    
		listTask = new GameListTask(); 
		listTask.execute();
	 }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lobby, menu);
		return true;
	}

	public class GameListTask extends AsyncTask<String, Void, Boolean> {
				
	    @Override
		protected Boolean doInBackground(String... params) {
			// TODO: attempt authentication against a network service.
			
		    gamesArray = LobbyActivity.this.obsApp.getObsStub().getGames();

		    Log.d("GAME LISTE", ""+gamesArray);
		    Log.d("", ""+ gamesArray);
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
			
			
			//TODO: Spieleliste in ListView reinkriegen!
//			int i = 0;
//			for(Games s : gamesArray) {
//				valueList[i] = String.valueOf(s.getId());
//			}
			
//			ArrayList<String> strings = new ArrayList<String>();
//			
//			for (Object object : gamesArray) {
//			    strings.add(object != null ? object.toString() : null);
//			}
			

			
			for(int i = 0; i < gamesArray.size(); i++) {
				games.add(gamesArray.get(i).toString());
			}
		    
			arrayAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onCancelled() {

		}
	}
}