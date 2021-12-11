package se.nyquist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataHandler {

    enum Category {
        INCREASED, DECREASED, UNCHANGED
    }
    private Map<Category,Integer> stats = new HashMap<>();
    private int nextToChange = 0;
    private int[] values = new int[] { 0, 0, 0};
    private int prevSum = -1;
    private int windowsSize = 0;

    public DataHandler() {
        stats.put(Category.INCREASED, 0);
        stats.put(Category.DECREASED, 0);
        stats.put(Category.UNCHANGED, 0);
    }

    public void add(int depth) {
        values[nextToChange] = depth;
        System.out.println(Arrays.stream(values).mapToObj(Integer::toString).collect(Collectors.joining(",", "[", "]")));
        int sum = Arrays.stream(values).sum();
        if (windowsSize<3) {
            windowsSize++;
        }
        if (windowsSize == 3) {
            if (prevSum < 0) {
                System.out.println(sum + " (N/A - no previous sum)");
            } else if (prevSum == sum) {
                System.out.println(sum + " (unchanged)");
                stats.put(Category.UNCHANGED, stats.get(Category.UNCHANGED) + 1);
            } else if (prevSum < sum) {
                System.out.println(sum + " (increased)");
                stats.put(Category.INCREASED, stats.get(Category.INCREASED) + 1);
            } else if (prevSum > sum) {
                System.out.println(sum + " (decreased)");
                stats.put(Category.DECREASED, stats.get(Category.DECREASED) + 1);
            }
            prevSum = sum;
        }
        nextToChange = (nextToChange+1) % 3;
    }
    public void printStats() {
        System.out.println("Unchanged: " + stats.get(Category.UNCHANGED));
        System.out.println("Increased: " + stats.get(Category.INCREASED));
        System.out.println("Decreased: " + stats.get(Category.DECREASED));
    }
}
