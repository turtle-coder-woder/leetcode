package p150problems.batista;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class NthUglyNumber {
    /*
    dp problem with 3 pointers picking next smallest and incrementing that pointer
    * */
    public int nthUglyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        int ar[] = new int[n + 4];
        int writePtr = 0;
        ar[writePtr++] = 1;
        set.add(1);
        Node i2 = new Node(0, 2);
        Node i3 = new Node(0, 3);
        Node i5 = new Node(0, 5);
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        pq.add(i2);
        pq.add(i3);
        pq.add(i5);

        while (ar[n - 1] == 0) {
            i2 = pq.poll();
            i3 = pq.poll();
            i5 = pq.poll();

            i2.val = ar[i2.ptrIndex] * i2.multiplier;
            i3.val = ar[i3.ptrIndex] * i3.multiplier;
            i5.val = ar[i5.ptrIndex] * i5.multiplier;
            pq.add(i2);
            pq.add(i3);
            pq.add(i5);
            Node minValPointer = pq.poll();
            if (!set.contains(minValPointer.val)) {
                ar[writePtr++] = minValPointer.val; //only write if not duplicate
                set.add(minValPointer.val);
            }

            minValPointer.ptrIndex++;
            minValPointer.val = Integer.MAX_VALUE; //so it doesn't come in front anymore
            pq.offer(minValPointer);
        }

        return ar[n - 1];
    }

    class Node {
        public int ptrIndex;
        public int val = 0;
        public int multiplier;

        public Node(int p, int multiplier) {
            this.ptrIndex = p;
            this.multiplier = multiplier;
        }
    }

    public static void main(String args[]) {
        System.out.println(new NthUglyNumber().nthUglyNumber(10));
    }
}
