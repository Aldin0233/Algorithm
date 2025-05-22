import java.io.*;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] peopleMap;
    static int[] P;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    //우측과 하단만 확인하겠다.
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        peopleMap = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                peopleMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        while(true) {
            make(N);
            boolean move = false;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    for(int d = 0 ; d < 2; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(isValid(nx, ny) && isOpen(peopleMap[i][j], peopleMap[nx][ny])) {
                            int a = find(i * N + j);
                            int b = find(nx * N + ny);
                            if(a != b) {
                                union(a, b);
                            }
                            move = true;
                        }
                    }
                }
            }
            if(move) {
                Map<Integer, List<int[]>> unionMap = new HashMap<>();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int ref = find(i * N + j);
                        unionMap.putIfAbsent(ref, new ArrayList<>());
                        unionMap.get(ref).add(new int[]{i, j});
                    }
                }

                for (List<int[]> union : unionMap.values()) {
                    int sum = 0;
                    for (int[] cell : union) {
                        sum += peopleMap[cell[0]][cell[1]];
                    }
                    int avg = sum / union.size();
                    for (int[] cell : union) {
                        peopleMap[cell[0]][cell[1]] = avg;
                    }
                }
                ans++;
            } else {
                break;
            }
        }
        System.out.println(ans);
    }

    private static boolean isValid(int x, int y) {
        return x < N && y < N;
    }

    private static boolean isOpen(int a, int b) {
        int diff = Math.abs(a - b);
        return diff >= L && diff <= R;
    }

    private static void make(int N) {
        P = new int[N * N];
        for(int i = 0; i < N * N; i++) {
            P[i] = i;
        }
    }

    private static int find(int x) {
        if(x == P[x]) {
            return x;
        }
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b) {
        P[a] = P[b];
    }


}


