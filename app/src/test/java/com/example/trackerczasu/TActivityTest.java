package com.example.trackerczasu;

import org.junit.Test;

import static org.junit.Assert.*;

public class TActivityTest {

    @Test
    public void insertActivityTest(){
        try{
            TActivity t = new TActivity("abc", 37200, 37230);
            assertNotNull(t);
            assertEquals(t.getDuration(), (long) 30);
        } catch(Exception e){
            System.err.println("Error!");
            System.exit(2);
        }
    }
}