package sorting;
import java.util.Random;
import java.util.Arrays;

/**
 * Bubble Sort reference: https://www.geeksforgeeks.org/bubble-sort/
 * Quick Sort reference: https://www.geeksforgeeks.org/quick-sort/
 */
public class sorting{

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    
    // generates random data
    public static int[] generateData(int size) {
        Random rand = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(100000);
        }
        return data;
    }

    // measures execution time
    public static long timeSort(int[] data, String algorithm) {
        int[] arr = Arrays.copyOf(data, data.length); // Avoid modifying original
        long startTime = System.nanoTime();

        switch (algorithm) {
            case "bubble":
                bubbleSort(arr);
                break;
            case "quick":
                quickSort(arr, 0, arr.length - 1);
                break;
            default:
                System.out.println("Unknown algorithm.");
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Convert nanoseconds to milliseconds
    }

    public static void main(String[] args) {
        // generate data sets - small, medium, large
        int[] smallData = generateData(10);
        int[] mediumData = generateData(1000);
        int[] largeData = generateData(10000);

        //  test bubble sort
        String[] algorithms = {"bubble", "quick"};
        int[][] dataSets = {smallData, mediumData, largeData};
        String[] sizeNames = {"Small (10)", "Medium (1,000)", "Large (10,000)"};

        // runtests
        for (int i = 0; i < dataSets.length; i++) {
            System.out.println("**************************");
            System.out.println("Data Set: " + sizeNames[i]);
            for (String algo : algorithms) {
                System.out.printf("Running %s sort.....\n", algo);
                long elapsed = timeSort(dataSets[i], algo);
                System.out.printf("%s Sort: %d ms\n", algo, elapsed);
            }
            System.out.println();
        }
    }
}
