package com.androidsalad.bonfire.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidsalad.bonfire.Model.Post;
import com.androidsalad.bonfire.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PostListAdapter extends BaseAdapter {

    private List<Post> postList;
    private Context context;

    private DatabaseReference mDatabase;

    public PostListAdapter(Context context) {
        this.context = context;
        this.postList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void updatePostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Post getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.single_post_item_recent, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.singlePostImageView);
            viewHolder.userProfileImageView = (ImageView) convertView.findViewById(R.id.singlePostRecentUserImageView);
            viewHolder.recentTextView = (TextView) convertView.findViewById(R.id.singlePostRecentTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Post post = postList.get(position);

        viewHolder.recentTextView.setText(post.getPostText());

        Picasso.with(context).load(R.mipmap.logo).into(viewHolder.imageView);
        //get user thumbnail image


        mDatabase.child(post.getPostUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imagePath = dataSnapshot.child("userImagePath").getValue(String.class);
                Picasso.with(context).load(imagePath).into(viewHolder.userProfileImageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return null;
    }


    private class ViewHolder {

        private ImageView userProfileImageView;
        private ImageView imageView;
        private TextView recentTextView;


    }
}
