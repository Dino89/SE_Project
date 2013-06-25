package com.example.menschapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.example.menschapp.GameDetailActivity.AllowOrDeclineRequestTask;
import com.example.menschapp.LoginActivity.UserLoginTask;
import com.example.menschapp.util.Games;
import com.example.menschapp.util.HighscoreList;
import com.example.menschapp.util.MenschSystemStub;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HighscoreActivity extends Activity {
	
	final Context context = this;
    ListView highscoreListView;
    ArrayAdapter arrayAdapter;
	String[] valueList;
	private NewHighscoreTask newHighscoreTask = null;
	ArrayList<Games> gamesArray = new ArrayList<Games>();
	String[] highscoreList = new String[] {"Test"};
	private TextView GameListStatusView;
	private SharedPreferences prefs;
	private MenschApplication obsApp;
	private HighscoreList hl = new HighscoreList();
	ArrayList<String> listHighscore = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore);
		GameListStatusView = (TextView) findViewById(R.id.login_status_message);
        /* Initialisiere den Stub zum GameServer */
        obsApp = (MenschApplication) this.getApplication();
        obsApp.setObsStub(new MenschSystemStub());
        
	    // Initialize the array

	    // Declare the UI components
	
	        // Initialize the UI components
        highscoreListView = (ListView) findViewById(R.id.lobbylist);
	        // For this moment, you have ListView where you can display a list.
	        // But how can we put this data set to the list?
	        // This is where you need an Adapter
	 
	        // context - The current context.
	        // resource - The resource ID for a layout file containing a layout 
	                // to use when instantiating views.
	        // From the third parameter, you plugged the data set to adapter

	    arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listHighscore);
//	 
//	        // By using setAdapter method, you plugged the ListView with adapter
	    highscoreListView.setAdapter(arrayAdapter);
//	    
	    newHighscoreTask = new NewHighscoreTask(); 
	    newHighscoreTask.execute();
		
	    

		findViewById(R.id.update_pic).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				newHighscoreTask = new NewHighscoreTask(); 
				newHighscoreTask.execute();
				
			}
		});
	}
		



	


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lobby, menu);
		//You must return true for the menu to be displayed; if you return false it will not be shown.
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


		



	public class NewHighscoreTask extends AsyncTask<String, Void, Boolean> {
				
	    @Override
		protected Boolean doInBackground(String... params) {
			// TODO: attempt authentication against a network service.
	    	
		    hl = HighscoreActivity.this.obsApp.getObsStub().getHighscoreList();	    
		    
		    
		    highscoreList = hl.getList();
		    
		    
		    
	        	
	        try {
	        	
	        	
	        	
	        	
	        	
				// Simulate network access.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return false;
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
			listHighscore.clear();
		    for(int i=1; i<highscoreList.length; i++) {
		    	listHighscore.add(highscoreList[i]);
		    }
		    arrayAdapter.notifyDataSetChanged();
		    
		    
		    
		    
		   try{ Thread.sleep(5000);}catch(Exception ex){System.out.println(ex.getMessage());}
        	if(listHighscore.size() == 0) {
        		Log.d("kk", "sleep called");
        		AlertDialog.Builder newRequestDialogBuilder = new AlertDialog.Builder(context);
        		newRequestDialogBuilder
				.setMessage("HighscoreServer zur Zeit nicht erreichbar! :(")
				.setCancelable(false)
				.setNeutralButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing

						//dialog.cancel();
					}
				});
        		
        		AlertDialog alertDialog = newRequestDialogBuilder.create();
        		 
				// show it
				alertDialog.show();
        	}else {Log.d("kk", "nicht drin!" + listHighscore.get(0).toString());}
			
		}
	
		@Override
		protected void onCancelled() {
	
		}
	}
}