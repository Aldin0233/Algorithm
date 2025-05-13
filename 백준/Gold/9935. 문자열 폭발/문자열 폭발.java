import java.io.*;
import java.util.*;

public class Main {

    static String str, boomKeyword; //문자열, 폭발 문자열
    static int[] keywordPrefixTable; //kmp 스타일로 매칭 검사
    static Stack<Integer> matchLengthsStack = new Stack<>(); // 매칭된 기존 기록
    static StringBuilder ans = new StringBuilder(); //결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력
        str = br.readLine();
        boomKeyword = br.readLine();
        //키워드 패턴 확인
        makePrefixTable();
        //폭발
        explode();
        System.out.println(ans);
    }

    private static void makePrefixTable() {
        int len = boomKeyword.length();
        keywordPrefixTable = new int[len];
        int j = 0;
        //패턴이 일치하는 가장 긴 위치 확인
        for (int i = 1; i < len; i++) {
            while(j > 0 && boomKeyword.charAt(i) != boomKeyword.charAt(j)) {
                j = keywordPrefixTable[j - 1];
            }
            if(boomKeyword.charAt(i) == boomKeyword.charAt(j)) {
                keywordPrefixTable[i] = ++j;
            }
        }
    }

    private static void explode() {
        int j = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            ans.append(c);
            //kmp 스타일 문자열 매칭 확인
            while(j > 0 && c != boomKeyword.charAt(j)) {
                j = keywordPrefixTable[j - 1];
            }
            if(c == boomKeyword.charAt(j)) {
                j++;
            }
            //현재까지 매칭된 기록 확인
            matchLengthsStack.push(j);
            //매칭
            if(j == boomKeyword.length()) {
                //폭발 처리
                ans.setLength(ans.length() - boomKeyword.length());
                for(int kmp = 0; kmp < boomKeyword.length(); kmp++) {
                    matchLengthsStack.pop(); //폭발된만큼 삭제
                }
                j = matchLengthsStack.isEmpty() ? 0 : matchLengthsStack.peek(); //이전까지 매칭된 것 가져오기
            }
        }

        if(ans.length() == 0) ans.append("FRULA"); //비었음
    }

}
