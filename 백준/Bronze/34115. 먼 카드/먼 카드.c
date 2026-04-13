#include <stdio.h>
#include <stdlib.h>

//34115 먼카드
//모든 카드는 단 두장씩만 들어오기로 약속되어 있다. 
//따라서 이전에 카드가 들어왔다면, 다음 카드가 들어올 때는 그 사이 거리만 확인하면 된다.

static inline int max(int a, int b) {
    return a > b ? a : b;
}

int main() {
    int N;
    scanf("%d", &N);
    int X[N + 1];
    for(int i = 1; i <= N; i++) {
        X[i] = 0;
    }
    int curMax = 0;
    //1부터 시작하고 대신 0을 기존에 들어온적 있는지 여부로 판단한다.
    for(int i = 1; i <= N * 2; i++) {
        int input;
        scanf("%d", &input);
        if(X[input]) {
            curMax = max(curMax, i - X[input] - 1);
        } else { // C, C++에서는 0을 거짓으로 취급
            X[input] = i;
        }
    }

    printf("%d", curMax);
    return 0;
}