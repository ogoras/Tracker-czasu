package com.example.lotto;
import android.graphics.Color;

import java.util.Random;

public class RandGen
{
    public static int generateRandomIntInRange(int min, int max) throws WrongRangeException
    {
        if(min>=max)
            throw new WrongRangeException();
        else
        {
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }
    }

    public static int generateRandomColor()
    {
        Random r = new Random();
        int a = r.nextInt(255);
        int b = r.nextInt(255);
        int c = r.nextInt(255);
        return Color.rgb(a,b,c);
    }

    public static int[] generateColorsVector(int amount)
    {
        int [] vec = new int [amount];
        for(int i=0; i<amount; i++)
        {
            vec[i] = RandGen.generateRandomColor();
        }
        return vec;
    }
}

