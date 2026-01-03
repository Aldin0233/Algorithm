import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //2453
    static int N, M;
    static int[] P;
    static int[] size;
    static List<int[]> rivalEdges;
    static Set<Integer>[] compressedRival;
    static int result;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = N; i >= 4; i--) {
            if(check(i)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(ans);
    }

    static boolean check(int n) {
        while(n > 0) {
            int remain = n % 10;
            if(remain == 4 || remain == 7) {
                n /= 10;
            } else {
                return false;
            }
        }
        return true;
    }



}




