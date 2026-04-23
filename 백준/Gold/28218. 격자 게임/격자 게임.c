#include <stdio.h>
#include <stdlib.h>

//28218 격자 게임
//하나의 말을 번갈아 움직여 마지막에 도착하는 사람이 이기는 게임,
//각자 최선을 다해서 이기기 위해서 플레이 하는 것이 목표이기 때문에,
//마지막 지점에 도달하는 것을 전제로 역추적 해서 조절한다.

static inline int inField(int r, int c, int N, int M) {
    return r <= N && c <= M;
}

//field는 0-based index 기 때문에 점검
static inline int isWall(int r, int c, int M, char field[][M + 1]) {
    return field[r - 1][c - 1] == '#';
} 


int main() {
    int N, M, K;
    scanf("%d %d %d", &N, &M, &K);
    char field[N][M + 1];
    for(int i = 0; i < N; i++) {
        scanf("%s", field[i]);
    }
    //1-Based index 변환
    int isWin[N + 1][M + 1];
    //만약 여기서 갈 수 있는 곳 중 승리할 수 없는 곳이 있다면 여기가 필승
    //여기서 갈 수 있는 모든 곳이 승리할 곳이라면 필패니까 패스
    //N, M은 추가 좌표로 들어오지 않고 0으로 초기화 되어있기 때문에 
    //자동으로 승리할 수 없는 곳 기준으로 초기화 되어있음
    for(int i = N; i >= 1; i--) {
        for(int j = M; j >= 1; j--) {
            isWin[i][j] = 0;
            //도착지에 도달하면 끝임
            if(i == N && j == M) continue;
            //벽이라서 고려 대상이 아님
            if(isWall(i, j, M, field)) continue;

            //필패할 수 있는 곳으로 한번이라도 간다면 바로 continue;

            //아래로
            if(inField(i + 1, j, N, M) && !isWall(i + 1, j, M, field) && !isWin[i + 1][j]) {
                isWin[i][j] = 1;
                continue;
            }

            //오른쪽으로
            if(inField(i, j + 1, N, M) && !isWall(i, j + 1, M, field) && !isWin[i][j + 1]) {
                isWin[i][j] = 1;
                continue;
            }

            //대각선으로
            for(int k = 1; k <= K; k++) {
                int nr = i + k, nc = j + k;
                //한번이라도 벗어나면 이후에도 벗어남
                if(!inField(nr, nc, N, M)) break;
                //벽을 건너뛸 수 있음
                if(isWall(nr, nc, M, field)) continue;
                if(!isWin[nr][nc]) {
                    isWin[i][j] = 1;
                    break;
                }
            }
        }
    }

    //질의 받기
    int Q;
    scanf("%d", &Q);
    //들어오는 좌표
    int x, y;
    for(int q = 0; q < Q; q++) {
        scanf("%d %d", &x, &y);
        //필승 위치인지 필패 위치인지 바로 구분해서 출력
        printf("%s\n", isWin[x][y] ? "First" : "Second");
    }

    return 0;
}

