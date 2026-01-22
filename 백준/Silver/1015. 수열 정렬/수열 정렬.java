import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr, sortedArr, resultArr;
    static boolean[] used;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sortedArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sortedArr[i] = arr[i];
        }
        Arrays.sort(sortedArr);
        used = new boolean[N];
        resultArr = new int[N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(used[j] || sortedArr[j] != arr[i]) continue;
                used[j] = true;
                resultArr[i] = j;
                break;
            }
        }
        for(int i = 0; i < N; i++) {
            ans.append(resultArr[i]).append(" ");
        }


        System.out.println(ans);
    }





}




