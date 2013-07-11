package com.clov3rlabs.codingdojo_android.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.clov3rlabs.codingdojo_android.R;
import com.clov3rlabs.codingdojo_android.entities.Comment;

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
    	TextView date=(TextView)row2.findViewById(R.id.date);
    	date.setText(GetDateFormat(c.getDate()));
    	name.setText(c.getName());
    	text.setText(c.getMessage());
		return row2;
    
    }
    
    /**
	 * Retorna la fecha en el formato deseado
	 *
	 */
	public String GetDateFormat(String date) {
			
		String date1 = date;
		String date2 = null;
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d1 = null;

		try {
			d1 = form.parse(date1);
			SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM");		    		
			date2 = sdf.format(d1).toString();
		} catch (java.text.ParseException e) {
	                // TODO Auto-generated catch block
			e.printStackTrace();
		}
			 	
		return date2;
	}
    public List<Comment> getItems() {	
		return items;
	}

}