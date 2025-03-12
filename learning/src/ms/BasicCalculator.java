package ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BasicCalculator {
    final public static String NEGATIVE = "-";
    final public static String POSITIVE = "+";

    public int calculate(String s) {
        List<Node> parsedString = parseNode(s);
        return calculatePortion(parsedString);
    }


    public int calculatePortion(List<Node> nodes) {
        Stack<Integer> operands = new Stack<>();
        Stack<Node> operators = new Stack<>();
        while (!nodes.isEmpty()) {
            Node node = nodes.remove(0);
            if (node.type.equals(Type.BracketOpen)) {
                operands.add(calculatePortion(nodes));
            } else if (node.type.equals(Type.BracketClose)) {
                break;
            } else if (node.type.equals(Type.Operand)) {
                operands.add(Integer.valueOf(node.val));
            } else if (node.type.equals(Type.Operator)) {
                if(!operators.isEmpty()){
                    operands.add(evaluateExpression(operands,operators));
                }
                operators.add(node);
            }
        }
        return evaluateExpression(operands, operators);
    }

    private int evaluateExpression(Stack<Integer> operands, Stack<Node> operators) {
        Integer ans = null;
        while (!operators.isEmpty()) {
            Node operator = operators.remove(0);
            Integer first = getNextOperand(operands);
            Integer second = getNextOperand(operands);
            if (operator.val.equals(POSITIVE)) {
                ans = second + first;
            } else {
                ans = second - first;
            }
            operands.push(ans);
        }

        return getNextOperand(operands);
    }

    private Integer getNextOperand(Stack<Integer> operands) {
        if (operands.isEmpty()) {
            return 0;
        }
        return operands.pop();
    }

    public List<Node> parseNode(String s) {
        List<Node> nodeList = new ArrayList<>();
        Node lastNode = null;
        char sar[] = s.toCharArray();
        for (char c : sar) {
            Type cType = gettType(c);
            Node n = new Node(c, cType);
            switch (cType) {
                case Operand:
                    if (lastNode != null && lastNode.type.equals(Type.Operand)) {
                        lastNode.appendValue(c);
                    } else {
                        nodeList.add(n);
                        lastNode = n;
                    }
                    break;
                case Operator:
                case BracketClose:
                case BracketOpen:
                    nodeList.add(n);
                    lastNode = n;
                    break;
                default:
                    break;

            }
        }
        return nodeList;
    }

    Type gettType(char c) {
        if (c == ' ') {
            return Type.SPACE;
        } else if (c == '+' || c == '-') {
            return Type.Operator;
        } else if (c == '(') {
            return Type.BracketOpen;
        } else if (c == ')') {
            return Type.BracketClose;
        } else {
            return Type.Operand;
        }
    }


    public class Node {
        public String val;
        public Type type;

        public Node(Character val, Type type) {
            this.val = val.toString();
            this.type = type;
        }

        public void appendValue(Character c) {
            this.val += c;
        }
    }

    public enum Type {Operator, Operand, BracketOpen, BracketClose, SPACE}
}
