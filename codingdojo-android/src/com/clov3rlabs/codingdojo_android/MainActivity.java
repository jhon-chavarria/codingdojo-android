package com.clov3rlabs.codingdojo_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
	  /** Read Comments  **/
	  public void GoToRead(View v) {
		 intent = new Intent(MainActivity.this,ReadActivity.class);
		 startActivity(intent);
	  }
	  
	  /** Send Form  **/
	  public void GoToSend(View v) {
		  intent = new Intent(MainActivity.this,SendActivity.class);
		  startActivity(intent);
	  }
}
