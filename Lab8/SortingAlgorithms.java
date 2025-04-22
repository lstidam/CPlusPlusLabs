/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sortingalgorithms;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author lilli
 */

public class SortingAlgorithms {

    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
        String outputFile = "sorting_results.csv";

        try (FileWriter writer = new FileWriter(outputFile)) {
            // Write header
            writer.write("Data Size,Selection Sort (ms),Insertion Sort (ms),Merge Sort (ms)\n");

            for (int size : sizes) {
                int[] array = generateArray(size);

                // Measure Selection Sort
                int[] selectionArray = Arrays.copyOf(array, array.length);
                long selectionStart = System.nanoTime();
                selectionSort(selectionArray);
                long selectionEnd = System.nanoTime();

                // Measure Insertion Sort
                int[] insertionArray = Arrays.copyOf(array, array.length);
                long insertionStart = System.nanoTime();
                insertionSort(insertionArray);
                long insertionEnd = System.nanoTime();

                // Measure Merge Sort
                int[] mergeArray = Arrays.copyOf(array, array.length);
                long mergeStart = System.nanoTime();
                mergeSort(mergeArray);
                long mergeEnd = System.nanoTime();

                // Write results to CSV
                writer.write(String.format("%d,%d,%d,%d\n",
                        size,
                        (selectionEnd - selectionStart) / 1_000_000,
                        (insertionEnd - insertionStart) / 1_000_000,
                        (mergeEnd - mergeStart) / 1_000_000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 1001);
        }
        return array;
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }
            int temp = array[smallest];
            array[smallest] = array[i];
            array[i] = temp;
        }
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insert = array[i];
            int moveItem = i;
            while (moveItem > 0 && array[moveItem - 1] > insert) {
                array[moveItem] = array[moveItem - 1];
                moveItem--;
            }
            array[moveItem] = insert;
        }
    }

    private static void mergeSort(int[] array) {
        sortArray(array, 0, array.length - 1);
    }

    private static void sortArray(int[] array, int low, int high) {
        if (high - low >= 1) {
            int middle = (low + high) / 2;
            sortArray(array, low, middle);
            sortArray(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private static void merge(int[] array, int low, int middle, int high) {
        int[] temp = new int[high - low + 1];
        int i = low, j = middle + 1, k = 0;

        while (i <= middle && j <= high) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }
        while (j <= high) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, 0, array, low, temp.length);
    }
}
