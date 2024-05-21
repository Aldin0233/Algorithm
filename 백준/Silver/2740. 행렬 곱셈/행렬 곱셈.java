import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] A, B, C;
	static int N, M, K;
	
	static StringBuilder result = new StringBuilder();
	
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		for(int i = 0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j = 0 ; j<M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine().trim());
		st.nextToken();
		K = Integer.parseInt(st.nextToken());
		B = new int[M][K];
		for(int i = 0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j = 0 ; j<K; j++) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		C = new int[N][K];
	}

	private static void testProcess() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                C[i][j] = 0;
                for (int k = 0; k < M; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
                result.append(C[i][j]).append(" ");
            }
            result.append("\n");
        }
    }

	private static void testOutput() {
		System.out.println(result);
	}
}