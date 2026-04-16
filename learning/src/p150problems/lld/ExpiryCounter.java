package p150problems.lld;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ExpiryCounter<K> {

    /*
     * Mental Model:
     * - Every putElement(k) inserts one live occurrence of key k.
     * - Each inserted occurrence expires after ttlMillis.
     * - Since all puts happen in time order, expiry order is also time order.
     * - So we can use a simple FIFO queue instead of a min-heap / priority queue.
     *
     * Data Structures:
     * 1) expiryQueue -> stores all inserted entries in expiry order
     * 2) liveCount   -> current live count per key
     * 3) totalLiveElements -> total live elements across all keys
     *
     * Before every read/write operation, we evict all expired entries from the front.
     *
     * Complexity:
     * - putElement      : O(1) amortized
     * - getElementCount : O(1) amortized
     * - getTotalElements: O(1) amortized
     * - Space           : O(n)
     */

    private static class Entry<K> {
        K key;
        long expiryTimeMillis;

        Entry(K key, long expiryTimeMillis) {
            this.key = key;
            this.expiryTimeMillis = expiryTimeMillis;
        }
    }

    private final long ttlMillis;
    private final Deque<Entry<K>> expiryQueue;
    private final Map<K, Integer> liveCount;
    private int totalLiveElements;

    public ExpiryCounter(long ttlSeconds) {
        if (ttlSeconds <= 0) {
            throw new IllegalArgumentException("TTL must be > 0");
        }

        this.ttlMillis = ttlSeconds * 1000;
        this.expiryQueue = new ArrayDeque<>();
        this.liveCount = new HashMap<>();
        this.totalLiveElements = 0;
    }

    public void putElement(K key) {
        long now = System.currentTimeMillis();
        evictExpired(now);

        long expiryTime = now + ttlMillis;
        expiryQueue.offerLast(new Entry<>(key, expiryTime));

        liveCount.put(key, liveCount.getOrDefault(key, 0) + 1);
        totalLiveElements++;
    }

    public int getElementCount(K key) {
        long now = System.currentTimeMillis();
        evictExpired(now);

        return liveCount.getOrDefault(key, 0);
    }

    public int getTotalElements() {
        long now = System.currentTimeMillis();
        evictExpired(now);

        return totalLiveElements;
    }

    private void evictExpired(long now) {
        while (!expiryQueue.isEmpty() && expiryQueue.peekFirst().expiryTimeMillis <= now) {
            Entry<K> expiredEntry = expiryQueue.pollFirst();

            int currentCount = liveCount.getOrDefault(expiredEntry.key, 0);

            if (currentCount <= 1) {
                liveCount.remove(expiredEntry.key);
            } else {
                liveCount.put(expiredEntry.key, currentCount - 1);
            }

            totalLiveElements--;
        }
    }

    // Optional helper for debugging / learning
    public Map<K, Integer> snapshotLiveCount() {
        long now = System.currentTimeMillis();
        evictExpired(now);
        return new HashMap<>(liveCount);
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiryCounter<String> counter = new ExpiryCounter<>(3); // TTL = 3 sec

        System.out.println("Putting: a");
        counter.putElement("a"); // time ~0
        System.out.println("count(a) = " + counter.getElementCount("a")); // 1
        System.out.println("total    = " + counter.getTotalElements());    // 1
        System.out.println("snapshot = " + counter.snapshotLiveCount());
        System.out.println();

        Thread.sleep(1000);

        System.out.println("Putting: a, b");
        counter.putElement("a"); // time ~1
        counter.putElement("b"); // time ~1
        System.out.println("count(a) = " + counter.getElementCount("a")); // 2
        System.out.println("count(b) = " + counter.getElementCount("b")); // 1
        System.out.println("total    = " + counter.getTotalElements());    // 3
        System.out.println("snapshot = " + counter.snapshotLiveCount());
        System.out.println();

        Thread.sleep(2500); // total elapsed ~3.5 sec from first "a"

        System.out.println("After 3.5 sec from first insert:");
        System.out.println("count(a) = " + counter.getElementCount("a")); // first "a" expired, second "a" alive => 1
        System.out.println("count(b) = " + counter.getElementCount("b")); // likely 1 if still within ttl
        System.out.println("total    = " + counter.getTotalElements());
        System.out.println("snapshot = " + counter.snapshotLiveCount());
        System.out.println();

        Thread.sleep(2000); // now all should expire

        System.out.println("After more time:");
        System.out.println("count(a) = " + counter.getElementCount("a")); // 0
        System.out.println("count(b) = " + counter.getElementCount("b")); // 0
        System.out.println("total    = " + counter.getTotalElements());    // 0
        System.out.println("snapshot = " + counter.snapshotLiveCount());
    }
}