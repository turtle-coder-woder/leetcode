package p150problems;

import java.util.Stack;

/*
* Keep 2 stacks 1 for normal stack values and another for only min stack values so that
* all operations are under O(1)
 */
public class MinStack {
    Stack<Integer> normalStack;
    Stack<Integer> stackForMinValueTracking;
    public MinStack() {
        normalStack = new Stack<>();
        stackForMinValueTracking = new Stack<>();
    }

    public void push(int val) {
        if(normalStack.empty()){
            stackForMinValueTracking.push(val);
        }else{
            int lastMin = stackForMinValueTracking.peek();
            stackForMinValueTracking.push(Math.min(lastMin,val));
        }
        normalStack.push(val);
    }

    public void pop() {
        normalStack.pop();
        stackForMinValueTracking.pop();
    }

    public int top() {
        return normalStack.peek();
    }

    public int getMin() {
        return stackForMinValueTracking.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */