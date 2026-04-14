package p150problems.lld;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentHashMapStudy {
//    static Map<String, Integer> map = new HashMap<>(); //ye use krega toh ans 20000 ni aeyag
    static Map<String, Integer> oldMap = new HashMap<>(); //ye use krega toh ans 20000 ni aeyag
static Map<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                //map.put("apple", map.getOrDefault("apple", 0) + 1);
                map.merge("apple", 1, (x,y)->{return x+y;});
                oldMap.merge("apple", 1, (x,y)->{return x+y;});
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map.get("apple"));
        System.out.println(oldMap.get("apple"));
    }
}
