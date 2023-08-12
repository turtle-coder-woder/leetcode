import java.util.Random;

public class ShuffleAnArrayNormalLLD {
    private int[] workingArray;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swap(int i, int j) {
        int temp = workingArray[i];
        workingArray[i] = workingArray[j];
        workingArray[j] = temp;
    }

    public ShuffleAnArrayNormalLLD(int[] nums) {
        workingArray = nums;
        original = nums.clone();
    }

    public int[] reset() {
        workingArray = original;
        original = original.clone();
        return original;
    }

    public int[] shuffle() {
        for (int i = 0; i < workingArray.length; i++) {
            swap(i, randRange(i, workingArray.length));
        }
        return workingArray;
    }
}