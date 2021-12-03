package com.example.footprintapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dinuscxj.progressbar.CircleProgressBar;


public class HomeActivity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {

    private static final String DEFAULT_PATTERN = "%d%%";
    CircleProgressBar circleProgressBar;
    CircleProgressBar circleProgressBar2;

    private ImageView imageview2;
    private TextView petname;

    private int eatProgess = 0;
    private int taken = 180;

    private int playProgess = 0;
    private int played = 10;

    private TextView recommendedCalories;
    private TextView recommendedPlay;
    private int maintenanceER;
    private int recommendPT;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        petname = findViewById(R.id.petName);
        imageview2 = findViewById(R.id.petImg);
        recommendedCalories = findViewById(R.id.recommend_calories);
        recommendedPlay = findViewById(R.id.recommend_play);

        Intent reIndent = getIntent();
        String text = reIndent.getStringExtra("petName");
        petname.setText(text);

        String newUri = reIndent.getStringExtra("imageUri");
        Uri mImageUri = Uri.parse(newUri);
        imageview2.setImageURI(mImageUri);

        String petWeight= reIndent.getStringExtra("petWeight");
        int petWeightInt = Integer.parseInt(petWeight);
        maintenanceER = calculateCalories(petWeightInt);
        recommendPT = calculatePlaytime(petWeightInt);

        eatProgess = taken * 100 / maintenanceER;
        circleProgressBar=findViewById(R.id.eat_circlebar);
        circleProgressBar.setProgress(eatProgess);  // 해당 퍼센트를 적용

        playProgess = played * 100 / recommendPT;
        circleProgressBar2=findViewById(R.id.play_circlebar);
        circleProgressBar2.setProgress(playProgess);  // 해당 퍼센트를 적용

        recommendedCalories.setText("권장 섭취량은 " + maintenanceER + "kcal이에요");
        recommendedPlay.setText("최소 " + recommendPT + "분은 뛰어 놀고 싶어요");
    }
    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }

    private int calculateCalories(int weight) {
        int restingEnergyRequirements;
        int maintenaceEnergyRequirement;
        if (weight <2){
            restingEnergyRequirements = (int) (70 * (weight * 0.75));
        } else
            restingEnergyRequirements = (int) (30 * weight + 70);

        maintenaceEnergyRequirement = (int) (restingEnergyRequirements * 1.6);
        return maintenaceEnergyRequirement;
    }

    private int calculatePlaytime(int weight) {
        int recommendedPlayTime;
        if(weight <7){
            recommendedPlayTime = 25;
        }else if(7<weight && weight<15){
            recommendedPlayTime = 30;
        }else{
            recommendedPlayTime = 45;
        }
        return recommendedPlayTime;
    }


}

