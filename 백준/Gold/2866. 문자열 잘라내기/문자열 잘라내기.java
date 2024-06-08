import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C;
    static String[] arr;
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
        st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new String[C];
        Arrays.fill(arr, "");
        for (int i = 0; i < R; i++) {
            String readLine = br.readLine().trim();
            for (int j = 0; j < C; j++) {
                arr[j] += readLine.charAt(j);
            }
        }
    }

    private static void testProcess() {
        int low = 0;
        int high = result = R - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (hasDuplicate(mid)) {
                high = result = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }

    private static boolean hasDuplicate(int cutIndex) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < C; i++) {
            String suffix = arr[i].substring(cutIndex);
            if (!set.add(suffix)) {
                return true;
            }
        }
        return false;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}