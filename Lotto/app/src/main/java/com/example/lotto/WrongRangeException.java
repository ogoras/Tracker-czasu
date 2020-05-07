package com.example.lotto;

public class WrongRangeException extends Exception
{
    public String toString()
    {
        return "WrongRangeException";
    }
    public String statement()
    {
        return "Please input correct numbers.";
    }
}
