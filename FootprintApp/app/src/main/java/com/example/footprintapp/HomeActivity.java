package com.example.footprintapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dinuscxj.progressbar.CircleProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeActivity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter{

    private static final String DEFAULT_PATTERN = "%d%%";
    CircleProgressBar circleProgressBar;
    CircleProgressBar circleProgressBar2;

    private ImageView imageview2;
    private TextView petname;

    private int eatProgess = 0;
    private int taken = 0;

    private int playProgess = 0;
    private int played = 0;

    private TextView recommendedCalories;
    private TextView recommendedPlay;
    private TextView petName;
    private int maintenanceER;
    private int recommendPT;
    private ImageButton btn_home;
    private ImageButton btn_calendar;
    private ImageButton btn_setting;

    private Button btn_eat;
    private Button btn_play;

    private String pleaseUri;
    private String pleaseName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //자정 지나면 섭취량 초기화 ??
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String currentTime = dateFormat.toString();
        if(currentTime == "24:00:00"){
            taken =0;
        }
        petName = findViewById(R.id.petName);

        btn_home = findViewById(R.id.ic_home);
        btn_calendar = findViewById(R.id.ic_calendar);
        btn_setting = findViewById(R.id.ic_setting);

        btn_home.setSelected(true);

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
                Intent intent3 = new Intent(HomeActivity.this, CalendarActivity.class);
                intent3.putExtra("uriPlease", pleaseUri);
                intent3.putExtra("namePlease", pleaseName);
                intent3.putExtra("eatPlease", Integer.toString(eatProgess));
                intent3.putExtra("playPlease", Integer.toString(playProgess));

                finish();
                startActivity(intent3);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_home.setSelected(false);
                btn_calendar.setSelected(false);
                btn_setting.setSelected(true);
                finish();
                startActivity(new Intent(HomeActivity.this,SettingActivity.class));
            }
        });

        petname = findViewById(R.id.petName);
        imageview2 = findViewById(R.id.petImg);
        recommendedCalories = findViewById(R.id.recommend_calories);
        recommendedPlay = findViewById(R.id.recommend_play);

        Intent reIndent = getIntent();
        String text = reIndent.getStringExtra("petName");
        petname.setText(text);
        pleaseName = text;

        String newUri = reIndent.getStringExtra("imageUri");
        Uri mImageUri = Uri.parse(newUri);
        imageview2.setImageURI(mImageUri);
        pleaseUri = newUri;

        // 먹자 dialog
        btn_eat = findViewById(R.id.eat_button);
        btn_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EatDialog dialog = new EatDialog(HomeActivity.this);
                //rounded 다이얼로그 띄우기 위해 drawable 추가 외에도 반드시 추가해야하는 코드
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setDialogListener(new EatDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String intake) {

                        //다이얼로그에 들어온 섭취량 받아와서 taken 값 갱신
                        int intakeInt = Integer.parseInt(intake);
                        taken += intakeInt;
                        //갱신된 taken 값으로 progress bar 업데이트
                        String petWeight= reIndent.getStringExtra("petWeight");
                        int petWeightInt = Integer.parseInt(petWeight);
                        maintenanceER = calculateCalories(petWeightInt);
                        eatProgess = taken * 100 / maintenanceER;
                        circleProgressBar=findViewById(R.id.eat_circlebar);
                        circleProgressBar.setProgress(eatProgess);  // 해당 퍼센트를 적용
                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
            }
        });

        //놀자 dialog
        btn_play = findViewById(R.id.play_button);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayDialog dialog = new PlayDialog(HomeActivity.this);
                //rounded 다이얼로그 띄우기 위해 drawable 추가 외에도 반드시 추가해야하는 코드
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setDialogListener(new PlayDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String playTime) {

                        //다이얼로그에 들어온 섭취량 받아와서 played 값 갱신
                        int playTimeInt = Integer.parseInt(playTime);
                        played += playTimeInt;
                        //갱신된 taken 값으로 progress bar 업데이트
                        String petWeight= reIndent.getStringExtra("petWeight");
                        int petWeightInt = Integer.parseInt(petWeight);
                        recommendPT = calculatePlaytime(petWeightInt);
                        playProgess = played * 100 / recommendPT;
                        circleProgressBar2=findViewById(R.id.play_circlebar);
                        circleProgressBar2.setProgress(playProgess);  // 해당 퍼센트를 적용
                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
            }
        });

        //권장 섭취량, 운동량 계산
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

