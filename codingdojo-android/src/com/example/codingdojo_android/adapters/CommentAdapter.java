package com.example.codingdojo_android.adapters;

import java.util.List;
import com.example.codingdojo_android.entities.Comment;
import com.example.codingdojo_android.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CommentAdapter extends ArrayAdapter<Comment> {
	private List<Comment> items;
	private Activity activity;
	private LayoutInflater inflater;

	public CommentAdapter(Activity a, int textViewResourceId, List<Comment> items) {
		super(a, textViewResourceId, items);
		this.activity =a;
		this.items=items;
	        
		inflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}	  
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	Comment  c = items.get(position);    	
    	LayoutInflater row = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row2=row.inflate(R.layout.list_items, parent, false);
	
    	TextView name=(TextView)row2.findViewById(R.id.name_list);
    	TextView text=(TextView)row2.findViewById(R.id.text_list);
    	name.setText(c.getName());
    	text.setText(c.getMessage());
		return row2;
    
    }
    public List<Comment> getItems() {	
		return items;
	}

}