package test.java.com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Environment;
import com.hal9000.solver.Solution;
import org.junit.Test;

import java.util.ArrayList;

//import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by mis on 15.11.15.
 */
public class Arc2OptTest {
/*
    private Opt mover;
    private ArrayList<Integer> sequence;
    private Solution solution;
    private int dim = 10;
    private TSPInstance problem;

    public void setUp() {
        mover = new Arc2Opt();
        setUpSequence();
        solution = mock(Solution.class);
        problem = mock(TSPInstance.class);
        when(solution.getSolution()).thenReturn(sequence);
        when(solution.getProblem()).thenReturn(problem);
    }

    @Test
    public void testMove() {

        for (int i = 0; i < dim; i++) {
            for (int j = i + 1; j < dim; j++) {
                testMove(i, j);
            }
        }

    }

    private void testMove(int i, int j) {
        setUp();

        ArrayList<Integer> expected = new ArrayList<>();
        for (int k = 0; k < i; k++) {
            expected.add(k);
        }
        for (int k = j; k >= i; k--) {
            expected.add(k);
        }
        for (int k = j + 1; k < dim; k++) {
            expected.add(k);
        }

        mover.move(i, j, solution);

        assertArrayEquals(expected.toArray(), solution.getSolution().toArray());
    }

    @Test
    public void testGetMoveDeltaWhenN_0_last() throws Exception {
        setUp();
        when(problem.getDistance(0, dim-1)).thenReturn(10.0);
        when(problem.getDistance(dim-1, 0)).thenReturn(10.0);
        when(problem.getDistance(0, 0)).thenReturn(0.0);
        when(problem.getDistance(dim-1, dim-1)).thenReturn(0.0);

        assertEquals(0.0, mover.getMoveDelta(0, dim-1, solution), Environment.eps);
    }

    @Test
    public void testGetMoveDeltaWhenN_0_1() throws Exception {
        setUp();
        when(problem.getDistance(0, dim-1)).thenReturn(99.0);
        when(problem.getDistance(1, dim-1)).thenReturn(27.0);
        when(problem.getDistance(1, 2)).thenReturn(123.0);
        when(problem.getDistance(0, 2)).thenReturn(27.0);

        assertEquals(-99.0 - 123.0 + 27.0 + 27.0, mover.getMoveDelta(0, 1, solution), Environment.eps);
    }

    @Test
    public void testGetMoveDeltaWhenN_1_4() throws Exception {
        setUp();
        when(problem.getDistance(1, 0)).thenReturn(99.0);
        when(problem.getDistance(4, 0)).thenReturn(27.0);
        when(problem.getDistance(4, 5)).thenReturn(123.0);
        when(problem.getDistance(1, 5)).thenReturn(27.0);

        assertEquals(-99.0 - 123.0 + 27.0 + 27.0, mover.getMoveDelta(1, 4, solution), Environment.eps);
    }

    private void setUpSequence() {
        sequence = new ArrayList<>();
        for (int i = 0; i < dim; i++) {
            sequence.add(i);
        }
    }
*/
}