import java.util.Scanner;

public class Main {

    //이전 구간이 반복되는 구조
    //점화식(구간 합) 2^0 부터 S(0) = 1
    //2^n까지 합은 S(n) = 2*S(n-1) + 2^n - 2^(n-1)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long A = sc.nextLong();
        long B = sc.nextLong();

        long calculatedA = calculateSumUpTo(A-1);
        long calculatedB = calculateSumUpTo(B);

        System.out.println(calculatedB - calculatedA);
    }

    private static long calculateSumUpTo(long number) {
        long sum = 0;

        while (number > 0) {
            long highestPowerOfTwo = Long.highestOneBit(number); // A 이하의 가장 큰 2의 거듭제곱
            sum += calculatePowerSum(highestPowerOfTwo); // 해당 구간까지의 합 추가
            number -= highestPowerOfTwo; // 구간 처리 후 남은 값 계산
        }

        return sum;
    }

    // 점화식으로 1부터 2^n까지의 합 계산
    private static long calculatePowerSum(long power) {
        long sum = 1; // S(0) = 1 초기값
        long powerOfTwo = 1; // 2^0

        while (powerOfTwo < power) {
            long nextPowerOfTwo = powerOfTwo * 2; // 2^(n+1)
            sum = 2 * sum + nextPowerOfTwo - powerOfTwo; // 점화식 적용
            powerOfTwo = nextPowerOfTwo; // 업데이트 2^n
        }

        return sum;
    }

}
