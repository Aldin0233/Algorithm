
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int packageLine, soloLine;
    static final int INF = 987654321;
    
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
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        packageLine = soloLine = INF;
        for(int i = 0 ; i < M; i++) {
        	st = new StringTokenizer(br.readLine().trim());
        	packageLine = Math.min(packageLine, Integer.parseInt(st.nextToken()));
        	soloLine = Math.min(soloLine, Integer.parseInt(st.nextToken()));
        }
    }

    private static void testProcess() {
    	if(packageLine > soloLine * 6) {
    		result = N * soloLine;
    		return;
    	} 
    	result = ((N / 6) * packageLine) + Math.min(packageLine, (N % 6) * soloLine);
    }

    private static void testOutput() {
        System.out.println(result);
    }
}
