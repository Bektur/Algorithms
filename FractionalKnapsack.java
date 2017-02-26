import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class FractionalKnapsack {
    private static double getOptimalValue(double capacity, double[] values, double[] weights) {
        double value = 0;
        //write your code here
        int n = values.length;
        double[] array = new double[n];
        int[] A = new int[n];
        double sum = 0;

        if (n == 1)
        {
            if (capacity > weights[0])
                value = values[0];
            else
                value = capacity * values[0]/weights[0];
            return value;
        }

        for (int i=0; i < n; i++)
        {
            double max = values[i] / weights[i];
            for (int j=i+1; j < n; j++)
            {
                if (values[j]/weights[j] > max)
                {
                    max = values[j]/weights[j];
                    double maxWeight = weights[i];
                    weights[i] = weights[j];
                    weights[j] = maxWeight;
                    double maxValue = values[i];
                    values[i] = values[j];
                    values[j] = maxValue;
                }
            }
            sum += weights[i];
            array[i] = max;
        }

        Arrays.fill(A, 0);
        double a = 0;
        if (sum <= capacity)
            value = sum;
        else {
            for (int i = 0; i < n; i++) {
                if (capacity == 0)
                    return value;
                else if (capacity <= weights[i])
                    a = capacity;
                else
                    a = Math.min(weights[i], capacity);
                value += a * array[i];
                weights[i] = weights[i] - a;
                A[i] += a;
                capacity = capacity - a;
            }
        }
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Random ran = new Random();
        int n = scanner.nextInt();
        double capacity = scanner.nextDouble();
        double[] values = new double[n];
        double[] weights = new double[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextDouble();
            weights[i] = scanner.nextDouble();
        }
        assert capacity != 0;
        System.out.printf("%.4f\n", getOptimalValue(capacity, values, weights));
    }
} 
