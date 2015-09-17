package me.adapters;


import java.util.ArrayList;

import me.flippr.R;
import me.flippr.R.id;
import me.flippr.R.layout;
import me.flippr.models.Contact;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter<Contact> {
	  private Activity context;
	  private ArrayList<String> namesEntry;
	  private ArrayList<String> titlesEntry;
	  

	  static class ViewHolder {
	    public TextView nameEntry;
	    public TextView titleEntry;
	    public ImageView contactPic;
	  }

	  public EntryAdapter(Activity context, ArrayList<Contact> contact ) {
	    super(context, R.layout.card, contact);
	    this.context = context;
	    namesEntry = new ArrayList<String>();
	    titlesEntry = new ArrayList<String>();
	    for(int i = 0; i < contact.size(); i++){
	    	this.namesEntry.add(i, contact.get(i).getNames());
	    	this.titlesEntry.add(i, contact.get(i).getTitles());
	  	}
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    View rowView = convertView;
	    if (rowView == null) {
	    		LayoutInflater inflater = context.getLayoutInflater();
	    		rowView = inflater.inflate(R.layout.contacts, null);
	    		ViewHolder viewHolder = new ViewHolder();
	    		viewHolder.nameEntry = (TextView) rowView.findViewById(R.id.text2);
	    		viewHolder.titleEntry = (TextView) rowView.findViewById(R.id.text3);
	    		viewHolder.contactPic = (ImageView) rowView
	    				.findViewById(R.id.icon);
	    		rowView.setTag(viewHolder);
	    	}

	    ViewHolder holder = (ViewHolder) rowView.getTag();
	    if(namesEntry.get(position)!= null) holder.nameEntry.setText(titlesEntry.get(position));
	    if(titlesEntry.get(position)!= null) holder.titleEntry.setText(namesEntry.get(position));

	    return rowView;
	  }
	} 

