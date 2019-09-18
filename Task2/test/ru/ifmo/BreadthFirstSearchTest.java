package ru.ifmo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ifmo.BreadthFirstSearch.breadthFirstSearch;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class BreadthFirstSearchTest {

    @Test
    void throwsExceptionOnIncorrectListArgument() {
        List<List<Integer>> adjacencyList = asList(asList(-1));

        assertThrows(IndexOutOfBoundsException.class,
                () -> breadthFirstSearch(adjacencyList, 0));
    }

    @Test
    void throwsExceptionOnIncorrectVertexArgument() {
        List<List<Integer>> adjacencyList = asList(asList(0));

        assertThrows(IndexOutOfBoundsException.class,
                () -> breadthFirstSearch(adjacencyList, -1));
    }

    @Test
    void throwsExceptionOnIndexOutOfBoundArgument() {
        List<List<Integer>> adjacencyList = asList(asList(1));

        assertThrows(IndexOutOfBoundsException.class,
                () -> breadthFirstSearch(adjacencyList, 0));
    }

    @Test
    void visitsItself() {
        List<List<Integer>> adjacencyList = asList(
                asList()
        );
        boolean[] expectedResult = { true };

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, 0));
    }

    @Test
    void visitsClosestNeighbour() {
        List<List<Integer>> adjacencyList = asList(
                asList(1),
                asList()
        );
        boolean[] expectedResult = { true, true };

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, 0));
    }

    @Test
    void notVisitsClosestNeighbourFromOppositeDirectionEdge() {
        List<List<Integer>> adjacencyList = asList(
                asList(),
                asList(1)
        );
        boolean[] expectedResult = { true, false };

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, 0));
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

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, startVertex));
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

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, startVertex));
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

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, startVertex));
    }

    @Test
    void surviveEdgeToSameVertex() {
        List<List<Integer>> adjacencyList = asList(
                asList(0),
                asList()
        );
        int startVertex = 0;
        boolean[] expectedResult = { true, false };

        assertArrayEquals(expectedResult, breadthFirstSearch(adjacencyList, startVertex));
    }
}
