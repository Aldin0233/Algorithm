class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int lt = 1; 
        int rt = 100000; 

        while (lt < rt) {
            int mid = (lt + rt) / 2; 
            if (limit < calcSolTime(diffs, times, mid)) {
                lt = mid + 1; 
            } else {
                rt = mid; 
            }
        }

        return rt;
    }
    
    public long calcSolTime(int[] diffs, int[] times, int curLevel) {
        long totalTime = times[0]; 
        for (int i = 1; i < diffs.length; i++) {
            int curDiff = diffs[i];
            int curTime = times[i]; 
            int prevTime = times[i - 1]; 

            if (curDiff <= curLevel) {
                totalTime += curTime;
            } else {
                int cnt = curDiff - curLevel;
                totalTime += (prevTime + curTime) * cnt + curTime;
            }
        }
        
        return totalTime; 
    }

}