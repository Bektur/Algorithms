import java.util.*;

/* 
    This program is implementation of knapsack
    without repetitions problem. The idea of take 
    as much gold as possible
 */

public class Knapsack {
    static int optimalWeight(int W, int[] weight) {
        //write you code here
        int n = weight.length;
        int[][] value = new int[W+1][n+1];
        int total = 0;
        for (int j=0; j <= n; j++)
            value[0][j] = 0;
        for (int w=0; w <= W; w++)
            value[w][0] = 0;
        for (int j = 1; j <= n; j++) {
            for (int w = 1; w <= W; w++) {
                value[w][j] = value[w][j - 1];
                if (weight[j-1] <= w) {
                    total = value[w - weight[j-1]][j - 1] + weight[j-1];
                    if (value[w][j] < total)
                        value[w][j] = total;
                }
            }
        }
        return value[W][n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

