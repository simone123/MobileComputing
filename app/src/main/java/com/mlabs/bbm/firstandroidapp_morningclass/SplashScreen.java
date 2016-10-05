package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by benjarmanalili on 31/07/2016.
 */
public class SplashScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final ImageView sai = (ImageView) this.findViewById(R.id.ikot);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.ikotikot);
        sai.startAnimation(animation);
        Thread timerThread = new Thread(){
            public void run(){
                try{

                    sleep(2500);

                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class );
                    startActivity(intent);

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }



}
