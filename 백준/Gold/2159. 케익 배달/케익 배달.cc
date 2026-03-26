#include <stdio.h>

#define INF 4000000000000000000LL

//시작용 Dp 만들기
void calDpFirst(int cur[2], int target[2], long long dp[5]);
//DP 갱신
void calDpNext(int cur[2], int target[2], long long dp[5], long long nDp[5]);
//dp 복사용 2차원 배열 복사
void arrCopy(long long arr[5], long long arr2[5]);
//맨해튼 거리 계산 
int calDist(int curX, int curY, int targetX, int targetY);
//절대값으로 변환
int absoluteValue(int num);
//배열 최대값으로 채우기
void fillInf(long long arr[5]);

int dx[5] = {0, -1, 1, 0, 0};
int dy[5] = {0, 0, 0, -1, 1};

int main() {
    int N;
    int st[2];
    scanf("%d %d %d", &N, &st[0], &st[1]);
    int first[2];
    scanf("%d %d", &first[0], &first[1]);
    long long dp[5];
    calDpFirst(st, first, dp);
    int last[2];
    last[0] = first[0];
    last[1] = first[1];
    for(int i = 1; i < N; i++) {
        int target[2];
        scanf("%d %d", &target[0], &target[1]);
        long long nDp[5];
        calDpNext(last, target, dp, nDp);
        arrCopy(dp, nDp);
        last[0] = target[0];
        last[1] = target[1];
    }
    long long min = INF;
    for(int i = 0; i < 5; i++) {
        min = min <= dp[i] ? min : dp[i];
    }
    printf("%lld", min);
    
    return 0;
}

void calDpFirst(int cur[2], int target[2], long long dp[5]) {
    for(int d = 0; d < 5; d++) {
        int targetX = target[0] + dx[d];
        int targetY = target[1] + dy[d];
        dp[d] = calDist(cur[0], cur[1], targetX, targetY);
    }
}

void calDpNext(int cur[2], int target[2], long long curDp[5], long long nDp[5]) {
    fillInf(nDp);
    for(int i = 0; i < 5; i++) {
        int curX = cur[0] + dx[i];
        int curY = cur[1] + dy[i];
        for(int j = 0; j < 5; j++) {
            int targetX = target[0] + dx[j];
            int targetY = target[1] + dy[j];
            long long newDistCandi = curDp[i] + calDist(curX, curY, targetX, targetY);
            nDp[j] = newDistCandi < nDp[j] ? newDistCandi : nDp[j];
        }
    }
}

void arrCopy(long long arr[5], long long arr2[5]) {
    for(int i = 0; i < 5; i++) {
        arr[i] = arr2[i];
    }
}

int calDist(int curX, int curY, int targetX, int targetY) {
    return absoluteValue(curX - targetX) + absoluteValue(curY - targetY);
}

int absoluteValue(int num) {
    return num >= 0 ? num : -num;
}

void fillInf(long long arr[5]) {
    for(int i = 0; i < 5; i++) {
        arr[i] = INF;
    }
}