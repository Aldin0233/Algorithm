import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] distEachC, pricePerL;
	
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
		distEachC = new int[N-1]; pricePerL = new int[N-1];
		// N 입력 및 배열 생성;
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0; i<N-1; i++) {
			distEachC[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0; i<N-1; i++) {
			pricePerL[i] = Integer.parseInt(st.nextToken());
		}
		//데이터 입력
	}

	private static void testProcess() {
		int minPricePerL = pricePerL[0];
		int prevIdx = 0;;
		for(int i = 1 ; i<N-1; i++) {
			if(minPricePerL>pricePerL[i]) {
				result += checkDist(prevIdx, i) * minPricePerL;
				minPricePerL = pricePerL[i];
				prevIdx = i;
			}
		} //현재 주유소보다 싼 곳이 나올때까지 탐색해서 그 전까지 주유통을 무한으로 채우겠다.
		result += checkDist(prevIdx, N-1) * minPricePerL;
		//마지막 도시 이전 가장 저렴한 주유소부터 마지막 도시까지 남은 거리 이동
	}
	
	private static long checkDist(int prevIdx, int endIdx) {
		long dist = 0;
		for(int i = prevIdx; i<endIdx; i++) {
			dist += distEachC[i];
		}
		return dist; //이전 도시부터 목표 도시까지 거리 반환
	}

	private static void testOutput() {
		System.out.println(result);
	}
}