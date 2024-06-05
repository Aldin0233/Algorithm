import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int C, N;
	static int[] dp;
	static int[][] info;
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
		C = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
		dp = new int[C+101]; info = new int[N+1][2];
		for(int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
	}

	private static void testProcess() {
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for(int i = 1 ; i <= N; i++) {
			int cost = info[i][0];
			int client = info[i][1];
			for(int j = client; j <= C+100; j++) {
				if (dp[j - client] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - client] + cost);
                }
			}
		}
		result = INF;
		for(int i = C; i <= C+100; i++) {
			result = Math.min(result, dp[i]);
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}