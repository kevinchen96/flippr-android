package me.flippr;

import java.util.ArrayList;
import java.util.Date;

import me.adapters.CardAdapter;
import me.flippr.models.Card;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import me.flippr.utilities.NewCardDialog;

public class CardActivity extends Activity implements NewCardDialog.NoticeDialogListener{
    private ListView cardlist;
    private ArrayList<Card> card;
    private CardAdapter cardAdapterer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);
		card = new ArrayList<Card>();
        card.add(new Card("Kevin Chen", "Flippr", "Pleasanton, California", "Android Developer", "Silicon Beach 09/07/2013",R.drawable.koala));
        cardlist = (ListView) this.findViewById(R.id.card_list);
        cardAdapterer = new CardAdapter(this, card);
        cardlist.setAdapter(cardAdapterer);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.card, menu);
		return true;
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	NewCardDialog newFragment = new NewCardDialog();
        newFragment.show(getFragmentManager(), "name");
		return true;
    	
    }
    
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        NewCardDialog dialog = new NewCardDialog();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }


	@Override
	public void onDialogPositiveClick(NewCardDialog dialog) {
		Date date = new Date(dialog.date.getYear() - 1900, dialog.date.getMonth(), dialog.date.getDayOfMonth());
    	Card temp = new Card(dialog.name.getText().toString(), dialog.company.getText().toString(), dialog.location.getText().toString(), dialog.position.getText().toString(), date.toString(),R.drawable.koala);
    	cardAdapterer.addCard(temp);
    	cardAdapterer.add(temp);
    	
	}

	@Override
	public void onDialogNegativeClick(NewCardDialog dialog) {
		
	}
}