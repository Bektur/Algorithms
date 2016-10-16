
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class CountInversions {


    public CountInversions() {
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        int[][] indices = new int[2*testCases][2];
        int v = 0;
        for (int i=0; i < testCases; i++) {
            int cost = scanner.nextInt();
            int sum = 0;
            int numElements = scanner.nextInt();
            int[] array = new int[numElements];
            for (int l = 0; l < array.length; l++) {
                array[l] = scanner.nextInt();
            }
            for (int j=0; j < array.length; j++) {
                for (int k=0; k < array.length; k++) {
                    if (j != k) {
                        sum = array[j] + array[k];
                        if (sum == cost) {
                            indices[v][0] = j+1;
                            indices[v][1] = k+1;
                            break;
                        }
                    }
                }
            }
            v++;
        }

        for (int k=0; k < testCases; k++) {
            System.out.println(indices[k][1] + " " + indices[k][0]);
        }
    }

    public static int[] Count_and_Sort(int[] A, int left, int right) {
        if (left+1 == right)
            return A;
        int mid = left + (right-left)/2;
        return merge(Count_and_Sort(A, left, mid), Count_and_Sort(A, mid+1, right));

    }

    public static int[] merge(int[] A, int[] B) {
        int[] C = new int[A.length+B.length];
        int i = 0;
        int j = 0;

        for (int k=0; k < C.length; k++) {
            if (A[i] < B[j]) {
                C[k] = B[i];
                i++;
            }
            else if (B[j] < A[i]) {
                C[k] = B[j];
                j++;
            }
        }
        return C;
    }

    public static String padLeft(String s, int n) {
        //return String.format("%1$" + n + "s", s);
        String paddedString = s;
        while (paddedString.length() < n) {
            paddedString = " " + paddedString;
        }

        return paddedString;
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