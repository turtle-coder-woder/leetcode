package p150problems.lld;

import java.util.*;

public class RateLimiter {
    class Request{
        String id;
        long timeStamp;
        public Request(){
            this.id = UUID.randomUUID().toString();
            this.timeStamp = System.currentTimeMillis();
        }
    }

    Map<String, Deque<Request>> mapOfUserToQueueOfRequest;
    long windowSizeInMilli;
    int maxRequestAllowed;
    public RateLimiter(long windowSizeInSeconds,
            int maxRequestAllowed){
        this.mapOfUserToQueueOfRequest = new HashMap<>();
        this.windowSizeInMilli = 1000L * windowSizeInSeconds;
        this.maxRequestAllowed = maxRequestAllowed;
    }

    boolean allowRequest(String userId){
        long now = System.currentTimeMillis();
        cleanUp(userId,now);
        Deque<Request> requestsDequeue = mapOfUserToQueueOfRequest.computeIfAbsent(userId,x->new ArrayDeque<>());
        if(requestsDequeue.size()>=maxRequestAllowed){
            System.err.println("can't make more request, pls cool down");
            return false;
        }

        requestsDequeue.add(new Request());
        return true;
    }

    private void cleanUp(String userId, long now) {
        Deque<Request> requestsDequeue = mapOfUserToQueueOfRequest.computeIfAbsent(userId,x->new ArrayDeque<>());
        if(requestsDequeue.isEmpty()){
            return;
        }
        long allowedFromTimeInMillis = now-windowSizeInMilli;
        while(!requestsDequeue.isEmpty() &&
                requestsDequeue.peekFirst().timeStamp<=allowedFromTimeInMillis){
            requestsDequeue.pollFirst();
        }
        if(requestsDequeue.isEmpty()){
            mapOfUserToQueueOfRequest.remove(userId);
        }
    }

    private long getCurrentWindowRequestCount(String user) {
        cleanUp(user,System.currentTimeMillis());
        Deque<Request> deque = mapOfUserToQueueOfRequest.get(user);
        return deque==null ? 0 : deque.size();
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(5, 3); // max 3 requests in 5 seconds

        String user = "mohnish";

        System.out.println("Request 1: " + limiter.allowRequest(user)); // true
        System.out.println("Request 2: " + limiter.allowRequest(user)); // true
        System.out.println("Request 3: " + limiter.allowRequest(user)); // true
        System.out.println("Request 4: " + limiter.allowRequest(user)); // false

        System.out.println("Current count = " + limiter.getCurrentWindowRequestCount(user)); // 3

        Thread.sleep(5500); // wait for window to expire

        System.out.println("After 5.5 sec...");
        System.out.println("Request 5: " + limiter.allowRequest(user)); // true
        System.out.println("Current count = " + limiter.getCurrentWindowRequestCount(user)); // 1
    }


}
