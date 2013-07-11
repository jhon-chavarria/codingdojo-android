package com.clov3rlabs.codingdojo_android;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.clov3rlabs.codingdojo_android.adapters.CommentAdapter;
import com.clov3rlabs.codingdojo_android.entities.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReadActivity extends Activity {
	private static WebService mainfeed;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	private CommentAdapter adapter;
	private ListView list1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_activity);

		mainfeed = new WebService(this.getString(R.string.server_url)+"/get");
		list1 = (ListView) findViewById(R.id.lista);

		/** Execute AsyncTask **/
		new ReadJsonTask().execute();
	}

	/** This is going to download all the comments */
	private class ReadJsonTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progressDialog;

		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(ReadActivity.this, "",
					getResources().getString(R.string.downloading), true);
		}

		@Override
		protected Void doInBackground(Void... arg) {

			Map<String, String> params = new HashMap<String, String>();
			String response = mainfeed.webGet("", params);
			List<Comment> list = null;
			try {
				Type collectionType = new TypeToken<ArrayList<Comment>>() {
				}.getType();
				try {
					list = new Gson().fromJson(response, collectionType);
				} catch (Exception e) {

				}

				for (Comment c : list) {
					comments.add(c);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			adapter = new CommentAdapter(ReadActivity.this,
					R.layout.list_items, comments);
			list1.setAdapter(adapter);
			progressDialog.dismiss();
		}
	}

}
