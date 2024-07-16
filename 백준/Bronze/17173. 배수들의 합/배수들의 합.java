import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int[] Karr;
	static boolean[] visited;
	
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
		st = new StringTokenizer(br.readLine().trim());		
		Karr = new int[M];
		for(int i = 0 ; i<M; i++) {
			Karr[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void testProcess() {
		visited = new boolean[N+1];
		for(int i = 0 ; i<M ; i++) {
			int bIdx = Karr[i];
			if(visited[bIdx]) {
				continue;
			}
			while(bIdx<=N) {
				if(!visited[bIdx]) {
					visited[bIdx] = true;
					result += bIdx;
				}
				bIdx += Karr[i];
			}
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}