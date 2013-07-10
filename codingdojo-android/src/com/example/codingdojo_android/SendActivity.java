package com.example.codingdojo_android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SendActivity extends Activity {
	private EditText name,email,comment;
	private int responsehttp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_activity);
	}
	
	/** Send data  **/
	public void SendData(View v) throws ClientProtocolException {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("Url desde @String");
        
		// Add your data
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
	}
}
