import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] P;
    static int nowBuildingRef;

    static int result;

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        testPreInput();
    }

    private static void testPreInput() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        unionInput();
        testProcessInput();
    }

    private static void unionInput() throws IOException {
        make();
        for(int i = 0 ;i <M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int A = find(Integer.parseInt(st.nextToken()));
            int B = find(Integer.parseInt(st.nextToken()));
            if(A!=B) {
                union(A, B);
            }
        }
    }

    private static void testProcessInput() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nowBuildingRef = find(Integer.parseInt(st.nextToken()));
        for(int i = 1 ; i<N; i++) {
            int newClass = find(Integer.parseInt(st.nextToken()));
            if(nowBuildingRef != newClass) {
                result++; nowBuildingRef = newClass;
            }
        }
        testOutput();
    }

    private static void make() {
        P = new int[N+1];
        for(int i = 1; i <= N; i++){
            P[i] = i;
        }
    }

    private static int find(int x){
        if(P[x] == x) return x;
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b){
        P[b] = a;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}
