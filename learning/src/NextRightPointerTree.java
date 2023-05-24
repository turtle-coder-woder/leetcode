// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


public class NextRightPointerTree {
    public Node connect(Node root) {
        assignNextRightPointer(root);
        return root;
    }

    public void assignNextRightPointer(Node root) {
        if (root == null) {
            return;
        }


        Node head = root;
        while (head != null) {
            Node dummy = new Node();
            Node nextLefmostStart = dummy;
            while (head != null) {
                if (head.left != null) {
                    dummy.next = head.left;
                    dummy = dummy.next;
                }
                if (head.right != null) {
                    dummy.next = head.right;
                    dummy = dummy.next;
                }
                head = head.next;
            }
            head = nextLefmostStart.next;
        }
    }
}
