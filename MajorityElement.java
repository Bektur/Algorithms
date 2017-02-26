import java.lang.Integer;
import java.lang.System;
import java.util.*;
import java.io.*;
import java.util.Hashtable;

public class MajorityElement {
    private static int first = -1;
    private static int second = -1;

    private static int getMajorityElement(int[] a, int left, int right) {
        int counter = 0;
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return a[left];
        }
        //write your code here
        int mid = left + (right - left) / 2;
        int melement1 = getMajorityElement(a, left, mid);
        int melement2 = getMajorityElement(a, mid+1, right);

        System.out.println(melement1 + " " + melement2);

        counter += merge(a, melement1, melement2, left, right);

        //System.out.println(counter);
        if (counter > a.length/2)
            return counter;
        else
            return -1;
    }

    private static int merge(int[] array, int melement1, int melement2, int left, int right) {
        int counter = 0;
        int counter2 = 0;

        if (melement1 != -1 && melement2 != -1) {
            if (melement1 == melement2)
                counter += (counter2+1);
        }

        if (melement1 != -1) {
            while (left <= right) {
                if (melement1 == array[left])
                    counter++;
                left++;
            }
        }
        if (melement2 != -1) {
            while (left <= right) {
                if (melement2 == array[left])
                    counter2++;
                left++;
            }
        }

        if (counter > counter2)
            return counter;
        else
            return counter2;
    }
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        /*if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }*/
        if (getMajorityElement(a, 0, a.length-1) != -1)
        {
            System.out.println(1);
        }
        else
        {
            System.out.println(0);
        }
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

