package com.example.android.tourguideapp;

/**
 * Created by Eddie on 8/3/2018.
 */

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of color vocabulary words.
 */
public class ArchaeologicalFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of locations
        final ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(new Location(R.string.sight,R.string.sight1,
                R.drawable.acropolis, "geo:37.971536, 23.725750?q=Acropolis Athens"));
        locations.add(new Location(R.string.sight,R.string.sight2,
                R.drawable.parthenon, "geo:37.971583, 23.726710?q=Parthenonas Athens"));
        locations.add(new Location(R.string.sight,R.string.sight3,
                R.drawable.olympian, "geo:37.969326, 23.733080?q=Stili Olimpiou Dios"));
        locations.add(new Location(R.string.sight,R.string.sight4,
                R.drawable.odeon, "geo:37.970824, 23.724572?q=Odeon of Herodes Atticus"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Location}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), locations, R.color.category_archaeological);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Location} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to go to the Google map when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Location} object at the given position the user clicked on
                Location location = locations.get(position);

                showMap(Uri.parse(location.getGeoResourceId()));
            }
        });

        return rootView;
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}