package p150problems.other;


import java.util.*;

// Build graph as course -> prerequisites.
// Run DFS with 3 states: unvisited, visiting, visited.
// If we revisit a "visiting" node, a cycle exists so no valid ordering.
// Add course to answer only after all its prerequisites are processed.
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int[] prereq : prerequisites) {
            edges.computeIfAbsent(prereq[0], x -> new ArrayList<Integer>()).add(prereq[1]);
        }


        //null means unvisited, 0 means visiting, 1 means visited
        Map<Integer, Integer> visitedMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] hasCycle = new boolean[1];
        for (int i = 0; i < numCourses; i++) {
            int course = i;
            if (visitedMap.get(course) == null) {
                dfs(course, edges, visitedMap, stack, hasCycle);
            }
            if (hasCycle[0]) {
                return new int[]{};
            }

        }

        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(int course, Map<Integer, List<Integer>> edges, Map<Integer, Integer> visitedMap,
                     Deque<Integer> stack, boolean[] hasCycle) {
        Integer memoValue = visitedMap.getOrDefault(course, null);
        if (memoValue != null) {
            if (memoValue == 0) {
                hasCycle[0]=true; //cycle detection
            }
            return;
        }
        visitedMap.put(course,0);
        List<Integer> dependents = edges.getOrDefault(course,new ArrayList<>());
        for(int dependent: dependents){
            dfs(dependent,edges,visitedMap,stack,hasCycle);
        }

        stack.add(course);
        visitedMap.put(course,1);
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        int[] ans = new CourseScheduleII().findOrder(numCourses, prerequisites);
        Arrays.stream(ans).forEach(x -> System.out.print(x + ", "));
    }
}
