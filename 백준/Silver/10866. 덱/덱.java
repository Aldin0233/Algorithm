import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Main {

    static int N;
    static Deque<Integer> deque = new ArrayDeque<>();
    static Map<String, Runnable> tasksNoArgs = new HashMap<>();
    static Map<String, Consumer<Integer>> tasksWithArgs = new HashMap<>();
    static StringBuilder ans = new StringBuilder(); //결과

    static {
        tasksNoArgs.put("pop_front", () -> ans.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n"));
        tasksNoArgs.put("pop_back", () -> ans.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n"));
        tasksNoArgs.put("size", () -> ans.append(deque.size()).append("\n"));
        tasksNoArgs.put("empty", () -> ans.append(deque.isEmpty() ? 1 : 0).append("\n"));
        tasksNoArgs.put("front", () -> ans.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n"));
        tasksNoArgs.put("back", () -> ans.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n"));
        tasksWithArgs.put("push_front", (x) -> deque.addFirst(x));
        tasksWithArgs.put("push_back", (x) -> deque.addLast(x));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] task = br.readLine().split(" ");
            if(task.length == 2) {
                tasksWithArgs.get(task[0]).accept(Integer.parseInt(task[1]));
            } else {
                tasksNoArgs.get(task[0]).run();
            }
        }

        System.out.println(ans);
    }


}
