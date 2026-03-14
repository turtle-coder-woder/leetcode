package p150problems;
import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {
    Map<Node,Node>mapOfNodes = new HashMap<>();

    public Node cloneGraph(Node node) {
        if(node==null){
            return null;
        }

        if(mapOfNodes.containsKey(node)){
            return mapOfNodes.get(node);
        }

        Node cloneNode = new Node(node.val);
        mapOfNodes.put(node,cloneNode);
        for(Node neighbhour : node.neighbors){
            cloneNode.neighbors.add(cloneGraph(neighbhour));
        }

        return cloneNode;
    }
}
