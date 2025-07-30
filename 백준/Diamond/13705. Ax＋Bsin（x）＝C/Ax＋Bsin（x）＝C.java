import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class Main {

    static int A, B, C;
    static final BigDecimal TWO = BigDecimal.valueOf(2);
    static final BigDecimal PI = pi();
    static final BigDecimal TWO_PI = PI.multiply(TWO);
    static final MathContext MC = new MathContext(30, RoundingMode.HALF_UP);
    static BigDecimal[] factorialsDp = new BigDecimal[60];
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        BigDecimal left = BigDecimal.ZERO;
        BigDecimal right = BigDecimal.valueOf(((double)(C + B) / A) + 1);
        preCalculateFactorials();
        for(int i = 0; i < 300; i++) {
            BigDecimal mid = left.add(right).divide(BigDecimal.valueOf(2), MC);
            if(calculate(mid).compareTo(BigDecimal.valueOf(C)) < 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        BigDecimal ans = left.add(right).divide(BigDecimal.valueOf(2), MC);
        ans = ans.setScale(6, RoundingMode.HALF_UP);

        System.out.printf(ans.toPlainString());
    }

    private static BigDecimal calculate(BigDecimal mid) {
        BigDecimal sinVal = sin(mid);
        return BigDecimal.valueOf(A).multiply(mid, MC).add(BigDecimal.valueOf(B).multiply(sinVal, MC), MC);
    }

    private static BigDecimal pi() {
        return new BigDecimal("3.141592653589793238462643383279");//PI 하드 코딩;
    }

    private static BigDecimal normalizeAngle(BigDecimal angle) {
        BigDecimal remain = angle.remainder(TWO_PI);
        if(remain.compareTo(PI) > 0) {
            remain = remain.subtract(TWO_PI, MC);
        } else if(remain.compareTo(PI.negate()) < 0) {
            remain = remain.add(TWO_PI, MC);
        }
        return remain;
    }

    private static void preCalculateFactorials() {
        factorialsDp[0] = BigDecimal.ONE;
        for(int i = 1; i < factorialsDp.length; i++) {
            factorialsDp[i] = factorialsDp[i - 1].multiply(BigDecimal.valueOf(i), MC);
        }
    }

    private static BigDecimal sin(BigDecimal mid) {
        BigDecimal angle = normalizeAngle(mid);
        BigDecimal angleSquared = angle.pow(2, MC);
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal xPower = angle;
        for(int i = 0; i < 30; i++) {
            int sign = (i % 2 == 0) ? 1 : -1;
            int factIdx = 2 * i + 1;
            BigDecimal term = xPower.divide(factorialsDp[factIdx], MC)
                    .multiply(BigDecimal.valueOf(sign), MC);
            result = result.add(term, MC);
            xPower = xPower.multiply(angleSquared, MC);
        }
        return result;
    }

}
