public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int hammingDistance = 0;
        while (x > 0 || y > 0) { //this will bring both number to zero eventually
            if (x % 2 != y % 2) {
                hammingDistance++;
            }

            x = x / 2;
            y = y / 2;
        }
        return hammingDistance;
    }
}
