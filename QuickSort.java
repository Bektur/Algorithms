import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.util.StringTokenizer;

public class QuickSort {

    //private static PrintWriter out;
    //private int[] arrayToSort;
    private static int comparisons = 0;

    public static void main(String[] args) throws IOException {
        // write your code here
        FastScanner scanner = new FastScanner(System.in);
        //int n = scanner.nextInt();
        int[] array = new int[10000];
        for (int i=0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        QuickSort.sort(array);
        assert isSorted(array, 0, array.length);
        printOut(array);
        System.out.println(comparisons);
    }

    private static boolean isSorted(int[] A, int left, int right) {
        for (int i = left+1; i <= right; i++) {
            if (A[i-1] > A[i])
                return false;
        }
        return true;
    }

    public static void sort(int[] A) {
        sort(A, 0, A.length-1);
    }

    private static void sort(int[] A, int left, int right) {
        if (left >= right)
            return;
        //comparisons += (hi-1);
        int size = right-left;
        int middle = left + (size)/2;
        if (size % 2 != 0) {
            //middle += 1;
        }

        int median = Math.max(Math.min(A[left], A[middle]), Math.min(Math.max(A[left], A[middle]), A[right]));
        if (median == A[middle]) {
            int temp = A[left];
            A[left] = A[middle];
            A[middle] = temp;
        }
        else if (median == A[right]) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
        }
        int j = partition(A, left, right);
        sort(A, left, j-1);
        sort(A, j+1, right);
    }

    private static int partition(int[] A, int left, int right) {
        /*
        int temp1 = A[left];
        A[left] = A[right];
        A[right] = temp1;
        */

        int pivot = A[left];
        int i = left + 1;
        int subArray = right - left;
        comparisons += subArray;

        for (int j = left + 1; j <= right; j++) {
            //comparisons++;
            if (A[j] < pivot) {
                if (j != i) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
                i++;
            }
        }
        //comparisons -= 1;
        int temp = A[left];
        A[left] = A[i - 1];
        A[i - 1] = temp;

        return (i-1);
    }

    private static void printOut(int[] A) {
        for (int i=0; i < A.length; i++)
            System.out.print(A[i] + " ");
        System.out.println();
    }

    /*public void write() {
        arrayToSort = new int[10000];
        for (int i=0; i < arrayToSort.length; i++) {
            arrayToSort[i] = in.nextInt();
        }
    }

    public void output() {
        for (int i=0; i < arrayToSort.length; i++) {
            out.print(arrayToSort[i] + " ");
        }
    }

    public void solve() {
        write();
        sort(arrayToSort, 0, arrayToSort.length);
        output();
    }
    */

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
