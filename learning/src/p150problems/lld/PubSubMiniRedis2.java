package p150problems.lld;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class PubSubMiniRedis2 {

    class Topic {
        String id;
        String name;

        public Topic(String name) {
            this.name = name;
            this.id = UUID.randomUUID().toString();
        }
    }

    class Message {
        public String id;
        public Topic topic;
        public String payload;
        public long timeStamp;

        public Message(String payload, Topic topic) {
            this.id = UUID.randomUUID().toString();
            this.topic = topic;
            this.payload = payload;
            this.timeStamp = System.currentTimeMillis();
        }
    }

    interface Subscriber {
        String getName();
        void consume(Message m);
    }

    class SubscribeWorker implements Runnable {
        Thread thread;
        Subscriber subscriber;
        BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

        public SubscribeWorker(Subscriber subscriber) {
            this.subscriber = subscriber;
            this.thread = new Thread(this);
            this.thread.setDaemon(true);
            this.thread.start();
        }

        public void enqueueMessage(Message m) {
            messageQueue.offer(m);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Message message = messageQueue.take();
                    subscriber.consume(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Broker {
        ConcurrentHashMap<String, Topic> mapOfTopic;
        ConcurrentHashMap<Topic, CopyOnWriteArraySet<SubscribeWorker>> mapOfTopicToListOfSubscriberWorkers;
        ConcurrentHashMap<Subscriber, SubscribeWorker> mapOfSubscriberToSubscriberWorker;

        Broker() {
            mapOfTopic = new ConcurrentHashMap<>();
            mapOfTopicToListOfSubscriberWorkers = new ConcurrentHashMap<>();
            mapOfSubscriberToSubscriberWorker = new ConcurrentHashMap<>();
        }

        Topic getTopic(String topicName) {
            if (topicName == null || !mapOfTopic.containsKey(topicName)) {
                throw new IllegalArgumentException("Invalid topic name: " + topicName);
            }
            return mapOfTopic.get(topicName);
        }

        void subscribe(Subscriber subscriber, String topicName) {
            Topic topic = getTopic(topicName);

            SubscribeWorker subscribeWorker =
                    mapOfSubscriberToSubscriberWorker.computeIfAbsent(
                            subscriber,
                            x -> new SubscribeWorker(subscriber)
                    );

            CopyOnWriteArraySet<SubscribeWorker> set =
                    mapOfTopicToListOfSubscriberWorkers.computeIfAbsent(
                            topic,
                            x -> new CopyOnWriteArraySet<>()
                    );

            if (set.contains(subscribeWorker)) {
                System.err.println("already subscribed");
                return;
            }

            set.add(subscribeWorker);
        }

        void unsubscribe(Subscriber subscriber, String topicName) {
            Topic topic = getTopic(topicName);

            SubscribeWorker subscribeWorker =
                    mapOfSubscriberToSubscriberWorker.getOrDefault(subscriber, null);

            if (subscribeWorker == null) {
                System.err.println("no subscriber present in our memory");
                return;
            }

            CopyOnWriteArraySet<SubscribeWorker> set =
                    mapOfTopicToListOfSubscriberWorkers.get(topic);

            if (set == null || !set.contains(subscribeWorker)) {
                System.err.println("already unsubscribed");
                return;
            }

            set.remove(subscribeWorker);
        }

        void publish(String topicName, String payload) {
            Topic topic = getTopic(topicName);
            Message message = new Message(payload, topic);

            CopyOnWriteArraySet<SubscribeWorker> set =
                    mapOfTopicToListOfSubscriberWorkers.get(topic);

            if (set == null || set.isEmpty()) {
                System.err.println("no subscribers for topic: " + topicName);
                return;
            }

            for (SubscribeWorker subscribeWorker : set) {
                subscribeWorker.enqueueMessage(message);
            }
        }

        public void createTopic(String topicName) {
            Topic oldTopic = mapOfTopic.putIfAbsent(topicName, new Topic(topicName));
            if (oldTopic != null) {
                System.err.println("topic already present");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PubSubMiniRedis2 app = new PubSubMiniRedis2();

        Broker broker = app.new Broker();

        // Create topics
        broker.createTopic("sports");
        broker.createTopic("stocks");

        // Create subscribers
        Subscriber sub1 = new Subscriber() {
            @Override
            public String getName() {
                return "s1";
            }

            @Override
            public void consume(Message message) {
                System.out.println("Subscriber " + getName() + " got: " + message.payload);
            }
        };

        Subscriber sub2 = new Subscriber() {
            @Override
            public String getName() {
                return "s2";
            }

            @Override
            public void consume(Message message) {
                System.out.println("Subscriber " + getName() + " got: " + message.payload);
            }
        };

        // Subscribe
        broker.subscribe(sub1, "sports");
        broker.subscribe(sub2, "sports");
        broker.subscribe(sub2, "sports");

        // Publish messages
        broker.publish("sports", "India won the match");
        broker.publish("sports", "Virat scored 100");

        Thread.sleep(1000);
        System.out.println("program end");
    }
}