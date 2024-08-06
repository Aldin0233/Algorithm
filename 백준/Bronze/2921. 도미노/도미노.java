import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int sum1 = 0;
        for (int i = 1; i < 2 * n; i += 2) {
            for (int j = i; j <= i / 2 + n; j++) {
                sum1 += j;
            }
        }
        
        int sum2 = 0;
        for (int k = 0; k <= n; k++) {
            sum2 += k;
        }
        sum2 *= 2;
        
        System.out.println(sum1 + sum2);
    }
}