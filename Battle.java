package com.example.zniannar.measureisland;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.measureisland.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.zniannar.measureisland.Data.currentProblemIndex;
import static com.example.zniannar.measureisland.Data.monsters;

public class Battle extends AppCompatActivity {

    MediaPlayer player, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        //To initiate number pad:
        //make the necessary objects
        EditText editText = (EditText) findViewById(R.id.battle_email);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        (findViewById(R.id.battle_layout)).setBackgroundResource(Data.background);

        //Put the enemies' names on the Ticker
        if (Data.monsters.size() == 1)
        ((TextView)findViewById(R.id.ticker)).setText(monsters.get(0).name + " is attacking!");

        if (Data.monsters.size() == 2)
            ((TextView)findViewById(R.id.ticker)).setText(monsters.get(0).name + " and " + monsters.get(1).name + " are attacking!");

        if (Data.monsters.size() == 3)
            ((TextView)findViewById(R.id.ticker)).setText(monsters.get(0).name + ", " + monsters.get(1).name + ", and " + monsters.get(2).name + " are attacking!");



        //If the special moves have been unlocked, show it
        if (Data.foundJockSpecialPower == true)
            ((Button)findViewById(R.id.jockSpecialMove)).setText("DAB");

        if (Data.foundMusicianSpecialPower == true)
            ((Button)findViewById(R.id.musicianSpecialMove)).setText("SING");

        if (Data.foundBookwormSpecialPower == true)
            ((Button)findViewById(R.id.bookwormSpecialMove)).setText("READ");

        if (Data.foundArtistSpecialPower == true)
            ((Button)findViewById(R.id.artistSpecialMove)).setText("DRAW");


        //Display the monsters
        if (monsters.size() == 1)
            ((ImageView)(findViewById(R.id.monsterC))).setImageResource(monsters.get(0).imageResource);
        else if (monsters.size() == 2){
            ((ImageView)(findViewById(R.id.monsterL))).setImageResource(monsters.get(0).imageResource);
            ((ImageView)(findViewById(R.id.monsterR))).setImageResource(monsters.get(1).imageResource);
        }
        else{
            ((ImageView)(findViewById(R.id.monsterL))).setImageResource(monsters.get(0).imageResource);
            ((ImageView)(findViewById(R.id.monsterC))).setImageResource(monsters.get(1).imageResource);
            ((ImageView)(findViewById(R.id.monsterR))).setImageResource(monsters.get(2).imageResource);
        }

        //Start the music
        player = MediaPlayer.create(Battle.this, monsters.get(0).songResource);
        player.setLooping(true);
        player.start();

        //Set the "current" variables
        Data.currentPlayer = changeCurrentPlayer(Data.artistName);

        //Determines the first problem (and answer) index #
        Data.currentProblemIndex = (int)(Math.random()*(monsters.get(0).problems.length));
        String currentProblem = monsters.get(0).problems[Data.currentProblemIndex];
        Data.currentAnswer = monsters.get(0).answers[Data.currentProblemIndex];

        //Display the current problem
        if (monsters.size() == 1)
            ((TextView)(findViewById(R.id.problemC))).setText(currentProblem);
        if (monsters.size() == 2)
            ((TextView)(findViewById(R.id.problemL))).setText(currentProblem);
        if (monsters.size() == 3)
            ((TextView)(findViewById(R.id.problemL))).setText(currentProblem);

/*
        //Start the Chronometers
        if (monsters.size() == 1) {
            ((Chronometer) (findViewById(R.id.timerC))).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) (findViewById(R.id.timerC))).start();
        }
        if (monsters.size() == 2) {
            ((Chronometer) (findViewById(R.id.timerL))).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) (findViewById(R.id.timerL))).start();
            ((Chronometer) (findViewById(R.id.timerR))).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) (findViewById(R.id.timerR))).start();
        }
        if (monsters.size() == 3) {
            ((Chronometer) (findViewById(R.id.timerC))).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) (findViewById(R.id.timerC))).start();
            ((Chronometer) (findViewById(R.id.timerL))).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) (findViewById(R.id.timerL))).start();
            ((Chronometer) (findViewById(R.id.timerR))).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) (findViewById(R.id.timerR))).start();
        }
*/

