import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] crane, box;

    static int result;

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        testInput();
        testProcess();
        testOutput();
    }

    private static void testInput() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        crane = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0 ; i < N; i++){
            crane[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine().trim());
        box = new int[M];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0 ; i < M; i++){
            box[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void testProcess() {
        Arrays.sort(crane);
        Arrays.sort(box);
        if(box[M - 1] > crane[N - 1]){
            result = -1;
            return;
        }
        boolean[] movedBox = new boolean[M];
        int moved = 0;
        while(moved < M) {
            int nIdx = N-1;
            int mIdx = M-1;
            while(nIdx >= 0 && mIdx >= 0) {
                if(movedBox[mIdx]) mIdx--;
                else if(box[mIdx] > crane[nIdx]) mIdx--;
                else if(box[mIdx] <= crane[nIdx]) {
                    nIdx--;
                    movedBox[mIdx--] = true;
                    moved++;
                }
            }
            result++;
        }

    }

    private static void testOutput() {
        System.out.println(result);
    }
}
