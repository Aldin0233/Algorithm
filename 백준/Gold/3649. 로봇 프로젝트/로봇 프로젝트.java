import java.io.*;
import java.util.*;

public class Main {

    static int X, N;
    static final int NTC = 10_000_000;
    static int[] arr;
    static int minAns, maxAns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null) {
            X = Integer.parseInt(line) * NTC;
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);
            int l = 0;
            int r = N - 1;
            minAns = Integer.MAX_VALUE;
            maxAns = Integer.MIN_VALUE;
            while(l < r) {
                int sum = arr[l] + arr[r];
                if(X == sum) {
                    check(arr[l], arr[r]);
                }
                if(sum < X) {
                    l++;
                } else {
                    r--;
                }
            }
            if(minAns == Integer.MAX_VALUE) {
                System.out.println("danger");
            } else {
                System.out.printf("yes %d %d\n", minAns, maxAns);
            }
        }

    }

    private static void check(int i, int j) {
        if(minAns == Integer.MAX_VALUE) {
            minAns = i;
            maxAns = j;
            return;
        }
        int diff = j - i;
        int oDiff = maxAns - minAns;
        if(diff > oDiff) {
            minAns = i;
            maxAns = j;
        }

    }

}


