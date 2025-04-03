import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] A, B;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        do {
            str = str.toUpperCase();
            int cnt = 0;
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') cnt++;
            }
            ans.append(cnt).append("\n");
            str = br.readLine();
        } while(!str.equals("#"));

        System.out.println(ans);
    }

}