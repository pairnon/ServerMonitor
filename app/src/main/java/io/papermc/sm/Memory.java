package io.papermc.sm;

public class Memory {
    
    public static double getMemUsed() {

        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        double usedMemoryInMB = (double)usedMemory/1000000;
        return usedMemoryInMB;
    }

    public static double getMemTotal() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        double totalMemoryInMB = (double)totalMemory/1000000;
        return totalMemoryInMB;
    }

}
