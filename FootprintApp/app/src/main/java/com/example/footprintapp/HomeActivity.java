package com.example.footprintapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {

    private ImageView imageview2;
    private TextView petname;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        petname = findViewById(R.id.petName);
        imageview2 = findViewById(R.id.petImg);

        Intent reIndent = getIntent();

        String text = reIndent.getStringExtra("petName");
        petname.setText(text);

        String newUri = reIndent.getStringExtra("imageUri");
        Uri mImageUri = Uri.parse(newUri);
        imageview2.setImageURI(mImageUri);


    }


}

