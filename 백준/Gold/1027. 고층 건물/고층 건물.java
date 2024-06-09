import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] building;
    static boolean[][] canSee;

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
        N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        building = new int[N];
        for (int i = 0; i < N; i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }
        canSee = new boolean[N][N];
    }

    private static void testProcess() {

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSee(i, j)) {
                    canSee[i][j] = true;
                    canSee[j][i] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int visibleCount = 0;

            for (int j = 0; j < N; j++) {
                if (i != j && canSee[i][j]) {
                    visibleCount++;
                }
            }

            result = Math.max(result, visibleCount);
        }
    }

    private static boolean isSee(int start, int end) {
    	int startB = building[start]; int endB = building[end];
        double slope = (double) (startB - endB) / (start - end);
    	if(startB > endB) {
        	for(int i = start + 1; i < end; i++) {
        		if(building[i] < endB) {
        			continue;
        		} else if(building[i] >= startB) {
        			return false;
        		}
        		double currentSlope = (double) (building[i] - startB) / (i - start);
        		if(currentSlope >= slope) {
        			return false;
        		}
        	}
        	return true;
    	} 

        for (int i = start + 1; i < end; i++) {
            if(building[i] < startB) {
            	continue;
            } else if(building[i] >= endB) {
            	return false;
            }
            double currentSlope = (double) (building[i] - startB) / (i - start);
    		if(currentSlope >= slope) {
    			return false;
    		}
        }
        return true;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}