        final EditText input = findViewById(R.id.battle_email);
        Button enter = (Button) findViewById(R.id.button_enter);

        final ImageView hpbars = findViewById(R.id.hpbars);

        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String playerAnswer = input.getText().toString().trim();
                final boolean gotItRight = playerAnswer.equals(Data.currentAnswer); //We gotta define this here before we set answer to the next problem's answer
                final Monster currentMonster = monsters.get(Data.currentMonster);
                final String player = Data.currentPlayer;
                final String problem = currentMonster.problems[currentProblemIndex];
                Data.currentAnswer = currentMonster.answers[currentProblemIndex];

                //If your Level is 5, then you should have a 5% chance of your "bonus" working, unless you're the Jock
                int luckyNumber = (int)(Math.random() * 100);

                //Honestly, this factor only exists so that the Bookworm can have an advantage and so I don't have to deal with Chronometers
                boolean youWillMiss = luckyNumber > 30 && luckyNumber < 60;

                //The bookworm's advantage. Right now, she'll never miss anyway, so there's no point in leveling her up :)
                if (player.equals(Data.bookwormName))
                    youWillMiss = false;

                //if the answer is correct
                if (gotItRight && !youWillMiss) {
                    //Play the hit sound effect
                    player2 = MediaPlayer.create(Battle.this, R.raw.swordhit);
                    player2.start();

                    //To determine how much damage is dealt at first
                    int damageIndex = (int)(Math.random() * Data.hitRange.length);
                    int damage = Data.hitRange[damageIndex];
                    //Add bonus damage from the Chronometer (WTF IS GOING ON HERE?????????????????????)
                    /*if (monsters.size() == 0) {
                        if (SystemClock.elapsedRealtime() - (((Chronometer)(findViewById(R.id.timerC)))).getBase() < 1000)
                            damage += 5;
                    }
                    */
                    //Add bonus damage if you're the Jock
                    if (player.equals(Data.jockName)) {
                        int jockBonusIndex = (int)(Math.random() * Data.jockBonusRange.length);
                        damage += (Data.jockBonusRange[jockBonusIndex] * Data.jockLevel);
                    }
                    //Display the damage that's been dealt
                    ((TextView) (findViewById(R.id.ticker))).setText(player + " dealt " + damage + " damage to " + currentMonster.name + "!");


                    //Account for the damage dealt to the monster
                    currentMonster.hp -= damage;


                    //Bonus if you're the Artist
                    if (Data.artistLevel >= luckyNumber){
                        Data.jockHP += 10;
                        Data.musicianHP += 10;
                        Data.bookwormHP += 10;
                        Data.artistHP += 10;
                    }

                    //If this monster is now dead, display the Out of Order Sign in its place and change the current monster. End fight if this is last monster.
                    if (monsters.size() == 1 && currentMonster.hp <= 0) {
                        ((ImageView) findViewById(R.id.monsterC)).setImageResource(R.drawable.monsterdead);
                        endFight();
                    }
                    if (monsters.size() == 2 && currentMonster.hp <= 0 && Data.currentMonster == 0) {
                        ((ImageView) findViewById(R.id.monsterL)).setImageResource(R.drawable.monsterdead);
                       // ((Chronometer)(findViewById(R.id.timerL))).stop();
                        Data.currentMonster ++;
                    }
                    if (monsters.size() == 2 && currentMonster.hp <= 0 && Data.currentMonster == 1) {
                        ((ImageView) findViewById(R.id.monsterR)).setImageResource(R.drawable.monsterdead);
                        endFight();
                    }
                    if (monsters.size() == 3 && currentMonster.hp <= 0 && Data.currentMonster == 0) {
                        ((ImageView) findViewById(R.id.monsterL)).setImageResource(R.drawable.monsterdead);
                        //((Chronometer)(findViewById(R.id.timerL))).stop();
                        Data.currentMonster ++;
                    }
                    if (monsters.size() == 3 && currentMonster.hp <= 0 && Data.currentMonster == 1) {
                        ((ImageView) findViewById(R.id.monsterC)).setImageResource(R.drawable.monsterdead);
                     //   ((Chronometer)(findViewById(R.id.timerC))).stop();
                        Data.currentMonster ++;
                    }
                    if (monsters.size() == 3 && currentMonster.hp <= 0 && Data.currentMonster == 2) {
                        ((ImageView) findViewById(R.id.monsterR)).setImageResource(R.drawable.monsterdead);
                        endFight();
                    }

                }//This is the end of the if block (the code to run if the right answer is entered)
                else  //If the player entered the wrong answer
                {

                    //Play the hit sound effect
                    player2 = MediaPlayer.create(Battle.this, R.raw.punch);
                    player2.start();

                    //To determine how much damage is dealt to the player
                    int damageIndex = (int)(Math.random() * currentMonster.hitRange.length);
                    int damage = currentMonster.hitRange[damageIndex];
                    ((TextView) (findViewById(R.id.ticker))).setText("The monster dealt " + damage + " damage to " + player + "!");

                    //Account for the damage and update hp bars
                    if (player.equals(Data.jockName)) {
                        Data.jockHP -= damage;
                        if (Data.jockHP < 0)
                            Data.jockHP = 0;
                        ((TextView)(findViewById(R.id.jockHP))).setText("" + Data.jockHP);
                    }
                    if (player.equals(Data.musicianName) && Data.musicianLevel < luckyNumber) {
                        Data.musicianHP -= damage;
                        if (Data.musicianHP < 0)
                            Data.musicianHP = 0;
                        ((TextView)(findViewById(R.id.musicianHP))).setText("" + Data.musicianHP);
                    }
                    if (player.equals(Data.bookwormName)) {
                        Data.bookwormHP -= damage;
                        if (Data.bookwormHP < 0)
                            Data.bookwormHP = 0;
                        ((TextView)(findViewById(R.id.bookwormHP))).setText("" + Data.bookwormHP);
                    }
                    if (player.equals(Data.artistName)) {
                        Data.artistHP -= damage;
                        if (Data.artistHP < 0)
                            Data.artistHP = 0;
                        ((TextView)(findViewById(R.id.artistHP))).setText("" + Data.artistHP);
                    }

                } //This is the end of the else block (the code to run if a wrong answer is entered)

