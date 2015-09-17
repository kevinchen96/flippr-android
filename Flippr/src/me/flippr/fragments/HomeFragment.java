package me.flippr.fragments;

import java.util.ArrayList;

import me.adapters.CardAdapter;
import me.flippr.R;
import me.flippr.models.Card;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment{
    private ListView cardlist;
    private ArrayList<Card> card;
    private Activity context;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, true);
        this.context = getActivity();
		card = new ArrayList<Card>();
        card.add(new Card("Kevin Chen", "Flippr", "Pleasanton, California", "Android Developer", "Silicon Beach 09/07/2013",R.drawable.dummy));
        cardlist = (ListView) context.findViewById(R.id.card_list);
        cardlist.setAdapter(new CardAdapter(context, card));
        return rootView;
    }
}

/*
*/