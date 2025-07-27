import java.io.*;
import java.util.Arrays;

public class Main {

    static int N, K;
    static String[] numArr;
    static NumCntInfo[] cntArr = new NumCntInfo[36]; //36진수
    static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numArr = new String[N];
        for (int i = 0; i < N; i++) numArr[i] = br.readLine();
        for (int i = 0; i <= 35; i++) cntArr[i] = new NumCntInfo(i);
        for (int i = 0; i < N; i++) {
            String str = numArr[i];
            for (int j = 0; j < str.length(); j++) {
                int nowStrIdx = str.length() - j - 1; //char의 인덱스
                int data = Base36NumChanger.charToInt(str.charAt(nowStrIdx));
                cntArr[data].addCnt(j);
            }
        }
        K = Integer.parseInt(br.readLine());

        ans = addAll();
        System.out.println(ans);
    }

    private static String addAll() {
        String[] numArr = new String[36];
        NumCntInfoSorter.sort(cntArr);
        for(int i = 0; i < 36; i++) {
            if(i < K) {
                numArr[i] = cntArr[i].changedNum;
            } else {
                numArr[i] = cntArr[i].unchangedNum;
            }
        }
        return Base36Calculator.addBase36Num(numArr);
    }


}

class NumCntInfo implements Comparable<NumCntInfo> {
    int data;
    int[] cntArr = new int[51];
    String changedGain, changedNum, unchangedNum;

    NumCntInfo(int data) {
        this.data = data;
    }

    public void addCnt(int len) {
        cntArr[len]++;
    }

    @Override
    public int compareTo(NumCntInfo o) {
        return Base36Calculator.compareBase36(o.changedGain, this.changedGain); //오름차순으로 변환
    }

    public void calChangedGain() { //변했을 때 최대 이득
        this.changedGain = Base36Calculator.subtractBase36Num(calChangedNum(), calUnchangedNum());
    }

    private String calChangedNum() { //변했을 때 얻는 값
        return changedNum = Base36Calculator.addBase36Num(cntArr, 35);
    }

    private String calUnchangedNum() { //안 변했을때 얻는 값
        return unchangedNum = Base36Calculator.addBase36Num(cntArr, data);
    }
}

class NumCntInfoSorter {

    static void sort(NumCntInfo[] arr) {
        for (NumCntInfo numCntInfo : arr) {
            numCntInfo.calChangedGain();
        }
        Arrays.sort(arr);
    }
}

class Base36Calculator { //36진수 계산기

    private static final int MAX_DIGITS = 52; //더해졌을 때 아무리 커도 52자리임

    static int compareBase36(String A, String B) {
        if(A.length() != B.length()) return Integer.compare(A.length(), B.length()); // 내림차순
        for(int i = 0; i < A.length(); i++) {
            int aDigit = Base36NumChanger.charToInt(A.charAt(i));
            int bDigit = Base36NumChanger.charToInt(B.charAt(i));
            if(aDigit != bDigit) {
                return Integer.compare(aDigit, bDigit); //내림차순
            }
        }
        return 0; //같음
    }

    //문자열 더하기
    static String addBase36Num(String[] arr) {
        int[] result = new int[MAX_DIGITS];

        for(String num : arr) {
            int len = num.length();
            for(int i = 0; i < len; i++) {
                result[i] += Base36NumChanger.charToInt(num.charAt(len - i - 1));
            }
        }
        applyCarry(result);
        return base36ToStr(result, true);
    }

    //cnt 배열로 더하기
    static String addBase36Num(int[] cntArr, int data) {
        int[] result = new int[MAX_DIGITS];
        for(int i = 0; i < 50; i++) {
            result[i] = cntArr[i] * data;
        }
        applyCarry(result);
        return base36ToStr(result, true);
    }

    //올림 처리
    private static void applyCarry(int[] result) {
        for(int i = 0; i < MAX_DIGITS - 1; i++) {
            if(result[i] >= 36) {
                result[i + 1] += result[i] / 36;
                result[i] %= 36;
            }
        }
    }

    //배열을 문자열로
    //더하기 시 역방향, 빼기 시 정방향
    private static String base36ToStr(int[] num, boolean reverse) {
        StringBuilder result = new StringBuilder();
        for (int i : num) result.append(Base36NumChanger.intToChar(i));
        return LeadingZeroStripper.stripLeadingZero(reverse ? result.reverse().toString() : result.toString());
    }

    //A가 B보다 클때 문자열 빼기
    static String subtractBase36Num(String A, String B) {
        int len = Math.max(A.length(), B.length());
        A = padLeft(A, len);
        B = padLeft(B, len);
        int[] result = new int[len];
        int borrow = 0;
        for(int i = len - 1; i >= 0; i--) {
            int aDigit = Base36NumChanger.charToInt(A.charAt(i));
            int bDigit = Base36NumChanger.charToInt(B.charAt(i));
            int diff = aDigit - bDigit - borrow;
            if(diff < 0) {
                diff += 36;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[i] = diff;
        }
        return base36ToStr(result, false);
    }

    private static String padLeft(String s, int len) {
        return "0".repeat(len - s.length()) + s;
    }

}

//문자열 선행 0 제거 클래스
class LeadingZeroStripper {
    static String stripLeadingZero(String num) {
        int i = 0;
        while(i < num.length() - 1 && num.charAt(i) == '0') i++;
        return num.substring(i);
    }
}

//36진수 변환기
class Base36NumChanger {
    static char intToChar(int num) {
        if(num < 10) return (char) ('0' + num);
        else return (char) ('A' + num - 10);
    }

    static int charToInt(char num) {
        if(Character.isDigit(num)) return num - '0';
        else return num - 'A' + 10; //10부터 35;
    }
}




