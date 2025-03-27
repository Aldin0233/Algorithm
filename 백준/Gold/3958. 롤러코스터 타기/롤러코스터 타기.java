import java.io.*;
import java.util.*;

public class Main {

    static int MAX_TIME = 25000;
    static int N, Q;
    static RollerCoaster[] info;
    static int[] dp = new int[MAX_TIME + 1];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new RollerCoaster[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            info[i] = new RollerCoaster(a, b, t);
        }

        for (RollerCoaster rc : info) {
            if(rc.b == 0) {
                int t = rc.t;
                for(int i = t ; i <= MAX_TIME; i++) {
                    dp[i] = Math.max(dp[i], dp[i - t] + rc.a);
                }
            } else {
                List<int[]> possibleRides = rc.getPossibleRides();
                for(int i = MAX_TIME; i >= rc.t; i--) {
                    for (int[] ride : possibleRides) {
                        int time = ride[0];
                        int fun = ride[1];
                        if(i - time >= 0) {
                            dp[i] = Math.max(dp[i], dp[i - time] + fun);
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(dp[query]).append("\n");
        }
        System.out.print(sb);
    }
}

class RollerCoaster {
    int a, b, t;
    RollerCoaster(int a, int b, int t) {
        this.a = a;
        this.b = b;
        this.t = t;
    }

    int fun(int i) {
        return a - (i * i * b);
    }

    List<int[]> getPossibleRides() {
        List<int[]> rides = new ArrayList<>();
        int totalFun = 0;
        int totalTime = 0;
        int rideCount = 0;

        while (true) {
            int currentFun = fun(rideCount);
            if (currentFun <= 0) break;
            totalFun += currentFun;
            totalTime += t;
            if (totalTime > 25000) break;
            rides.add(new int[]{totalTime, totalFun});
            rideCount++;
        }

        return rides;

    }

}