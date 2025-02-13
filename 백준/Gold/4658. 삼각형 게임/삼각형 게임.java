import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] triangleInfo;
    private static List<int[]> permutations;
    private static int sum;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        permutations = makePermutation();
        do {
            triangleInfo = new int[6][3];
            for (int i = 0; i < 6; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    triangleInfo[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sum = -1;

            findPermutation();

            if (sum == -1) {
                ans.append("none\n");
            } else {
                ans.append(sum).append("\n");
            }
        } while (br.readLine().charAt(0) != '$');

        ans.setLength(ans.length() - 1);
        System.out.println(ans);
    }

    private static void findPermutation() {
        for(int[] permutation : permutations) {
            for(int j = 0 ; j < 3 ; j++) {
                for(int k = 0 ; k < 3 ; k++) {
                    if(checkConnect(permutation[0], permutation[1], (j + 1) % 3, k)) {
                        continue;
                    }
                    for(int l = 0 ; l < 3 ; l++) {
                        if(checkConnect(permutation[1], permutation[2], (k + 1) % 3, l)) {
                            continue;
                        }
                        for(int m = 0 ; m < 3 ; m++) {
                            if(checkConnect(permutation[2], permutation[3], (l + 1) % 3, m)) {
                                continue;
                            }
                            for(int n = 0 ; n < 3 ; n++) {
                                if(checkConnect(permutation[3], permutation[4], (m + 1) % 3, n)) {
                                    continue;
                                }
                                for(int p = 0 ; p < 3 ; p++) {
                                    if(checkConnect(permutation[4], permutation[5], (n + 1) % 3, p)) {
                                        continue;
                                    }
                                    if(checkConnect(permutation[5], permutation[0], (p + 1) % 3, j)) {
                                        continue;
                                    }
                                    sum = Math.max(sum, makeSum(permutation, new int[]{j, k, l, m, n, p}));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static int makeSum(int[] permutation, int[] rotation) {
        int tmp = 0;
        for(int i = 0 ; i < 6 ; i++) {
            tmp += triangleInfo[permutation[i]][idx(rotation[i])];
        }
        return tmp;
    }

    private static int idx(int i) {
        return (i + 2) % 3;
    }

    private static boolean checkConnect(int i, int j, int iIdx, int jIdx) {
        return triangleInfo[i][iIdx] != triangleInfo[j][jIdx];
    }

    private static List<int[]> makePermutation() {
        List<int[]> result = new ArrayList<>();
        int[] arr = {0, 1, 2, 3, 4, 5};
        permute(arr, 0, result);
        return result;
    }

    private static void permute(int[] arr, int start, List<int[]> result) {
        if (start == arr.length) {
            int[] copy = arr.clone();
            result.add(copy);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            swap(arr, start, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
