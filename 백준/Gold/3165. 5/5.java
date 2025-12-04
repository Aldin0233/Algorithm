import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[] num;
    static int K;
    static int len, nowFiveCnt;
    static boolean needMore = false;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());
        len = num.length;
        if(len < K) {
            System.out.println("5".repeat(K));
            return;
        }
        //기존보다 큰 것부터
        if(num[len - 1] < '9') num[len - 1]++;
        else {
            num[len - 1] = '0';
            addNext(len - 2);
        }
        //현재 5의 갯수
        nowFiveCnt = 0;
        for(int i = 0; i < len; i++) {
            if(num[i] == '5') nowFiveCnt++;
        }
        //가장 아래서부터 5로 바꿔나감
        int nowIdx = len - 1;
        while(nowFiveCnt < K && nowIdx >= 0) {
            if(num[nowIdx] == '5') { nowIdx--; continue; }
            //만약 기존게 5보다 컷다면 위에것도 바꿔줘야 더 큰 값이 됨
            if(num[nowIdx] > '5') addNext(nowIdx - 1);
            if(nowFiveCnt >= K) {
                num[nowIdx] = '0';
                break;
            }
            num[nowIdx] = '5';
            nowIdx--;
            nowFiveCnt++;
        }
        //만약 바꿔주는 과정에서 자릿수 추가가 필요하다면
        if(needMore) ans.append(1);
        for(char c : num) ans.append(c);
        System.out.println(ans);
    }

    private static void addNext(int idx) {
        if(idx < 0) {
            needMore = true;
            return;
        }
        if(num[idx] < '9') {
            if(num[idx] == '5') nowFiveCnt--;
            num[idx]++;
            if(num[idx] == '5') nowFiveCnt++;
        } else {
            num[idx] = '0';
            addNext(idx - 1);
        }
    }

}


