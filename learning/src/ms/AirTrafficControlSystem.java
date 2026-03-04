    package ms;



    import java.util.*;

    // Enum for Aircraft Size
    enum Size {
        LARGE, SMALL, MINI
    }

    // Enum for Aircraft Type
    enum Type {
        PASSENGERS, CARGO
    }

    // Aircraft class representing an aircraft
    class Aircraft {
        private final Size size;
        private final Type type;
        private final String identifier;

        public Aircraft(String identifier, Size size, Type type) {
            this.identifier = identifier;
            this.size = size;
            this.type = type;
        }

        public Size getSize() {
            return size;
        }

        public Type getType() {
            return type;
        }

        public String getIdentifier() {
            return identifier;
        }

        @Override
        public String toString() {
            return "Aircraft{" + "identifier='" + identifier + '\'' + ", size=" + size + ", type=" + type + '}';
        }
    }

    // Comparator to determine aircraft priority
    class AircraftComparator implements Comparator<Aircraft> {
        @Override
        public int compare(Aircraft a1, Aircraft a2) {
    //        // Passengers before Cargo
    //        if (a1.getType() != a2.getType()) {
    //            return a1.getType() == Type.PASSENGERS ? -1 : 1;
    //        }
    //        // Large before Small
    //        return a1.getSize() == a2.getSize() ? 0 : (a1.getSize() == Size.LARGE ? -1 : 1);

            // Passengers before Cargo
            if ((a1.getType() != a2.getType())) {
                return a1.getType() == Type.PASSENGERS ? -1 : 1;
            }
            // Large before Small
            return a1.getSize() == a2.getSize() ? 0 : (a1.getSize() == Size.LARGE ? -1 : 1);

        }
    }




    // Air Traffic Control System
    class AirTrafficControl {
        private final PriorityQueue<Aircraft> queue;

        public AirTrafficControl() {
            this.queue = new PriorityQueue<>(new AircraftComparator());
        }

        public void scheduleAircraft(Aircraft aircraft) {
            queue.offer(aircraft);
            System.out.println("Scheduled: " + aircraft);
        }

        public Aircraft getNextAircraft() {
            return queue.poll();
        }

        public boolean hasAircrafts() {
            return !queue.isEmpty();
        }
    }

    // Main class to run the simulation
    public class AirTrafficControlSystem {
        public static void main(String... args) {
            AirTrafficControl atc = new AirTrafficControl();

            // Creating 10 aircraft
            List<Aircraft> aircrafts = Arrays.asList(
                    new Aircraft("A1", Size.LARGE, Type.PASSENGERS),
                    new Aircraft("A2", Size.SMALL, Type.PASSENGERS),
                    new Aircraft("A3", Size.LARGE, Type.CARGO),
                    new Aircraft("A4", Size.SMALL, Type.CARGO),
                    new Aircraft("A5", Size.LARGE, Type.PASSENGERS),
                    new Aircraft("A6", Size.LARGE, Type.CARGO),
                    new Aircraft("A7", Size.SMALL, Type.PASSENGERS),
                    new Aircraft("A8", Size.SMALL, Type.CARGO),
                    new Aircraft("A9", Size.LARGE, Type.PASSENGERS),
                    new Aircraft("A10", Size.SMALL, Type.PASSENGERS)
            );

            // Scheduling aircraft
            for (Aircraft aircraft : aircrafts) {
                atc.scheduleAircraft(aircraft);
            }

            System.out.println("\nProcessing takeoffs...");
            while (atc.hasAircrafts()) {
                System.out.println("Taking off: " + atc.getNextAircraft());
            }
        }
    }
