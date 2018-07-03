package com.example.zniannar.measureisland;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nick.measureisland.R;

//Go here if Start New Game is pressed

public class Main2Activity extends AppCompatActivity {

    int timeDuration = 0;
    MediaPlayer player, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        player = MediaPlayer.create(this, R.raw.maintheme);
        player.setLooping(true);
        player.seekTo(Data.playerTime);
        player.start();
    }

    public void continueGame(View v)
    {
        player2 = MediaPlayer.create(this, R.raw.sound2);
        player2.start();

        String name1 = ((EditText) findViewById(R.id.email)).getText().toString();
        String name2 = ((EditText) findViewById(R.id.email2)).getText().toString();
        String name3 = ((EditText) findViewById(R.id.email3)).getText().toString();
        String name4 = ((EditText) findViewById(R.id.email4)).getText().toString();

        if ((name1.length() > 8) || (name2.length() > 8) || (name3.length() > 8) || (name4.length() > 8))
        {
            String s = "Can't be longer than 8 letters.";
            TextView cat = findViewById(R.id.cat);
            cat.setText(s);
        }
        else
        {
            player.stop();
            Data.jockName = name1;
            Data.bookwormName = name2;
            Data.musicianName = name3;
            Data.artistName = name4;

            int time = player.getCurrentPosition();
            Data.playerTime = time;
            player.stop();
            startActivity(new Intent(Main2Activity.this, Main4Activity.class));
        }
    }

    public void deleteText(View v){
        ((EditText) v).setText("");
    }
}
