# [Gold III] Money Sharing - 18611 

[문제 링크](https://www.acmicpc.net/problem/18611) 

### 성능 요약

메모리: 41484 KB, 시간: 472 ms

### 분류

자료 구조, 그리디 알고리즘, 오프라인 쿼리, 우선순위 큐

### 제출 일자

2025년 2월 24일 23:03:36

### 문제 설명

<p>It becomes more and more popular to share something instead of buying it.</p>

<p>One of the promising sharing systems is money sharing. There are numerous approaches to it, but we will deal with the one when there is a public entry point where money may be borrowed or returned free of charge. No need to say that the system quickly rose to be extremely popular.</p>

<p>Due to such popularity, it is hard to keep the system stable, so one has to request borrowing some money several days in advance. You are to develop an automatic managing system for money sharing. Consider a single day: during the day, you have n requests for money lending, and there are also m resupplies scheduled. They both may be described by non-zero integer x. Initially, the entry point has no money. On event described by x:</p>

<ul>
	<li>If x > 0, then it is a resupply, so the amount of money in the entry point is increased by x.</li>
	<li>If x < 0, then it is a request to borrow the amount of money equal to |x|. If the request is approved, then the amount of money in the entry point is decreased by |x|. Otherwise, it does not change.</li>
</ul>

<p>Unfortunately, it is not always possible to satisfy all requests, because the entry point may eventually end up not having enough money to lend, so some requests may have to be declined. Your task is, given the description of all requests and resupplies, to choose for each request whether to accept or decline it, so that the entry point always has enough money to satisfy the accepted requests. If there are multiple possible answers, you should choose the one having the minimum possible number of requests declined. If there are still multiple possible answers, find any one of them.</p>

### 입력 

 <p>The first line of input contains two integers n and m (1 ≤ n, m ≤ 10<sup>5</sup>).</p>

<p>Each of the next n + m lines contains a single integer x (1 ≤ |x| ≤ 10<sup>9</sup>) and describe the events.</p>

<p>Events are described in the order they occur, no two events occur at the same moment of time.</p>

### 출력 

 <p>Output your answer in n + m lines.</p>

<p>For each resupply event, simply output “resupplied”.</p>

<p>For each request, output “approved” or “declined” depending on your decision.</p>

