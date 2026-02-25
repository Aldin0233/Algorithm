import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, T;
    static State[] table;
    static final int INF = Integer.MAX_VALUE;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        table = new State[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            table[i] = new State(M);
            for(int j = 0; j < M; j++) {
                table[i].arr[j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for(int X = x; X <= N; X += x) {
                table[X - 1].turn(d, k);
            }
            tableSearch();
        }
        int answer = tableSum();
        System.out.println(answer);
    }

    static int tableSum() {
        int sum = 0;
        for(State s : table) sum += s.sum();
        return sum;
    }

    static void tableSearch() {
        boolean[][] needRemove = new boolean[N][M];
        boolean removed = false;
        double sum = 0;
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int cur = table[i].get(j);
                if(cur == -1) continue;
                sum += cur;
                cnt++;
                if(i != N - 1) {
                    int down = table[i + 1].get(j);
                    if(cur == down) {
                        needRemove[i][j] = true;
                        needRemove[i + 1][j] = true;
                        removed = true;
                    }
                }
                int next = table[i].get(j + 1);
                if(cur == next) {
                    needRemove[i][j] = true;
                    if(j == M - 1) needRemove[i][0] = true;
                    else needRemove[i][j + 1] = true;
                    removed = true;
                }
            }
        }
        if(removed) {
            remove(needRemove);
        } else {
            avg(sum, cnt);
        }
    }

    static void remove(boolean[][] needRemove) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(needRemove[i][j]) {
                    table[i].setRemove(j);
                }
            }
        }
    }

    static void avg(double sum, int cnt) {
        double avg = sum / cnt;
        for(State s : table) {s.inAvg(avg);}
    }

    static class State {
        int turn;
        int[] arr;

        State(int size) {
            arr = new int[size];
        }

        int sum() {
            int sum = 0;
            for(int num : arr) {
                if(num == -1) continue;
                sum += num;
            }
            return sum;
        }

        int get(int idx) {
            return arr[(idx + turn) % M];
        }

        void inAvg(double avg) {
            for(int i = 0; i < M; i++) {
                if(arr[i] == -1) continue;
                if(arr[i] > avg) arr[i]--;
                else if(arr[i] < avg) arr[i]++;
            }
        }

        void setRemove(int idx) {
            arr[(idx + turn) % M] = -1;
        }

        void turn(int d, int k) {
            turn += M;
            turn += d == 0 ? -k : k;
            turn %= M;
        }

    }

}

