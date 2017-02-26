import java.io.*;
import java.lang.System;
import java.util.*;

/*
    This program is an implementation of 3-way partition of 
    randomized quick sort algorith to improve the processing time.
    It's Assignemnt 3 of UCSD Algorithm course.
 */

public class ThreeWayPartition {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
      //write your code here

        int x = a[l];
      int m1 = l;
        for (int i = l +1; i <= r; i++)
        {
            if (a[i] < x){
                m1++;
                int t = a[i];
                a[i] = a[m1];
                a[m1] = t;
            }
            //System.out.println("m1 " + m1);
        }
        int m3 = m1;
        for (int i = m1+1; i <= r; i++)
        {
            if (a[i] == x){
                m3++;
                int t = a[i];
                a[i] = a[m3];
                a[m3] = t;
            }
            //System.out.println("m3 " + m3);
        }
        //System.out.println("m3 " + m3);
        int m2 = m3;
        //System.out.println(m2);
      int[] m = {m1, m2};
      return m;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
            //System.out.println("j " + j);
        }
        //System.out.println("l " + l);
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m = partition2(a, l, r);
        int[] n = partition3(a, l, r);
        randomizedQuickSort(a, l, n[0]);
        randomizedQuickSort(a, n[1]+1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

