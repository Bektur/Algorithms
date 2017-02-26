import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {

    private int[] data;
    private int size;
    private FastScanner in;
    private PrintWriter out;
    // here list is defined, if we define it inside method than previous values
    // will be deleted
    private List<Swap> swaps = new ArrayList<Swap>();

    public BuildHeap(int n) {
        data = new int[n];
    }

    public static void main(String[] args) throws IOException {
        //new BuildHeap().solve();
    }

    /*private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; i++) {
            add(in.nextInt());
        }
    }*/

    public void add(int n) {
        data[size] = n;
        if (size > 1) {
            siftUp(size);

        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int n) {
        if (data[0] == n)
            return true;
        else {
            for (int i = 0; i < size / 2; i++) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (right < size) {
                    if (data[left] == n || data[right] == n)
                        return true;
                }
            }
        }
        return false;
    }

    public int extractMin() {
        int result = data[0];
        data[0] = data[size-1];
        size--;
        siftDown(0);

        return result;
    }

    private void writeResponse() {
        for (int i=0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    private void generateSwaps() {
        int i = data.length/2;
        while (i >= 0)
        {
            siftDown(i);
            i--;
        }
    }
    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void siftUp(int n) {
        while (n > 0 && data[(n-1)/2] >= data[n]) {
            swap((n-1)/2, n);
            n = (n-1)/2;
        }
    }
    public void siftDown(int n) {
        int minIndex = n;
        int left = 2 * n +1;
        if (left < data.length && data[left] < data[minIndex])
            minIndex = left;
        int right = 2 * n + 2;
        if (right < data.length && data[right] < data[minIndex])
            minIndex = right;
        if (n != minIndex)
        {
            swaps.add(new Swap(n, minIndex));
            swap(n, minIndex);
            siftDown(minIndex);
            //out.println((n) + " " + (maxIndex));
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        //readData();
        //generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
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