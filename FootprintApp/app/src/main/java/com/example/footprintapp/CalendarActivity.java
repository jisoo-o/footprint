package com.example.footprintapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class CalendarActivity extends AppCompatActivity {

    private CircleImageView petCircleImage;
    private TextView myPetName;
    private TextView dayIntake;
    private TextView dayPlay;
    private TextView congrats;
    private ImageButton btn_home;
    private ImageButton btn_calendar;
    private ImageButton btn_setting;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        petCircleImage = findViewById(R.id.petCircleImage);
        myPetName = findViewById(R.id.passedPetName);
        dayIntake = findViewById(R.id.dayIntake);
        dayPlay = findViewById(R.id.dayPlay);
        congrats = findViewById(R.id.congrats);
        btn_home = findViewById(R.id.ic_home);
        btn_calendar = findViewById(R.id.ic_calendar);
        btn_setting = findViewById(R.id.ic_setting);

        ImageView confettiGif = (ImageView)findViewById(R.id.confettiGif);
        // Glide.with(this).load(R.raw.confetti).into(confettiGif);

        Intent gIndent = getIntent();
        String newUri = gIndent.getStringExtra("uriPlease");
        Uri mImageUri = Uri.parse(newUri);
        petCircleImage.setImageURI(mImageUri);

        String namePlz = gIndent.getStringExtra("namePlease");
        myPetName.setText(namePlz);

        String eatPlz = gIndent.getStringExtra("eatPlease");
        dayIntake.setText(eatPlz);
        String playPlz = gIndent.getStringExtra("playPlease");
        dayPlay.setText(playPlz);

        int eat = Integer.parseInt(eatPlz) ;
        int play = Integer.parseInt(playPlz) ;
        if(eat > 99 && play > 99){
            Glide.with(this).load(R.raw.confetti).into(confettiGif);
            congrats.setText(namePlz + "는 오늘 행복해요 !");
        }else if(eat > 50 && play < 50){
            congrats.setText(namePlz + "는 더 뛰어 놀고 싶어요 🥱");
        }else if(eat < 50 && play > 50){
            congrats.setText(namePlz + "는 더 먹고 싶어요 🤤");
        }else {
            congrats.setText(namePlz + "는 더 먹고, 더 놀고 싶어요 🥲");
        }

        btn_calendar.setSelected(true);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_home.setSelected(true);
                btn_calendar.setSelected(false);
                btn_setting.setSelected(false);
            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_home.setSelected(false);
                btn_calendar.setSelected(true);
                btn_setting.setSelected(false);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_home.setSelected(false);
                btn_calendar.setSelected(false);
                btn_setting.setSelected(true);
                finish();
                startActivity(new Intent(CalendarActivity.this,SettingActivity.class));
            }
        });

    }
}
