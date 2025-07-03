import java.io.*;
import java.util.*;

public class Main {

    static double[] A = new double[3], B = new double[3], C = new double[3];
    static final int REPEAT = 100;
    static final double EPSILON = 1e-8;
    static double ansDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = toDoubleArr(st);
        B = toDoubleArr(st);
        C = toDoubleArr(st);
        ansDistance = ternarySearch();
        System.out.printf("%.10f\n", ansDistance);
    }

    static double[] toDoubleArr(StringTokenizer st) {
        return new double[] {
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken())
        };
    }

    // 삼분 탐색 //추가 공부 중, 응용
    static double ternarySearch() {
        double left = 0.0;
        double right = 1.0; //100분율로 접근
        for (int i = 0; i < REPEAT; i++) {
            double t1 = (2 * left + right) / 3; //왼쪽 1/3 지점
            double t2 = (left + 2 * right) / 3; //오른 1/3 지점

            double d1 = calDistance(t1);
            double d2 = calDistance(t2);

            if (d1 < d2) { //d1이 작은 경우 찾는 중간 지점이 더 왼쪽에 있음
                right = t2;
            } else { //d2가 작은 경우 찾는 지점이 더 오른쪽에 있음
                left = t1;
            }
        }
        return calDistance((left + right) / 2);
    }

    static double calDistance(double t) { //특정 지점에서 C까지의 거리
        double[] point = pointOnSegment(t);
        double dx = point[0] - C[0];
        double dy = point[1] - C[1];
        double dz = point[2] - C[2];
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    static double[] pointOnSegment(double t) { //A 부터 B까지 선을 그었을 때 그 위에 있는 점 하나
        return new double[] {
                A[0] + ((B[0] - A[0]) * t),
                A[1] + ((B[1] - A[1]) * t),
                A[2] + ((B[2] - A[2]) * t)
        };
    }
}
