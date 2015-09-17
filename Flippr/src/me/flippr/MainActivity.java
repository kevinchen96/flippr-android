package me.flippr;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import me.adapters.CardAdapter;
import me.adapters.DrawerArrayAdapter;
import me.flippr.fragments.*;
import me.flippr.models.Card;
import me.flippr.models.Contact;
import me.flippr.utilities.CustomHttpClient;
import me.flippr.utilities.FetchCardDialog;
import me.flippr.utilities.NewCardDialog;
import me.flippr.utilities.FetchCard;

public class MainActivity extends FragmentActivity implements NewCardDialog.NoticeDialogListener,FetchCardDialog.NoticeDialogListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mPageTitles;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ArrayList<Contact> contact;
    private int[] mPageImages = {R.drawable.grid,R.drawable.person,R.drawable.time,R.drawable.dummy};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//card stuffs

        contact = new ArrayList<Contact>();
        contact.add(new Contact("Kevin Chen", "Flippr, Android Developer"));
        
		card = new ArrayList<Card>();
        card.add(new Card("Kevin Chen", "Flippr", "Pleasanton, California", "Android Developer", "Silicon Beach 09/07/2013",R.drawable.koala));
        cardlist = (ListView) this.findViewById(R.id.card_list);
        cardAdapterer = new CardAdapter(this, card);
        cardlist.setAdapter(cardAdapterer);
        
        //Drawer Stuff
        mTitle = mDrawerTitle = getTitle();
        mPageTitles = getResources().getStringArray(R.array.pages_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new DrawerArrayAdapter(this, mPageTitles, mPageImages));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        
        
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(3);
        }
        
		
	}
	
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        case R.id.action_create_new_card:
        	NewCardDialog newFragment = new NewCardDialog();
            newFragment.show(getFragmentManager(), "name");
    		return true;
        case R.id.action_post_card:
        	try {
				Log.v("test",(new FetchCard().execute("test")).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return true;
        case R.id.action_fetch_card:
        	FetchCardDialog dialog1 = new FetchCardDialog();
        	dialog1.show(getFragmentManager(), "fetch");
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private void selectItem(int position) {
    	
    	Fragment fragment = null;
    	
        switch(position) {
        case 0:
        	//fragment = new HomeFragment();
    		//Intent intent = new Intent(this, CardActivity.class);
        	//startActivity(intent);
        case 1:
        	fragment = new FlippsFragment();
        case 2:
        	fragment = new MyFlippFragment();
        case 3:
        	break;
        default:
        	//follow notification
        	break;
        }
        
        /*if (position<3 && fragment != null) { //Stuff to do when switching fragments
            Bundle args = new Bundle();
            args.putInt(PageFragment.ARG_PAGE_NUMBER, position);
            fragment.setArguments(args);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mPageTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);	
        }*/
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
	
    private ListView cardlist;
    private ArrayList<Card> card;
    private CardAdapter cardAdapterer;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.card, menu);
		return true;
	}
    
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        NewCardDialog dialog = new NewCardDialog();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }


	@Override
	public void onDialogPositiveClick(NewCardDialog dialog) {
		if (dialog.getTag()=="name") {
			Date date = new Date(dialog.date.getYear() - 1900, dialog.date.getMonth(), dialog.date.getDayOfMonth());
	    	Card temp = new Card(dialog.name.getText().toString(), dialog.company.getText().toString(), dialog.location.getText().toString(), dialog.position.getText().toString(), date.toString(),R.drawable.koala);
	    	cardAdapterer.addCard(temp);
	    	cardAdapterer.add(temp);
		}
	}

	@Override
	public void onDialogNegativeClick(NewCardDialog dialog) {
		
	}

	@Override
	public void onDialogPositiveClick(FetchCardDialog dialog) {
    	String str = null;
    	String text = dialog.fetch.getText().toString();
    	try {
			str = (new FetchCard().execute(text)).get();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if (str != null) {
    		Log.v("TEST",dialog.fetch.getText().toString());
    		Log.v("TEST", str);
        	String[] split = str.split("\\^");
        	Card c = new Card(split[0],split[1],split[2],split[3],split[4],R.drawable.koala);
        	cardAdapterer.addCard(c);
        	cardAdapterer.add(c);
    	}
		
	}

	@Override
	public void onDialogNegativeClick(FetchCardDialog dialog) {
		// TODO Auto-generated method stub
		
	}
}
