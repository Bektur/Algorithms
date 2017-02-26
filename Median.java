import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;

public class Median {

    private int[] minHeap;
    private int[] maxHeap;
    private int[] list;
    private int size;
    private int maxSize;

    public Median() throws IOException {
        FastScanner scanner = new FastScanner();
        int n = 10000;
        minHeap = new int[n];
        maxHeap = new int[n];
        list = new int[n];
        size = 0;
        maxSize = 0;
        int sum = 0;
        for (int i=0; i < n; i++) {
            int k = scanner.nextInt();
            list[i] = k;
            Arrays.sort(list, 0, i+1);
            sum += list[i/2];
            addMin(k);
            addMax(k);
        }
        System.out.println(sum % 10000);
        int child = 0;
        while (child < maxSize) {
            siftDownMax(child);
            siftUpMax(child);
            child++;
        }
        child = 0;
        while (child < size) {
            siftDown(child);
            child++;
        }

        //read();
    }

    public static void main(String[] args) throws IOException {
        Median median = new Median();
    }

    public void addMin(int n) {
        minHeap[size] = n;
        siftUp(size);

        size++;
    }

    public void addMax(int n) {
        maxHeap[maxSize] = n;
        siftUpMax(maxSize);

        maxSize++;
    }

    public void siftUp(int n) {
        while (n > 0 && minHeap[(n-1)/2] >= minHeap[n]) {
            swap(minHeap, (n-1)/2, n);
            n = (n-1)/2;
        }
    }

    public void siftDown(int n) {
        int minIndex = n;
        int left = 2 * n +1;
        if (left < minHeap.length && minHeap[left] < minHeap[minIndex])
            minIndex = left;
        int right = 2 * n + 2;
        if (right < minHeap.length && minHeap[right] < minHeap[minIndex])
            minIndex = right;
        if (n != minIndex)
        {
            swap(minHeap, n, minIndex);
            siftDown(minIndex);
            //out.println((n) + " " + (maxIndex));
        }
    }

    public void siftDownMax(int n) {
        int maxIndex = n;
        int left = 2 * n + 1;
        if (left < maxHeap.length && maxHeap[left] > maxHeap[maxIndex])
            maxIndex = left;
        int right = 2 * n + 2;
        if (right < maxHeap.length && maxHeap[right] > maxHeap[maxIndex])
            maxIndex = right;
        if (n != maxIndex) {
            swap(maxHeap, n, maxIndex);
            siftDownMax(maxIndex);
        }
    }

    public void siftUpMax(int n) {
        while (n > 0 && maxHeap[(n-1)/2] < minHeap[n]) {
            swap(maxHeap, (n-1)/2, n);
            n = (n-1)/2;
        }
    }

    public int extractMin() {
        int result = minHeap[0];
        minHeap[0] = minHeap[size-1];
        size--;
        siftDown(0);

        return result;
    }

    public int extractMax() {
        int result = maxHeap[0];
        maxHeap[0] = maxHeap[maxSize-1];
        maxSize--;
        siftDownMax(0);

        return result;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int size() {
        return size;
    }

    private void read() {
        for (int i=0; i < 10; i++) {
            System.out.println(extractMin() + " " + extractMax());
        }
        System.out.println();
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