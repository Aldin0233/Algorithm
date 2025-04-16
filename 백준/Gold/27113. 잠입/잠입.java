import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int curY = 1;
        ans = true;
        for(int i = 0 ; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            //레이저 없으니 통과
            if(x == 0) continue;
            //레이저 하나
            else if(x == 1) {
                int c = Integer.parseInt(st.nextToken());
                char d = st.nextToken().charAt(0);
                if(d == 'L') {
                    curY = Math.max(curY, c + 1);
                } else if(d == 'R') {
                    if(curY >= c) {
                        ans = false;
                        break;
                    }
                }
            } else {
                int c1 = Integer.parseInt(st.nextToken());
                char d1 = st.nextToken().charAt(0);
                int c2 = Integer.parseInt(st.nextToken());
                char d2 = st.nextToken().charAt(0);
                if(d1 == d2) {
                    if(d1 == 'L') {
                        int p = Math.max(c1, c2);
                        curY = Math.max(curY, p + 1);
                    } else {
                        int p = Math.min(c1, c2);
                        if(curY >= p) {
                            ans = false;
                            break;
                        }
                    }
                } else {
                    int left = d1 == 'L' ? c1 : c2;
                    int right = d1 == 'L' ? c2 : c1;

                    if(right < left) {
                        //오른쪽으로 통과해야 함, 레이저 센서랑 같은 열이여도 한칸 더 가야함
                        if(curY >= right) {
                            curY = Math.max(curY, left + 1);
                        }
                        //왼쪽으로 통과 가능시 별도 처리 필요 없음
                    } else {
                        //얘는 사실 막힌거임
                        if(right == left + 1) {
                            ans = false;
                            break;
                        }
                        //이미 중앙보다 넘어서 간 경우 로봇한테 잡힘
                        if(curY >= right) {
                            ans = false;
                            break;
                        }
                        curY = Math.max(curY, left + 1);
                    }
                }
            }
            if(curY > M) {
                ans = false;
                break;
            }
        }

        System.out.println(ans ? "YES" : "NO");
    }

}

