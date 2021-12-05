package com.example.footprintapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayDialog extends Dialog implements View.OnClickListener {

    private Button confirmButton2;
    private Button cancelButton2;
    private EditText editPlay;
    private Context context;

    private CustomDialogListener customDialogListener;

    public PlayDialog(Context context) {
        super(context);
        this.context = context;
    }

    interface CustomDialogListener{
        void onPositiveClicked(String intake);
        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_dialog);
        confirmButton2 = (Button)findViewById(R.id.confirm2);
        cancelButton2 = (Button)findViewById(R.id.cancel2);

        editPlay = (EditText)findViewById(R.id.playNumber);
        //버튼 클릭 리스너 등록
        confirmButton2.setOnClickListener(this);
        cancelButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm2:
                String playTime = editPlay.getText().toString();

                customDialogListener.onPositiveClicked(playTime);
                dismiss();
                break;
            case R.id.cancel2:
                cancel();
                break;
        }
    }
}
