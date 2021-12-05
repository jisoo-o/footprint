package com.example.footprintapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    private ImageButton btn_home;
    private ImageButton btn_calendar;
    private ImageButton btn_setting;
    private ImageButton btn_logout;
    private ImageButton btn_signout;
    private ImageButton btn_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_home = findViewById(R.id.ic_home);
        btn_calendar = findViewById(R.id.ic_calendar);
        btn_setting = findViewById(R.id.ic_setting);
        btn_contact = findViewById(R.id.contact);
        btn_signout = findViewById(R.id.signout);
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
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignoutDialog dialog = new SignoutDialog(SettingActivity.this);
                //rounded 다이얼로그 띄우기 위해 drawable 추가 외에도 반드시 추가해야하는 코드
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setDialogListener(new SignoutDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked() {
                        FirebaseAuth.getInstance().getCurrentUser().delete(); //회원탈퇴
                        Toast.makeText(SettingActivity.this, "회원탈퇴가 정상적으로 처리되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(SettingActivity.this, MainActivity.class));

                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
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

        btn_contact.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("plain/text");
            String[] address = {"admin.footprint@gmail.com"};
            email.putExtra(Intent.EXTRA_EMAIL, address);
            email.putExtra(Intent.EXTRA_SUBJECT,"발자국 팀에게 문의하기");
            email.putExtra(Intent.EXTRA_TEXT,"\n\n\n문의주셔서 감사합니다. 빠른 시일 내에 답변 드리겠습니다 :)");
            startActivity(email);


        }
        });

    }

}
