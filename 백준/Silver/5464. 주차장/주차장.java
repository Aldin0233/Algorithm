import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static ParkingStateManager manager;
    static CarState[] carStates;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ParkingState[] parkingStates = new ParkingState[N];
        carStates = new CarState[M + 1];
        for (int i = 0; i < N; i++) {
            parkingStates[i] = new ParkingState(Integer.parseInt(br.readLine()));
        }
        manager = new ParkingStateManager(parkingStates);
        for (int i = 1; i <= M; i++) {
            carStates[i] = new CarState(Integer.parseInt(br.readLine()));
        }

        for(int i = 0; i < 2 * M; i++) {
             int order = Integer.parseInt(br.readLine());
             if(order > 0) manager.addWaiting(carStates[order]);
             else manager.exit(carStates[-order]);
        }

        System.out.println(manager.totalSales);
    }

    static class CarState {
        int weight;
        int useParkingSpotNo;
        public CarState(int weight) {
            this.weight = weight;
            this.useParkingSpotNo = -1;
        }
    }

    static class ParkingStateManager {
        ParkingState[] parkingStates;
        long totalSales;
        PriorityQueue<Integer> emptySpace;
        Queue<CarState> waitingQu;

        public ParkingStateManager(ParkingState[] parkingStates) {
            this.parkingStates = parkingStates;
            this.totalSales = 0L;
            this.emptySpace = new PriorityQueue<>();
            this.waitingQu = new LinkedList<>();
            initSetPq();
        }

        void initSetPq() {
            for (int i = 0; i < parkingStates.length; i++) {
                emptySpace.add(i);
            }
        }

        void addWaiting(CarState carState) {
            waitingQu.add(carState);
            if(!emptySpace.isEmpty()) parking(waitingQu.poll());
        }

        void parking(CarState carState) {
            int parkingIdx = emptySpace.poll();
            carState.useParkingSpotNo = parkingIdx;
            parkingStates[parkingIdx].used = true;
            totalSales += (long) carState.weight * parkingStates[parkingIdx].cost;
        }

        void exit(CarState carState) {
            int parkingIdx = carState.useParkingSpotNo;
            parkingStates[parkingIdx].used = false;
            emptySpace.add(carState.useParkingSpotNo);
            if(!waitingQu.isEmpty()) parking(waitingQu.poll());
        }


    }

    static class ParkingState {
        int cost;
        boolean used;

        ParkingState(int cost) {
            this.cost = cost;
            this.used = false;
        }

    }


}
