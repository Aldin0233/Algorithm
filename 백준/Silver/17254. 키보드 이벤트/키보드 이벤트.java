import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
//    static boolean[][] map;
//    static final int MAX = Integer.MAX_VALUE;
//    static final int[] dr = {-1, 1, 0, 0};
//    static final int[] dc = {0, 0, -1, 1};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<KeyboardInput> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new KeyboardInput(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }
        while (!pq.isEmpty()) {
            ans.append(pq.poll().input);
        }
        System.out.println(ans);
    }

}

class KeyboardInput implements Comparable<KeyboardInput> {
    int keyboardNo;
    int inTime;
    char input;

    public KeyboardInput(int keyboardNo, int inTime, char input) {
        this.keyboardNo = keyboardNo;
        this.inTime = inTime;
        this.input = input;
    }

    public int compareTo(KeyboardInput o) {
        if(this.inTime == o.inTime) return keyboardNo - o.keyboardNo;
        return inTime - o.inTime;
    }
}
