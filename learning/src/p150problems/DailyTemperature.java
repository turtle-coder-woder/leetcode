package p150problems;

import java.util.ArrayDeque;
import java.util.Arrays;

public class DailyTemperature {
    /*
    * Its a next greater element problem from stack NGE
    * This makes this problem as Monotonus stack problem
    * in stack keep index instead of temperature values, have a while loop within to see NGE
    * if current element is bigger than peek
    * */
    public int[] dailyTemperatures(int[] temperatures) {
        int ans[] = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i=0;i<temperatures.length;i++){
            if(!stack.isEmpty()){
                int currentTemperature = temperatures[i];
                while(!stack.isEmpty() && currentTemperature>temperatures[stack.peek()]){
                    int lastMinValueIndex = stack.pop();
                    ans[lastMinValueIndex] = i-lastMinValueIndex;
                }
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String args[]) {
        int dailyTemperatrures[] = {73,74,75,71,69,72,76,73};
        Arrays.stream(new DailyTemperature().dailyTemperatures(dailyTemperatrures)).forEach(x->System.out.print(x+","));
    }

}
