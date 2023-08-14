package easyCollection;

public class MaxProfitForSingleTransaction {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int current = prices[i];
            if (current < minPrice) {
                maxProfit = Math.max(maxPrice - minPrice, maxProfit);
                minPrice = maxPrice = current; // new valley
            } else if (current > maxPrice) {
                maxPrice = current;
            }
        }
        return Math.max(maxPrice - minPrice, maxProfit);
    }
}
