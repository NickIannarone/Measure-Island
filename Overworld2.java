package com.example.zniannar.measureisland;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.measureisland.R;

public class Overworld2 extends AppCompatActivity {

    MediaPlayer player, player2;

    public void onClick(View v){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overworld2);

        //To start background music:
        player = MediaPlayer.create(this, R.raw.maintheme);
        player.setLooping(true);
        player.seekTo(Data.playerTime);
        player.start();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        //To initiate number pad:
        //make the necessary objects
        EditText editText = (EditText) findViewById(R.id.email);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        //Because this is a Fountain screen
        Data.jockHP = Data.maxHP;
        Data.bookwormHP = Data.maxHP;
        Data.musicianHP = Data.maxHP;
        Data.artistHP = Data.maxHP;
    }

    public void hitMenu(View v)
    {
        player2 = MediaPlayer.create(this, R.raw.sound3);
        player2.start();

        int time = player.getCurrentPosition();
        Data.playerTime = time;
        player.stop();

        Data.location = getClass();
        startActivity(new Intent(Overworld2.this, Menu.class));
    }

    public void deleteText(View v){
        ((EditText) v).setText("");
    }

    public static boolean isViewOverlapping(View view1, View view2) {
        Rect myViewRect = new Rect();
        view1.getHitRect(myViewRect);

        Rect otherViewRect1 = new Rect();
        view2.getHitRect(otherViewRect1);

        return Rect.intersects(myViewRect, otherViewRect1);
    }

    //Wall version
    public void walkDown(View v) {

        final ImageButton downArrow = (ImageButton) findViewById(R.id.downArrow3);

        downArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar3);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

                    //if avatar goes off the screen, load the next screen or block the avatar
                    if (isViewOverlapping(avatar, findViewById(R.id.bottomBorder))) {

                        float y = avatar.getY();
                        y -= 30;
                        avatar.setY(y);

                        return false;
                    }

                    //For the actual walking
                    avatar.setImageResource(R.drawable.walkfront2);
                    float y = avatar.getY();
                    y += 2;
                    avatar.setY(y);
                    avatar.setImageResource(R.drawable.walkfront1);
                }

                return false;
            }
        });
    }

    //Door version
    public void walkLeft(View v) {

        final ImageButton leftArrow = (ImageButton) findViewById(R.id.leftArrow3);

        leftArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar3);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

                    //if avatar goes off the screen, load the next screen
                    if (isViewOverlapping(avatar, findViewById(R.id.leftBorder))) {

                        float x = avatar.getX();
                        x += 30;
                        avatar.setX(x);

                        leftArrow.setEnabled(false);
                        leftArrow.setEnabled(true);

                        int time = player.getCurrentPosition();
                        Data.playerTime = time;
                        player.stop();

                        //Reset all the "enter battle" shapes/regions on the screen
                        Data.battle1Done = false;
                        Data.battle2Done = false;
                        Data.battle3Done = false;
                        Data.battle4Done = false;

                        startActivity(new Intent(Overworld2.this, Main3Activity.class));
                        player2 = MediaPlayer.create(Overworld2.this, R.raw.sound2);
                        player2.start();
                        return false;
                    }

                    avatar.setImageResource(R.drawable.walkleft2);
                    float x = avatar.getX();
                    x -= 2;
                    avatar.setX(x);
                    avatar.setImageResource(R.drawable.walkleft1);
                }

                return false;
            }
        });
    }

    //Wall version
    public void walkUp(View v) {

        final ImageButton upArrow = (ImageButton) findViewById(R.id.upArrow3);

        upArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar3);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

                    //if avatar goes off the screen, load the next screen or block the avatar
                    if (isViewOverlapping(avatar, findViewById(R.id.topBorder))) {

                        float y = avatar.getY();
                        y += 30;
                        avatar.setY(y);

                        return false;
                    }

                    avatar.setImageResource(R.drawable.walkback2);
                    float y = avatar.getY();
                    y -= 2;
                    avatar.setY(y);
                    avatar.setImageResource(R.drawable.walkback1);
                }

                return false;
            }
        });
    }

    //Door version
    public void walkRight(View v) {

        final ImageButton rightArrow = (ImageButton) findViewById(R.id.rightArrow3);

        rightArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar3);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

                    //if avatar goes off the screen, load the next screen
                    if (isViewOverlapping(avatar, findViewById(R.id.rightBorder))) {

                        float x = avatar.getX();
                        x -= 30;
                        avatar.setX(x);

                        rightArrow.setEnabled(false);
                        rightArrow.setEnabled(true);

                        int time = player.getCurrentPosition();
                        Data.playerTime = time;
                        player.stop();

                        //Reset all the "enter battle" shapes/regions on the screen
                        Data.battle1Done = false;
                        Data.battle2Done = false;
                        Data.battle3Done = false;
                        Data.battle4Done = false;

                        startActivity(new Intent(Overworld2.this, Overworld6.class));
                        player2 = MediaPlayer.create(Overworld2.this, R.raw.sound2);
                        player2.start();
                        return false;
                    }

                    avatar.setImageResource(R.drawable.walkright2);
                    float x = avatar.getX();
                    x += 2;
                    avatar.setX(x);
                    avatar.setImageResource(R.drawable.walkright1);
                }

                return false;
            }
        });
    }
}
