package com.hal9000.solver;

import org.junit.Test;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Environment;
import com.hal9000.solver.Solution;
import com.hal9000.solver.move.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*/*
 * Created by mis on 15.11.15.
 */
public class City2OptTest {

    private Opt mover;
    private ArrayList<Integer> sequence;
    private Solution solution;
    private int dim = 10;
    private TSPInstance problem;

    public void setUp() {
        mover = new City2Opt();
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
        expected.add(j);
        for (int k = i + 1; k < j; k++) {
            expected.add(k);
        }
        expected.add(i);
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
        when(problem.getDistance(0, 1)).thenReturn(50.0);
        when(problem.getDistance(dim-1, dim-2)).thenReturn(22.0);
        when(problem.getDistance(dim-1, 1)).thenReturn(15.0);
        when(problem.getDistance(0, dim-2)).thenReturn(393.0);

        assertEquals(-22.0 - 50.0 + 393.0 + 15.0, mover.getMoveDelta(0, dim-1, solution), Environment.eps);
    }

    @Test
    public void testGetMoveDeltaWhenN_0_1() throws Exception {
        setUp();
        when(problem.getDistance(0, 1)).thenReturn(10.0);
        when(problem.getDistance(1, 0)).thenReturn(10.0);
        when(problem.getDistance(0, dim-1)).thenReturn(50.0);
        when(problem.getDistance(1, dim-1)).thenReturn(22.0);
        when(problem.getDistance(1, 2)).thenReturn(15.0);
        when(problem.getDistance(0, 2)).thenReturn(393.0);

        assertEquals(-50.0 - 15.0 + 22.0 + 393.0, mover.getMoveDelta(0, 1, solution), Environment.eps);
    }

    @Test
    public void testGetMoveDeltaWhenN_1_4() throws Exception {
        setUp();
        when(problem.getDistance(1, 0)).thenReturn(10.0);
        when(problem.getDistance(1, 2)).thenReturn(121.0);
        when(problem.getDistance(4, 5)).thenReturn(50.0);
        when(problem.getDistance(4, 3)).thenReturn(23.0);
        when(problem.getDistance(4, 0)).thenReturn(76.0);
        when(problem.getDistance(4, 2)).thenReturn(8.0);
        when(problem.getDistance(1, 3)).thenReturn(89.0);
        when(problem.getDistance(1, 5)).thenReturn(142.0);

        assertEquals(-10.0 - 121.0 - 50.0 - 23.0 + 76.0 + 8.0 + 89.0 +142.0, mover.getMoveDelta(1, 4, solution), Environment.eps);
    }

    private void setUpSequence() {
        sequence = new ArrayList<>();
        for (int i = 0; i < dim; i++) {
            sequence.add(i);
        }
    }

}