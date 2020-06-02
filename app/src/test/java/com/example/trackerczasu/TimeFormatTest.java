package com.example.trackerczasu;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeFormatTest<e> {

    @Test
    public void HMSDurationTestHour(){
        try{
            TimeFormat t = new TimeFormat();
            assertNotNull(t);
            assertEquals("10:20:30",t.HMSDuration(37230));
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
            assertEquals("20:30",t.HMSDuration(1230));
        } catch(Exception e){
            System.err.println("Error!");
            System.exit(3);
        }
    }

    @Test
    public void dayOfYear() {
        try{
            TimeFormat t = new TimeFormat();
            assertNotNull(t);
            assertEquals(1, t.dayOfYear(0)); //zaczynamy na 1 dniu wiec po 0 sekund nadal jest ten sam dzien
            assertEquals(1, t.dayOfYear(1)); //po 1 sekudzie mamy nadal 1. dzien
            assertEquals(1, t.dayOfYear(3600)); //minela godzina
            assertEquals(2, t.dayOfYear(3600*24)); //polnoc to juz nastepny dzien
            assertEquals(2, t.dayOfYear(3600*25)); //po 25 godzinach mamy juz nastepny dzien
            assertEquals(31, t.dayOfYear(3600*24*30)); //zaczyanmy od 1. dnia i po uplywie 30 dni mamy polnoc dnia 30. czyli dzien 31

        } catch(Exception e){
            System.err.println("Error!");
            System.exit(2);
        }
    }

    @Test
    public void dayAndMonth() {
        try{
            TimeFormat t = new TimeFormat();
            assertNotNull(t);
            assertEquals("01 sty", t.dayAndMonth(0)); //rok zaczyna sie 1 sty i po 0 oraz 1 sek nadal jest ten dzien
            assertEquals("01 sty", t.dayAndMonth(1));
            assertEquals("01 sty", t.dayAndMonth(3600)); //po godzinie
            assertEquals("02 sty", t.dayAndMonth(3600*24)); //po dobie do polnocy wiec nowy dzien
            assertEquals("02 sty", t.dayAndMonth(3600*25)); //po dobie i godzinie
            assertEquals("31 sty", t.dayAndMonth(3600*24*30)); //po 30 dniach po polnocy wiec 31 sty
            assertEquals("01 lut", t.dayAndMonth(3600*24*31)); //po 31 dniach
            assertEquals("27 gru", t.dayAndMonth(3600*24*30*12)); //po okolo roku
            assertEquals("26 sty", t.dayAndMonth(3600*24*30*13)); //po okolo roku i miesiacu, prawidlowo przechodzi w nastepny rok

        } catch(Exception e){
            System.err.println("Error!");
            System.exit(3);
        }
    }
}