import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int L, C;
	static int[] canChar;
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
		st = new StringTokenizer(br.readLine().trim());
		L = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		canChar = new int[C];
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0 ; i<C; i++) {
			canChar[i] = st.nextToken().charAt(0);
		}
		visited = new boolean[C];
	}

	private static void testProcess() {
		Arrays.sort(canChar);
		makePassword(0, 0, 0, 0);
	}

	private static void makePassword(int idx, int selectCnt, int vowel, int consonant) {
		if(selectCnt >= L) {
			if(vowel>=1 && consonant>=2) {
				add();
			}
			return;
		}
		for(int i = idx ; i < C; i++) {
			visited[i] = true;
			int nowChar = canChar[i];
			if(nowChar == 97 || nowChar == 101 || nowChar == 105 || nowChar == 111 || nowChar == 117) {
				makePassword(i +1, selectCnt +1, vowel +1, consonant);
			} else {
				makePassword(i +1, selectCnt +1, vowel, consonant +1);
			}
			visited[i] = false;
		}
	}

	private static void add() {
		for(int i = 0 ; i<C; i++) {
			if(visited[i]) result.append((char) canChar[i]);
		}
		result.append("\n");
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}