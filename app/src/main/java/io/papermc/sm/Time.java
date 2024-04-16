package io.papermc.sm;

public class Time {
    
    public static long getTicksFromSeconds(int seconds) {
        return (long)seconds * 20;
    }

}
