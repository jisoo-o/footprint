package com.example.footprintapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignoutDialog extends Dialog implements View.OnClickListener {

    private Button confirmButton3;
    private Button cancelButton3;
    private Context context;

    private CustomDialogListener customDialogListener;

    public SignoutDialog(Context context) {
        super(context);
        this.context = context;
    }

    interface CustomDialogListener{
        void onPositiveClicked();
        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signout_dialog);
        confirmButton3 = (Button)findViewById(R.id.confirm3);
        cancelButton3 = (Button)findViewById(R.id.cancel3);

        //버튼 클릭 리스너 등록
        confirmButton3.setOnClickListener(this);
        cancelButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm3:
                customDialogListener.onPositiveClicked();

                dismiss();
                break;
            case R.id.cancel3:
                cancel();
                break;
        }
    }
}