import java.io.*;
import java.util.*;

public class Main {

    static int S1, S2, S3;
    static int[] frequency;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S1 = Integer.parseInt(st.nextToken()); S2 = Integer.parseInt(st.nextToken()); S3 = Integer.parseInt(st.nextToken());
        frequency = new int[S1 + S2 + S3 + 1];
        for(int i = 1; i <= S1; i++) {
            for(int j = 1; j <= S2; j++) {
                for(int k = 1; k <= S3; k++) {
                    frequency[i + j + k]++;
                }
            }
        }
        ans = 0;
        int maxFrequency = 0;
        for(int i = 3; i < frequency.length; i++) {
            //빈도 우선, 낮은거 우선
            if(frequency[i] > maxFrequency) {
                maxFrequency = frequency[i];
                ans = i;
            }
        }
        System.out.println(ans);
    }




}