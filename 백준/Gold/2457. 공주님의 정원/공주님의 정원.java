import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Flower[] flowers;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flowers = new Flower[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            flowers[i] = makeFlower(st);
        }
        Arrays.sort(flowers);

        ans = search();

        System.out.println(ans);
    }

    private static int search() {
        int idx = 0, cnt = 0;
        int curDate = 301;
        int edDate = 1201;
        int nowEdDate = 0;
        while(curDate < edDate) {
            boolean found = false;
            
            while (idx < N && flowers[idx].st <= curDate) {
                if (flowers[idx].ed > nowEdDate) {
                    nowEdDate = flowers[idx].ed;
                    found = true;
                }
                idx++;
            }

            if(!found) {
                cnt = 0;
                break;
            }

            curDate = nowEdDate;
            cnt++;
        }
        return cnt;
    }

    private static Flower makeFlower(StringTokenizer st) {
        return new Flower(Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken()));
    }

}

class Flower implements Comparable<Flower> {
    int st, ed;
    public Flower(int st, int ed) {
        this.st = st;
        this.ed = ed;
    }

    public int compareTo(Flower o) {
        if(this.st == o.st) return o.ed - this.ed;
        return this.st - o.st;
    }

}
