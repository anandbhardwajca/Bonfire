package com.androidsalad.bonfire.Activities.Welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.androidsalad.bonfire.Adapter.ImageGridAdapter;
import com.androidsalad.bonfire.Model.Photo;
import com.androidsalad.bonfire.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImageGallery extends AppCompatActivity {

    private GridView gridView;
    private ImageGridAdapter imageGridAdapter;

    private List<Photo> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        //initialize
        photoList = new ArrayList<>();
        imageGridAdapter = new ImageGridAdapter(this);
        gridView = (GridView) findViewById(R.id.imageGalleryGridView);

        gridView.setAdapter(imageGridAdapter);

        getDataFromFirebase();

    }

    private void getDataFromFirebase() {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("photos");

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Photo photo = dataSnapshot.getValue(Photo.class);
                photoList.add(photo);
                imageGridAdapter.updatePhotoList(photoList);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
