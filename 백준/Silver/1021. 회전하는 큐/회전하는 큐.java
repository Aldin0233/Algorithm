import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static int[] arr, order;
	static boolean[] visited;
	static int stIdx, edIdx;

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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		order = new int[M];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < M; i++) {
			order[i] = Integer.parseInt(st.nextToken()) - 1;
		}
	}

	private static void testProcess() {
		stIdx = N;
		for (int i = 0; i < M; i++) {
			result += checkLeftAndRight(order[i], stIdx%N);
			visited[order[i]] = true;
			stIdx = order[i] + 1;
			while(i<M-1 && visited[stIdx%N]) stIdx++;
		}
	}

	private static String check() {
		String tmp = "";
		for(int i = stIdx ; i< stIdx + N; i++) {
			if(!visited[i%N]) {
				tmp += (i%N) + " ";
			}
		}
		return tmp;
	}

	private static int checkLeftAndRight(int order, int idx) {
		if(order == idx) {
			return 0;
		}
		int lIdx = idx+1;
		int rIdx = idx+N-1;
		int lCnt = 1; int rCnt = 1;
		while(idx != lIdx%N) {
			if(order == lIdx%N) break;
			if(!visited[lIdx%N]) lCnt++;
			lIdx++;
		}
		while(idx != rIdx%N) {
			if(order == rIdx%N) break;
			if(!visited[rIdx%N]) rCnt++;
			rIdx--;
		}
		return Math.min(lCnt, rCnt);
	}

	private static void testOutput() {
		System.out.println(result);
	}
}