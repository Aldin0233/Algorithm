
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr, tmp; //머지 소트용 임시 배열
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        tmp = new int[N];

        mergeSort(0, N - 1);

        System.out.println(ans);
    }

    private static void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private static void merge(int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) { //왼쪽이 더 작을 때
                tmp[k++] = arr[i++];
            } else { //오른 쪽이 더 작을 때
                tmp[k++] = arr[j++];
                ans += (mid - i + 1); //왼쪽 원수 갯수 더해서 버블 일어날 횟수 더하기 (중간으로부터 현재 왼쪽 인덱스 + 1
            }
        }
        while (i <= mid) tmp[k++] = arr[i++];
        while (j <= right) tmp[k++] = arr[j++];
        for(int idx = left; idx <= right; idx++) arr[idx] = tmp[idx];
    }

}

