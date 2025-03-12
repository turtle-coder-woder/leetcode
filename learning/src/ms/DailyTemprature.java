package ms;

import java.util.Stack;

public class DailyTemprature {
    /*
    Input: temperatures = [73,74,75,71,69,72,76,73]
    Output: [1,1,4,2,1,1,0,0]

     */
    public int[] dailyTemperatures(int[] temperatures) {
        int index = 0;
        int[] result = new int[temperatures.length];
        Stack<Integer> stackMinAllowed = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int todayTemp = temperatures[i];
            if (!stackMinAllowed.isEmpty()) {
                while (getLastTemp(stackMinAllowed, temperatures) < todayTemp) {
                    int day = stackMinAllowed.pop();
                    result[day] = i - day;
                }
            }
            stackMinAllowed.push(i);
        }

        return result;
    }

    private int getLastTemp(Stack<Integer> stackMinAllowed, int[] temp) {
        if(stackMinAllowed.isEmpty()){
            return Integer.MAX_VALUE;
        }
        return temp[stackMinAllowed.peek()];
    }
}
