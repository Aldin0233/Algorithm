import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Point[] points;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            points = new Point[n + 2];
            StringTokenizer st;
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            if (bfs()) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }

    }

    static boolean bfs() {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[n + 2];

        queue.add(points[0]);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(points[n + 1])) {
                return true;
            }

            for (int i = 1; i < n + 2; i++) {
                if (!visited[i] && current.distance(points[i]) <= 1000) {
                    queue.add(points[i]);
                    visited[i] = true;
                }
            }
        }

        return false;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double distance(Point p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }
}
