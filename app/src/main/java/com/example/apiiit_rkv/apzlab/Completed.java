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

public class Completed extends AppCompatActivity {
    Bundle bd;
    TextView tv;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        tv=(TextView)findViewById(R.id.setscore);
        bd=getIntent().getExtras();
        tv.setText("Your Score is:-"+bd.getInt("score")+"/100");
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

    public void ReplyGame(View view) {

        pd= new ProgressDialog(Completed.this);
        pd.setMessage("Loading Please Wait....");
        pd.show();
        new CountDownTimer(1000,800) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                pd.dismiss();
                startActivity(new Intent(Completed.this,MainActivity.class));
            }
        }.start();
    }
}
