import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int diceMax = 0;
	static int[] dicePaper;
	static int[] diceInfo = new int[3];

	static long result;

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
		dicePaper = new int[6];
		st = new StringTokenizer(br.readLine().trim());
		dicePaper[0] = Integer.parseInt(st.nextToken());
		dicePaper[2] = Integer.parseInt(st.nextToken());
		dicePaper[4] = Integer.parseInt(st.nextToken());
		dicePaper[5] = Integer.parseInt(st.nextToken());
		dicePaper[3] = Integer.parseInt(st.nextToken());
		dicePaper[1] = Integer.parseInt(st.nextToken());
		//마주 보는대로 01, 23, 45로 받았다.
	}

	private static void testProcess() {
		if(N == 1) {
			//N이 1일땐 큰면만 아래로 가게
			int sum = 0;
			for(int i = 0 ; i<6 ; i++) {
				diceMax = Math.max(dicePaper[i], diceMax);
				sum += dicePaper[i];
			}
			result = sum - diceMax;
			return;
		}
		int a = Math.min(dicePaper[0], dicePaper[1]);
		int b = Math.min(dicePaper[2], dicePaper[3]);
		int c = Math.min(dicePaper[4], dicePaper[5]);
		diceInfo[0] = Math.min(a, Math.min(b, c)); //가장 작은 면
		diceInfo[1] = Math.min(a+b, Math.min(b+c, a+c)); // 가장 작은 두면의 합
		diceInfo[2] = a + b + c; // 가장 작은 세면의 합
		result = diceInfo[2] *4;
		result += diceInfo[1] * 4; //기본적으로 아래에 붙어있는 두면과, 가장 위 삼면을 4개씩 묶어 처리하겠다.
		if(N !=2) {
			result += ((long) diceInfo[1]) * (N-2) * 8;
			result += ((long) diceInfo[0]) * (N-2) * (N-2) * 5;
			result += ((long) diceInfo[0]) * (N-2) * 4; // 가장 아래 한 면짜리 모서리를 따로 더한다.
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}

}