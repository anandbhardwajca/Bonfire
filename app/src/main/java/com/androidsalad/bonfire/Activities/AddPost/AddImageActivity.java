package com.androidsalad.bonfire.Activities.AddPost;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.androidsalad.bonfire.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class AddImageActivity extends AppCompatActivity {

    private static final String TAG = "AddImageActivity";

    private ArrayList<String> photoPaths;
    private Bitmap fullSizeBitmap, thumbnailBitmap;

    private StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("images");

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("photos");

    private FirebaseUser firebaseUser;

    private ImageAdapter imageAdapter;
    private GridView imageGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //File Picker
        FilePickerBuilder.getInstance().setMaxCount(5)
                .setSelectedFiles(photoPaths)
                .setActivityTheme(R.style.AppTheme)
                .pickPhoto(AddImageActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            photoPaths = new ArrayList<>();

            photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
            Log.d(TAG, "onActivityResult: Size: " + photoPaths.size());
        }

        try {
            addThemToView(photoPaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addThemToView(ArrayList<String> photoPaths) throws IOException {


        imageGridView = (GridView) findViewById(R.id.addImageGridView);
        imageAdapter = new ImageAdapter(this);
        imageGridView.setAdapter(imageAdapter);

        imageAdapter.updateImageList(photoPaths);

//
//        for (String photoPath : photoPaths) {
//            //first convert to bitmap
//
//            Log.d(TAG, "addThemToView: " + photoPath);
//
//            Uri uri = Uri.fromFile(new File(photoPath));
//
//            fullSizeBitmap = decodeSampledBitmapFromUri(uri, 1280, 1280);
//            thumbnailBitmap = decodeSampledBitmapFromUri(uri, 128, 128);
//
//            saveToFirebase(fullSizeBitmap, thumbnailBitmap);
//        }
    }

    private void saveToFirebase(Bitmap fullSize, final Bitmap thumbnail) {

        final String key = mDatabase.push().getKey();

        ByteArrayOutputStream fullSizeStream = new ByteArrayOutputStream();
        fullSize.compress(Bitmap.CompressFormat.JPEG, 90, fullSizeStream);
        byte[] bytes = fullSizeStream.toByteArray();

        mStorage.child(key).child("full").putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                final String fullSizeUrl = taskSnapshot.getDownloadUrl().toString();

                ByteArrayOutputStream thumbnailStream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, thumbnailStream);
                mStorage.child(key).child("thumb").putBytes(thumbnailStream.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String thumbnailUrl = taskSnapshot.getDownloadUrl().toString();

//                        Photo photo = new Photo(key, user, fullSizeUrl, thumbnailUrl, System.currentTimeMillis(), "This is photo Text");

//                        mDatabase.child(key).setValue(photo).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(AddImageActivity.this, "Image saved!!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(AddImageActivity.this, WelcomeActivity.class));
//                                finish();
//                            }
//                        });
                    }
                });
            }
        });
    }


    private Bitmap decodeSampledBitmapFromUri(Uri fileUri, int reqWidth, int reqHeight) throws IOException {
        InputStream stream = new BufferedInputStream(
                getApplicationContext().getContentResolver().openInputStream(fileUri));
        stream.mark(stream.available());
        BitmapFactory.Options options = new BitmapFactory.Options();
        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream, null, options);
        stream.reset();

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeStream(stream, null, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
// Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public class ImageAdapter extends BaseAdapter {

        private Context context;
        private List<String> imageList;

        public ImageAdapter(Context context) {
            this.context = context;
            this.imageList = new ArrayList<>();
        }

        public void updateImageList(List<String> imageList) {
            this.imageList = imageList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public String getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.single_image_item, parent, false);
            }

            imageView = (ImageView) convertView.findViewById(R.id.singleImageItem);

            Uri uri = Uri.fromFile(new File(imageList.get(position)));

            Picasso.with(context).load(uri).into(imageView);

            return convertView;
        }
    }

}



