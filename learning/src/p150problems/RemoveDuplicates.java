package p150problems;

import java.util.Arrays;

public class RemoveDuplicates {
    //since number are already sorted form min to max
    // we can just keep a var to keep frequency of elements we are iterating
    // we need to keep 2 pointers , 1 for scanning/reading and 1 for writing
    // writing pointer will only move and write once we are having legit frequency i.e 2max
    // if we see 3rd duplicate, dont increase write pointer but move on to increase read pointer
    public int removeDuplicates(int[] nums) {
        int writePointer = 1;
        int readPointer = 1;
        Integer lastElement = nums[0];
        Integer frequency = 1;

        for (; readPointer < nums.length; readPointer++) {
            int currentElement = nums[readPointer];
            if (currentElement == lastElement) {
                if(frequency<2){
                    frequency++;
                    nums[writePointer++]=currentElement;
                }
            }else{
                lastElement=currentElement;
                frequency=1;
                nums[writePointer++]=currentElement;
            }
        }
//to debug
//        Arrays.stream(nums).forEach(x->System.out.println(x+","));

        return writePointer;
    }

    public static void main(String args[]) {
        int a[] = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(new RemoveDuplicates().removeDuplicates(a));
    }
}
