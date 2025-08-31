import java.io.*;
import java.util.*;

public class Main {

    static int T, K;
    static PriorityQueue<Integer> maxPq, minPq;
    static Map<Integer, Integer> minRemoved, maxRemoved;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++) {
            K = Integer.parseInt(br.readLine());
            maxPq = new PriorityQueue<>(Collections.reverseOrder()); //최댓값 용 내림차순
            minPq = new PriorityQueue<>(); //최솟값용 오름차순
            minRemoved = new HashMap<>();
            maxRemoved = new HashMap<>();
            for(int j = 0; j < K; j++) {
                String order = br.readLine();
                char ch = order.charAt(0);
                int num = Integer.parseInt(order.substring(2));
                if(ch == 'I') {
                    //양쪽에 추가
                    maxPq.add(num);
                    minPq.add(num);
                } else {
                    if(maxPq.isEmpty() || minPq.isEmpty()) {
                        continue;
                    }
                    //찾은 값을 제거
                    if(num == 1) {
                        deleteOtherSideRemoved(false);
                        if(maxPq.isEmpty()) continue;
                        int max = maxPq.poll();
                        maxRemoved.put(max, maxRemoved.getOrDefault(max, 0) + 1);
                    }
                    else {
                        deleteOtherSideRemoved(true);
                        if(minPq.isEmpty()) continue;
                        int min = minPq.poll();
                        minRemoved.put(min, minRemoved.getOrDefault(min, 0) + 1);
                    }
                }
            }
            deleteOtherSideRemoved(false);
            deleteOtherSideRemoved(true);
            if(maxPq.isEmpty() || minPq.isEmpty()) {
                ans.append("EMPTY\n");
            } else {
                ans.append(maxPq.poll()).append(" ").append(minPq.poll()).append("\n");
            }
        }
        System.out.println(ans);
    }

    //삭제한 걸 나중에 따로 지우는 메서드
    private static void deleteOtherSideRemoved(boolean isMin) {
        if(isMin) {
            while(!minPq.isEmpty() && maxRemoved.getOrDefault(minPq.peek(), 0) > 0) {
                int val = minPq.poll();
                maxRemoved.put(val, maxRemoved.get(val) - 1);
            }
        } else {
            while(!maxPq.isEmpty() && minRemoved.getOrDefault(maxPq.peek(), 0) > 0) {
                int val = maxPq.poll();
                minRemoved.put(val, minRemoved.get(val) - 1);
            }
        }
    }





}