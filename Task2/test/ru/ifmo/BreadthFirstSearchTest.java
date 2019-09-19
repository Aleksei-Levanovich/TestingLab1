package ru.ifmo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class BreadthFirstSearchTest {

    @Test
    void throwsExceptionOnIncorrectListArgument() {
        List<List<Integer>> adjacencyList = asList(asList(-1));

        assertThrows(IndexOutOfBoundsException.class,
                () -> new BreadthFirstSearch(adjacencyList, 0).run());
    }

    @Test
    void throwsExceptionOnIncorrectVertexArgument() {
        List<List<Integer>> adjacencyList = asList(asList(0));

        assertThrows(IndexOutOfBoundsException.class,
                () -> new BreadthFirstSearch(adjacencyList, -1).run());
    }

    @Test
    void throwsExceptionOnIndexOutOfBoundArgument() {
        List<List<Integer>> adjacencyList = asList(asList(1));

        assertThrows(IndexOutOfBoundsException.class,
                () -> new BreadthFirstSearch(adjacencyList, 0).run());
    }

    @Test
    void visitsItself() {
        List<List<Integer>> adjacencyList = asList(
                asList()
        );
        boolean[] expectedResult = { true };

        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(0, new boolean[] {true})
        );

        checkResultAndLog(adjacencyList, 0, expectedResult, expectedLog);
    }

    private void checkResultAndLog(List<List<Integer>> adjacencyList, int startVertex, boolean[] expectedResult,
                                   Collection<BreadthFirstSearch.LogItem> expectedLog) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(adjacencyList, startVertex);
        boolean[] result = bfs.run();
        assertArrayEquals(expectedResult, result);

        Collection<BreadthFirstSearch.LogItem> logs = bfs.getLogs();
        Assertions.assertEquals(expectedLog, logs);
    }

    @Test
    void visitsClosestNeighbour() {
        List<List<Integer>> adjacencyList = asList(
                asList(1),
                asList()
        );
        boolean[] expectedResult = { true, true };
        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(0, new boolean[] {true, true}),
                new BreadthFirstSearch.LogItem(1, new boolean[] {true, true})
        );
        checkResultAndLog(adjacencyList, 0, expectedResult, expectedLog);
    }

    @Test
    void notVisitsClosestNeighbourFromOppositeDirectionEdge() {
        List<List<Integer>> adjacencyList = asList(
                asList(),
                asList(1)
        );
        boolean[] expectedResult = { true, false };
        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(0, new boolean[] {true, false})
        );
        checkResultAndLog(adjacencyList, 0, expectedResult, expectedLog);
    }

    @Test
    void notVisitVertexesWithoutWayTo() {
        List<List<Integer>> adjacencyList = asList(
                asList(1),
                asList(3),
                asList(3, 4),
                asList(4),
                asList()
        );
        int startVertex = 1;
        boolean[] expectedResult = { false, true, false, true, true };

        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(1, new boolean[] {false, true, false, true, true}),
                new BreadthFirstSearch.LogItem(3, new boolean[] {false, true, false, true, true}),
                new BreadthFirstSearch.LogItem(4, new boolean[] {false, true, false, true, true})
        );
        checkResultAndLog(adjacencyList, startVertex, expectedResult, expectedLog);
    }

    @Test
    void surviveCycle() {
        List<List<Integer>> adjacencyList = asList(
                asList(1, 2),
                asList(3),
                asList(0),
                asList(2, 1),
                asList(1)
        );
        int startVertex = 0;
        boolean[] expectedResult = { true, true, true, true, false };

        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(0, new boolean[] {true, true, true, true, false}),
                new BreadthFirstSearch.LogItem(1, new boolean[] {true, true, true, true, false}),
                new BreadthFirstSearch.LogItem(2, new boolean[] {true, true, true, true, false}),
                new BreadthFirstSearch.LogItem(3, new boolean[] {true, true, true, true, false})
        );

        checkResultAndLog(adjacencyList, startVertex, expectedResult, expectedLog);
    }

    @Test
    void surviveMoreThanOneEdgeBetweenSimilarVertexes() {
        List<List<Integer>> adjacencyList = asList(
                asList(1, 1),
                asList(),
                asList(1)
        );
        int startVertex = 0;
        boolean[] expectedResult = { true, true, false };

        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(0, new boolean[] {true, true, false}),
                new BreadthFirstSearch.LogItem(1, new boolean[] {true, true, false}),
                new BreadthFirstSearch.LogItem(1, new boolean[] {true, true, false})
        );
        checkResultAndLog(adjacencyList, startVertex, expectedResult, expectedLog);
    }

    @Test
    void surviveEdgeToSameVertex() {
        List<List<Integer>> adjacencyList = asList(
                asList(0),
                asList()
        );
        int startVertex = 0;
        boolean[] expectedResult = { true, false };
        Collection<BreadthFirstSearch.LogItem> expectedLog = asList(
                new BreadthFirstSearch.LogItem(0, new boolean[] {true, false})
        );

        checkResultAndLog(adjacencyList, startVertex, expectedResult, expectedLog);
    }
}
