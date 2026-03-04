package p150problems;

import java.util.Arrays;

public class ProductExceptSelf {
    //make a new array called product, init with 1
    //now start from left to right form i=1 , and keep on updating product as product[i]=nums[i-1]*product[i-1]
    //similarly do from right to left and start from n-1 to 0
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int leftProduct[] = new int[n];
        Arrays.fill(leftProduct, 1);
        int rightProduct[] = new int[n];
        Arrays.fill(rightProduct,1);

        //left to right
        for(int i=1;i<n;i++){
            leftProduct[i]=leftProduct[i-1]*nums[i-1];
        }

        //right to left
        for(int i=n-2;i>=0;i--){
            rightProduct[i]=rightProduct[i+1]*nums[i+1];
        }

        //multiply left and right
        for(int i=0;i<n;i++){
            leftProduct[i]=leftProduct[i]*rightProduct[i];
        }
        return leftProduct;


        /*
        enhance answer
            int n = nums.length;
    int[] result = new int[n];

    Arrays.fill(result, 1);

    int prefix = 1;
    for (int i = 0; i < n; i++) {
        result[i] = prefix;
        prefix *= nums[i];
    }

    int suffix = 1;
    for (int i = n - 1; i >= 0; i--) {
        result[i] *= suffix;
        suffix *= nums[i];
    }

    return result;
         */
    }
    public static void main(String args[]) {
        int a[] = new int[]{1,2,3,4};
        int ans[] = new ProductExceptSelf().productExceptSelf(a);
        Arrays.stream(ans).forEach(x-> System.out.print(x+","));
        System.out.println();
    }
}
