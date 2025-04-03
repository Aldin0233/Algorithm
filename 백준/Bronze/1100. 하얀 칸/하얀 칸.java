import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        for(int i = 0 ; i < 8; i++) {
            int now = i % 2;
            String str = br.readLine();
            for(int j = 0 ; j < 8; j++) {
                if(j % 2 == now && str.charAt(j) == 'F') {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }



}
