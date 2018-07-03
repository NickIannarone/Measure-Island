package com.example.zniannar.measureisland;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nick.measureisland.R;

public class Main4Activity extends AppCompatActivity {

    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void open1(){
        ((TextView) findViewById(R.id.squiggle)).setText("Hello...");

    }

    public void open2(){
        ((TextView) findViewById(R.id.squiggle2)).setText("Hello...?");
    }
    public void open3(){
        ((TextView) findViewById(R.id.squiggle3)).setText("    After calling for hours, you finally come to your senses, escpaing from what seemed like an endless coma.");
    }
    public void open4(){
        ((TextView) findViewById(R.id.squiggle4)).setText("    You are now on an island, presumably the evil 'Measure Island' that the strange men had mentioned.");
    }
    public void open5(){
        ((TextView) findViewById(R.id.squiggle5)).setText("    You can remember all of what happened...It was a peaceful day in Mr. Moore's class when some strange men burst through the door, said they were taking over class, and banished you from Simmons for good. You posed a threat to them since you were the smartest kids in class, but now...");
    }
    public void open6(){
        ((TextView) findViewById(R.id.squiggle6)).setText("    ...now, you're obviously far from any classroom.");
    }
    public void open7(){
        ((TextView) findViewById(R.id.squiggle7)).setText("    You're all nerds. Therefore, the only way you can leave this desert island is to channel your 'psychic energy' by solving the world's hardest math problems.");
    }
    public void open8(){
        ((TextView) findViewById(R.id.squiggle8)).setText("    The fate of Simmons Elementary relies on you. Solve those problems and save the day!");
    }
    public void open9() {
        startActivity(new Intent(Main4Activity.this, Main3Activity.class));
    }


    public void openButton(View v){
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
            case 8:
                counter++;
                open9();
                break;
        }
    }
}
