import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class IntegerListImplTest {
    IntegerListImpl integerList;
    Random random;
    Integer item = 6;
    Integer wrongItem = 70;

    @BeforeEach
    void setUp() {
        integerList = new IntegerListImpl(10);
        random = new Random();
    }

    @Test
    void add() {
        Integer[] expected = new Integer[34];
        for (int i = 0; i < 30; i++) {
            integerList.add(i);
            expected[i] = i;
        }
        System.out.println(Arrays.toString(integerList.toArray()));
        Assertions.assertArrayEquals(expected, integerList.toArray());

    }

    @Test
    void addWithIndex() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Integer[] expected = {0, 1, 2, 15, 3, 4, null, null, null, null};
        integerList.add(3, 15);
        Assertions.assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void addShouldThrowExceptionWhenIndexExceedSize() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integerList.add(6, wrongItem));
    }

    @Test
    void addShouldThrowExceptionWhenIndexLessThanZero() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.add(-1, wrongItem));
    }

    @Test
    void set() {
        Integer[] expected = {0, 1, 6, 3, 4, 5, 6, 7, 8, 9, 10, null, null, null, null, null, null, null};
        for (int i = 0; i < 11; i++) {
            integerList.add(i);
        }
        integerList.set(2, 6);
        System.out.println(Arrays.toString(integerList.toArray()));
        Assertions.assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void setThrowsExceptionOnIncorrectIndex() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.set(5, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.set(-2, 3));
    }

    @Test
    void remove() {
        Integer[] expected = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10, null, null, null, null, null, null, null, null};
        for (int i = 0; i < 11; i++) {
            integerList.add(i);
        }
        integerList.remove(item);
        System.out.println(Arrays.toString(integerList.toArray()));
        Assertions.assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void removeThrowsExceptionOnUnexistingItem() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.remove(wrongItem));
    }

    @Test
    void testRemove() {
        Integer[] expected = {0, 1, 2, 3, 4, 6, 7, 8, 9, 10, null, null, null, null, null, null, null, null};
        for (int i = 0; i < 11; i++) {
            integerList.add(i);
        }
        integerList.remove(5);
        System.out.println(Arrays.toString(integerList.toArray()));
        Assertions.assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void removeByIndexThrowsExceptions() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.remove(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.remove(6));
    }

    @Test
    void contains() {
        boolean[] actual = new boolean[2];
        boolean[] expected = {true, false};

        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        actual[0] = integerList.contains(3);
        actual[1] = integerList.contains(7);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void indexOf() {
        for (int i = 0; i < 15; i++) {
            integerList.add(i);
        }
        Assertions.assertEquals(6, integerList.indexOf(6));
    }

    @Test
    void IndexOfUnexistingElement() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertEquals(-1, integerList.indexOf(wrongItem));
    }


    @Test
    void lastIndexOf() {
        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }
        integerList.add(8, 3);
        Assertions.assertEquals(8, integerList.lastIndexOf(3));
    }


    @Test
    void get() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertEquals(2, integerList.get(2));
    }

    @Test
    void getThrowsExceptoinOnWrongIndex() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.get(5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> integerList.get(-2));
    }

    @Test
    void testEquals() {
        for (int i = 0; i < 15; i++) {
            integerList.add(i);
        }

        IntegerList expected = new IntegerListImpl(10);

        for (int i = 0; i < 15; i++) {
            expected.add(i);
        }

        Assertions.assertTrue(integerList.equals(expected));
    }

    @Test
    void testEqualsDifferentLists() {

        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }

        IntegerList expected = new IntegerListImpl(10);

        for (int i = 0; i < 10; i++) {
            expected.add(i);
        }
        expected.add(wrongItem);

        Assertions.assertFalse(integerList.equals(expected));
    }


    @Test
    void size() {
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }
        Assertions.assertEquals(5, integerList.size());
    }

    @Test
    void isEmpty() {
        integerList.add(12);
        integerList.remove(0);
        Assertions.assertTrue(integerList.isEmpty());
    }

    @Test
    void isEmptyWhenNot() {
        integerList.add(12);
        Assertions.assertFalse(integerList.isEmpty());
    }

    @Test
    void clear() {
        for (int i = 0; i < 25; i++) {
            integerList.add(i);
        }
        integerList.clear();
        System.out.println(Arrays.toString(integerList.toArray()));
        Integer[] expected = new Integer[10];
        Assertions.assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void toArray() {
        Integer[] expected = new Integer[10];
        for (int i = 0; i < 5; i++) {
            integerList.add(i);
            expected[i] = i;
        }
        Assertions.assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void allNullsTest() {
        integerList.add(wrongItem);
        Assertions.assertThrows(NullItemException.class, () -> integerList.add(null));
        Assertions.assertThrows(NullItemException.class, () -> integerList.add(0, null));
        Assertions.assertThrows(NullItemException.class, () -> integerList.contains(null));
        Assertions.assertThrows(NullItemException.class, () -> integerList.set(0, null));
        Assertions.assertThrows(NullItemException.class, () -> integerList.indexOf(null));
        Assertions.assertThrows(NullItemException.class, () -> integerList.lastIndexOf(null));
        Assertions.assertThrows(NullItemException.class, () -> integerList.remove(null));
    }

    @Test
    void TimSort() {
        for (int i = 0; i < 25; i++) {
            integerList.add(random.nextInt(500));
        }

        integerList.sortWithTimSort();

        boolean naturalOrder = false;
        for (int i = 0; i < integerList.size() - 1; i++) {
            if (integerList.get(i) < integerList.get(i + 1)) {
                naturalOrder = true;
            }
        }
        Assertions.assertTrue(naturalOrder);
    }

    @Test
    void sort() {
        for (int i = 0; i < 25; i++) {
            integerList.add(random.nextInt(500));
        }

        integerList.sort();

        boolean naturalOrder = false;
        for (int i = 0; i < integerList.size() - 1; i++) {
            if (integerList.get(i) < integerList.get(i + 1)) {
                naturalOrder = true;
            }
        }
        Assertions.assertTrue(naturalOrder);
    }
}