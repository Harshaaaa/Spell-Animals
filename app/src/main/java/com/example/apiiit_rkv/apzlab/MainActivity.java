package com.example.apiiit_rkv.apzlab;


import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener  {

    ImageView regProfile;
    public static int index=1;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    List<Integer> list = new ArrayList<>();
    private static int prev;
    private TextToSpeech tts;
    public static int chances=0;
    TextView scoretextview;
    public static int score=0;
    public static int count=1;
    int imagearray[]={R.drawable.cat,R.drawable.dog,R.drawable.elephant,R.drawable.horse,R.drawable.rabbit,R.drawable.lion,R.drawable.monkey,R.drawable.tiger,R.drawable.fox,R.drawable.zebra};
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoretextview=(TextView)findViewById(R.id.scoretextview);
        regProfile=(ImageView)findViewById(R.id.animal);
        tts = new TextToSpeech(this, this);
        UniqueRandomNumbers();
        regProfile.setImageResource(imagearray[list.get(0)]);
        prev=imagearray[list.get(0)];
    }
    public void check(String a)
    {

        if(a.equalsIgnoreCase("dog")&&prev==R.drawable.dog)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("cat")&&prev==R.drawable.cat)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("rabbit")&&prev==R.drawable.rabbit)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("zebra")&&prev==R.drawable.zebra)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("elephant")&&prev==R.drawable.elephant)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("horse")&&prev==R.drawable.horse)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("lion")&&prev==R.drawable.lion)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("monkey")&&prev==R.drawable.monkey)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("tiger")&&prev==R.drawable.tiger)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else if(a.equalsIgnoreCase("fox")&&prev==R.drawable.fox)
        {
            updateImage();
            score=score+10;
            ++count;
        }
        else
        {
            ++chances;
            speakOut();
            if(chances<=2)
            {
                new CountDownTimer(1000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        askSpeechInput();
                    }
                }.start();
            }
            else {
                updateImage();
                speakNextImage();
                ++count;
                chances = 0;
            }
        }
        scoretextview.setText("Score :- "+score);
        if(count==10)
        {
            Intent scoresend=new Intent(this,Completed.class);
            scoresend.putExtra("score",score);
            startActivity(scoresend);
            index=1;
            chances=0;
            score=0;
            count=0;
        }
    }
    public void updateImage()
    {
        if(index<=9)
        {
            ImageViewAnimatedChange(this, regProfile, imagearray[list.get(index)]);
            prev = imagearray[list.get(index)];
        }
        chances=0;
        speakCongo();
        index++;
    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
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
    public void UniqueRandomNumbers (){
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
    }
    public void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Tell Me Animal Name");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    public static void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageResource(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(this, result.get(0)+"", Toast.LENGTH_SHORT).show();
                        check(result.get(0));
                }
                break;
            }

        }
    }
    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
    private void speakOut() {

        String text = "Please Try Agian";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void speakCongo() {


        String text = "Welldone";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void speakNextImage() {

        String text = "Plase Try Next Image";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void askSpeechText(View view) {
        askSpeechInput();
    }
}