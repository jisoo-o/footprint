package com.example.footprintapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    private ImageButton btn_home;
    private ImageButton btn_calendar;
    private ImageButton btn_setting;
    private ImageButton btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_home = findViewById(R.id.ic_home);
        btn_calendar = findViewById(R.id.ic_calendar);
        btn_setting = findViewById(R.id.ic_setting);

        btn_logout = findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //로그아웃
                Toast.makeText(SettingActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
            }
        });

        btn_setting.setSelected(true);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_home.setSelected(true);
                btn_calendar.setSelected(false);
                btn_setting.setSelected(false);
                finish();
                startActivity(new Intent(SettingActivity.this,HomeActivity.class));
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
            }
        });

    }

}
