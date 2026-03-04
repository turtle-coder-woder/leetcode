package p150problems;

import java.util.Stack;

public class BuySellStocks {
    //    don't make stack
//    iterate over prices
//    see for peek and valley
//    when you see valley which is more lower update that valley and try to find maximum length of peek-valley
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minValley = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (currentPrice < minValley) {
                minValley = currentPrice;
            } else {
                maxProfit = Math.max(maxProfit, (currentPrice - minValley));
            }
        }
        return maxProfit;
    }

    public static void main(String args[]) {
        int a[] = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(new BuySellStocks().maxProfit(a));
    }
}
