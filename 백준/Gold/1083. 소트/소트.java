import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, S;
	static int[] arr;
	static boolean[] visited;

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
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[N];
		S = Integer.parseInt(br.readLine().trim());
	}

	private static void testProcess() {
		int visit = 0;
		while (visit < N && S >= 0) {
			int max = 0;
			int idx = -1;
			int i = 0;
			int maxIdx = Math.min(N, S+1);
			while (i < maxIdx && i < N ) {
				if (!visited[i] && max < arr[i]) {
					idx = i;
					max = arr[i];
				} else if(visited[i]){
					maxIdx++;
				}
				i++;
			}
			if(idx != -1) {
				result.append(max).append(" ");
				visited[idx] = true;
				S -= check(idx);				
			} else {
				break;
			}
		}
		resultAppend();
	}

	private static int check(int idx) {
		int cnt = 0;
		for(int i = idx; i>= 0; i--) {
			if(!visited[i]) cnt++;
		}
		return cnt;
	}
	
	private static void resultAppend() {
		for(int i = 0 ; i < N; i++) {
			if(!visited[i]) result.append(arr[i]).append(" ");
		}
		result.setLength(result.length()-1);
	}


	private static void testOutput() {
		System.out.println(result);
	}
}