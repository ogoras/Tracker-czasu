package com.example.trackerczasu;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TActivityTest {

    @Test
    public void getDurationTest()  {
        TActivity t = new TActivity("stare", -1500, 1500);
        Assert.assertNotNull(t);
        Assert.assertEquals(3000, t.getDuration());
        try {
            t = new TActivity("nowe");
            Assert.assertNotNull(t);
            Assert.assertTrue(t.getDuration() >= 0);
            Thread.sleep(1000);
            Assert.assertTrue(t.getDuration() >= 1);
        }
        catch (InterruptedException e){
            System.err.println("Error!");
            System.exit(3);
        }
    }

    @Test
    public void getDurationTest2(){
        try{
            TActivity t = new TActivity("abc", 37200, 37230);
            Assert.assertNotNull(t);
            Assert.assertEquals(t.getDuration(), (long) 30);
        } catch(Exception e){
            System.err.println("Error!");
            System.exit(2);
        }
    }

    @Test
    public void testClone() {
        try {
            TActivity t = new TActivity("type", 1000, 2000);
            Assert.assertNotNull(t);
            t.tag = "tag";
            t.comment = "comment";
            TActivity c = (TActivity)t.clone();
            Assert.assertNotNull(c);
            assertEquals(t.type, c.type);
            assertEquals(t.getDuration(), c.getDuration());
            assertEquals(t.comment, c.comment);
            assertEquals(t.tag, c.tag);
            assertNotEquals(t, c);
        }
        catch (CloneNotSupportedException e) {
            System.err.println("Error!");
            System.exit(4);
        }
    }
}