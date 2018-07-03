package com.example.zniannar.measureisland;

import java.util.ArrayList;

/**
 * Created by zniannar on 5/19/2018.
 */

public class Monster {

    public String name;
    public int imageResource;
    public int songResource;
    public int hp;
    public int originalhp;
    public String[] problems = new String[0];
    public String[] answers = new String[0];
    public int[] hitRange = new int[0];


    public Monster(String n, int i, int s, int h, int o, String[] p, String[] a, int[] hr){
        name = n;
        imageResource = i;
        songResource = s;
        hp = h;
        originalhp = o;
        problems = p;
        answers = a;
        hitRange = hr;
    }
}
