import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        int Fizz = 3;
        int Buzz = 5;
        int FizzBuzz = 15;
        List<String> ans = new ArrayList();
        for(int i=1;i<=n;i++){
            if(i%15==0){
                ans.add("FizzBuzz");
            }else if(i%3==0){
                ans.add("Fizz");
            }else if(i%5==0){
                ans.add("Buzz");
            }else{
                ans.add(i+"");
            }
        }
        return ans;
    }
}