                //Change the current problem, and thus the answer
                Data.currentProblemIndex = (int)(Math.random()*(currentMonster.problems.length));

                //Change the current party member
                Data.currentPlayer = changeCurrentPlayer(Data.currentPlayer);

                //Change the hpbars ImageView resource to move the ruler sword
                if (Data.currentPlayer.equals(Data.jockName))
                    hpbars.setImageResource(R.drawable.hpbars1);
                if (Data.currentPlayer.equals(Data.musicianName))
                    hpbars.setImageResource(R.drawable.hpbars2);
                if (Data.currentPlayer.equals(Data.bookwormName))
                    hpbars.setImageResource(R.drawable.hpbars3);
                if (Data.currentPlayer.equals(Data.artistName))
                    hpbars.setImageResource(R.drawable.hpbars4);

                //Display the next problem on the screen, whether you got the last one right or wrong
                if (monsters.size() == 1)
                    ((TextView)(findViewById(R.id.problemC))).setText(problem);
                if (monsters.size() == 2 && Data.currentMonster == 0)
                    ((TextView)(findViewById(R.id.problemL))).setText(problem);
                if (monsters.size() == 2 && Data.currentMonster == 1)
                    ((TextView)(findViewById(R.id.problemR))).setText(problem);
                if (monsters.size() == 3 && Data.currentMonster == 0)
                    ((TextView)(findViewById(R.id.problemL))).setText(problem);
                if (monsters.size() == 3 && Data.currentMonster == 1)
                    ((TextView)(findViewById(R.id.problemC))).setText(problem);
                if (monsters.size() == 3 && Data.currentMonster == 2)
                    ((TextView)(findViewById(R.id.problemR))).setText(problem);


