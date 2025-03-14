package ms;

class RandomizedSet {
    HashMap<Integer,Integer> map =null;
    List<Integer> list = null;
    public RandomizedSet() {
        this.map= new HashMap<>();
        this.list= new ArrayList<>();
    }

    public boolean insert(int val) {
        // System.out.println("adding "+val);
        int last = list.size();
        Integer mapVal = map.getOrDefault(val,null);
        boolean status = mapVal==null;
        if(status){
            map.put(val,last);
            list.add(val);
        }
        // System.out.println(map.toString());
        // System.out.println(list.toString());
        return status;
    }

    public boolean remove(int val) {
        // System.out.println("removing "+val);
        Integer mapVal = map.getOrDefault(val,null);
        boolean status = mapVal!=null;
        if(status){
            int toBeDeletedPosition = map.get(val);
            list.remove(toBeDeletedPosition);
            map.remove(val);
            if(toBeDeletedPosition!=list.size() && !list.isEmpty()){
                val = list.removeLast();
                list.add(toBeDeletedPosition,val);
                map.put(val,toBeDeletedPosition);
            }
        }
        // System.out.println(map.toString());
        // System.out.println(list.toString());
        return status;
    }

    public int getRandom() {
        int last = list.size();
        int index = (int)(Math.random()*last);
        return list.get(index);
    }

    class Node{
        int val;
        int index;
        public Node(int val, int index){
            this.val = val;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            Node o = (Node)obj;
            return this.val==o.val;
        }
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */