package me.adapters;

import me.flippr.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerArrayAdapter extends ArrayAdapter<String> {
  private final Activity context;
  private final String[] names;
  private final int[] images;

  static class ViewHolder {
    public TextView text;
    public ImageView image;
  }

  public DrawerArrayAdapter(Activity context, String[] names, int[] images) {
    super(context, R.layout.drawer_list_item, names);
    this.context = context;
    this.names = names;
    this.images = images;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder viewHolder = null;
    if (convertView == null) {
    	LayoutInflater inflater = context.getLayoutInflater();
    	viewHolder = new ViewHolder();
    	switch(position) {
    	case 3:
    		convertView = inflater.inflate(R.layout.title, null);
    		viewHolder.text = (TextView) convertView.findViewById(R.id.text2);
    		convertView.setTag(viewHolder);
    		break;

    	default:
    		convertView = inflater.inflate(R.layout.drawer_list_item, null);
    		viewHolder.text = (TextView) convertView.findViewById(R.id.text1);
        	viewHolder.image = (ImageView) convertView.findViewById(R.id.icon);
        	convertView.setTag(viewHolder);
        	break;
    	}
    } else {
        viewHolder = (ViewHolder) convertView.getTag();
    }
    viewHolder.text.setTag(position);
    viewHolder.text.setText(names[position]);
    if (position>3) {
    	viewHolder.image.setImageResource(R.drawable.dummy);
    } else if (position!=3) {
    	viewHolder.image.setImageResource(images[position]);
    }
    return convertView;
  }
  
  @Override
  public int getViewTypeCount() {
	return 2;
  	
  }
}
