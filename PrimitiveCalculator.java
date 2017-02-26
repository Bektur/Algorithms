import java.lang.Integer;
import java.util.*;
import java.util.Hashtable;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        Hashtable<Integer> numbers = new Hashtable<Integer>();
        int[] primitive = new int[n+1];
        primitive[0] = 0;

        for (int i=1; i <= n; i++)
        {
            for (int j=1; j < 3; j++)
            {
                primitive
            }
        }

        sequence.add(n);
        int total = 0;


        /*while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }*/
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

