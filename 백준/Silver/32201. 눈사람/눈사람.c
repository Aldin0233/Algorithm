#include <stdio.h>
#include <stdlib.h>

#define MOD 131071

int N;

typedef struct Node {
    int key;
    int value;
    struct Node *next;
} Node;

Node* table[MOD];

unsigned int hash(int key);
int get(int key);
void insert(int key, int value);

int main () {
    scanf("%d", &N);
    for(int i = 1; i <= N; i++) {
        int key;
        scanf("%d", &key);
        insert(key, i);
    }
    int arr[N + 1];
    for(int i = 1; i <= N; i++) {
        scanf("%d", &arr[i]);
    }
    int max = -100000;
    int printAns[N];
    int ansCnt = 0;

    for(int i = 1; i <= N; i++) {
        int old = get(arr[i]);
        int now = i;
        int change = old - now;
        if(change > max) {
            //출력 초기화
            ansCnt = 0;
            max = change;
            //출력 더하기
            printAns[ansCnt++] = arr[i];
        } else if(change == max) {
            //문자열 더하기
            printAns[ansCnt++] = arr[i];
        }
    }
    for(int i = 0; i < ansCnt; i++) {
        printf("%d ", printAns[i]);
    }
    return 0;
}

//이 문제에서는 무조건 같은 집합으로 다른 번호들로 들어온다. 따라서 중복 삽입과 없는 조회는 고려하지 않는다.
int get(int key) {
    unsigned int idx = hash(key);
    Node* cur = table[idx];
    while(cur->key != key) {
        cur = cur->next;
    }
    return cur->value;
}

void insert(int key, int value) {
    unsigned int idx = hash(key);
    Node* newNode = malloc(sizeof(Node));
    newNode -> key = key;
    newNode -> value = value;
    newNode -> next = table[idx];

    table[idx] = newNode;
}

unsigned int hash(int key) {
    unsigned int x = (unsigned int) key;
    x ^= x >> 16;
    x *= 0x7feb352d;
    x ^= x >> 15;
    x *= 0x846ca68b;
    x ^= x >> 16;
    return x % MOD;
}