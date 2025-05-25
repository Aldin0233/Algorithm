import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static int N; //용액 갯수
    static int diff = Integer.MAX_VALUE; //최소 차잇값
    static int[] arr; //정렬된 용액 목록
    static int leftAns, rightAns; //출력한 좌측, 우측값(오름차순)
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger a = new BigInteger(br.readLine());
        BigInteger b = new BigInteger(br.readLine());

        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
    }




}