package com.example.trackerczasu;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeFormatTest<e> {

    @Test
    public void HMSDurationTestHour(){
        try{
            TimeFormat t = new TimeFormat();
            assertNotNull(t);
            assertEquals(t.HMSDuration(37230), "10:20:30");
        } catch(Exception e){
            System.err.println("Error!");
            System.exit(2);
        }
    }
    @Test
    public void HMSDurationTestNotHour(){
        try{
            TimeFormat t = new TimeFormat();
            assertNotNull(t);
            assertEquals(t.HMSDuration(1230), "20:30");
        } catch(Exception e){
            System.err.println("Error!");
            System.exit(3);
        }
    }
}