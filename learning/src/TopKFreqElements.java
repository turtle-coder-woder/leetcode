import java.util.*;

public class TopKFreqElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer key = nums[i];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        PriorityQueue<Element> maxHeap = new PriorityQueue<>(k, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.freq-o2.freq;
            }
        });
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            Integer key = entry.getKey();
            Integer freq = entry.getValue();
            Element e = new Element(key, freq);
            if (maxHeap.size() < k) {
                maxHeap.add(e);
            } else if (!maxHeap.isEmpty() && freq >= maxHeap.peek().freq) {
                maxHeap.remove();
                maxHeap.add(e);
            }
        }

        int[] ans = new int[k];
        while (!maxHeap.isEmpty()) {
            ans[--k] = maxHeap.remove().key;
        }
        return ans;
    }

    class Element {
        public Integer key, freq;

        public Element(Integer key, Integer freq) {
            this.freq = freq;
            this.key = key;
        }
    }
}
