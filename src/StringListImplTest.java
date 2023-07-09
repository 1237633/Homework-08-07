import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringList stringList;

    @BeforeEach
    void setUp() {
        stringList = new StringListImpl(10);
    }

    @org.junit.jupiter.api.Test
    void add() {
        String[] expected = new String[34];
        for (int i = 0; i < 30; i++) {
            stringList.add(String.valueOf(i));
            expected[i] = String.valueOf(i);
        }
        Assertions.assertArrayEquals(expected, stringList.toArray());

    }

    @Test
    void addWithIndex() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        String[] expected = {"0", "1", "2", "a", "4", null, null, null, null, null};
        stringList.add(3, "a");
        Assertions.assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void addShouldThrowExceptionWhenIndexExceedSize() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> stringList.add(6, "a"));
    }

    @Test
    void addShouldThrowExceptionWhenIndexLessThanZero() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.add(-1, "a"));
    }

    @Test
    void set() {
        String[] expected = {"0", "1", "6", "3", "4", "5", "6", "7", "8", "9", "10", null, null, null, null, null, null, null};
        for (int i = 0; i < 11; i++) {
            stringList.add(String.valueOf(i));
        }
        stringList.set(2, "6");
        System.out.println(Arrays.toString(stringList.toArray()));
        Assertions.assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void setThrowsExceptionOnUnexistingItem() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.set(1, "a"));
    }

    @Test
    void setThrowsExceptionOnIncorrectIndex() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.set(5, "3"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.set(-2, "3"));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        String[] expected = {"0", "1", "2", "3", "4", "6", "7", "8", "9", "10", null, null, null, null, null, null, null, null};
        for (int i = 0; i < 11; i++) {
            stringList.add(String.valueOf(i));
        }
        stringList.remove("5");
        System.out.println(Arrays.toString(stringList.toArray()));
        Assertions.assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void removeThrowsExceptionOnUnexistingItem() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.remove("Kebab"));
    }

    @org.junit.jupiter.api.Test
    void testRemove() {
        String[] expected = {"0", "1", "2", "3", "4", "6", "7", "8", "9", "10", null, null, null, null, null, null, null, null};
        for (int i = 0; i < 11; i++) {
            stringList.add(String.valueOf(i));
        }
        stringList.remove(5);
        System.out.println(Arrays.toString(stringList.toArray()));
        Assertions.assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void removeByIndexThrowsExceptions() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.remove(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.remove(6));
    }

    @org.junit.jupiter.api.Test
    void contains() {
        boolean[] actual = new boolean[2];
        boolean[] expected = {true, false};

        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        actual[0] = stringList.contains("3");
        actual[1] = stringList.contains("7");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void indexOf() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertEquals(3, stringList.indexOf("3"));
    }

    @Test
    void IndexOfUnexistingElement() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertEquals(-1, stringList.indexOf("a"));
    }


    @org.junit.jupiter.api.Test
    void lastIndexOf() {
        for (int i = 0; i < 10; i++) {
            stringList.add(String.valueOf(i));
        }
        stringList.add(8, "3");
        Assertions.assertEquals(8, stringList.lastIndexOf("3"));
    }


    @Test
    void get() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertEquals("2", stringList.get(2));
    }

    @Test
    void getThrowsExceptoinOnWrongIndex() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.get(5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringList.get(-2));
    }

    @Test
    void testEquals() {
        for (int i = 0; i < 15; i++) {
            stringList.add(String.valueOf(i));
        }

        StringList expected = new StringListImpl(10);

        for (int i = 0; i < 15; i++) {
            expected.add(String.valueOf(i));
        }

        Assertions.assertTrue(stringList.equals(expected));
    }

    @Test
    void testEqualsDifferentLists() {

        for (int i = 0; i < 10; i++) {
            stringList.add(String.valueOf(i));
        }

        StringList expected = new StringListImpl(10);

        for (int i = 0; i < 10; i++) {
            expected.add(String.valueOf(i));
        }
        expected.add("Vasya");

        Assertions.assertFalse(stringList.equals(expected));
    }


    @org.junit.jupiter.api.Test
    void size() {
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
        }
        Assertions.assertEquals(5, stringList.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        stringList.add("12");
        stringList.remove(0);
        Assertions.assertTrue(stringList.isEmpty());
    }

    @Test
    void isEmptyWhenNot() {
        stringList.add("12");
        Assertions.assertFalse(stringList.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        for (int i = 0; i < 25; i++) {
            stringList.add(String.valueOf(i));
        }
        stringList.clear();
        System.out.println(Arrays.toString(stringList.toArray()));
        String[] expected = new String[10];
        Assertions.assertArrayEquals(expected, stringList.toArray());
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        String[] expected = new String[10];
        for (int i = 0; i < 5; i++) {
            stringList.add(String.valueOf(i));
            expected[i] = String.valueOf(i);
        }
        Assertions.assertArrayEquals(expected, stringList.toArray());
    }
}