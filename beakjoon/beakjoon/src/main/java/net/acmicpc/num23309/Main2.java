package net.acmicpc.num23309;

import java.io.*;
import java.util.*;

class Node {
    int stationId;
    Node prev, next;

    Node(int stationId) {
        this.stationId = stationId;
    }
}

class Main2 {
    static Map<Integer, Node> stationMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("data/num23309.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 기존 역 개수
        int M = Integer.parseInt(st.nextToken()); // 공사 횟수

        st = new StringTokenizer(br.readLine());

        Node head = null, prevNode = null;

        // 원형 연결 리스트 구성
        for (int i = 0; i < N; i++) {
            int stationId = Integer.parseInt(st.nextToken());
            Node newNode = new Node(stationId);
            stationMap.put(stationId, newNode);

            if (prevNode != null) {
                prevNode.next = newNode;
                newNode.prev = prevNode;
            } else {
                head = newNode;
            }
            prevNode = newNode;
        }

        // 마지막과 첫 번째 연결 (원형 연결 리스트)
        prevNode.next = head;
        head.prev = prevNode;

        // 명령어 처리
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int iStation = Integer.parseInt(st.nextToken());

            switch (command) {
                case "BN":
                    int jStation = Integer.parseInt(st.nextToken());
                    bw.write(insertAfter(iStation, jStation) + "\n");
                    break;
                case "BP":
                    jStation = Integer.parseInt(st.nextToken());
                    bw.write(insertBefore(iStation, jStation) + "\n");
                    break;
                case "CN":
                    bw.write(removeNext(iStation) + "\n");
                    break;
                case "CP":
                    bw.write(removePrevious(iStation) + "\n");
                    break;
            }
        }

        bw.flush();
        bw.close();
    }

    static int insertAfter(int i, int j) {
        Node cur = stationMap.get(i);
        Node nextNode = cur.next;

        Node newNode = new Node(j);
        stationMap.put(j, newNode);

        // 연결 관계 변경
        cur.next = newNode;
        newNode.prev = cur;
        newNode.next = nextNode;
        nextNode.prev = newNode;

        return nextNode.stationId; // 삽입 전 기존 다음 역 반환
    }

    static int insertBefore(int i, int j) {
        Node cur = stationMap.get(i);
        Node prevNode = cur.prev;

        Node newNode = new Node(j);
        stationMap.put(j, newNode);

        // 연결 관계 변경
        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = cur;
        cur.prev = newNode;

        return prevNode.stationId; // 삽입 전 기존 이전 역 반환
    }

    static int removeNext(int i) {
        Node cur = stationMap.get(i);
        Node removeNode = cur.next;

        if (stationMap.size() > 2) {
            cur.next = removeNode.next;
            removeNode.next.prev = cur;
            stationMap.remove(removeNode.stationId);
        }

        return removeNode.stationId; // 삭제된 역 번호 반환
    }

    static int removePrevious(int i) {
        Node cur = stationMap.get(i);
        Node removeNode = cur.prev;

        if (stationMap.size() > 2) {
            cur.prev = removeNode.prev;
            removeNode.prev.next = cur;
            stationMap.remove(removeNode.stationId);
        }

        return removeNode.stationId; // 삭제된 역 번호 반환
    }
}