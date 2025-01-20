import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static List<Customer> customers;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int taxiX = Integer.parseInt(st.nextToken()) - 1;
        int taxiY = Integer.parseInt(st.nextToken()) - 1;

        customers = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            map[startX][startY] = 2; // 고객 출발지 표시
            customers.add(new Customer(startX, startY, endX, endY));
        }

        Taxi taxi = new Taxi(taxiX, taxiY, K);
        int result = simulateTaxiOperation(taxi);
        System.out.println(result);
    }

    // 시뮬레이션 실행
    private static int simulateTaxiOperation(Taxi taxi) {
        for (int i = 0; i < M; i++) {
            CustomerInfo nextCustomer = findClosestCustomer(taxi);
            if (nextCustomer == null) return -1;
            if (!moveToDestination(taxi, nextCustomer)) return -1;
        }
        return taxi.fuel;
    }

    // 가장 가까운 고객 찾기
    private static CustomerInfo findClosestCustomer(Taxi taxi) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new Point(taxi.x, taxi.y, 0));

        CustomerInfo closest = null;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (visited[p.x][p.y]) continue;
            visited[p.x][p.y] = true;

            if (closest != null && p.time > closest.distance) break;

            if (map[p.x][p.y] == 2) {
                for (Customer customer : customers) {
                    if (customer.isAt(p.x, p.y)) {
                        CustomerInfo newCustomer = new CustomerInfo(customer, p.time);
                        if (closest == null || newCustomer.isCloserThan(closest)) {
                            closest = newCustomer;
                        }
                        break;
                    }
                }
            }

            moveTaxi((Queue<Point>) queue, visited, p);
        }

        if (closest != null) {
            taxi.useFuel(closest.distance);
            map[closest.customer.startX][closest.customer.startY] = 0;
        }
        return closest;
    }

    // 목적지로 이동
    private static boolean moveToDestination(Taxi taxi, CustomerInfo customerInfo) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new Point(customerInfo.customer.startX, customerInfo.customer.startY, 0));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (visited[p.x][p.y]) continue;
            visited[p.x][p.y] = true;
            if(p.time > taxi.fuel) {
                break;
            }
            if (p.x == customerInfo.customer.endX && p.y == customerInfo.customer.endY) {
                taxi.updatePosition(p.x, p.y);
                taxi.addFuel(p.time); // 도착 시 연료 충전
                customers.remove(customerInfo.customer);
                return true;
            }

            moveTaxi((Queue<Point>) queue, visited, p);
        }
        return false;
    }

    private static void moveTaxi(Queue<Point> queue, boolean[][] visited, Point p) {
        for (int d = 0; d < 4; d++) {
            int nx = p.x + dr[d];
            int ny = p.y + dc[d];
            if (isValid(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
                queue.add(new Point(nx, ny, p.time + 1));
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Taxi {
        int x, y, fuel;

        Taxi(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
        }

        void useFuel(int amount) {
            this.fuel -= amount;
        }

        void addFuel(int amount) {
            this.fuel += amount;
        }

        void updatePosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Point {
        int x, y, time;

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class CustomerInfo {
        Customer customer;
        int distance;

        CustomerInfo(Customer customer, int distance) {
            this.customer = customer;
            this.distance = distance;
        }

        boolean isCloserThan(CustomerInfo other) {
            if (this.distance != other.distance) return this.distance < other.distance;
            if (this.customer.startX != other.customer.startX)
                return this.customer.startX < other.customer.startX;
            return this.customer.startY < other.customer.startY;
        }
    }

    static class Customer {
        int startX, startY, endX, endY;

        Customer(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        boolean isAt(int x, int y) {
            return this.startX == x && this.startY == y;
        }
    }
}
