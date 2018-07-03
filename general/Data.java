package com.example.zniannar.measureisland;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.nick.measureisland.R;

import java.util.ArrayList;

public class Data extends AppCompatActivity {

    //Character info and stats
    public static String jockName = "jock";
    public static String bookwormName = "bookworm";
    public static String musicianName = "musician";
    public static String artistName = "artist";

    public static int jockHP = 50;
    public static int bookwormHP = 50;
    public static int musicianHP = 50;
    public static int artistHP = 50;

    //NOTE: THESE SHOULD NEVER, EVER GO BACK TO 0 AFTER THE START OF THE GAME! I'm using these as a condition to remove defaults in the overworld that should only appear at the start.
    public static int jockXP = 0;
    public static int bookwormXP = 0;
    public static int musicianXP = 0;
    public static int artistXP = 0;

    public static int jockIQ = 0;
    public static int bookwormIQ = 0;
    public static int musicianIQ = 0;
    public static int artistIQ = 0;

    public static int jockLevel = 1;
    public static int bookwormLevel = 1;
    public static int musicianLevel = 1;
    public static int artistLevel = 1;

    public static int maxHP = 50;

    //The coordinates through which the avatar enters the current screen. Will be used to determine where the avatar appears on the current screen.
    public static int enterXAt = 100;
    public static int enterYAt = 100;

    //The timestamp of the background music player
    public static int playerTime = 0;

    //Battle info
    public static int[] jockBonusRange = new int[] {0, 0, 0, 0, 0, 1, 2, 3, 5};
    public static String currentPlayer = jockName;
    public static int currentMonster = 0;
    public static String currentAnswer = "";
    public static int currentProblemIndex = 0;
    public static int[] hitRange = new int[] {5, 6, 7, 8, 9, 10};
    public static ArrayList<Monster> monsters = new ArrayList<>();
    public static int monsterImage;
    public static int background;
    public static int screenNumber; //which Activity is this? your location in the game determines the problems we can choose.

    //Name of the current Activity you're in
    public static Class location;

    //Whenever you load a new screen, there are 1-4 invisible "regions" that initiate a battle if you step into them. These must go away once the battle's done.
    public static boolean battle1Done = false;
    public static boolean battle2Done = false;
    public static boolean battle3Done = false;
    public static boolean battle4Done = false;
    public static boolean battle10Done = false;
    public static boolean battle13Done = false;
    public static boolean battle14Done = false;
    public static boolean battle15Done = false;
    public static boolean battle16Done = false;
    public static boolean battle17Done = false;
    public static boolean battle18Done = false;
    public static boolean battle19Done = false;
    public static boolean inBattle = false;

    //There are going to be many one-time events throughout the game. These booleans ensure they don't repeat once they happen.
    public static boolean introBattleDone = false;
    public static boolean foundGhostKey = false;
    public static boolean haveMap = false;
    public static boolean haveLens = false;
    public static boolean haveCompass = false;
    public static boolean killedSeaBoss = false;
    public static boolean killedLakeBoss = false;
    public static boolean killedFireBoss = false;
    public static boolean killedSkyBoss = false;
    public static boolean killedSpaceBoss = false;
    public static boolean killedForestBoss = false;
    public static boolean killedBarBoss = false;
    public static boolean killedCaveBoss = false;
    public static boolean foundJockSpecialPower = false;
    public static boolean foundBookwormSpecialPower = false;
    public static boolean foundMusicianSpecialPower = false;
    public static boolean foundArtistSpecialPower = false;

    //All the monsters in the game! Stored right freaking here!!!
    public static Monster alice = new Monster("A Wild Alice", R.drawable.npcalice, R.raw.battlecowboy, 50, 50, Problems.t1d1, Problems.t1d1A, new int[]{5, 6, 7, 8, 9, 10, 5, 6, 7, 8});
    public static Monster seaBoss = new Monster("The Axolotl of Doom", R.drawable.seaboss, R.raw.seaboss, 50, 50, Problems.t2d2, Problems.t2d2A, new int[]{10, 11, 12, 13, 14, 15, 15, 15});
    public static Monster sea1 = new Monster("Sir Pinch-a-Lot", R.drawable.seacrab, R.raw.battlefunk, 20, 20, Problems.t2d1, Problems.t2d1A, new int[]{5, 6, 7, 8, 9, 10, 10, 10});
    public static Monster sea2 = new Monster("The Toilet Goldfish", R.drawable.seafish, R.raw.battlenewwave, 20, 20, Problems.t2d1, Problems.t2d1A, new int[]{5, 6, 7, 8, 9, 10, 10, 10});
    public static Monster sea3 = new Monster("The Dwarf Giant Squid", R.drawable.seaocto, R.raw.battlefunk, 20, 20, Problems.t2d1, Problems.t2d1A, new int[]{5, 6, 7, 8, 9, 10, 10, 10});
    public static Monster sea4 = new Monster("Peter Piranha", R.drawable.seapiranha, R.raw.battleboredumb, 20, 20, Problems.t2d1, Problems.t2d1A, new int[]{5, 6, 7, 8, 9, 10, 10, 10});
    public static Monster sea5 = new Monster("Evil Sewer Snail", R.drawable.seasnail, R.raw.battlevoodoo, 20, 20, Problems.t2d1, Problems.t2d1A, new int[]{5, 6, 7, 8, 9, 10, 10, 10});
    //SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
    }
}
