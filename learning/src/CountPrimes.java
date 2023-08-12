public class CountPrimes {
    public int countPrimes(int n) {
        // base condition
        if (n <= 2) {
            return 0;
        }

        int array[] = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int j = 2;
            if (array[i] == 0) {
                while (i * j <= n) {
                    array[i * (j++)] = 1;
                }
            }
        }

        //count primes from 2 to n-1
        int countPrimes = 0;
        for (int i = 2; i < n; i++) {
            if (array[i] == 0) {
                countPrimes++;
            }
        }
        return countPrimes;
    }
}
