import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class BSTWithHeightTest {

    BSTWithHeight<Integer> T;
    HashSet<Integer> H;
    int[] A = { 5, 2, 4, 1, 5, 9, 8 }, A_sorted = { 1, 2, 4, 5, 8, 9 };

    @Before
    public void setUp() throws Exception {
        T = new BSTWithHeight<>();
        H = new HashSet<Integer>();
    }

    @Test
    public void find() throws Exception {
        for (int i = 0; i != A.length; ++i) {
            T.insert(A[i]);
            H.add(A[i]);
        }
        for (Integer k : H) assertTrue(T.find(k));
        for (int k : A_sorted) assertTrue(T.find(k));
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void remove() throws Exception {
        for (int i = 0; i != A.length; ++i) {
            T.insert(A[i]);
            H.add(A[i]);
        }

        for (int i = 0; i != A.length; ++i) {
            T.remove(A[i]);
            assertFalse(T.find(A[i]));
        }

    }

    @Test
    public void isAVL() throws Exception {
        BSTWithHeight<Integer> T = new BSTWithHeight<>();
        T.insert(53);
        assertTrue(T.isAVL());
        T.insert(42);
        assertTrue(T.isAVL());
        T.insert(21);
        assertFalse(T.isAVL());
    }

}