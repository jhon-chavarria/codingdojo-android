package com.clov3rlabs.codingdojo_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.clov3rlabs.codingdojo_android.adapters.CommentAdapter;
import com.clov3rlabs.codingdojo_android.entities.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivity extends Activity {
	private EditText name, email, message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_activity);
	}

	/** Send data **/
	public void SendData(View v) throws ClientProtocolException {

		name = (EditText) findViewById(R.id.name_list);
		email = (EditText) findViewById(R.id.email);
		message = (EditText) findViewById(R.id.comment);

		if (name.length() > 0 && email.length() > 0 && message.length() > 0) {

			new SendCommentTask().execute(getBaseContext().getString(
					R.string.server_url)
					+ "/post");

		} else {
			Toast.makeText(this.getBaseContext(),
					"Codingjodo-Android: No pueden quedar campos vacios",
					Toast.LENGTH_SHORT).show();
		}
	}

	/** This is going to send the comment */
	private class SendCommentTask extends AsyncTask<String, Void, String> {
		private ProgressDialog progressDialog;

		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(SendActivity.this, "",
					getResources().getString(R.string.sending), true);
		}

		@Override
		protected String doInBackground(String... arg) {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(arg[0]);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("name", name.getText()
					.toString()));
			nameValuePairs.add(new BasicNameValuePair("email", email.getText()
					.toString()));
			nameValuePairs.add(new BasicNameValuePair("message", message
					.getText().toString()));

			String msg = "Comentario enviado.";

			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
						nameValuePairs);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
						"utf-8"));
				httppost.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			HttpResponse response = null;
			
			try {
				response = httpclient.execute(httppost);
				String jsonString = EntityUtils.toString(response.getEntity());
				JSONObject o = new JSONObject(jsonString);

				if (o.has("error"))
					msg = o.getString("error");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return msg;
		}

		@Override
		protected void onPostExecute(String result) {

			Toast.makeText(getBaseContext(), "Codingjodo-Android: " + result,
					Toast.LENGTH_SHORT).show();

			name.setText("");
			message.setText("");
			email.setText("");

			progressDialog.dismiss();
		}
	}
}
