package com.androidsalad.bonfire.Activities.Welcome.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidsalad.bonfire.Adapter.PostListAdapter;
import com.androidsalad.bonfire.Model.Post;
import com.androidsalad.bonfire.R;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.fragment_event, container, false);


        // Inflate the layout for this fragment
        return convertView;
    }
}
