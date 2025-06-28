
import java.io.*;
import java.util.*;

public class Main {

    static String S, T;
    static int[] weights, ballWeights;
    static boolean[][] dp;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        ans = reverseTraking() ? 1 : 0;
        System.out.println(ans);
    }

    private static boolean reverseTraking() {
        int stIdx = 0;
        int edIdx = T.length() - 1;
        boolean backSide = true;
        while(calLength(stIdx, edIdx) > S.length()) {
            char c = T.charAt(backSide ? edIdx : stIdx);
            if(backSide) edIdx--; //일단 뒤에서 하나 뗌
            else stIdx++;
            if(c == 'B') {
                backSide = !backSide; //B였으면 방향 전환
            }
        }
        String sameLengthStr = T.substring(stIdx, edIdx + 1);
        if(backSide) {
            return sameLengthStr.equals(S);
        } else {
            for(int i = 0; i < sameLengthStr.length(); i++) {
                if(S.charAt(i) != sameLengthStr.charAt(sameLengthStr.length() - 1 - i)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static int calLength(int st, int ed) {
        return ed - st + 1;
    }


}




