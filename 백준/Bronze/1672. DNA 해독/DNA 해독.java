import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static String str;
    static char[][] arr =
            {{'A', 'C', 'A', 'G'},
            {'C', 'G', 'T', 'A'},
            {'A', 'T', 'C', 'G'},
            {'G', 'A', 'G', 'T'}};
    static char ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        int idx = N - 2;
        char lastChar = str.charAt(str.length() - 1);
        while(idx >= 0) {
            lastChar = solve(str.charAt(idx--), lastChar);
        }
        System.out.println(lastChar);
    }

    private static char solve(char a, char b) {
        int aIdx = calIdx(a);
        int bIdx = calIdx(b);
        return arr[aIdx][bIdx];
    }

    private static int calIdx(char a) {
        if(a == 'A') return 0;
        else if(a == 'G') return 1;
        else if(a == 'C') return 2;
        return 3;
    }


}


