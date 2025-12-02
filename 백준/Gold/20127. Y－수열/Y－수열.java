import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int increaseK = checkI();
        int decreaseK = checkD();

        if(increaseK != -1 && decreaseK != -1) ans = Math.min(increaseK, decreaseK);
        else if(increaseK != -1) ans = increaseK;
        else ans = decreaseK;

        System.out.println(ans);
    }

    private static int checkI() {
        int bad = 0, badIdx = -1;
        for(int i = 0; i < N; i++) {
            int j = (i + 1) % N;
            if(arr[i] > arr[j]) {
                bad++;
                badIdx = i;
                if(bad >= 2) return -1; //증가 수열이 2번 이상 관측
            }
        }

        if(bad == 0) return 0; //이미 증가 수열

        return (badIdx + 1) % N;
    }

    private static int checkD() {
        int bad = 0, badIdx = -1;
        for(int i = 0; i < N; i++) {
            int j = (i + 1) % N;
            if(arr[i] < arr[j]) {
                bad++;
                badIdx = i;
                if(bad >= 2) return -1; //감소 수열이 2번 이상 관측
            }
        }

        if(bad == 0) return 0; //이미 감소 수열

        return (badIdx + 1) % N;
    }

}


