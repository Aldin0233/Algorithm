import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, K;
	static long[] word;
	static boolean[] useChar = new boolean[26];
	
	static int result;
	
	static {
		useChar[0] = useChar[2] = useChar[8] = useChar[13] = useChar[19] = true;
	}
	
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
		N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		word = new long[N];
		for(int i = 0 ; i<N; i++) {
			word[i] = toBitmask(br.readLine().trim());
		}
	}

	private static void testProcess() {
		if(K<5) {
			return;
		} else if(K==26) {
			result = N;
			return;
		}
		comb(0, 5);
	}

	private static void comb(int st, int k) {
		if(k==K) {
			check(toBitmask());
			return;
		}
		for(int i = st; i<26; i++) {
			if(useChar[i]) {
				continue;
			}
			useChar[i] = true;
			comb(i + 1, k+1);
			useChar[i] = false;
		}
	}
	
	private static long toBitmask() {
		long l = 0L;
		for(int i = 0 ; i < 26; i++) {
			if(useChar[i]) {
				l = (l | (1 << i));
			}
		}
		return l;
	}
	
	private static long toBitmask(String str) {
		long l = 0L;
		for(int i = 0 ; i < str.length(); i++) {
			l = (l | (1 << str.charAt(i) - 'a'));
		}
		return l;
	}

	private static void check(long bitMask) {
		int tmp = 0;
		for(int i = 0 ; i<N; i++) {
			if((word[i] & bitMask) == word[i]) {
				tmp++;
			}
		}
		result = Math.max(tmp, result);
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}