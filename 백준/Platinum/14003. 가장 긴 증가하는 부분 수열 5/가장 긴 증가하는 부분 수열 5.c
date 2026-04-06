#include <stdio.h>
#include <stdlib.h>

typedef struct LIS {
    int *arr;
    int size;
} LIS;


//LIS 위치 탐색을 위한 메서드, LIS 배열은 정렬되어 있다(오름차순) 이분 탐색 가능!
//위치를 찾아 LIS 구조체에 집어 넣고, 집어넣은 인덱스를 반환해주는 메서드
int binarySearchAndSetLIS(LIS *lis, int data);

//역추적해서 내려가면서 가장 큰 인덱스에서부터 순서대로 내려갈때 가장 길어지는 부분 수열을 추적하는 메서드
void backTrackingForPrint(int *printArr, int *arr, int *pos, int arrLen, int lisLen);

//출력 반복문 메서드
void printIntArr(int *printArr, int size, char separator);

int main() {
    int N;
    scanf("%d", &N);
    int *arr = malloc(sizeof(int) * N);
    LIS lis;
    lis.arr = calloc(N, sizeof(int));
    lis.size = 0;
    int *pos = malloc(sizeof(int) * N);
    int *printArr = malloc(sizeof(int) * N);
    for(int i = 0; i < N ; i++) {
        scanf("%d", &arr[i]);
        pos[i] = binarySearchAndSetLIS(&lis, arr[i]);
    }

    backTrackingForPrint(printArr, arr, pos, N, lis.size);
    printf("%d\n", lis.size);
    printIntArr(printArr, lis.size, ' ');

    free(arr); free(lis.arr); free(pos); free(printArr);
    return 0;
}

int binarySearchAndSetLIS(LIS *lis, int data) {
    //현재 lis가 비어있을 때 첫번째에 넣어주고 반환
    if(lis->size == 0) {
        lis->arr[0] = data;
        lis->size++;
        return 0;
    }
    //현재 lis 수열 끝보다 크다면 수열 늘려주면서 데이터 추가
    if(lis->arr[lis->size - 1] < data) {
        lis->arr[lis->size] = data;
        lis->size++;
        return lis->size - 1;   
    }

    //lis에 데이터를 넣을 건데 어디에 들어갈지 이분 탐색으로
    //lower bound 아랫쪽에서부터 찾아요
    int l = 0;
    int r = lis->size - 1;
    while(l < r) {
        int mid = (l + r) >> 1;
        if(lis->arr[mid] >= data) r = mid;
        else l = mid + 1;
    }
    lis->arr[l] = data;
    return l;
}

void backTrackingForPrint(int *printArr, int *arr, int *pos, int arrLen, int lisLen) {
    int nowIdx = lisLen - 1;
    for(int i = arrLen - 1; i >= 0; i--) {
        if(pos[i] == nowIdx) {
            printArr[nowIdx--] = arr[i];
        }
    }
}

void printIntArr(int *printArr, int size, char separator) {
    for(int i = 0; i < size; i++) {
        printf("%d%c", printArr[i], separator);
    }
}