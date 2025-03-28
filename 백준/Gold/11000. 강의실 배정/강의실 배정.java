import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Point> pq = new PriorityQueue<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new Point(a, b));
        }
        q.add(0);
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            if(q.peek() <= p.s) {
                q.poll();
            }
            q.add(p.t);
        }

        System.out.print(q.size());
    }
}

class Point implements Comparable<Point> {
    int s, t;

    public Point(int s, int t) {
        this.s = s;
        this.t = t;
    }

    public int compareTo(Point p) {
        if(this.s == p.s) {
            return this.t - p.t;
        }
        return this.s - p.s;
    }
}