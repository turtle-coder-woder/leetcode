package p150problems;
import java.util.*;

public class CourseScheduleI {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> edges = new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            int dependentCourse = prerequisites[i][0];
            int course = prerequisites[i][1];
            edges.computeIfAbsent(course,k->new ArrayList<>()).add(dependentCourse);
        }

        Integer[] uniqueCourses = edges.keySet().toArray(new Integer[0]);
        Map<Integer,Integer> visitedMap = new HashMap<>();
        //0 or null means not visited, 1 means visiting, 2 means visited
        edges.keySet().forEach(x->visitedMap.put(x,0));

        for(int i=0;i<uniqueCourses.length;i++){
            int course = uniqueCourses[i];
            if(visitedMap.get(course)!=2 && hasCycleDFS(edges,course,visitedMap)){
                return false;
            }
        }
        return true;
    }

    private boolean hasCycleDFS(Map<Integer, List<Integer>> edges,
                                int course, Map<Integer, Integer> visitedMap) {
        int visitedValue = visitedMap.getOrDefault(course,0);
        if(visitedValue==2){
            //means we already visited from this node and there was no cycle, return false
            return false;
        }

        if(visitedValue==1){
            return true; //yes there is a cycle if this course has been already visited
        }

        visitedMap.put(course,1); //put this course in visiting
        //Collections.emptyList() vs new ArrayList<>() ====>>>>>
        // Collections.emptyList() creates no extra memory but new ArrayList<>() creates extra memory
        List<Integer> dependentCourses = edges.getOrDefault(course,Collections.emptyList());
        for (Integer dependentCourse : dependentCourses) {
            if (hasCycleDFS(edges, dependentCourse, visitedMap)) {
                return true;
            }
        }
        visitedMap.put(course,2);//course successfully visited
        return false;
    }

    public static void main(String[] args){
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1,0}};
        boolean ans = new CourseScheduleI().canFinish(numCourses,prerequisites);
        System.out.println(ans);
    }

}
