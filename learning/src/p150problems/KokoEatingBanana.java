package p150problems;

import java.util.Arrays;
import java.util.OptionalInt;

public class KokoEatingBanana {
    /*
    find maxPile

binary search on speed from 1 to maxPile

check if Koko can finish in h hours at speed mid

if yes, shrink right

if no, increase left

answer is left
    * */
    public int minEatingSpeed(int[] piles, int h) {
        int maxPilesofBanana =0;
        for(int pile:piles){
            maxPilesofBanana = Math.max(maxPilesofBanana,pile);
        }
        int left=1;
        int right = maxPilesofBanana;
        while(left<right){
            int mid=left+((right-left)/2);
            if(kokoCanEatInSpeed(piles,h,mid)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    private boolean kokoCanEatInSpeed(int[] piles, int hours, int eatingSpeedPerHour) {
        for(int i=0;i<piles.length;i++){
            hours-= Math.ceil(piles[i]/(eatingSpeedPerHour*1.0));
            if(hours<0){
                return false;
            }
        }
        return hours>=0;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3,6,7,11};
        int h=8;
        System.out.println(new KokoEatingBanana().minEatingSpeed(a,h));
    }
}
