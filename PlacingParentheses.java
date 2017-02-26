import java.lang.Character;
import java.lang.System;
import java.util.Scanner;

/*
    This program maximizes
    arithmetic sum by placing parenthesis using Dynamic 
    Programming approach
 */

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
      //write your code here
        int n = exp.length()/2 +1;
        long[][] m = new long[n+1][n+1];
        long[][] M = new long[n+1][n+1];
        for (int i=1; i <= n; i++) {
            m[i][i] = Character.getNumericValue(exp.charAt(2 * (i-1)));
            M[i][i] = Character.getNumericValue(exp.charAt(2 * (i-1)));
        }

        for (int s = 1; s <= n; s++) {
            for (int i = 1; i <= (n - s); i++) {
                int j = i + s;
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                for (int k=i; k < j; k++)
                {
                    //System.out.println(exp.charAt(2*(k-1)+1));
                    long a = eval(M[i][k], M[k+1][j], exp.charAt(2*(k-1) +1));
                    long b = eval(M[i][k], m[k+1][j], exp.charAt(2*(k-1) +1));
                    long c = eval(m[i][k], M[k+1][j], exp.charAt(2*(k-1) +1));
                    long d = eval(m[i][k], m[k+1][j], exp.charAt(2*(k-1) +1));
                    min = Math.min(Math.min(Math.min(Math.min(min, a), b), c), d);
                    max = Math.max(Math.max(Math.max(Math.max(max, a), b), c), d);
                }
                m[i][j] = Math.min(max, min);
                M[i][j] = Math.max(max, min);
            }

        }
      return M[1][n];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

