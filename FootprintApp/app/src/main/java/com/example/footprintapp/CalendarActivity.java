package com.example.footprintapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class CalendarActivity extends AppCompatActivity {

    private CircleImageView petCircleImage;
    private TextView myPetName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        petCircleImage = findViewById(R.id.petCircleImage);
        myPetName = findViewById(R.id.passedPetName);

        Intent gIndent = getIntent();
        String newUri = gIndent.getStringExtra("uriPlease");
        Uri mImageUri = Uri.parse(newUri);
        petCircleImage.setImageURI(mImageUri);

        String namePlz = gIndent.getStringExtra("namePlease");
        myPetName.setText(namePlz);


    }
}
