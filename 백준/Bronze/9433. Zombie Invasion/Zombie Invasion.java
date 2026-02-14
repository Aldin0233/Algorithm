import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[20];
            for(int j = 0; j < 20; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            for(int j = 19; j > 0; j--) {
                arr[j - 1] += (arr[j] >> 1);
                arr[j] %= 2;
            }
            for(int j = 0; j < 20; j++) {
                ans.append(arr[j]).append(" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }






}

