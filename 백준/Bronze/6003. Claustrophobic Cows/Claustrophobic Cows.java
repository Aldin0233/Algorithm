import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        double dist = Double.MAX_VALUE;
        Cow[] cows = new Cow[N];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int[] ans = new int[2];
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(i == j) continue;
                if(dist > cows[i].getDist(cows[j])) {
                    dist = cows[i].getDist(cows[j]);
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        System.out.printf("%d %d\n", ans[0] + 1, ans[1] + 1);
    }
}

class Cow {
    int x, y;

    public Cow(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDist(Cow o) {
        return Math.sqrt(Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2));
    }
}

