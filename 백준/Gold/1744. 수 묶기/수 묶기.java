
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int ansScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int idx = 0;

        //-끼리 곱해줌
        for(int i = 0; i < N - 1; i += 2) {
            if(arr[i] < 0 && arr[i + 1] < 0) {
                ansScore += arr[i] * arr[i + 1];
                continue;
            } else if(arr[i] < 0 && arr[i + 1] == 0) {
                idx = i + 2;
            } else if(arr[i] == 0) {
                idx = i + 1;
            } else {
                idx = i;
            }
            break;
        }
        //+끼리 곱해줌
        for(int i = N - 1; i >= idx; i--) {
            if(arr[i] > 1 && i - 1 >= 0 && arr[i - 1] > 1) { //음수 가능성은 탐방하고 왔고, 처음이 1 혹은 0으로만 차있을때 확인
                ansScore += arr[i] * arr[i - 1];
                i--;
            } else {
                ansScore += arr[i];
            }
        }
        System.out.println(ansScore);
    }


}

