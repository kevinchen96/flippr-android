package me.adapters;

import java.util.ArrayList;

import me.flippr.R;
import me.flippr.R.id;
import me.flippr.R.layout;
import me.flippr.models.Card;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CardAdapter extends ArrayAdapter<Card> {
  private Activity context;
  private ArrayList<String> names;
  private ArrayList<String> jobs;
  private ArrayList<String> locations;
  private ArrayList<String> titles;
  private ArrayList<String> places;
  private ArrayList<Integer> pictures;

  static class ViewHolder {
    public TextView name;
    public TextView job;
    public TextView location;
    public TextView title;
    public TextView place;
    public ImageView picture;
  }

  public CardAdapter(Activity context, ArrayList<Card> card ) {
    super(context, R.layout.card, card);
    this.context = context;
    names = new ArrayList<String>();
    jobs = new ArrayList<String>();
    locations = new ArrayList<String>();
    titles = new ArrayList<String>();
    places = new ArrayList<String>();
    pictures = new ArrayList<Integer>();
    for(int i = 0; i < card.size(); i++){
    	addCard(card.get(i));
  	}
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View rowView = convertView;
    if (rowView == null) {
    		LayoutInflater inflater = context.getLayoutInflater();
    		rowView = inflater.inflate(R.layout.card, null);
    		ViewHolder viewHolder = new ViewHolder();
    		viewHolder.name = (TextView) rowView.findViewById(R.id.Name);
    		viewHolder.job = (TextView) rowView.findViewById(R.id.Job);
    		viewHolder.location = (TextView) rowView.findViewById(R.id.Location);
    		viewHolder.title = (TextView) rowView.findViewById(R.id.Title);
    		viewHolder.place = (TextView) rowView.findViewById(R.id.Place);
    		viewHolder.picture = (ImageView) rowView.findViewById(R.id.Picture);
    		rowView.setTag(viewHolder);
    	}

    ViewHolder holder = (ViewHolder) rowView.getTag();
    if(names.get(position)!= null) holder.name.setText(names.get(position));
    if(jobs.get(position)!= null) holder.job.setText(jobs.get(position));
    if(locations.get(position)!= null) holder.location.setText(locations.get(position));
    if(titles.get(position)!= null) holder.title.setText(titles.get(position));
    if(places.get(position)!= null) holder.place.setText(places.get(position));
    if(pictures.get(position)!= null)
    	holder.picture.setImageDrawable(context.getResources().getDrawable(pictures.get(position)));
    return rowView;
  }

public void addCard(Card card) {
	  this.names.add(card.getNames());
	  this.jobs.add(card.getJobs());
	  this.locations.add(card.getLocations());
	  this.titles.add(card.getTitles());
	  this.places.add(card.getPlaces());
	  this.pictures.add(card.getPictures());
	
}
} 