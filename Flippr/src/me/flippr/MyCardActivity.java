package me.flippr;

import java.util.ArrayList;

import me.adapters.CardAdapter;
import me.flippr.models.Card;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MyCardActivity extends Activity{
	 private ListView cardlist;
	 private ArrayList<Card> card;
	 private CardAdapter cardAdapterer;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_card);
			// Get the message from the intent
			if(getIntent()!= null){
				Bundle extras = getIntent().getExtras();
			    String name = extras.getString(BrowsePictureActivity.NAME);
			    String company = extras.getString(BrowsePictureActivity.COMPANY);
			    String title = extras.getString(BrowsePictureActivity.TITLE);
			    String location = extras.getString(BrowsePictureActivity.LOCATION);
			    String picture = extras.getString(BrowsePictureActivity.PICTURE); // eventually change adapter to take in a URI PATH;
			    card = new ArrayList<Card>(); // eventually change so that it loads content from server
			    card.add(0, new Card(name, company, location, title, "Silicon Beach 09/07/2013",R.drawable.koala));
			    cardlist = (ListView) this.findViewById(R.id.card_list); // don't know if we need to use a new cardlist so we dont run into problems
			    cardAdapterer = new CardAdapter(this, card);
			    cardlist.setAdapter(cardAdapterer);
			}
		}
		public void newCard(View view){
			Intent intent = new Intent(this, BrowsePictureActivity.class);
			startActivity(intent);
		}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			super.onCreateOptionsMenu(menu);
			getMenuInflater().inflate(R.menu.card, menu);
			return true;
		}
}
