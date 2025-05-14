import java.io.*;
import java.util.*;

public class Main {

    static int N; //용액 갯수
    static int diff = Integer.MAX_VALUE; //최소 차잇값
    static int[] arr; //정렬된 용액 목록
    static int leftAns, rightAns; //출력한 좌측, 우측값(오름차순)
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;
        while(left < right) {
            int sum = arr[left] + arr[right]; //기본 합
            int newDiff = Math.abs(sum); //0에 가까운 정도
            if (newDiff < diff) {
                leftAns = arr[left];
                rightAns = arr[right];
                diff = newDiff;
            }
            if(sum > 0) { //sum이 컷으면 알칼리쪽으로
                right--;
            } else if(sum < 0) { //산성쪽으로
                left++;
            } else { //0이면 탐지 멈춤
                break;
            }
        }
        System.out.println(ans.append(leftAns).append(" ").append(rightAns));
    }




}
