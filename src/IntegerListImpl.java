import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private int initialSize;
    private int arraySize;
    private Integer[] integerArray;
    private boolean sorted;

    public IntegerListImpl(int initialSize) {
        this.initialSize = initialSize;
        integerArray = new Integer[initialSize];
        sorted = false;
        arraySize = 0;
    }

    private void grow() {
        int newLength = (int) (integerArray.length * 1.5);
        if ((integerArray.length - arraySize) <= 1) {
            integerArray = Arrays.copyOf(integerArray, newLength);
        }
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullItemException("You can't add null element!");
        }
        grow();
        integerArray[arraySize] = item;
        arraySize++;
        sorted = false;
        //System.out.println(item + " " + arraySize + " " + integerArray.length);
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
                integerArray[i] = integerArray[i - 1];
            }
            integerArray[index] = item;
        }
        sorted = false;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        if (index > arraySize - 1 || index < 0) {
            throw new IllegalArgumentException("Index must be positive and less or equal to List size -1");
        }
        integerArray[index] = item;
        sorted = false;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
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
    public Integer remove(int index) {
        if (index > arraySize - 1 || index < 0) {
            throw new IllegalArgumentException("Index must be positive and less or equal to List size -1");
        }
        Integer result = integerArray[index];
        for (int i = index; i < arraySize; i++) {
            integerArray[i] = integerArray[i + 1];
        }
        arraySize--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        if (indexOf(item) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        sort();
        int min = 0;
        int max = arraySize - 1;
        int mid;
        while (min <= max) {
            mid = (min + max) / 2;
            if (integerArray[mid] == item) {
                return mid;
            }
            if (integerArray[mid] > item) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new NullItemException("Null parameter received for 'item'");
        }
        for (int i = arraySize - 1; i >= 0; i--) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index > arraySize - 1 || index < 0) {
            throw new IllegalArgumentException("Index must be positive and less or equal to List size -1");
        }
        Integer result = integerArray[index];
        return result;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(arraySize);
        result = 31 * result + Arrays.hashCode(integerArray);
        return result;
    }


    public boolean equals(IntegerList otherList) {
        if (this == otherList) return true;
        if (otherList == null || getClass() != otherList.getClass()) return false;
        IntegerListImpl that = (IntegerListImpl) otherList;
        return arraySize == that.arraySize && Arrays.equals(integerArray, that.integerArray);
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
        integerArray = new Integer[10];
        sorted = false;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = integerArray;
        return result;
    }

    @Override
    public void sortWithTimSort() {  // Неьбольшой чит, поскольку по факту самым быстрым из рассмотренных во ВСЕМ уроке был тимсорт, который в итоге лежит в основе Arrays.sort.
        // На написание комментария потрачено больше времени и сил, чем на написание метода. Этот труд точно того стоит
        Arrays.sort(integerArray, 0, arraySize);
        sorted = true;
    }

    @Override
    public void sort() {
        if (!sorted) {
            mergeSort();
            sorted = true;
        } else {
            return;
        }
    }

    private void mergeSort(Integer[] array) {
        if (array.length < 2) {
            return;
        }
        int pivot = array.length / 2;
        Integer[] left = new Integer[pivot];
        Integer[] right = new Integer[array.length - pivot];

        for (int i = 0; i < left.length; i++) {
            left[i] = array[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = array[pivot + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);

    }

    private void mergeSort() {
        Integer[] arrToSort = Arrays.copyOf(integerArray, arraySize);
        mergeSort(arrToSort);
        integerArray = Arrays.copyOf(arrToSort, integerArray.length);
    }

    private void merge(Integer[] array, Integer[] left, Integer[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;

        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                array[mainP] = left[leftP];
                mainP++;
                leftP++;  //в таком виде не так красиво, но более читабельно на мой взгляд
            } else {
                array[mainP] = right[rightP];
                mainP++;
                rightP++;
            }
        }

        while (leftP < left.length) {
            array[mainP] = left[leftP];
            mainP++;
            leftP++;
        }
        while (rightP < right.length) {
            array[mainP] = right[rightP];
            mainP++;
            rightP++;
        }
    }
}
