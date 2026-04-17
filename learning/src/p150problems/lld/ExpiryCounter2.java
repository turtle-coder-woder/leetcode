package p150problems.lld;

import java.util.*;

public class ExpiryCounter2<K> {


    class Entry<K> {
        K key;
        long timeExpiryInMilli;

        public Entry(K key, long timeExpiryInMilli) {
            this.key = key;
            this.timeExpiryInMilli = timeExpiryInMilli;
        }
    }

    long ttlInMillis;
    Deque<Entry<K>> dequeAllKeys;
    Map<K,Integer> mapOfEntryToCount;
    public ExpiryCounter2(long timeInSeconds) {
        if (timeInSeconds <= 0) {
            throw new IllegalArgumentException("time can't be less than or equal to 0");
        }

        this.ttlInMillis = timeInSeconds * 1000;
        this.dequeAllKeys = new ArrayDeque<>();
        this.mapOfEntryToCount = new HashMap<>();
    }


    public void putElement(K key){
        long now = System.currentTimeMillis();
        cleanUp(now);
        Entry<K> entry = new Entry<>(key,now+this.ttlInMillis);
        dequeAllKeys.offerLast(entry);
        mapOfEntryToCount.merge(key,1, Integer::sum);
    }

    public int getElementCount(K key){
        long now = System.currentTimeMillis();
        cleanUp(now);
        return mapOfEntryToCount.getOrDefault(key,0);
    }

    public int getTotalCount(){
        cleanUp(System.currentTimeMillis());
        return dequeAllKeys.size();
    }

    private void cleanUp(long now){
        //since you have now information, you can remove all elements from front if they are greater than now
        while(!dequeAllKeys.isEmpty() && dequeAllKeys.peekFirst().timeExpiryInMilli<=now){
            K key = dequeAllKeys.pollFirst().key;
            int newValue = mapOfEntryToCount.getOrDefault(key,0)-1;
            if(newValue<=0){
                mapOfEntryToCount.remove(key);
            }else{
                mapOfEntryToCount.put(key,newValue);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiryCounter2<String> counter = new ExpiryCounter2<>(3); // TTL = 3 sec

        System.out.println("Putting: a");
        counter.putElement("a"); // time ~0
        System.out.println("count(a) = " + counter.getElementCount("a")); // 1
        System.out.println("total    = " + counter.getTotalCount());    // 1
        //System.out.println("snapshot = " + counter.snapshotLiveCount());
        System.out.println();

        Thread.sleep(1000);

        System.out.println("Putting: a, b");
        counter.putElement("a"); // time ~1
        counter.putElement("b"); // time ~1
        System.out.println("count(a) = " + counter.getElementCount("a")); // 2
        System.out.println("count(b) = " + counter.getElementCount("b")); // 1
        System.out.println("total    = " + counter.getTotalCount());    // 3
        //System.out.println("snapshot = " + counter.snapshotLiveCount());
        System.out.println();

        Thread.sleep(2500); // total elapsed ~3.5 sec from first "a"

        System.out.println("After 3.5 sec from first insert:");
        System.out.println("count(a) = " + counter.getElementCount("a")); // first "a" expired, second "a" alive => 1
        System.out.println("count(b) = " + counter.getElementCount("b")); // likely 1 if still within ttl
        System.out.println("total    = " + counter.getTotalCount());
        //System.out.println("snapshot = " + counter.snapshotLiveCount());
        System.out.println();

        Thread.sleep(2000); // now all should expire

        System.out.println("After more time:");
        System.out.println("count(a) = " + counter.getElementCount("a")); // 0
        System.out.println("count(b) = " + counter.getElementCount("b")); // 0
        System.out.println("total    = " + counter.getTotalCount());    // 0
        //System.out.println("snapshot = " + counter.snapshotLiveCount());
    }
}
