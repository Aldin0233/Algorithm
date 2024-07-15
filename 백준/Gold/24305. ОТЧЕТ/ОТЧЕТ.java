import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] arr;
    static int[] result;

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
        arr = new int[N];
        result = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void testProcess() {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = 0; 
            } else {
                result[i] = s.peek() + 1; // 
            }
            s.push(i);
        }
    }

    private static void testOutput() {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < N; i++) {
            resultStr.append(result[i]).append(" ");
        }
        System.out.println(resultStr.toString().trim());
    }
}