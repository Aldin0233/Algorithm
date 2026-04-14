#include <stdio.h>
#include <stdlib.h>

//34116 직각 이등변 삼각형
//X축에 빗변이 평행한 삼각형이면서 모든 점을 포함하기 위해서는 직각부분이 아래를 향하는 삼각형과 위를 향하는 삼각형 두 형태 밖에 없다. 
//이때 모든 점이 삼각형 내부에 있는지 확인하기 위해서 극값을 사용하겠다. 
//극값들을 포함하는 직선들로 이루어진 삼각형이 가장 작은 삼각형

//변수 설명 -> 모든 점에 대해서 갱신하며 극값 갱신
//직각이 아래에 있을때(빗변이 위)
// extremumPos[0] : min(x + y) 왼쪽 변
// extremumPos[1] : min(y - x) 오른쪽 변
// extremumPos[2] : max(Y) 가장 위 높이 (빗변의 y좌표)
//직각이 위에 있을 때(빗변이 아래)
// extremumPos[3] : max(x + y) 오른쪽 변
// extremumPos[4] : max(y - x) 왼쪽 변
// extremumPos[5] : min(Y) 가장 아래 높이

#define INF 200000000

static inline int max(int a, int b) {
    return a > b ? a : b;
}

static inline int min(int a, int b) {
    return a < b ? a : b;
}

void updateExtremumPos(int extremumPos[6], int x, int y);

//점이 끝과 끝에 있을때 점을 모두 감싸기 위해 삼각형은 좌표값을 넘어서 빗변이 long long이 나올 수 있다.
int calcRightAngleIsoscelesTriangleHypotenuse(int extremumPos[6]);

int main() {
    int N;
    scanf("%d", &N);
    int extremumPos[6] = {INF, INF, -INF, -INF, -INF, INF};
    for(int i = 0; i < N; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        updateExtremumPos(extremumPos, x, y);
    }
    int minHypotenuse = calcRightAngleIsoscelesTriangleHypotenuse(extremumPos);
    printf("%d", minHypotenuse);
    return 0;
}

void updateExtremumPos(int extremumPos[6], int x, int y) {
    int sumXY = x + y;
    int subYX = y - x;
    extremumPos[0] = min(extremumPos[0], sumXY);
    extremumPos[1] = min(extremumPos[1], subYX);
    extremumPos[2] = max(extremumPos[2], y);
    extremumPos[3] = max(extremumPos[3], sumXY);
    extremumPos[4] = max(extremumPos[4], subYX);
    extremumPos[5] = min(extremumPos[5], y);
}

int calcRightAngleIsoscelesTriangleHypotenuse(int extremumPos[6]) {
    //두 사선의 기울기는 1(직각 이등변 삼각형)
    //따라서 극값을 풀어 만나는 곳이 그냥 직각점이다. 
    //또한 우리는 높이만 알면 된다 //직각 삼각형의 빗변은 빗변 기준 높이의 2배다.(정수 보장!)
    // double downSideY = (extremumPos[0] + extremumPos[1]) / 2.0;ㅁ
    // double downSideHeight = extremumPos[2] - downSideY;
    // int downSideHypotenuse = (int)(downSideHeight * 2);
    //2를 곱하는 과정이 있기 떄문에 좌표 값 계산에서 /2를 생략하고 바로 정수형으로 계산
    int downSideHypotenuse = 2 * extremumPos[2] - (extremumPos[0] + extremumPos[1]);
    int upSideHypotenuse = (extremumPos[3] + extremumPos[4]) - 2 * extremumPos[5];
    return min(downSideHypotenuse, upSideHypotenuse);
}