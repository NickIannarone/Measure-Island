package com.example.zniannar.measureisland;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.measureisland.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer player, player2;

    public void onClick(View v){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(this, R.raw.maintheme);
        player.setLooping(true);
        player.seekTo(com.example.zniannar.measureisland.Data.playerTime);
        player.start();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        EditText editText = (EditText) findViewById(R.id.email);
        com.example.zniannar.measureisland.MyKeyboard keyboard = (com.example.zniannar.measureisland.MyKeyboard) findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }

    public void startNewGame(View v)
    {
        player2 = MediaPlayer.create(this, R.raw.sound1);
        player2.start();

        int time = player.getCurrentPosition();
        com.example.zniannar.measureisland.Data.playerTime = time;
        player.stop();
        startActivity(new Intent(MainActivity.this, com.example.zniannar.measureisland.Main2Activity.class));
    }

    public void continueGame(View v)
    {
        player2 = MediaPlayer.create(this, R.raw.sound1);
        player2.start();

        int time = player.getCurrentPosition();
        com.example.zniannar.measureisland.Data.playerTime = time;
        player.stop();
        startActivity(new Intent(MainActivity.this, com.example.zniannar.measureisland.Overworld20.class));
    }

    public void hitMenu(View v)
    {
        player2 = MediaPlayer.create(this, R.raw.sound3);
        player2.start();

        TextView cat = (TextView) findViewById(R.id.text);
        cat.setText("No menu yet. Sorry...");
    }

    public void deleteText(View v){
        ((EditText) v).setText("");
    }

    public void walkDown(View v) {

        final ImageButton downArrow = (ImageButton) findViewById(R.id.downArrow);

        downArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar1);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

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

    public void walkLeft(View v) {

        final ImageButton leftArrow = (ImageButton) findViewById(R.id.leftArrow);

        leftArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar1);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

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

    public void walkUp(View v) {

        final ImageButton upArrow = (ImageButton) findViewById(R.id.upArrow);

        upArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar1);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

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

    public void walkRight(View v) {

        final ImageButton rightArrow = (ImageButton) findViewById(R.id.rightArrow);

        rightArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar1);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

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
