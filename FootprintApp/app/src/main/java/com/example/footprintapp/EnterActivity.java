package com.example.footprintapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EnterActivity extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private Button readyBtn;
    private EditText petNameET;
    private TextView nicknameT;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        nicknameT = findViewById(R.id.nickname);
        petNameET = findViewById(R.id.petnameET);

        Intent reIndent1 = getIntent();
        String userNickname = reIndent1.getStringExtra("userName");
        //nicknameT.setText(userNickname);
        nicknameT.setText("jisoo");

        imageview = findViewById(R.id.selectPhoto);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);

            }
        });

        readyBtn = (Button)findViewById(R.id.ready_btn);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);

            Intent intent = new Intent(EnterActivity.this, HomeActivity.class);
            intent.putExtra("imageUri", selectedImageUri.toString());
            intent.putExtra("petName", petNameET.getText().toString());

            readyBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EnterActivity.this, HomeActivity.class);
                    intent.putExtra("imageUri", selectedImageUri.toString());
                    intent.putExtra("petName", petNameET.getText().toString());
                    startActivity(intent);
                }
            });
        }

    }

}