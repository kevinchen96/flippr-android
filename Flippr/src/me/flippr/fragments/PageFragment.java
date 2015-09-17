package me.flippr.fragments;

import java.util.Locale;

import me.flippr.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PageFragment extends Fragment {
	public static final String ARG_PAGE_NUMBER = "page_number";
	
    public PageFragment() {
        // Empty constructor required for fragment subclasses
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        int i = getArguments().getInt(ARG_PAGE_NUMBER);
        String page = getResources().getStringArray(R.array.pages_array)[i];

        int imageId = getResources().getIdentifier(page.toLowerCase(Locale.getDefault()),
                        "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(page);
        return rootView;
    }
}
