import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Map<Direction, Integer> diCnt;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        diCnt = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            addDirection(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        searchMaxCnt();
        System.out.println(ans);
    }

    private static void searchMaxCnt() {
        for(int cnt : diCnt.values()) {
            if(cnt > ans) ans = cnt;
        }
    }

    private static void addDirection(int dx, int dy) {
        int g = gcd(Math.abs(dx), Math.abs(dy));
        dx /= g;
        dy /= g;
        diCnt.merge(new Direction(dx, dy), 1, Integer::sum);
    }

    private static int gcd(int a, int b) {
        while(b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }



}

class Direction {
    int dx, dy;
    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return dx == direction.dx && dy == direction.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }
}







