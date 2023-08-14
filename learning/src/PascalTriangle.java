import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> innerList1 = new ArrayList<>();
        innerList1.add(1);
        list.add(innerList1);
        if (numRows == 1) {
            return list;
        }

        ArrayList<Integer> innerList2 = new ArrayList<>();
        innerList2.add(1);
        innerList2.add(1);
        list.add(innerList2);
        if (numRows == 2) {
            return list;
        }

        //0,1,2
        for (int i = 3; i <= numRows; i++) {
            List<Integer> previousList = list.get(i - 2);

            ArrayList<Integer> innerList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    innerList.add(1);
                } else {
                    innerList.add(previousList.get(j - 1) + previousList.get(j));
                }
            }
            list.add(innerList);
        }


        return list;
    }
}
