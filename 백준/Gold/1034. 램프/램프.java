import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, K;
	static Map<String, Integer> NM;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testProcess(0, 0);
		testOutput();
	}

	private static void testInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		NM = new HashMap<>();
		for(int i = 0; i<N; i++) {
			String str = br.readLine().trim();
			if(!NM.containsKey(str)) NM.put(str, 0);
			NM.put(str, NM.get(str)+1);
		}
		K = Integer.parseInt(br.readLine().trim());
		
	}

	private static void testProcess(int idx, int k) {
		for(String str : NM.keySet()) {
			int cnt = NM.get(str);
			if(cnt > result) {
				if(check(str)) result = cnt;
			}
		}
	}


	private static boolean check(String str) {
		int cnt = 0;
		for(int i = 0 ; i < M; i++) {
			if(str.charAt(i)=='0') {
				cnt++;
			}
		}
		if(cnt <= K && cnt % 2 == K % 2) return true;
		return false;
	}

	private static void testOutput() {
		System.out.println(result);
	}
}