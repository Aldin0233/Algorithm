import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] files = new int[N];
        for(int i = 0; i < N; i++) {
            files[i] = sc.nextInt();
        }
        int clusterSize = sc.nextInt();
        long ans = 0;
        for(int i = 0; i < N; i++) {
            ans += ((files[i] + clusterSize - 1) / clusterSize); 
        }
        System.out.println(ans * clusterSize);
    }
}