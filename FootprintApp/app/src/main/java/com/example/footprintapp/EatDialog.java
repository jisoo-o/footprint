package com.example.footprintapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EatDialog extends Dialog implements View.OnClickListener {

    private Button confirmButton;
    private Button cancelButton;
    private EditText editIntake;
    private Context context;

    private CustomDialogListener customDialogListener;

    public EatDialog(Context context) {
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
        setContentView(R.layout.eat_dialog);
        confirmButton = (Button)findViewById(R.id.confirm);
        cancelButton = (Button)findViewById(R.id.cancel);

        editIntake = (EditText)findViewById(R.id.intakeNumber);
        //버튼 클릭 리스너 등록
        confirmButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm:
                String intake = editIntake.getText().toString();

                customDialogListener.onPositiveClicked(intake);
                dismiss();
                break;
            case R.id.cancel:
                cancel();
                break;
        }
    }
}
