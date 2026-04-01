#include <stdio.h>
#include <stdlib.h>

static inline int move(int a, int b) {
    int next = a - 1;
    int cur = b;
    if(next >= cur) {
        int dist = next - cur;
        return dist / 2 + dist % 2 + 1;
    } else return 0;
}

int main() {
    int N;
    scanf("%d", &N);
    int moveCnt = 0;
    int last = 0;
    for(int i = 0; i < N; i++) {
        int curObstacle;
        scanf("%d", &curObstacle);
        int tryMove = move(curObstacle, last);
        if(tryMove) {
            moveCnt += tryMove;
            last = curObstacle + 1;
        } else {
            printf("%d", -1);
            return 0;
        }
    }

    printf("%d", moveCnt);
    return 0;
}