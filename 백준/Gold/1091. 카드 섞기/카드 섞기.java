import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] P, S;
    
    //문제 지문이 너무 이상한데 아무튼 출력이 같아질 때까지 이것저것 만져본 코드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N];
        S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            S[Integer.parseInt(st.nextToken())] = i;
        }
        int[] originalArr = new int[N];
        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i++) {
            arr[i] = i % 3;
            originalArr[i] = i % 3;
        }
        int result = 0;
        while(!Arrays.equals(arr,P) && !(result !=0 && Arrays.equals(arr, originalArr))) {
            int[] arrTmp = new int[N];
            for(int j=0;j<N;j++) arrTmp[S[j]] = arr[j];

            arr = arrTmp.clone();
            result++;
        }
        if(Arrays.equals(arr, P)) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

}
