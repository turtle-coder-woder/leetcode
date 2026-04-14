package p150problems.lld;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;


public class PubSubMiniRedis {

    class Message {
        private final String id;
        private final String topic;
        private final String payload;
        private final long timestamp;

        public Message(String topic, String payload) {
            this.id = UUID.randomUUID().toString();
            this.topic = topic;
            this.payload = payload;
            this.timestamp = System.currentTimeMillis();
        }

        public String getId() {
            return id;
        }

        public String getTopic() {
            return topic;
        }

        public String getPayload() {
            return payload;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }

    interface Subscriber {
        String getId();
        void consume(Message message);
    }

    class SubscriberWorker implements Runnable {

        private final Subscriber subscriber;
        private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        private final Thread workerThread;

        public SubscriberWorker(Subscriber subscriber) {
            this.subscriber = subscriber;
            this.workerThread = new Thread(this); // Runnable pass kiya
            this.workerThread.setDaemon(true);
            this.workerThread.start();
        }

        public Subscriber getSubscriber() {
            return subscriber;
        }

        public void enqueue(Message message) {
            queue.offer(message);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Message message = queue.take();
                    subscriber.consume(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Topic {
        private final String name;
        private final CopyOnWriteArrayList<SubscriberWorker> subscribers;

        public Topic(String name) {
            this.name = name;
            this.subscribers = new CopyOnWriteArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<SubscriberWorker> getSubscribers() {
            return subscribers;
        }

        public void addSubscriber(SubscriberWorker worker) {
            subscribers.add(worker);
        }
    }

    class PubSubBroker {
        private final Map<String, Topic> topics = new ConcurrentHashMap<>();

        public void createTopic(String topicName) {
            topics.putIfAbsent(topicName, new Topic(topicName));
        }

        public void subscribe(String topicName, Subscriber subscriber) {
            Topic topic = topics.get(topicName);
            if (topic == null) {
                throw new IllegalArgumentException("Topic does not exist: " + topicName);
            }

            SubscriberWorker worker = new SubscriberWorker(subscriber);
            topic.addSubscriber(worker);
        }

        public void publish(String topicName, String payload) {
            Topic topic = topics.get(topicName);
            if (topic == null) {
                throw new IllegalArgumentException("Topic does not exist: " + topicName);
            }

            Message message = new Message(topicName, payload);

            for (SubscriberWorker worker : topic.getSubscribers()) {
                worker.enqueue(message);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PubSubMiniRedis app = new PubSubMiniRedis();

        PubSubBroker broker = app.new PubSubBroker();

        // Create topics
        broker.createTopic("sports");
        broker.createTopic("stocks");

        // Create subscribers
        Subscriber sub1 = new Subscriber() {
            @Override
            public String getId() {
                return "S1";
            }

            @Override
            public void consume(Message message) {
                System.out.println("Subscriber S1 got: " + message.getPayload());
            }
        };

        Subscriber sub2 = new Subscriber() {
            @Override
            public String getId() {
                return "S2";
            }

            @Override
            public void consume(Message message) {
                System.out.println("Subscriber S2 got: " + message.getPayload());
            }
        };

        // Subscribe
        broker.subscribe("sports", sub1);
        broker.subscribe("sports", sub2);

        // Publish messages
        broker.publish("sports", "India won the match");
        broker.publish("sports", "Virat scored 100");

        // Sleep to allow async processing
        Thread.sleep(1000);
    }
}
