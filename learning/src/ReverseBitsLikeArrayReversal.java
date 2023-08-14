public class ReverseBitsLikeArrayReversal {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        String orignal= Integer.toBinaryString(n);
        String reverse = new StringBuilder(orignal).reverse().toString();
        return Integer.parseInt(reverse, 2);
    }
}
