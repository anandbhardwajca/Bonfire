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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class RecentFragment extends Fragment {

    private ListView listView;
    private List<Post> postList;
    private PostListAdapter adapter;

    private FirebaseAuth firebaseAuth;

    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View convertView = inflater.inflate(R.layout.fragment_recent, container, false);

//        listView = (ListView) convertView.findViewById(R.id.recentFragmentListView);
//        postList = new ArrayList<>();
//        adapter = new PostListAdapter(getActivity());
//
//        listView.setAdapter(adapter);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//        Post post = new Post("postId", user.getDisplayName(), user.getUid(), user.getDisplayName(), "postDate");
//        postList.add(post);
//
//        adapter.updatePostList(postList);

        return convertView;
    }

}
