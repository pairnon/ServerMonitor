package io.papermc.sm;

import org.apache.commons.io.FileUtils;

// Class containing methods for memory calculations and formatting
public class MemoryCalc {
    
    public static String getMemUsed() {

        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        String formatted = FileUtils.byteCountToDisplaySize(usedMemory);
        return formatted;
    }

    public static String getMemTotal() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        String formatted = FileUtils.byteCountToDisplaySize(totalMemory);
        return formatted;
    }

    public static double getPercentMemUsed() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long memUsed = totalMemory - freeMemory;
        long memTotal = Runtime.getRuntime().totalMemory();
        return (double)memUsed / memTotal;
    }

}
