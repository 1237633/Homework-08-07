import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private int initialSize;
    private int arraySize = 0;
    private String[] IntegerArray = new String[initialSize];

    public IntegerListImpl(int initialSize) {
        this.initialSize = initialSize;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullItemException("You can't add null element!");
        }
        if (arraySize < IntegerArray.length - 2) { // Оставляем небольшой запас для "заглядывания вперед" разными методами
            IntegerArray[arraySize] = item;
        } else {
            IntegerArray = Arrays.copyOf(IntegerArray, arraySize + 10);
            IntegerArray[arraySize] = item;
        }
        arraySize++;
        //System.out.println(item + " " + arraySize + " " + IntegerArray.length);
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new NullItemException("You can't add null element!");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Index of element must be positive!");
        }
        if (index > arraySize - 1) {  //arraySize can't be > array.length
            throw new IndexOutOfBoundsException("There is no such cell in List");
        } else {
            for (int i = arraySize; i >= index; i--) {
                IntegerArray[i] = IntegerArray[i - 1];
            }
            IntegerArray[index] = item;
        }
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        if (index > arraySize - 1 || index < 0) {
            throw new IllegalArgumentException("Index must be positive and less or equal to List size -1");
        }
        if (!contains(item)) {
            throw new IllegalArgumentException("No such item in list!");
        }
        IntegerArray[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        if (!contains(item)) {
            throw new IllegalArgumentException("No such item!");
        }
        int index = indexOf(item);
        remove(index);
        return item;
    }

    @Override
    public String remove(int index) {
        if (index > arraySize - 1 || index < 0) {
            throw new IllegalArgumentException("Index must be positive and less or equal to List size -1");
        }
        String result = IntegerArray[index];
        for (int i = index; i < arraySize; i++) {
            if (IntegerArray[i + 1] == null) {
                IntegerArray[i] = null;
                break;
            }
            IntegerArray[i] = IntegerArray[i + 1];
        }
        arraySize--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        for (int i = 0; i < arraySize; i++) {
            if (IntegerArray[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        for (int i = 0; i < arraySize; i++) {
            if (IntegerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        for (int i = arraySize - 1; i >= 0; i--) {
            if (IntegerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index > arraySize - 1 || index < 0) {
            throw new IllegalArgumentException("Index must be positive and less or equal to List size -1");
        }
        String result = IntegerArray[index];
        return result;
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(arraySize);
        result = 31 * result + Arrays.hashCode(IntegerArray);
        return result;
    }


    public boolean equals(StringList otherList) {
        if (this == otherList) return true;
        if (otherList == null || getClass() != otherList.getClass()) return false;
        IntegerListImpl that = (IntegerListImpl) otherList;
        return arraySize == that.arraySize && Arrays.equals(IntegerArray, that.IntegerArray);
    }

    @Override
    public int size() {
        int result = arraySize;
        return result;
    }

    @Override
    public boolean isEmpty() {
        if (arraySize == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        IntegerArray = new String[10];
    }

    @Override
    public String[] toArray() {
        String[] result = IntegerArray;
        return result;
    }
}
