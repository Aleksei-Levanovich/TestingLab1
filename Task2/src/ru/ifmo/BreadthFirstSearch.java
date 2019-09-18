package ru.ifmo;

import java.util.*;

public class BreadthFirstSearch {

    public static boolean[] breadthFirstSearch(List<List<Integer>> adjacencyList, int startVertex) {
        boolean[] visited = new boolean[adjacencyList.size()];
        Queue<Integer> visitQueue = new LinkedList<>();
        visitQueue.add(startVertex);

        while (!visitQueue.isEmpty()) {
            Integer vertex = visitQueue.poll();
            visited[vertex] = true;

            List<Integer> neighbors = adjacencyList.get(vertex);
            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visitQueue.add(neighbor);
                }
            }
            logStep(vertex, visited);
        }
        return visited;
    }

    private static void logStep(int visitedVertex, boolean[] curResult) {
        System.out.println(Arrays.toString(curResult) + ": visited vertex " + visitedVertex);
    }
}
