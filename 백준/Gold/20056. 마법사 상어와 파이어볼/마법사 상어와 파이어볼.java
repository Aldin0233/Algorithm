
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    static int N, M, K;
    static List<FireBall> fireBalls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireBalls = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(x, y, m, s, d));
        }
        for(int i = 0; i < K; i++) {
            List<FireBall> movedFireBalls = new ArrayList<>();
            Map<Point, List<FireBall>> fireBallMap = new HashMap<>();
            for(FireBall fb : fireBalls) {
                fb.move(N);
                Point p = new Point(fb.x, fb.y);
                fireBallMap.putIfAbsent(p, new ArrayList<>());
                fireBallMap.get(p).add(fb);
            }
            for(Point p : fireBallMap.keySet()) {
                List<FireBall> fireBallList = fireBallMap.get(p);
//                System.out.println(i);
//                for(FireBall fb : fireBallList) {
//                    System.out.println(String.format("%d %d %d %d %d", fb.x, fb.y, fb.m, fb.s, fb.d));
//                }
                if(fireBallList.size() == 1) {
                    movedFireBalls.add(fireBallList.get(0));
                } else {
                    List<FireBall> merged = FireBall.merge(fireBallList);
                    if(merged != null) movedFireBalls.addAll(merged);
                }
            }
            fireBalls = movedFireBalls;
        }
        int sum = 0;
        for(FireBall fb : fireBalls) {
            sum += fb.m;
        }
        System.out.println(sum);
    }

}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point) {
            Point p = (Point) obj;
            return x == p.x && y == p.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

class FireBall {
    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    int x, y;
    int m;
    int s;
    int d;
    public FireBall(int x, int y, int m, int s, int d) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;
    }

    public static List<FireBall> merge(List<FireBall> fbs) {
        int x = fbs.get(0).x;
        int y = fbs.get(0).y;
        int weightSum = 0, speedSum = 0;
        boolean allSpeedOdd = true, allSpeedEven = true;
        for(FireBall fb : fbs) {
            if(fb.d % 2 == 0) {
                allSpeedOdd = false;
            } else {
                allSpeedEven = false;
            }
            weightSum += fb.m;
            speedSum += fb.s;
        }
        int weight = weightSum / 5;
        int speed = speedSum / fbs.size();
        if(weight == 0) {
            return null;
        }
        int newD = 1;
        if(allSpeedOdd || allSpeedEven) {
            newD = 0;
        }
        return List.of(new FireBall[]{
                new FireBall(x, y, weight, speed, 0 + newD),
                new FireBall(x, y, weight, speed, 2 + newD),
                new FireBall(x, y, weight, speed, 4 + newD),
                new FireBall(x, y, weight, speed,  6 + newD)
        });
    }

    public void move(int N) {
        int speed = this.s % N;
        int nx = (x + N + (dx[d] * speed)) % N;
        int ny = (y + N + (dy[d] * speed)) % N;
        this.x = nx;
        this.y = ny;
    }



}



