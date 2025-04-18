import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        PriorityQueue<Line> pq = new PriorityQueue<>();
        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            pq.add(new Line(a, b));
        }
        List<Line> lines = new ArrayList<>();
        Line line = pq.poll();
        while(!pq.isEmpty()) {
            Line l = pq.poll();
            if(line.b < l.a) {
                lines.add(line);
                line = l;
            } else {
                line.setNewMaxB(l.b);
            }
        }
        lines.add(line);
        for(int i = 0; i < lines.size(); i++) {
            ans += lines.get(i).calculateDist();
        }
        
        System.out.println(ans);
    }


}

class Line implements Comparable<Line> {
    long a, b;
    public Line(long a, long b) {
        this.a = a;
        this.b = b;
    }

    public long calculateDist() {
        return b - a;
    }

    public void setNewMaxB(long B) {
        this.b = Math.max(this.b, B);
    }

    public int compareTo(Line o) {
        return Long.compare(this.a, o.a);
    }
}