                //Clear the user input
                input.setText("");

            }  //This is the end of the onClick method for ENTER

        });

        /*
        THIS IS JUST TO REMIND YOU TO USE THE BOOLEANS battle1Done, battle2Done, etc. IN ORDER TO
        HAVE INVISIBLE SHAPES ON THE SCREENS THAT ARE INACTIVE ONCE THE BOOLEANS ARE TRUE.
        Also, note that due to the structure of this class, you should make all changes to the monsters ArrayList from outside the class before you open the Activity
        */


    }

    public String changeCurrentPlayer(String currentPlayer){

        boolean gameOver = Data.jockHP <= 0 && Data.musicianHP <= 0 && Data.bookwormHP <= 0 && Data.artistHP <= 0;

        if (currentPlayer.equals(Data.jockName)){
            if (Data.musicianHP > 0)
                currentPlayer = Data.musicianName;
            else if (Data.bookwormHP > 0)
                currentPlayer = Data.bookwormName;
            else if (Data.artistHP > 0)
                currentPlayer = Data.artistName;
            else if (gameOver){
                player.stop();
                monsters.clear();
                startActivity(new Intent(Battle.this, GameOver.class));
            }

            return currentPlayer;
        }
        if (currentPlayer.equals(Data.musicianName)) {
            if (Data.bookwormHP > 0)
                currentPlayer = Data.bookwormName;
            else if (Data.artistHP > 0)
                currentPlayer = Data.artistName;
            else if (Data.jockHP > 0)
                currentPlayer = Data.jockName;
            else if (gameOver){
                player.stop();
                monsters.clear();
                startActivity(new Intent(Battle.this, GameOver.class));
            }

            return currentPlayer;
        }
        if (currentPlayer.equals(Data.bookwormName)) {
            if (Data.artistHP > 0)
                currentPlayer = Data.artistName;
            else if (Data.jockHP > 0)
                currentPlayer = Data.jockName;
            else if (Data.musicianHP > 0)
                currentPlayer = Data.musicianName;
            else if (gameOver){
                player.stop();
                monsters.clear();
                startActivity(new Intent(Battle.this, GameOver.class));
            }

            return currentPlayer;
        }
        if (currentPlayer.equals(Data.artistName)) {
            if (Data.jockHP > 0)
                currentPlayer = Data.jockName;
            else if (Data.musicianHP > 0)
                currentPlayer = Data.musicianName;
            else if (Data.bookwormHP > 0)
                currentPlayer = Data.bookwormName;
            else if (gameOver){
                player.stop();
                monsters.clear();
                startActivity(new Intent(Battle.this, GameOver.class));
            }

            return currentPlayer;
        }

        return currentPlayer;
    }

    //This method is supposed to show the end stats and reset everything so that the next battle can run smoothly
    public void endFight(){

        Data.inBattle = false;

        ((TextView)(findViewById(R.id.ticker))).setText("Hooray! You defeated the monster.");
        player.stop();
        if (monsters.get(0).imageResource == R.drawable.seaboss || monsters.get(0).imageResource == R.drawable.lakeboss || monsters.get(0).imageResource == R.drawable.fireboss || monsters.get(0).imageResource == R.drawable.skyboss || monsters.get(0).imageResource == R.drawable.spaceboss)
        player = MediaPlayer.create(Battle.this, R.raw.battlebossvictory);
        else
            player = MediaPlayer.create(Battle.this, R.raw.battlevictory);
        player.start();

        Data.currentMonster = 0;
        for (int i = 0; i < monsters.size(); i++)
            monsters.get(i).hp = monsters.get(i).originalhp;
        monsters.clear();

        if (Data.jockHP > 0){
            Data.jockXP++;
            ((TextView) (findViewById(R.id.jockXP))).setText("" + Data.jockXP);
        }
        if (Data.bookwormHP > 0){
            Data.bookwormXP++;
            ((TextView) (findViewById(R.id.bookwormXP))).setText("" + Data.bookwormXP);
        }
        if (Data.musicianHP > 0){
            Data.musicianXP++;
            ((TextView) (findViewById(R.id.musicianXP))).setText("" + Data.musicianXP);
        }
        if (Data.artistHP > 0){
            Data.artistXP++;
            ((TextView) (findViewById(R.id.artistXP))).setText("" + Data.artistXP);
        }

        if (Data.jockXP >= 20){
            ((TextView)(findViewById(R.id.ticker))).append(" " + Data.jockName + "'s leveling up!");
            Data.jockXP = 0;
            Data.jockLevel++;
            Data.maxHP += 2;
        }

        if (Data.musicianXP >= 20){
            ((TextView)(findViewById(R.id.ticker))).append(" " + Data.musicianName + "'s leveling up!");
            Data.musicianXP = 0;
            Data.musicianLevel++;
            Data.maxHP += 2;
        }

        if (Data.bookwormXP >= 20){
            ((TextView)(findViewById(R.id.ticker))).append(" " + Data.bookwormName + "'s leveling up!");
            Data.bookwormXP = 0;
            Data.bookwormLevel++;
            Data.maxHP += 2;
        }

        if (Data.artistXP >= 20){
            ((TextView)(findViewById(R.id.ticker))).append(" " + Data.artistName + "'s leveling up!");
            Data.artistXP = 0;
            Data.artistLevel++;
            Data.maxHP += 2;
        }

        if (Data.introBattleDone == false){
            ((TextView)(findViewById(R.id.ticker))).append(" Every character who's alive just earned 1 XP. If a character collects 20 XP, they'll Level Up.");
            ((TextView)(findViewById(R.id.problemL))).setText("Each character levels up differently. The Jock deals more damage when he hits the monster.");
            ((TextView)(findViewById(R.id.problemC))).setText("The Musician dodges more attacks. The Bookworm is less likely to miss.");
            ((TextView)(findViewById(R.id.problemR))).setText("And the Artist might heal everyone when she gets problems right. You can view stats in the MENU.");
        }

        Data.introBattleDone = true;

        ((Button)findViewById(R.id.exitBattleButton)).setText("CONTINUE");
        ((Button)findViewById(R.id.exitBattleButton)).setTextColor(Color.parseColor("#ff2b2b"));
    }


    public void exitBattleScreen(View view){
        if (Data.inBattle == true){
            return;
        }

        player.stop();

        switch (Data.screenNumber) {
            case 1:
            startActivity(new Intent(Battle.this, Main3Activity.class));
            break;
            case 2:
                Data.battle2Done = true;
                startActivity(new Intent(Battle.this, Overworld2.class));
                break;
            case 3:
                Data.battle3Done = true;
                startActivity(new Intent(Battle.this, Overworld3.class));
                break;
            case 4:
                Data.battle4Done = true;
                startActivity(new Intent(Battle.this, Overworld4.class));
                break;
            case 10:
                Data.battle10Done = true;
                startActivity(new Intent(Battle.this, Overworld10.class));
                break;
            case 13:
                Data.battle13Done = true;
                startActivity(new Intent(Battle.this, Overworld13.class));
                break;
            case 14:
                Data.battle14Done = true;
                startActivity(new Intent(Battle.this, Overworld14.class));
                break;
            case 15:
                Data.battle15Done = true;
                startActivity(new Intent(Battle.this, Overworld15.class));
                break;
            case 16:
                Data.battle16Done = true;
                startActivity(new Intent(Battle.this, Overworld16.class));
                break;
            case 17:
                Data.battle17Done = true;
                startActivity(new Intent(Battle.this, Overworld17.class));
                break;
            case 18:
                Data.battle18Done = true;
                startActivity(new Intent(Battle.this, Overworld18.class));
                break;
            case 19:
                Data.battle19Done = true;
                startActivity(new Intent(Battle.this, Overworld19.class));
                break;
            case 20:
                Data.killedSeaBoss = true;
                startActivity(new Intent(Battle.this, Overworld20.class));
                break;
            case 63:
                //startActivity(new Intent(Battle.this, Overworld63.class));
                break;
        }
    }

    public void deleteText(View v){
        ((EditText) v).setText("");
    }

    //THE IMAGES WILL NOT BE RANDOM. THAT IS TOO FUCKING TEDIOUS.

}
