import java.util.*;
import java.util.Arrays;

public class DotProduct {
    private static long minDotProduct(int[] a, int[] b) {
        //write your code here
        long result = 0;
        int n = a.length;
        if (n == 1)
            return a[0] * b[0];

        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < n; i++) {
            result += a[(n - 1) - i] * b[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int numerOfRotations = scanner.nextInt();
        int queries = scanner.nextInt();
        int[] m = new int[queries];
        int[] firstArray = new int[size];
        int[] secondArray = new int[size];

        for (int i = 0; i < size; i++) {
            firstArray[i] = scanner.nextInt();
        }

        for (int i=0; i < m.length; i++) {
            m[i] = scanner.nextInt();
        }

        if (numerOfRotations == size) {
            for (int i = 0; i < numerOfRotations; i++) {
                secondArray[i] = firstArray[(numerOfRotations - 1) - i];
            }
        } else if (numerOfRotations < size) {
            for (int j = 0; j < numerOfRotations; j++) {
                secondArray[j] = firstArray[(numerOfRotations - 1) - j];
            }
            for (int i = numerOfRotations; i < size; i++) {
                secondArray[i] = firstArray[i];
            }
            //System.out.println(numbers[0] + " " + timePeriod);
        }
        for (int i=0; i < m.length; i++) {
            System.out.println(secondArray[m[i]]);
        }
    }
}

