import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {
    private int initialSize;
    private int arraySize = 0;
    private String[] stringArray = new String[initialSize];

    public StringListImpl(int initialSize) {
        this.initialSize = initialSize;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullItemException("You can't add null element!");
        }
        if (arraySize < stringArray.length - 2) { // Оставляем небольшой запас для "заглядывания вперед" разными методами
            stringArray[arraySize] = item;
        } else {
            stringArray = Arrays.copyOf(stringArray, arraySize + 10);
            stringArray[arraySize] = item;
        }
        arraySize++;
        //System.out.println(item + " " + arraySize + " " + stringArray.length);
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
                stringArray[i] = stringArray[i - 1];
            }
            stringArray[index] = item;
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
        stringArray[index] = item;
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
        String result = stringArray[index];
        for (int i = index; i < arraySize; i++) {
            if (stringArray[i + 1] == null) {
                stringArray[i] = null;
                break;
            }
            stringArray[i] = stringArray[i + 1];
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
            if (stringArray[i].equals(item)) {
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
            if (stringArray[i].equals(item)) {
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
            if (stringArray[i].equals(item)) {
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
        String result = stringArray[index];
        return result;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(arraySize);
        result = 31 * result + Arrays.hashCode(stringArray);
        return result;
    }

    @Override
    public boolean equals(StringList otherList) {
        if (this == otherList) return true;
        if (otherList == null || getClass() != otherList.getClass()) return false;
        StringListImpl that = (StringListImpl) otherList;
        return arraySize == that.arraySize && Arrays.equals(stringArray, that.stringArray);
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
        stringArray = new String[10];
    }

    @Override
    public String[] toArray() {
        String[] result = stringArray;
        return result;
    }
}
