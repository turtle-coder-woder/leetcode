package easyCollection;

import java.util.Stack;

public class StockSpan {
    public int maxProfit(int[] prices) {
        //after base condition about input , atleast 1 value will be present so we can safely init our stack
        int maxProfit = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(prices[0]);
        for (int day = 1; day < prices.length; day++) {
            Integer localMax = 0;
            Integer todayPrice = prices[day];
            while (!stack.isEmpty() && stack.peek() <= todayPrice) {
                localMax = Math.max(localMax, todayPrice - stack.pop());
            }
            stack.add(todayPrice);
            maxProfit += localMax;

        }
        return maxProfit;
    }
}
