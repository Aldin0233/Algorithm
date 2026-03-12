import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N, K;
    static Point[] metals;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            metals = new Point[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                metals[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(metals);
            ans.append(cost()).append("\n");
        }
        System.out.println(ans);
    }

    static String cost() {
        int lo = 0, hi = 200_000_000;
        while(lo < hi) {
            int mid = (lo + hi) >>> 1;
            if(check(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return String.format("%.1f", hi / 2.0);
    }

    static boolean check(int limit) {
        int used = 1;
        int minY = metals[0].y;
        int maxY = metals[0].y;
        for(int i = 1; i < metals.length; i++) {
            int ny = metals[i].y;
            int nextMin = Math.min(minY, ny);
            int nextMax = Math.max(maxY, ny);
            if(nextMax - nextMin <= limit) {
                minY = nextMin;
                maxY = nextMax;
            } else {
                used++;
                minY = ny;
                maxY = ny;
            }
            if(used > K) return false;
        }
        return true;
    }

    static class Point implements Comparable<Point> {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o) {
            return Integer.compare(x, o.x);
        }
    }



}

