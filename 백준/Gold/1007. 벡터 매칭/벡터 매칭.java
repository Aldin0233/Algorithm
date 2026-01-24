import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static int N;
    static Point[] arr;
    static int need;
    static int totalX, totalY;
    static double best;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            arr = new Point[N];
            totalX = 0;
            totalY = 0;
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                totalX += x;
                totalY += y;
                arr[j] = new Point(x, y);
            }
            need = N >> 1;
            best = Double.MAX_VALUE;
            dfs(1, 1, arr[0].x, arr[0].y);
            ans.append(String.format("%.12f\n", best));
        }
        System.out.println(ans);
    }

    static void dfs(int idx, int chosen, long sumX, long sumY) {
        if(chosen == need) {
            long vx = 2L * sumX - totalX;
            long vy = 2L * sumY - totalY;
            double len = Math.hypot(vx, vy);
            best = Math.min(best, len);
            return;
        }
        if(idx == N) return;
        //너무 건너뜀
        if(chosen + (N - idx) < need) return;
        dfs(idx + 1, chosen, sumX, sumY);
        dfs(idx + 1, chosen + 1, sumX + arr[idx].x, sumY + arr[idx].y);
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }





}




