import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    

    static int N, K;

    static int[] bottles;

    static int result;

    public static void main(String[] args){

        test();

    }

    private static void test(){

        testInput();

        testProcess();

        testOutput();

    }

    private static void testInput(){

        N = sc.nextInt();

        K = sc.nextInt();

        bottles = new int[K];

    }

    private static void testProcess(){

        if (K >= N) {

            

            return;

        }

        Arrays.fill(bottles, 1);

        N -= K;

        int i = 0;

        while(i<K) {

            while (N >= bottles[i]) {

                N -= bottles[i];

                bottles[i] = bottles[i] * 2;

            }

            if(N==0) return;

            i++;

        }

        i = 987654321;

        for(int j = 0; j<K; j++){

            i = Math.min(i, bottles[j]);

        }

        result = i - N;

    }

    private static void testOutput(){

        System.out.println(result);

    }

}