package com.example.codingdojo_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SendActivity extends Activity {
	private EditText name,email,comment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_activity);
	}
	
	/** Send data  **/
	public void SendData(View v) {
		
	}
}
