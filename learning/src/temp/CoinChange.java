package temp;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return(minCoins(coins,amount));
    }

    public  int minCoins(int[] coins, int target) {
        // Sort coins in descending order
        Arrays.sort(coins);
        reverseArray(coins);

        int numCoins = 0;
        for (int coin : coins) {
            numCoins += target / coin;
            target %= coin;
        }

        return target==0 ? numCoins : -1;
    }

    // Utility function to reverse the array
    private void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
