package p150problems;

import java.util.*;

public class InsertIntervals {
    static class Node implements Comparable<Node>{
        public int first;
        public int second;
        Node(int f, int s){
            first=f;
            second=s;
        }

        @Override
        public int compareTo(Node o) {
            if(o.first!=this.first){
                return Integer.compare(o.first,this.first);
            }
            return Integer.compare(o.second,this.second);
        }
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Node> list = new ArrayList<>();
        Arrays.stream(intervals).forEach(x-> list.add(new Node(x[0],x[1])));
        int left=0;
        int right=intervals.length-1;
        while(left<right){
            int mid = left+((right-left)/2);
            if(newInterval[0] > intervals[mid][0]){
                left=mid+1;
            }else {
                right=mid;
            }
        }

        //pair to be added
        Node newP = new Node(newInterval[0], newInterval[1]);

        if(intervals.length==0){
            //means there is no interval its empty
            list.add(newP);
        }
        //so now left is last element which is smaller than new interval start
        else if(newInterval[0]>intervals[left][0])//if new interval is very very small
        {
            list.add(left+1,newP);
        }else {
            list.add(left,newP);
        }

        return mergeIntervals(list);
    }

    private int[][] mergeIntervals(List<Node> list) {
        int i=1;
        while(i<list.size()){
            Node prev = list.get(i-1);
            Node curr = list.get(i);
            if(curr.first>=prev.first && curr.first<=prev.second){
                prev.second = Math.max(prev.second,curr.second);
                list.remove(i);
            }else{
                i++;
            }
        }
        int ans[][]=new int[list.size()][2];
        int x=0;
        for(Node node : list){
            ans[x][0]=node.first;
            ans[x][1]=node.second;
            x++;
        }
        return ans;
    }

    public static void main(String args[]){
        int[][] intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = new int[]{4,8};
        for (int[] num : new InsertIntervals().insert(intervals, newInterval)) {
            System.out.print("["+num[0]+" ,"+num[1]+"] ,");
        }
    }
}
