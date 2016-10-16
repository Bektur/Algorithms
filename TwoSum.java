import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.*;

public class TwoSum {
    private static int counter = 0;

    private TwoSum() {

    }

    private void write() {

    }

    public static void compute(Map<Long, String> map, long[] t, Set<Long> set) {
        for (int j=0; j < t.length; j++) {
            //System.out.println(t[j]);
            Iterator<Long> iterator = set.iterator();
            while (iterator.hasNext()) {
                Long input = iterator.next();
                Long value = t[j] - input;
                //System.out.println(t[j] + " " + input);
                if (!(Long.toString(input)).equals(map.get(value))) {
                    if (map.containsKey(value)) {
                        System.out.println(t[j] + " " + input + " " + value + " " + map.get(value));
                        String s = map.remove(value);
                        System.out.println("true");
                        counter++;
                        break;
                    }
                }
                else {
                    continue;
                }
            }
       }
    }


    public static void read(long[] A, Map<Long, String> map) {
        /*for (int i=0; i < input.length; i++) {
            System.out.println(input[i] + " " + table.get(Long.toString(input[i])));
        }*/
        for (int i=0; i < A.length; i++) {
            System.out.println(A[i] + " " + map.get(Long.toString(A[i])));
        }


    }

    public static void mergeSort(long[] A) {
        long[] B = new long[A.length];
        mergeSort(A, B, 0, A.length-1);
    }

    private static void mergeSort(long[] A, long[] B, int left, int right) {
        if (right <= left)
            return;
        int mid = left + (right-left) / 2;
        mergeSort(A, B, left, mid);
        mergeSort(A, B, mid+1, right);

        merge(A, B, left, mid, right);
    }

    private static void merge(long[] A, long[] B, int left, int mid, int right) {
        // copy the contents of B to A
        for (int i=0; i < A.length; i++) {
            B[i] = A[i];
        }

        int i = left;
        int j = mid+1;
        int k = left;
        while (k <= right) {
            if (i > mid) {
                A[k] = B[j++];
            } else if (j > right){
                A[k] = B[i++];
            } else if (B[j] < B[i]) {
                A[k] = B[j++];
            }
            else {
                A[k] = B[i++];
            }
            k++;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Long, String> table = new HashMap<Long, String>();
        Set<Long> set = new HashSet<Long>();
        long[] t = new long[20001];
        int i = 0;
        while (i < 1000000) {
            long value = scanner.nextLong();
            set.add(value);
            String key = Long.toString(value);
            table.put(value, key);
            i++;
        }
        long j = -10000;
        int k = 0;
        while (k <= 20000) {
            t[k] = j;
            j++;
            k++;
        }
        //TwoSum.mergeSort(input);
        TwoSum.compute(table, t, set);
        //TwoSum.read(input, table);
        System.out.println(counter);
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}