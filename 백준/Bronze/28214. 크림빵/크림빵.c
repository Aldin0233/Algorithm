#include <stdio.h>
#include <stdlib.h>

//28214 크림빵
//N * K을 빵을 '앞에서부터 순서대로' K개씩 묶어서 한묶음으로 -> [N][K] 이차원 배열
//K개의 빵 중 크림이 없는 빵이 P개 미만 -> 하나의 행에 크림 없는게 P개 미만
// 0은 크림이 없음, 1은 있음

int main() {
    int N, K, P;
    //2번 틀린 이유 -> scanf에 원소를 하나만 적고 있었다..
    scanf("%d %d %d", &N, &K, &P);
    int breadForSale = 0;
    for(int i = 0; i < N; i++) {
        int noneCreamBreadCnt = 0;
        int isCream; //크림을 갖고 있는지
        for(int j = 0; j < K; j++) {
            scanf("%d", &isCream);
            if(!isCream) noneCreamBreadCnt++;
        }
        //크림 없는 빵이 P개 미만 시 판매 가능
        if(noneCreamBreadCnt < P) breadForSale++;
    }
    printf("%d", breadForSale);
    return 0;
}

