package com.mlabs.bbm.firstandroidapp_morningclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class generate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blank);
        ImageView img;
        img = (ImageView)findViewById(R.id.img);

        img.setOnTouchListener(new View.OnTouchListener() {
            float x=0;
            float y=0;
            float x1=0;
            float y1=0;
            float x2=0;
            float y2=0;
            String message="";
            String message1="";
            String message3="";
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                TextView tv1 = (TextView) findViewById(R.id.tv1);
                TextView tv2 = (TextView) findViewById(R.id.tv2);
                TextView tv3 = (TextView) findViewById(R.id.tv3);
                TextView tv4 = (TextView) findViewById(R.id.tv4);
                TextView tv5 = (TextView) findViewById(R.id.tv5);
                TextView tv6 = (TextView) findViewById(R.id.tv6);
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x=e.getX();
                        y=e.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x1=e.getX();
                        y1=e.getY();
                        if (x<x1){

                            message = "Left To Right";

                        }else{
                            message="Right To Left";
                        }
                        if (y<y1){

                            message1 = "Top to Bottom";

                        }else
                        {
                            message1="Bottom to Top";
                        }
                        if (x1>350 && y1>350){
                            message3="Quadrant 4";
                        }
                        else if(x1<350 && y1>350){
                            message3="Quadrant 3";
                        }
                        else if(x1>350 && y1<350){
                            message3="Quadrant 1";
                        }
                        else if(x1<350 && y1<350){
                            message3="Quadrant 2";
                        }
                        if(x>x1){
                            x2= x-x1;
                        }
                        else if(x<x1){
                            x2=x1-x;
                        }
                        if(y>y1){
                            y2= y-y1;
                        }
                        else if(y<y1){
                            y2=y1-y;
                        }
                        if(x2 < 20 && y2 <20){
                            message="No swipe";
                            message1="No swipe";
                            message3="No Quadrant";
                        }
                        tv1.setText("x="+x+" to x="+x1);
                        tv2.setText("y="+y+" to y="+y1);
                        tv3.setText("Difference of x= "+(x2));
                        tv4.setText("Difference of y= "+(y2));
                        tv5.setText("Direction = "+message +" and "+ message1 );
                        tv6.setText("Quadrant: "+ message3 );
                }
                return  false;
            }

        });
    }
}