import java.util.Scanner;

public class Change {
    private static int getChange(int n) {
        if (n >= 10) {
            int m = n % 10;
            if (m == 0)
                return n / 10;
            else {
                if (m % 5 == 0)
                    return n/10 + m/5;
                else
                    return n/10 + m/5 + m%5;
            }
        }
        else if (n >=5)
            return n/5 + n%5;
        else
            return n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

    }
}

