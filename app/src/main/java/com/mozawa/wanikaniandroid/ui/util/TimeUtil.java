package com.mozawa.wanikaniandroid.ui.util;

public class TimeUtil {

    /**
     * Modified utility class based off the Google I/O 2012 app.
     */

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public static String getTimeUntil(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time <= 0) {
            return null;
        }

        final long diff = time - now;
        if (diff < MINUTE_MILLIS) {
            return "now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "in 1 minute";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return "in " + diff / MINUTE_MILLIS + " minutes";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "in an hour";
        } else if (diff < 24 * HOUR_MILLIS) {
            return "in " + diff / HOUR_MILLIS + " hours";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "tomorrow";
        } else {
            return "in " + diff / DAY_MILLIS + " days";
        }
    }

}
