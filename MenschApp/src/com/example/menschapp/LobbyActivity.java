package com.example.menschapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class LobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lobby);
		
		List valueList = new ArrayList<String>();
		
		for (int i = 0; i < 10; i++)
		{
			valueList.add("value"+i);
		}
		
//		ListAdapter adapter = new ArrayAdapter<T>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lobby, menu);
		return true;
	}

}
