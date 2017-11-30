package com.androidsalad.bonfire.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidsalad.bonfire.Model.Photo;
import com.androidsalad.bonfire.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {

    private Context context;
    private List<Photo> photoList;

    public ImageGridAdapter(Context context) {
        this.context = context;
        this.photoList = new ArrayList<>();
    }

    public void updatePhotoList(List<Photo> photos) {
        this.photoList = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Photo getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(R.layout.grid_item_image_gallery, parent, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.singleGridItemImageView);

        Photo photo = photoList.get(position);

        Picasso.with(context).load(photo.getPhotoImagePath()).into(imageView);

        return convertView;
    }
}
