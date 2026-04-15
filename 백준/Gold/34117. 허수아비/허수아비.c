#include <stdio.h>
#include <stdlib.h>

#define MAXN 500001

//34117 허수아비 
//방어력만큼 감소하고 진행
//화살이 위치 i에서 멈추거나 왼쪽에서 멈추도록 하기 위해 필요한 허수아비의 최소 개수, 
//멈출 수 없을 때 출력 -1
//힘이 최대 1억 -> 단순 DP 반복 X
//우선순위 큐? 들어오는 대로 추가하고, 만약 P보다 클때 Peek을 빼도 P보다 크다면 낮은 순서대로 빼내기

typedef struct PriorityQueue {
    int size;
    int heap[MAXN];
} PriorityQueue;

void init(PriorityQueue *pq);
int isEmpty(PriorityQueue *pq);
void push(PriorityQueue *pq, int defensePower);
int pop(PriorityQueue *pq);
int peek(PriorityQueue *pq);

void calculate(int A[MAXN], int needPower[MAXN], int idx, int P, PriorityQueue *pq);

int prefixSum = 0;

int main() {
    int N, P;
    scanf("%d %d", &N, &P);
    int A[MAXN];
    int needPower[MAXN];
    for(int i = 0; i < N; i++) {
        scanf("%d", &A[i]);
    }

    PriorityQueue pq;
    init(&pq);

    for(int i = 0; i < N; i++) {
        calculate(A, needPower, i, P, &pq);
    }

    for(int i = 0; i < N; i++) {
        printf("%d ", needPower[i]);
    }
    return 0;
}

//pq에서 오름차순으로 정렬된 허수아비들 중 제거할 수 있는 허수아비 부터 제거
void calculate(int A[MAXN], int needPower[MAXN], int idx, int P, PriorityQueue *pq) {
    prefixSum += A[idx];
    push(pq, A[idx]);
    while(!isEmpty(pq) && prefixSum - peek(pq) >= P) {
        prefixSum -= pop(pq);
    }
    needPower[idx] = prefixSum >= P ? pq -> size : -1;
}

//PQ 구현체

void init(PriorityQueue *pq) {
    pq -> size = 0;
}

int isEmpty(PriorityQueue *pq) {
    return pq -> size == 0;
}

void push(PriorityQueue *pq, int defensePower) {
    int idx = ++pq -> size;
    pq -> heap[idx] = defensePower;
    //내림차순 PQ
    while(idx > 1 && pq -> heap[idx] < pq -> heap[idx / 2]) {
        int tmp = pq -> heap[idx];
        pq -> heap[idx] = pq -> heap[idx / 2];
        pq -> heap[idx / 2] = tmp;
        idx /= 2;
    }
}

int pop(PriorityQueue *pq) {
    int result = pq -> heap[1];
    pq -> heap[1] = pq -> heap[pq -> size--];
    int idx = 1;
    while(1) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        //현시점 최대값 찾아두는 것
        int minimum = idx; 

        if(left <= pq -> size && pq -> heap[left] < pq -> heap[minimum]) {
            minimum = left;
        }
        if(right <= pq -> size && pq -> heap[right] < pq -> heap[minimum]) {
            minimum = right;
        }

        if(minimum == idx) break;

        int tmp = pq -> heap[idx];
        pq -> heap[idx] = pq -> heap[minimum];
        pq -> heap[minimum] = tmp;
        
        idx = minimum;
    }
    return result;
}

int peek(PriorityQueue *pq) {
    return pq -> heap[1];
}