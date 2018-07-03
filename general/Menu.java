package com.example.zniannar.measureisland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nick.measureisland.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ((TextView) findViewById(R.id.menuName1)).setText(Data.jockName + ": ");
        ((TextView) findViewById(R.id.menuName2)).setText(Data.bookwormName + ": ");
        ((TextView) findViewById(R.id.menuName3)).setText(Data.musicianName + ": ");
        ((TextView) findViewById(R.id.menuName4)).setText(Data.artistName + ": ");

        ((TextView) findViewById(R.id.menuHP1)).setText("HP = " + Data.jockHP);
        ((TextView) findViewById(R.id.menuHP2)).setText("HP = " + Data.bookwormHP);
        ((TextView) findViewById(R.id.menuHP3)).setText("HP = " + Data.musicianHP);
        ((TextView) findViewById(R.id.menuHP4)).setText("HP = " + Data.artistHP);

        ((TextView) findViewById(R.id.menuXP1)).setText("XP = " + Data.jockXP);
        ((TextView) findViewById(R.id.menuXP2)).setText("XP = " + Data.bookwormXP);
        ((TextView) findViewById(R.id.menuXP3)).setText("XP = " + Data.musicianXP);
        ((TextView) findViewById(R.id.menuXP4)).setText("XP = " + Data.artistXP);

        ((TextView) findViewById(R.id.menuIQ1)).setText("IQ = " + Data.jockIQ);
        ((TextView) findViewById(R.id.menuIQ2)).setText("IQ = " + Data.bookwormIQ);
        ((TextView) findViewById(R.id.menuIQ3)).setText("IQ = " + Data.musicianIQ);
        ((TextView) findViewById(R.id.menuIQ4)).setText("IQ = " + Data.artistIQ);

        ((TextView) findViewById(R.id.maxHP)).setText("Max HP (what fountains bring everyone up to): " + Data.maxHP);

        ((TextView) findViewById(R.id.jockLevel)).setText(Data.jockName + " = Level " + Data.jockLevel + ", ");
        ((TextView) findViewById(R.id.bookwormLevel)).setText(Data.bookwormName + " = Level " + Data.bookwormLevel + ", ");
        ((TextView) findViewById(R.id.musicianLevel)).setText(Data.musicianName + " = Level " + Data.musicianLevel + ", ");
        ((TextView) findViewById(R.id.artistLevel)).setText(Data.artistName + " = Level " + Data.artistLevel);

        if (Data.haveMap == true){
            ((TextView)(findViewById(R.id.menuItem1))).setText("   Map   ");
        }
        if (Data.haveLens == true){
            ((TextView)(findViewById(R.id.menuItem2))).setText("   Lens   ");
        }
        if (Data.haveCompass == true){
            ((TextView)(findViewById(R.id.menuItem3))).setText("   Compass   ");
        }
        if (Data.killedSeaBoss == true){
            ((TextView)(findViewById(R.id.menuBoss1))).setText("   Sea");
        }
        if (Data.killedLakeBoss == true){
            ((TextView)(findViewById(R.id.menuBoss2))).setText("   Ice");
        }
        if (Data.killedFireBoss == true){
            ((TextView)(findViewById(R.id.menuBoss3))).setText("   Fire");
        }
        if (Data.killedSkyBoss == true){
            ((TextView)(findViewById(R.id.menuBoss4))).setText("   Sky");
        }
        if (Data.killedSpaceBoss == true){
            ((TextView)(findViewById(R.id.menuBoss5))).setText("   Space   ");
        }
    }

    public void exitMenu(View view){
        startActivity(new Intent(Menu.this, Data.location));
    }
}
