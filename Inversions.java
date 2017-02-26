import java.lang.Integer;
import java.lang.System;
import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class Inversions {

    private static int counter = 0;

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        return numberOfInversions;
    }

    public static long mergeSort(int[] A) {
        int[] B = new int[A.length];
        return mergeSort(A, B, 0, A.length-1);
    }

    private static long mergeSort(int[] A, int[] B, int left, int right) {
        if (right <= left)
            return 0;
        long inversions = 0;
        int mid = left + (right-left) / 2;

        inversions += mergeSort(A, B, left, mid);
        inversions += mergeSort(A, B, mid+1, right);

        inversions += merge(A, B, left, mid, right);

        return inversions;
    }

    private static long merge(int[] A, int[] B, int left, int mid, int right) {
        long inversions = 0;

        for (int i=0; i <= right; i++) {
            B[i] = A[i];
        }
        int i = left;
        int j = mid+1;
        int k = left;
        while (k <= right) {
            if (i > mid) {
                A[k] = B[j++];
            }
            else if (j > right){
                A[k] = B[i++];
            }
            else if (B[j] < B[i]) {
                A[k] = B[j++];
                inversions += (mid-i+1);
            }
            else {
                A[k] = B[i++];
            }
            k++;
        }

        return inversions;
    }


    public static boolean isSorted(int[] A) {
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i-1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        int[] a = new int[100000];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[a.length];
        //System.out.println(getNumberOfInversions(a, b, 0, a.length));
        long inversions = Inversions.mergeSort(a);
        for (int i=0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n" + inversions);
        //System.out.println("\n" + counter);
    }
}

