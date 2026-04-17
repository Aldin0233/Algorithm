#include <stdio.h>
#include <stdlib.h>

#define INF 1000000000

#define MAXN 2001
#define PRM (5+1) //PERSONAL_REMAININGDAY_MAX
#define GRM (4+1) //GROUP_REMAININGDAY_MAX
#define TICKET_TYPE 4

//34118 부산관광
//두 명이 여행 가는데 1, 3, 5일권은 본인만 사용 가능, 묶음권은 같이 사용 가능.
//각 날에 대해서 구매 여부를 판단하고 잔여일이 남았는지 확인하는 방식으로 진행

int dp[PRM][PRM][GRM];
int nextDP[PRM][PRM][GRM];

void intArrFillByINF(int arr[PRM][PRM][GRM]);
void intArrCopy(int dst[PRM][PRM][GRM], int src[PRM][PRM][GRM]);

static inline int min(int a, int b) {
    return a < b ? a : b;
}

static inline int max(int a, int b) {
    return a > b ? a : b;
}

int personalDay[4] = {0, 1, 3, 5}; // 안삼 1일권 3일권 5일권
int personalCost[4];
int groupDay[2] = {0,  4}; //안삼, 4일권
int groupCost[2];

int main() {
    int N;
    scanf("%d", &N);
    char A[MAXN];
    char B[MAXN];
    scanf("%s", A);
    scanf("%s", B);
    int P[TICKET_TYPE];
    for(int i = 0; i < TICKET_TYPE; i++) {
        scanf("%d", &P[i]);
    }
    personalCost[0] = 0;
    personalCost[1] = P[0];
    personalCost[2] = P[1];
    personalCost[3] = P[2];
    groupCost[0] = 0;
    groupCost[1] = P[3];

    intArrFillByINF(dp);
    dp[0][0][0] = 0;
    for(int i = 0; i < N; i++) {
        intArrFillByINF(nextDP);
        for(int a = 0; a < PRM; a++) {
            for(int b = 0; b < PRM; b++) {
                for(int c = 0; c < GRM; c++) {
                    if(dp[a][b][c] == INF) continue;
                    // 4*4*2 = 32 충분히 작음
                    for(int buyA = 0; buyA < 4; buyA++) {
                        for(int buyB = 0; buyB < 4; buyB++) {
                            for(int buyG = 0; buyG < 2; buyG++) {

                                //사려는게 티켓이 현 잔여일보다 작으면 굳이 찾을 필요 없음)
                                if(buyA != 0 && personalDay[buyA] <= a) continue;
                                if(buyB != 0 && personalDay[buyB] <= b) continue;
                                if(buyG != 0 && groupDay[buyG] <= c) continue;

                                //안 샀을때 대비해서 아직 max 남겨둠
                                //티켓 구매시(안삼 포함) 잔여일
                                int curA = max(a, personalDay[buyA]);
                                int curB = max(b, personalDay[buyB]);
                                int curG = max(c, groupDay[buyG]);

                                //관광하는 날이니 티켓 검사
                                if(A[i] == '1') {
                                    if(curA == 0 && curG == 0) continue;
                                }
                                if(B[i] == '1') {
                                    if(curB == 0 && curG == 0) continue;
                                }

                                int cost = dp[a][b][c] + personalCost[buyA] + personalCost[buyB] + groupCost[buyG];
                                int nextA = (curA > 0) ? curA - 1 : 0;
                                int nextB = (curB > 0) ? curB - 1 : 0;
                                int nextG = (curG > 0) ? curG - 1 : 0;
                                nextDP[nextA][nextB][nextG] = 
                                min(nextDP[nextA][nextB][nextG], cost);
                            }
                        }
                    }
                }
            }
        }
        intArrCopy(dp, nextDP);
    }

    int minCost = INF;
    for(int a = 0; a < PRM; a++) {
        for(int b = 0; b < PRM; b++) {
            for(int c = 0; c < GRM; c++) {
                minCost = min(minCost, dp[a][b][c]);
            }
        }
    }
    printf("%d", minCost);
    return 0;
}

void intArrFillByINF(int arr[PRM][PRM][GRM]) {
    for(int a = 0; a < PRM; a++) {
        for(int b = 0; b < PRM; b++) {
            for(int c = 0; c < GRM; c++) {
                arr[a][b][c] = INF;
            }
        }
    }
}

void intArrCopy(int dst[PRM][PRM][GRM], int src[PRM][PRM][GRM]) {
    for(int a = 0; a < PRM; a++) {
        for(int b = 0; b < PRM; b++) {
            for(int c = 0; c < GRM; c++) {
                dst[a][b][c] = src[a][b][c];
            }
        }
    }
}