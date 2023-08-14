public class BitTraversal {
    public static void main(String[] args) {
        int number = 42;  // Replace this with your desired number

        for (int i = 31; i >= 0; i--) {
            int bit = (number >> i) & 1;
            System.out.print(bit);
        }
        System.out.println();
    }
}
