package com.example.menschapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.menschapp.util.Games;
import com.example.menschapp.util.MenschSystemStub;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LobbyActivity extends Activity {
	
	
    private ListView monthsListView;
    private ArrayAdapter arrayAdapter;
	List valueList = new ArrayList<String>();
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
	    ArrayList<Games> gamesArray = null;
	    gamesArray = LobbyActivity.this.obsApp.getObsStub().getGames();
	    // Declare the UI components
	
	        // Initialize the UI components
	    monthsListView = (ListView) findViewById(R.id.lobbylist);
	        // For this moment, you have ListView where you can display a list.
	        // But how can we put this data set to the list?
	        // This is where you need an Adapter
	 
	        // context - The current context.
	        // resource - The resource ID for a layout file containing a layout 
	                // to use when instantiating views.
	        // From the third parameter, you plugged the data set to adapter
//	    arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, gamesArray);
	 
	        // By using setAdapter method, you plugged the ListView with adapter
//	    monthsListView.setAdapter(arrayAdapter);
	 }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lobby, menu);
		return true;
	}

}
