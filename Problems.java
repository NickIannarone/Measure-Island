package com.example.zniannar.measureisland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nick.measureisland.R;

import java.util.ArrayList;

public class Problems extends AppCompatActivity {

    /*
    NAMING SCHEME: t1 = Type 1 (the specific type is given in a comment above each group), d1 = Difficulty (1 is the easiest)
    */

    //Type 1 = General (+ - x ÷) for use in Overworld
    public static String[] t1d1 = {"1 + 1", "2 - 1", "4 x 3", "28 ÷ 4", "5 x 5", "3 - 3", "3 + 7", "4 x 2", "18 ÷ 2", "18 ÷ 6", "9 + 7"};
    public static String[] t1d1A = {"2", "1", "12", "7", "25", "0", "10", "8", "9", "3", "16"}; //If there's an A next to the name, this is an Answers list.

    //Type 2 = Shape Shore
    public static String[] t2d1 = {"How many more sides does a rectangle have than a triangle?", "What is the permieter of a fence with four 3 cm sides?", "What is the permieter of a square with four 8-inch sides?", "A fence has three 3-yard sides and two 5-yard sides. What's the perimeter?", "What is the area of a swimming pool that's 4 feet long and 7 feet wide?", "What is the area of a swimming pool that's 10 feet long and 3 feet wide?", "What is the area of a swimming pool that's 22 feet long and 0 feet wide?", "How many sides does a hexagon have?", "What is the volume of a cube whose length, width, and height are all 2 cm?"};
    public static String[] t2d1A = {"1", "12", "32", "19", "28", "30", "0", "6", "8"};

    public static String[] t2d2 = {"For an octagon to become a pentagon, how many sides must be taken away?", "How many sides does a trapezoid have?", "A fence has two 7-foot sides and four 2-foot sides. What's the perimeter?", "A fence's side lengths are as follows: 1, 2, 3, 4, 5, 6. What's the perimeter?", "A swimming pool has width 5 feet, height 7 feet, and length 2 feet. What's its volume?", "A pool has a 2-by-2 surface, and it's 8 feet deep. What is its volume?", "What is the area of a square whose sides are all 10 cm?", "What is the perimeter of a square whose sides are all 10 cm?"};
    public static String[] t2d2A = {"3", "4", "22", "21", "70", "32", "100", "40"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
    }
}










