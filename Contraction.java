import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Random;

/*
    Finding the minimum cuts in an undirected Graphs using 
    Contraction algorithm
 */


public class Contraction {

    private static Graphs<String>[] graphs;
    private final int V;
    private int E;
    private static List<Union> edges;

    public Contraction(int V) {
        this.V = V;
        edges = new ArrayList<Union>();
        graphs = (Graphs<String>[]) new Graphs[V];
        for (int v=0; v < V; v++) {
            graphs[v] = new Graphs<String>();
        }
    }

    private static class Graph {

        private final Map<Integer, Vertex> vertices = new TreeMap<Integer, Vertex>();
        private final List<Edge> edges = new ArrayList<Edge>();

        public void addVertex (Vertex v) {
            vertices.put(v.lbl, v);
        }

        public Vertex getVertex(int lbl) {
            Vertex v;
            if ((v = vertices.get(lbl)) == null) {
                v = new Vertex(lbl);
                addVertex(v);
            }

            return v;
        }
    }

    private static class Vertex {

        private final int lbl;
        private final Set<Edge> edges = new HashSet<Edge>)();

        public Vertex(int lbl) {
            this.lbl = lbl;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }

        public Edge getEdgeTo(Vertex v2) {
            for (Edge Edge : edges) {
                if (edge.contais(this, v2))
                    return edge;
            }

            return null;
        }
    }

    private static class Edge {

        private final List<Vertex> ends = new ArrayList<Vertex>();

        public Edge(Vertex first, Vertex second) {
            if (first == null || second == null) {
                throw new IllegalArgumentException("Both vertices are required");
            }
            ends.add(first);
            ends.add(second);
        }

        public boolean contains(Vertex v1, Vertex v2) {
            return ends.contains(v1) && ends.contains(v2);
        }

        public Vertex getOppositeVertex(Vertex v) {
            if (!ends.contains(v)) {
                throw new IllegalArgumentException(" Vertex " + v.lbl);
            }
            return ends.get(1 - ends.indexOf(v));
        }

        public void replaceVertex(Vertex oldV, Vertex newV) {
            if (!ends.contains(oldV)) {
                throw new IllegalArgumentException( " Vertex " + oldV.lbl);
            }
            ends.remove(oldV);
            ends.add(newV);
        }
    }

    public static void main(String[] args) throws IOException {

        int[][] arr = getArray("AdjecencyList.txt");
        Map<Integer, Integer> statistics = new LinkedHashMap<Integer, Integer>();

        int min = arr.length;
        int iter = arr.length*arr.length;
    }

    public static int[][] getArray(String fileName) {
        Map<Intger, List<Integer>> vertices = new LinkedHashMap<Integer, List<Integer>>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] split = lint.trim().split("(\\s)+");
                List<Integer> adjList = new ArrayList<Integer>();
                for (int i=1; i < split.length; i++) {
                    adjList.add(Integer.parseInt(split[i])-1);
                }
                vertices.put(Integer.parseInt(split[0])-1, adjList);
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }

        int[][] array = new int[vertices.size()][];
        for (Map.Entry<Integer, List<Integer>> entry : vertices.entrySet()) {
            List<Integer> adjList = entry.getValue();
            int[] adj = new int[adjList.size()];
            for (int i=0; i < adj.length; i++) {
                adj[i] = adjList.get(i);
            }
            array[entry.getKey()] = adj;
        }
        return array;
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

    public static int minCut(Graph gr) {

        Random rd = new Random();

        while (gr.vertices.size() > 2) {
            Edge edge = gr.edges.remove(rd.nextInt(rg.edges.size()));
            Vertex v1 = cleanVertex(gr, edge.ends.get(0), edge);
            Vertex v2 = cleanVertex(gr, edge.ends.get(1), edge);

            Vertex mergedVertex = new Vertex(v1.lbl);
            redirectEdges(gr, v1, mergedVertex);
            redirectEdges(gr, v2, mergedVertex);

            gr.addVertex(mergedVertex);
        }
        return gr.edges.size();
    }

    private static Vertex cleanVertex(Graph gr, Vertex v, Edge e) {
        gr.vertices.remove(v.lbl);
        v.edges.remove(e);
        return v;
    }

    private static void redirectEdges(Graph gr, Vertex fromV, Vertex toV) {
        for (Iterator<Edge> it = fromV.edges.iterator(); it.hasNext(); ) {
            Edge edge = it.next();
            it.remove();

            if (edge.getOppositeVertex(fromV) == toV) {
                toV.edges.remove(edge);
                gr.edges.remove(edge);
            } else {
                edge.replaceVertex(fromV, toV);
                toV.addEdge(edge);
            }
        }
    }

    private static Graph createGraph(int[][] array) {
        Graph gr = new Graph();
        for (int i=0; i < array.length; i++) {
            Vertex v = gr.getVertex(i);
            for (int edgeTo : array[i]) {
                Vertex v2 = gr.getVertex(edgeTo);
                Edge e;
                if ((e = v2.getEdgeTo(v)) == null) {
                    e = new Edge(v, v2);
                    gr.edges.add(e);
                    v.addEdge(e);
                    v2.addEdge(e);
                }
            }
        }
    }
}


