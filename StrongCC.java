import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.Iterator;

// Algorithms and Design Coursera 
/*
 * Compute strongly connected components of a graph 
 * input SCC.txt
 */
public class StrongCC {

    private boolean[] marked;
    private int[] finishTime;
    private Stack<Integer>[] adj2;
    private int t = 0;
    private int s = 0;
    private List<Integer>[] leaders;

    public StrongCC(Digraph g) {
        finishTime = new int[g.V];
        leaders = (ArrayList<Integer>[]) new ArrayList[g.V];
        marked = new boolean[g.V];
        adj2 = (Stack<Integer>[]) new Stack[g.V];
        for (int v=0; v < g.V; v++) {
            adj2[v] = new Stack<Integer>();
            leaders[v] = new ArrayList<Integer>();
        }
        for (int i=0; i < g.V; i++) {
            marked[i] = false;
        }
        /*for (int i=0; i < g.V; i++) {
            System.out.println(i + " " + g.adj(i));
        }*/

        DFS(g.reverse());
    }

    public Iterable<Integer> adj2(int v) {
        return adj2[v];
    }

    public void DFS(Digraph g) {
        /*for (int k=0; k < g.V; k++) {
            System.out.println("reverse " + k + " " + g.adj(k));
        }*/
        int i = (g.V-1);
        while (i >= 0) {
            if (!marked[i]) {
                s = i;
                nonrecursiveExplore(g, i);
            }
            i--;
        }
        //g.remove();
        //g = g.reverse();
        for (int k=0; k < g.V; k++) {
            for (int w : g.adj(k)) {
                adj2[finishTime[w]].push(finishTime[k]);
            }
        }

        for (int k=0; k < g.V; k++) {
            marked[k] = false;
        }

        int j = (g.V-1);
        while (j >= 0) {
            if (!marked[j]) {
                s = j;
                explore(j);
            }
            j--;
        }
        int[] sizes = new int[leaders.length];
        for (int f=0; f < leaders.length; f++) {
            sizes[f] = leaders[f].size();
        }
        Arrays.sort(sizes);

        for (int f=0; f < sizes.length; f++) {
            System.out.println(sizes[(sizes.length-1)-f]);
        }
    }

    private void nonrecursiveExplore(Digraph g, int s) {

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[g.V()];
        for (int v = 0; v < g.V(); v++)
            adj[v] = g.adj(v).iterator();

        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                // StdOut.printf("check %d\n", w);
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    // edgeTo[w] = v;
                    stack.push(w);
                    System.out.printf("dfs(%d)\n", w);
                }
            }
            else {
                // StdOut.printf("%d done\n", v);
                stack.pop();
                t++;
                //System.out.println(t);
                finishTime[v] = t-1;
            }
        }
    }

    private void nonrecursiveexplore(int k) {
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[adj2.length];
        for (int v=0; v < adj2.length; v++) {
            adj[v] = adj2(v).iterator();
        }

        Stack<Integer> stack = new Stack<Integer>();
        marked[k] = true;
        leaders[s].add(s);
        stack.push(k);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();

                if (!marked[w]) {

                    marked[w] = true;
                    leaders[s].add(s);
                    stack.push(w);
                }
            }
            else {
                stack.pop();
            }
        }
        /*for (int w : adj2(v)) {
            if (!marked[w]) {
                explore(w);
            }
        }*/
    }

    private void exploreReverse(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                exploreReverse(g, w);
            }
        }
        t++;
        finishTime[v] = t-1;
        //g.replace(v, finishTime[v]);
    }

    public void addEdge(int v, int w) {
        adj2[v].push(w);
        //indegree[w]++;
    }

    public StrongCC reverse() {
        // reverse in order to get the Strong Connected Components
        Digraph g = new Digraph();
        StrongCC s = new StrongCC(g);
        for (int v=0; v < g.V; v++) {
            for (int w : g.adj(v)) {
                s.addEdge(w, v);
            }
        }
        return s;
    }

    public boolean stronglyConnected(int v, int w) {
        return leaders[v] == leaders[w];
    }

    public static void main(String[] args) {

        Digraph g = new Digraph();
        StrongCC sc = new StrongCC(g);
    }

    private static class Digraph {
        private int V;
        private Stack<Integer>[] adj;
        private int[] indegree;
        private int E;

        public Digraph() {
            this.E = 0;
            this.V = 875714;
            adj = (Stack<Integer>[]) new Stack[V];
            for (int v=0; v < V; v++) {
                adj[v] = new Stack<Integer>();
            }
            indegree = new int[V];

            writeData();
            //printOut();
        }

        private void writeData() {
            Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    int m = scanner.nextInt() - 1;
                    int n = scanner.nextInt() - 1;
                    addEdge(m, n);
                }
        }

        public void remove() {
            for (int v=0; v < V; v++) {
                for (int w : adj(v))
                    adj[v].pop();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].push(w);
            //indegree[w]++;
            E++;
        }

        public void replace() {
            Digraph g = new Digraph();
            StrongCC s = new StrongCC(g);
            for (int k=0; k < V; k++) {
                for (int l : adj(k)) {
                    adj[s.finishTime[k]].push(s.finishTime[l]);
                }
            }
        }

        private void validateVertex(int v) {
            if (v < 0 || v > V)
                throw new IndexOutOfBoundsException(" Vertex is out of range");
        }

        public Digraph reverse() {
            // reverse in order to get the Strong Connected Components
            Digraph reverse = new Digraph();
            for (int v=0; v < V; v++) {
                for (int w : adj(v)) {
                    reverse.addEdge(w, v);
                }
            }
            return reverse;
        }

        public Iterable<Integer> adj(int v) {
            validateVertex(v);
            return adj[v];
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        private void printOut() {
            System.out.println(E);
            //for (int i=0; i < V; i++) {
                for (int j=0; j < adj[1].size(); j++) {
                    System.out.print(adj[1].get(j) + " ");
                }
                System.out.println();
          //  }
        }
    }
}