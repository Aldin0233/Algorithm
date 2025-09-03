import java.io.*;
import java.util.*;

public class Main {

    static boolean[] selfNumber = new boolean[10001];
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        Arrays.fill(selfNumber, true);

        for(int i = 1; i <= 10000; i++) {
            if(selfNumber[i]) {
                ans.append(i).append('\n');
                findNextSelfNumber(i);
            }
        }

        System.out.println(ans);
    }

    private static void findNextSelfNumber(int n) {
        int now = selfNumbering(n);
        while(now <= 10000 && selfNumber[now]) { //selfNumber가 이미 아니라면 그 다음부터 똑같은 경로
            selfNumber[now] = false;
            now = selfNumbering(now);
        }
    }

    private static int selfNumbering(int n) {
        int num = n;
        int res = n;
        while(num > 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }



}
