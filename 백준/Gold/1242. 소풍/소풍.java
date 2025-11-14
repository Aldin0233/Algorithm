import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, M;
    static int beforeM, afterM;
    static int turn;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        turn = 1;
        //동호 기준으로 전, 후 구간으로 나눠 접근
        beforeM = M - 1;
        afterM = N - M;
        int curPos = 0;
        while(true) {
            //다음 사람 찾기
            curPos = (curPos + K - 1) % alive();
            //다음 사람이 속한 구간 찾기
            if(curPos < beforeM) beforeM--;
            else if(curPos == beforeM) break;
            else afterM--;
            turn++;
        }

        System.out.println(ans = turn);
    }

    private static int alive() {
        return beforeM + afterM + 1;
    }

}

