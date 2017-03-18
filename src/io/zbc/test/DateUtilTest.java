package io.zbc.test;

import java.text.ParseException;
import java.util.Date;

import io.zbc.util.DateUtil;

public class DateUtilTest {

    public static class TestSimpleDateFormatThreadSafe extends Thread {
        private Date date = new Date();

        @Override
        public void run() {
            while (true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName() + ":" + DateUtil.parseTime("17:00:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new TestSimpleDateFormatThreadSafe().start();
        }

    }
}