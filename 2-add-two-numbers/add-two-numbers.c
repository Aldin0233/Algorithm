/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    int carry = 0;
    //구조체를 반환할 때 next로 찾는 구조체인 node를 쉽게 반환하기 위해서 처음 구조체를 dummy로 만들었다가 마지막에 dummy.next를 반환
    struct ListNode dummy;
    dummy.next = NULL;
    struct ListNode* cur = &dummy;
    //두 수 중 긴 수가 남아있거나, 올릴 수가 남아있으면
    while(l1 != NULL || l2 != NULL || carry != 0) {
        int sum = carry;
        carry = 0;
        //각각 연결리스트 끝났으면 0으로 처리
        if(l1 != NULL) {
            sum += l1 -> val;
            l1 = l1 -> next;
        }

        if(l2 != NULL) {
            sum += l2 -> val;
            l2 = l2 -> next;
        }

        //올릴 수
        carry = sum / 10;

        //더한 수 연결리스트에 연결
        struct ListNode* newNode = malloc(sizeof(struct ListNode));
        newNode -> val = sum % 10;
        newNode -> next = NULL;
        cur -> next = newNode;
        cur = cur -> next;
    }

    return dummy.next;
}