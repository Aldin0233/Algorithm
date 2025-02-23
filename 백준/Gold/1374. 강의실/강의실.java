import java.io.*;
import java.util.*;

public class Main {

    public static int N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Class> eduClass = new PriorityQueue<>();
        PriorityQueue<Long> room = new PriorityQueue<>();
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            eduClass.add(new Class(b, c));
        }
        room.add(0L);
        while(!eduClass.isEmpty()) {
            Class c = eduClass.poll();
            long nowRoom = room.peek();
            if(nowRoom > c.start) {
                room.add(c.end);
            } else {
                room.poll();
                room.add(c.end);
            }
        }

        System.out.println(room.size());
    }


}

class Class implements Comparable<Class> {
    long start, end;
    public Class(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Class c) {
        return Long.compare(start, c.start);
    }
}





