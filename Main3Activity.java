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

public class Main3Activity extends AppCompatActivity {

    MediaPlayer player, player2;

    public void onClick(View v){}

/*
    Display display = getWindowManager().getDefaultDisplay();
    int width = display.getWidth();
    int height = display.getHeight();
*/
    //In order to get the leaveX and leaveY values for Data, you'll need to find the screen's dimensions and trigger the next Activity whenever you hit the border.
    //So that the player gets to defeat the intro enemy before they talk to the NPC, have the monster run up to the player immediately after this Activity starts.
    //The most important menu features are HP, XP, IQ, Stats [Offense = X, Defense = X, etc.] and Grade for each character, the current area in the game (where are we?), and a list of items and a very brief description of each one (Mushroom = Healing, Red Pepper = Attack boost)
    //The battle Activity should increment Data.XP for each character by a random amount from within a certain range based on the "difficulty class" of the monster defeated.
    //Be sure to add some coordinate to enterXAt and enterYAt so that the avatar doesn't get stuck in an eternal loop of exiting and entering each Activity (don't spawn him right at the exit range; push him an inch ahead).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ((TextView)findViewById(R.id.aliceTalk)).setVisibility(View.INVISIBLE);

        //To put avatar in right place on screen (in general):
        if (Data.introBattleDone == true) {
            ((TextView)findViewById(R.id.aliceTalk)).setVisibility(View.VISIBLE);
        }


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
    }

    public void hitMenu(View v)
    {
        player2 = MediaPlayer.create(this, R.raw.sound3);
        player2.start();

        int time = player.getCurrentPosition();
        Data.playerTime = time;
        player.stop();

        Data.location = getClass();
        startActivity(new Intent(Main3Activity.this, Menu.class));
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

    public void walkDown(View v) {

        final ImageButton downArrow = (ImageButton) findViewById(R.id.downArrow3);

        downArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar3);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

                    //if avatar goes off the screen, load the next screen
                    if (isViewOverlapping(avatar, findViewById(R.id.bottomBorder))) {

                        float y = avatar.getY();
                        y -= 30;
                        avatar.setY(y);

                        downArrow.setEnabled(false);
                        downArrow.setEnabled(true);

                        int time = player.getCurrentPosition();
                        Data.playerTime = time;
                        player.stop();

                        //Reset all the "enter battle" shapes/regions on the screen
                        Data.battle1Done = false;
                        Data.battle2Done = false;
                        Data.battle3Done = false;
                        Data.battle4Done = false;

                        //startActivity(new Intent(Main3Activity.this, Overworld3.class));
                        return false;
                    }

                    //if avatar touches NPC1, start intro battle
                    if (isViewOverlapping(avatar, findViewById(R.id.NPC1)) && Data.introBattleDone != true) {

                        //Bounce the avatar back and disable the Listener so you can't spam the battle square while it's still active
                        float y = avatar.getY();
                        y -= 30;
                        avatar.setY(y);

                        //Play the start battle sound effect
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.startbattle);
                        player2.start();

                        //Set all the necessary Data values
                        Data.monsters.add(Data.alice);
                        Data.background = R.drawable.overworldbattle1;
                        Data.location = getClass();
                        Data.screenNumber = 1;
                        Data.inBattle = true;

                        //Start the battle
                        player.stop();
                        startActivity(new Intent(Main3Activity.this, IntroBattle.class));
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.sound2);
                        player2.start();
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

                        //startActivity(new Intent(Main3Activity.this, Overworld4.class));
                        return false;
                    }

                    //if avatar touches NPC1, start intro battle
                    if (isViewOverlapping(avatar, findViewById(R.id.NPC1)) && Data.introBattleDone != true) {

                        //Bounce the avatar back and disable the Listener so you can't spam the battle square while it's still active
                        float x = avatar.getX();
                        x += 30;
                        avatar.setX(x);
                        //leftArrow.setEnabled(false);

                        //Play the start battle sound effect
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.startbattle);
                        player2.start();

                        //Set all the necessary Data values
                        Data.monsters.add(Data.alice);
                        Data.background = R.drawable.overworldbattle1;
                        Data.location = getClass();
                        Data.screenNumber = 1;
                        Data.inBattle = true;

                        //Start the battle
                        player.stop();
                        startActivity(new Intent(Main3Activity.this, IntroBattle.class));
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.sound2);
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

    public void walkUp(View v) {

        final ImageButton upArrow = (ImageButton) findViewById(R.id.upArrow3);

        upArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView avatar = findViewById(R.id.avatar3);

                if(event.getAction() != MotionEvent.ACTION_CANCEL) {

                    //if avatar goes off the screen, load the next screen
                    if (isViewOverlapping(avatar, findViewById(R.id.topBorder))) {

                        float y = avatar.getY();
                        y += 30;
                        avatar.setY(y);

                        upArrow.setEnabled(false);
                        upArrow.setEnabled(true);

                        int time = player.getCurrentPosition();
                        Data.playerTime = time;
                        player.stop();

                        //Reset all the "enter battle" shapes/regions on the screen
                        Data.battle1Done = false;
                        Data.battle2Done = false;
                        Data.battle3Done = false;
                        Data.battle4Done = false;


                        //startActivity(new Intent(Main3Activity.this, Overworld63.class));
                        return false;
                    }

                    //if avatar touches NPC1, start intro battle
                    if (isViewOverlapping(avatar, findViewById(R.id.NPC1)) && Data.introBattleDone != true) {

                        //Bounce the avatar back and disable the Listener so you can't spam the battle square while it's still active
                        float y = avatar.getY();
                        y += 30;
                        avatar.setY(y);
                        //leftArrow.setEnabled(false);

                        //Play the start battle sound effect
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.startbattle);
                        player2.start();

                        //Set all the necessary Data values
                        Data.monsters.add(Data.alice);
                        Data.background = R.drawable.overworldbattle1;
                        Data.location = getClass();
                        Data.screenNumber = 1;
                        Data.inBattle = true;

                        //Start the battle
                        player.stop();
                        startActivity(new Intent(Main3Activity.this, IntroBattle.class));
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.sound2);
                        player2.start();
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

                        startActivity(new Intent(Main3Activity.this, Overworld2.class));
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.sound2);
                        player2.start();
                        return false;
                    }

                    //if avatar touches NPC1, start intro battle
                    if (isViewOverlapping(avatar, findViewById(R.id.NPC1)) && Data.introBattleDone != true) {

                        //Bounce the avatar back and disable the Listener so you can't spam the battle square while it's still active
                        float x = avatar.getX();
                        x -= 30;
                        avatar.setX(x);
                        //leftArrow.setEnabled(false);

                        //Play the start battle sound effect
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.startbattle);
                        player2.start();

                        //Set all the necessary Data values
                        Data.monsters.add(Data.alice);
                        Data.background = R.drawable.overworldbattle1;
                        Data.location = getClass();
                        Data.screenNumber = 1;
                        Data.inBattle = true;

                        //Start the battle
                        player.stop();
                        startActivity(new Intent(Main3Activity.this, IntroBattle.class));
                        player2 = MediaPlayer.create(Main3Activity.this, R.raw.sound2);
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
