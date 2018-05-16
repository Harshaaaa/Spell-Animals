package com.example.apiiit_rkv.apzlab;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView textView;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView=(TextView)findViewById(R.id.palygame);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        } else {
            new CountDownTimer(2000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    textView.setVisibility(View.VISIBLE);
                }
            }.start();

        }
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Are you sure want to Exit the game?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        }).setNegativeButton("No", null).show();
    }
    public void NavToGame(View view) {
        pd= new ProgressDialog(SplashScreen.this);
        pd.setMessage("Loading Please Wait....");
        pd.show();
        new CountDownTimer(1000,800) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                pd.dismiss();
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        }.start();
    }
}
