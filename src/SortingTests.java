import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortingTests {

    static IntegerListImpl integerList;
    static Random random;

    @BeforeAll
    static void setUp() {
        integerList = new IntegerListImpl(10);
        random = new Random();
    }

    private static void swap(Integer[] arr, int a, int b, int tempVar) {
        tempVar = arr[b];  //создание в цикле на миллиард повторений миллиард новых переменных по одной на итерацию, может и не сильно замедлит процесс, но быстро загадит память
        arr[b] = arr[a];
        arr[a] = tempVar;
    }

    void bubbleSorting(Integer[] arr) {
        Integer[] bubbleSort = arr;
        int temp = 0;
        for (int i = 0; i < bubbleSort.length; i++) {
            if (bubbleSort[i] == null) {
                break;
            }
            for (int j = i + 1; j < bubbleSort.length; j++) {
                if (bubbleSort[j] == null) {
                    continue;
                }
                if (bubbleSort[i] > bubbleSort[j]) {
                    swap(bubbleSort, i, j, temp);
                }
            }
        }
    }

    void sortSelection(Integer[] arr) {
        int min = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == null) {
                    continue;
                }
                if (arr[i] > arr[j]) {
                    min = j;
                    swap(arr, j, i, temp);
                }
            }
        }
    }

    void sortInsertion(Integer[] arr) {
        Integer temp;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            int j = i;
            if (arr[j] == null) {
                break;
            }
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    @Test
    void compareSorts() {
        for (int i = 0; i < 100000; i++) {
            integerList.add(random.nextInt(Integer.MAX_VALUE));
        }
        Integer[] bubbleSort = Arrays.copyOf(integerList.toArray(), integerList.toArray().length);
        Integer[] sortSelection = Arrays.copyOf(integerList.toArray(), integerList.toArray().length);
        Integer[] insertionSort = Arrays.copyOf(integerList.toArray(), integerList.toArray().length);


        //Bubble time
        long startBS = System.currentTimeMillis();
        long spentBS;

        bubbleSorting(bubbleSort);

        spentBS = System.currentTimeMillis() - startBS;
        System.out.println("Bubblesort: " + spentBS);

        //Choice time
        long startSS = System.currentTimeMillis();
        long spentSS;

        sortSelection(sortSelection);

        spentSS = System.currentTimeMillis() - startSS;
        System.out.println("SortSelection: " + spentSS);

        //Insertion time
        long startIS = System.currentTimeMillis();
        long spentIS;

        sortInsertion(insertionSort);

        spentIS = System.currentTimeMillis() - startIS;
        System.out.println("InsertionSelection: " + spentIS);

        //Timsort time
        long startTS = System.currentTimeMillis();
        long spentTS;

        integerList.sortWithTimSort();

        spentTS = System.currentTimeMillis() - startTS;
        System.out.println("Timsort: " + spentTS);

    }
}