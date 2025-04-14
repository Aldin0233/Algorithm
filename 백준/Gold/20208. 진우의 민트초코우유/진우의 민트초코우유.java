import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H;
    static int ans = 0;
    static int[] home;
    static List<int[]> mintChocoMilk;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        mintChocoMilk = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if(a == 1) home = new int[]{i, j};
                else if(a == 2) mintChocoMilk.add(new int[]{i, j});
            }
        }

        makeComb(new boolean[mintChocoMilk.size()], new ArrayList<>());

        System.out.println(ans);
    }

    private static void makeComb(boolean[] visited, List<Integer> combs) {
        if(combs.size() == mintChocoMilk.size()) {
            useComb(combs);
        }

        for(int i = 0; i < mintChocoMilk.size(); i++) {
            if(visited[i]) continue;
            visited[i] = true;
            combs.add(i);
            makeComb(visited, combs);
            visited[i] = false;
            combs.remove(combs.size() - 1);
        }
    }

    private static void useComb(List<Integer> combs) {
        int health = M;
        int nowX = home[0], nowY = home[1];
        for(int i = 0; i < combs.size(); i++) {
            int[] point = mintChocoMilk.get(combs.get(i));
            int dist = calDistance(nowX, nowY, point);
            if(health < dist) {
                return;
            } else {
                health = health - dist + H;
                nowX = point[0];
                nowY = point[1];
                if(health >= calDistance(nowX, nowY, home)) {
                    ans = Math.max(ans, i + 1);
                }
            }
        }
    }

    private static int calDistance(int nowX, int nowY, int[] point) {
        return Math.abs(nowX - point[0]) + Math.abs(nowY - point[1]);
    }


}

