import java.io.IOException;
import java.util.ArrayList;

public class Proj3 {
    // Sorting Method declarations

    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {

        if (left < right){
            // Find the middle index of the array
            int middle = left + (right - 1) / 2;

            // Make recursive calls to sort smaller portions
            // Sort the first half of the array
            mergeSort(a, left, middle);
            // Sort the second half of the array
            mergeSort(a, middle + 1, right);

            // Now merge the divided halves
            merge(a, left, middle, right);
        }
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        // Find the size of the two arrays that are going to be sorted
        int lengthOne = mid - left + 1;
        int lengthTwo = right - mid;

        // Create temporary arrays to store the left and right portions into their own distinct arrays
        ArrayList<T> leftArray = new ArrayList<T>();
        ArrayList<T> rightArray = new ArrayList<T>();

        // Now populate those arrays with the values needed
        for (int i = 0; i < lengthOne; i++){
            leftArray.add(a.get(left + i));
        }
        for (int j = 0; j < lengthTwo; j++){
            rightArray.add(a.get(mid + j + 1));
        }

        // Now we need to merge the two arrays together, first set the array indices back to zero
        int i = 0, j = 0;

        // Now perform the merge and comparison for this array
        int mergeIndex = left;

        while (i < lengthOne && j < lengthTwo){
            // Check if the left array value is smaller than the right array value
            if (leftArray.get(i).compareTo(rightArray.get(j)) <= 0){
                a.set(mergeIndex, leftArray.get(i)); // Insert the value from left array
                i++; // Now increment to the next index of the leftArray
            }
            // If not use the right array value instead
            else{
                a.set(mergeIndex, rightArray.get(j)); // Insert the value from right array
                j++; // Now increment to the next index of the rightArray
            }
            mergeIndex++; // Now move onto next part of the list
        }

        // Bring over remaining values from the left array if needed
        while(i < lengthOne){
            a.set(mergeIndex, leftArray.get(i));
            i++; // Increment the position of both lists
            mergeIndex++;

        }
        // Bring over the remaining values from the right array if needed
        while(j < lengthTwo){
            a.set(mergeIndex, rightArray.get(j));
            j++; // Increment the position of both lists
            mergeIndex++;
        }

    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left < right){
            // Obtain the pivot index here
            int pivotIndex = partition(a, left, right);

            // Recursively apply quick sort to the sub arrays
            quickSort(a, left, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, right);
        }
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {

        int comparisonCounter = 0; // The number of total comparisons that take place

        T temp; // Set up a temporary value that can be used to perform the swapping
        boolean valuesSwapped;
        // Outer loop that determines how many times the inner loop of the swapping takes place
        for (int i = 0; i < size; i++){
            // Set valuesSwapped value to false since this marks the start of a new pass
            valuesSwapped = false;
            for (int j = 0; j < size - i; i++){
                // Check if adjacent values are sorted out of order
                // Since this is a comparison we increment the comparisonCounter variable
                comparisonCounter++;
                if (a.get(j).compareTo(a.get(j+1)) > 0){
                    temp = a.get(j);
                    // Perform the swap
                    a.set(j, a.get(j+1));
                    a.set(j+1, temp);
                    valuesSwapped = true; // Indicate the swap has been performed

                }

                // If no swaps were performed then either best case or final pass
                if (valuesSwapped == false){
                    break;
                }
            }
        }

        return(comparisonCounter);

    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
    }

    public static void main(String [] args)  throws IOException {
        //...
        // Finish Me
        //...
    }
}
