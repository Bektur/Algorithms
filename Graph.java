
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.*;

public class Graph {

    private List<Integer>[] adj;
    public List<Map<Integer, Integer>> distance;
    private final int V;
    private int E;

    public Graph(int V) {
        this.V = V+1;
        E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V+1];
        distance = new ArrayList<Map<Integer, Integer>>();
        for (int v=0; v <=V; v++) {
            adj[v] = new ArrayList<Integer>();
        }

        write();
        //read();
    }

    /*public Graph(Graph g) {
        for (int v=0; v < g.V; v++) {
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj(v)) {
                reverse.push(w);
            }

            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }*/

    private void write() {
        //Scanner scanner = new Scanner(System.in);
        FileReader fr;
        try {
            fr = new FileReader("dijkstraData.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int j = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split("(\\s)+");
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                //System.out.println(line);
                for (int i=1; i < split.length; i++) {
                    //System.out.println(split[i] + " ");
                    String[] weight = split[i].split(",");
                    addEdge(j, Integer.parseInt(weight[0]));
                    map.put((Integer.parseInt(weight[0])), (Integer.parseInt(weight[1])));
                }
                distance.add(j, map);
                j++;
                //System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(E);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public int degree(int v) {
        return adj[v].size();
    }

    private void read() {
        /*for (int v=0; v < V; v++) {
            for (int w=0; w < adj[v].size(); w++) {
                System.out.print(adj[v].get(w) + " ");
            }
            System.out.println();
        }*/
        for (int v=0; v < V; v++) {
            for (int w : adj(v)) {
                System.out.println((v+1) + " " + w + " " + distance.get(v).get(w) + " ");
            }
            //System.out.println();
        }
    }

    public int V() {
        return V;
    }


    public static void main(String[] args) {
        //Graph graph = new Graph(200);
    }
}