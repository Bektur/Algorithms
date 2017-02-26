import java.lang.System;
import java.lang.reflect.Array;
import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        int[] points = new int[2 * segments.length];
        int[] pointsToReturn = new int[2 * segments.length];
        for (int i = 0; i < segments.length; i++) {
            points[2 * i] = segments[i].start;
            points[2 * i + 1] = segments[i].end;
        }
        int small = 1;
        int large = 1;
        for (int i=0; i < segments.length; i++)
        {
            if (points[2*i+1] < points[small])
                small = 2*i+1;
            if (points[2*i+1] > points[large])
                large = 2*i+1;
        }
        int i = 0;
        while (i < segments.length)
        {
            pointsToReturn[2*i] = points[2 * i];
            pointsToReturn[2*i+1] = points[2*i+1];
            i++;
            while (i < segments.length && points[2*i] <= points[2*i+1])
                i++;
        }
        return pointsToReturn;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
        System.out.println();
    }
}
 
