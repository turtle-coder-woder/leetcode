import java.util.Stack;

public class MinStack {
    Stack<Integer> stackForData, stackForMin;

    public MinStack() {
        stackForData = new Stack<>();
        stackForMin = new Stack<>();
    }

    public void push(int val) {
        stackForData.push(val);
        maintainStackForMinimumData(val);
    }

    public void pop() {
        stackForData.pop();
        stackForMin.pop();
    }

    public int top() {
        return stackForData.peek();
    }

    public int getMin() {
        return stackForMin.peek();
    }

    private void maintainStackForMinimumData(int newElement) {
        if (stackForData.size() == 1) {
            stackForMin.push(newElement);
        } else {
            stackForMin.push(Math.min(newElement, stackForMin.peek()));
        }
    }
}
