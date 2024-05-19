import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[][] paper;
	
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		result=testProcess(0, 0, N);
		testOutput();
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		paper = new int[N][N];
		for(int i = 0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j = 0 ; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int[] testProcess(int r, int c, int size) {
		int[] tmp = new int[3];
		if(size==1) {
			if(paper[r][c]==-1) {
				tmp[0] = 1;
			} else if(paper[r][c]==0) {
				tmp[1] = 1;
			} else {
				tmp[2] = 1;
			}
			return tmp;
		}
		int ns = size/3;
		for(int i = 0 ; i< 3; i++) {
			for(int j = 0 ; j<3; j++) {
				int[] tmpResult = testProcess(r + i*ns, c + j*ns, ns);
				for(int k = 0 ; k<3; k++) {
					tmp[k] += tmpResult[k];
				}
			}
		}
		if((tmp[0]==0 && (tmp[1]==0 || tmp[2]==0))|| (tmp[1]==0 && tmp[2]==0)) {
			for(int i = 0 ; i<3; i++) {
				if(tmp[i]==9) {
					tmp[i] = 1;
				}
			}
		}
		
		return tmp;
		
	}

	private static void testOutput() {
		System.out.println(result[0]+"\n" + result[1] +"\n" + result[2]);
	}
}