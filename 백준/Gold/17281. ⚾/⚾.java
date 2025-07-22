import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] info;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[] visited = new boolean[9];
        visited[0] = true; //1번 선수는 기용 완료
        int[] arr = new int[9];
        makeComb(arr , visited, 0); //조합
        System.out.println(ans);
    }

    private static void makeComb(int[] arr, boolean[] visited, int selected) {
        if(selected == 9) { // 9명이 골라지면
            checkScore(arr); //체크
        }
        else if(selected == 3) { //4번 타석은 1번 선수 고정 // 초기값 0
            makeComb(arr, visited, selected + 1);
        }
        else {
            for(int i = 1; i < 9; i++) { //2번부터 9번 선수까지
                if(!visited[i]) {
                    visited[i] = true;
                    arr[selected] = i; //골라서 넘기기
                    makeComb(arr, visited, selected + 1);
                    visited[i] = false;
                }
            }
        }
    }

    private static void checkScore(int[] arr) {
        int idx = 0; //시작 선수
        int score = 0; //경기 점수
        for(int i = 0 ; i < N; i++) {
            boolean[] base = new boolean[4]; //베이스 상황
            int outCount = 0; //아웃카운트
            while(outCount < 3) {
                int now = info[i][arr[idx++ % 9]]; //해당 타자가 이번 이닝에 몇 치는지
                if(now == 0) outCount++; //아웃
                else score += checkBase(now, base);
            }
        }
        ans = Math.max(ans, score);
    }

    private static int checkBase(int now, boolean[] base) {
        int score = 0; //베이스 진루 & 점수 득점
        for(int i = 3; i > 0; i--) {
            if(base[i]) { //베이스에 주자가 있으면
                base[i] = false;
                if(i + now > 3) score++; //홈 넘어가면 득점
                else base[i + now] = true; //이외에는 진루
            }
        }
        //타자 진루
        if(now == 4) score++;
        else base[now] = true;
        return score;
    }


}




