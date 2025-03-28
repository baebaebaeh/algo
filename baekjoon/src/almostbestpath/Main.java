package almostbestpath;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int idx;
    int cost;

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {
    public static void main (String args[]) throws Exception {
        System.setIn(new FileInputStream("data/almostbestpath.txt"));
    }
}