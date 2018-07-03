package com.example.zniannar.measureisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nick.measureisland.R;

public class IntroBattle extends AppCompatActivity {

    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_battle);
    }

    public void open1(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("Every enemy attacks through math problems, which you will see displayed here shortly.");
    }

    public void open2(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("You are to fight back by typing up the answer into the Number Pad below and pressing ENTER.");
    }
    public void open3(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("The sooner you can answer each problem, the stronger your attack will be.");
    }
    public void open4(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("If you answer right, your attack might miss. But if you answer wrong, you'll definitely miss.");
    }
    public void open5(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("Each player will take turns attacking until the monster is defeated.");
    }
    public void open6(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("If everyone's HP reaches zero, it's Game Over for you.");
    }
    public void open7(){
        ((TextView) findViewById(R.id.aliceSpeech)).setText("When you're ready, press the button one last time to begin your first fight.");
    }
    public void open8() {
        startActivity(new Intent(IntroBattle.this, Battle.class));
    }


    public void scrollThroughText(View v){
        switch(counter){
            case 0:
                counter++;
                open1();
                break;
            case 1:
                counter++;
                open2();
                break;
            case 2:
                counter++;
                open3();
                break;
            case 3:
                counter++;
                open4();
                break;
            case 4:
                counter++;
                open5();
                break;
            case 5:
                counter++;
                open6();
                break;
            case 6:
                counter++;
                open7();
                break;
            case 7:
                counter++;
                open8();
                break;
        }
    }
}
