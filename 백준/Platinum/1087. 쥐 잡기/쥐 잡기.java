import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Mouse[] mice;
    static final double EPSILON = 1e-9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mice = new Mouse[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int vx = Integer.parseInt(st.nextToken());
            int vy = Integer.parseInt(st.nextToken());
            mice[i] = new Mouse(x, y, vx, vy);
        }

        double minCoverSize = ternarySearch();

        System.out.printf("%.11f\n", minCoverSize);
    }

    // 삼분 탐색 //추가 공부 필요
    static double ternarySearch() {
        double left = 0;
        double right = 1e4; //최대 10000초까지
        for (int i = 0; i < 100; i++) { //100번 탐색하면 충분히 정확 
            double m1 = (2 * left + right) / 3;
            double m2 = (left + 2 * right) / 3;

            double l1 = getSquareSizeAtTime(m1);
            double l2 = getSquareSizeAtTime(m2);

            if (l1 < l2) {
                right = m2;
            } else {
                left = m1;
            }
        }

        return getSquareSizeAtTime((left + right) / 2);
    }

    static double getSquareSizeAtTime(double t) {
        double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;

        for (Mouse m : mice) {
            double x = m.x + m.vx * t;
            double y = m.y + m.vy * t;

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        return Math.max(maxX - minX, maxY - minY);
    }

    static class Mouse {
        int x, y, vx, vy;

        public Mouse(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }
    }
}
