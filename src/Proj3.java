import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * @file: Proj3.java
 * @description: This program imports the random numbers csv and runs a variety of sorting algorithms on those values. It compares the performance of these sorting algorithms based on how long they take.
 * @author: Andrew Young
 * @date: November 11th 2024
 */

//@Class Proj3: This class stores all of the contents of the file
public class Proj3 {
    // Sorting Method declarations

    // Merge Sort, this part is the called portion to implement the sorting
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {

        if (left < right){
            // Find the middle index of the array
            int middle = left + (right - left) / 2;

            // Make recursive calls to sort smaller portions
            // Sort the first half of the array
            mergeSort(a, left, middle);
            // Sort the second half of the array
            mergeSort(a, middle + 1, right);

            // Now merge the divided halves
            merge(a, left, middle, right);
        }

    }
    // Merge, this function is a helper function for mergeSort and serves to perform the merges required in merge sort
    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {

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

    // Quick Sort, this function is the called portion in the file to begin the sorting
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
    // Partition, this is the helper function for quickSort that sorts the split parts of the array
    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        T pivot = a.get(right); // Choosing the last element as the pivot
        int i = left - 1; // Pointer for the smaller portion

        // Iterate from the left index all the way up until the right index
        for (int j = left; j < right; j++) {
            // If current element is less than the pivot element then perform the swap
            if (a.get(j).compareTo(pivot) < 0) {
                i++;
                // Now swap a[i] and a[j]
                T temp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, temp);
            }
        }

        // Swap the pivot element with the element at i + 1 to place it in the correct position at the end
        T temp = a.get(i + 1);
        a.set(i + 1, a.get(right));
        a.set(right, temp);

        return i + 1; // Return the index of the pivot
    }
    // Basic swap function to perform for sorting
    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort, the function that is called in the file to begin the sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Define the starting index for this iteration of the heapSort call
        int numNodes = right - left + 1;

        // Build the max heap by calling heapify, start at the middle then work backwards
        for (int i = left + (numNodes / 2) - 1; i >= left; i--) {
            heapify(a, i, right);
        }

        // Extract elements from the heap one by one through each iteration of the loop
        for (int i = right; i > left; i--) {
            // Move current root to end
            T temp = a.get(left);
            a.set(left, a.get(i));
            a.set(i, temp);

            // Call heapify on the reduced heap
            heapify(a, left, i - 1);
        }


    }
    // Helper function for heapify that arranges the array into a heap structure
    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Initialize the left argument as the root for this heap at first
        int largest = left;
        // Declare the child node definitions
        int leftChild = 2 * left + 1;
        int rightChild = 2 * left + 2;

        // Check if left child is within bounds and is greater than root
        if (leftChild <= right && a.get(leftChild).compareTo(a.get(largest)) > 0) {
            largest = leftChild;
        }

        // Check if right child is within bounds and is greater than the largest so far
        if (rightChild <= right && a.get(rightChild).compareTo(a.get(largest)) > 0) {
            largest = rightChild;
        }

        // If largest is not root, swap and continue heapifying
        if (largest != left) {
            T swap = a.get(left);
            a.set(left, a.get(largest)); // Perform the swapping of the nodes
            a.set(largest, swap);

            // Recursively heapify the affected subtree with the now swapped nodes
            heapify(a, largest, right);
        }

    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {

        int comparisonCounter = 0; // The number of total comparisons that take place

        T temp; // Set up a temporary value that can be used to perform the swapping
        boolean valuesSwapped;
        // Outer loop that determines how many times the inner loop of the swapping takes place
        for (int i = 0; i < size - 1; i++){
            // Set valuesSwapped value to false since this marks the start of a new pass
            valuesSwapped = false;
            for (int j = 0; j < size - i - 1; j++){
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
            }

            // If no swaps were performed then either best case or final pass
            if (valuesSwapped == false){
                break;
            }
        }


        return(comparisonCounter);

    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Declare the numComparisons variable to be used in the final output
        int numComparisons = 0;
        boolean sorted = false;

        // Continue until the array is sorted using the sorted variable
        while (!sorted) {
            // Serves here for last redundant call, if a swap has to made in either the odd or even phase this will be set back to false so the loop continues
            sorted = true;

            // Odd phase call, increment by 2 to stay on the odd indices only
            for (int i = 1; i < size - 1; i += 2) {
                numComparisons++;
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    // Swap the adjacent elements to get the correct order
                    T temp = a.get(i);
                    a.set(i, a.get(i + 1));
                    a.set(i + 1, temp);
                    sorted = false; // Set sorted back to false since a swap had to be made
                }
            }

            // Even phase call, increment by 2 to stay on the even indices only
            for (int i = 0; i < size - 1; i += 2) {
                numComparisons++;
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    // Swap the adjacent elements to get the correct order
                    T temp = a.get(i);
                    a.set(i, a.get(i + 1));
                    a.set(i + 1, temp);
                    sorted = false; // Set sorted back to false since a swap had to be made
                }
            }
        }

        return numComparisons; // Return the total number of comparisons
    }

    public static void main(String [] args)  throws IOException {

        // First need to read in the data set that will be sorted
        String fileName = args[0];
        String sortMethod = args[1];
        int numLines = Integer.parseInt(args[2]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(fileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line, since it is just a header
        inputFileNameScanner.nextLine();

        // Create the array variable that will store the contents of the file
        // TODO: Ask Dr. Cho if it is ok that you declare a specific data type here rather than a generic type
        String currLine; // Variable to store the most recent line read from the file
        String[] parts;

        // Now declare all the array options
        ArrayList<BaseballBatter> sortedArray = new ArrayList<>();
        ArrayList<BaseballBatter> shuffledArray = new ArrayList<>();
        ArrayList<BaseballBatter> reverseSortArray = new ArrayList<>();

        // Declare all the player variables outside here
        String playerID;
        String yearID;
        int stint;
        String teamID;
        String lgID;
        int G;
        int AB;
        int R;
        int H;
        int Doubles;
        int Triples;
        int HR;
        int RBI;
        int SB;
        int CS;
        int BB;
        int SO;
        int IBB;
        int HBP;
        int SH;
        int SF;
        int GIDP;
        String nameGiven;
        String masterID;
        // For loop to iterate through the csv and all the BaseballBatter player classes into an ArrayList
        for (int i = 0; i < numLines; i++){
            //Read in the current line for a player in the csv
            currLine = inputFileNameScanner.nextLine();
            // Now split the values based on the commas to get all the variables
            parts = currLine.split(",");

            playerID = parts[0];
            yearID = parts[1];
            stint = Integer.parseInt(parts[2]);
            teamID = parts[3];
            lgID = parts[4];
            G = Integer.parseInt(parts[5]);
            AB = Integer.parseInt(parts[6]);
            R = Integer.parseInt(parts[7]);
            H = Integer.parseInt(parts[8]);
            Doubles = Integer.parseInt(parts[9]);
            Triples = Integer.parseInt(parts[10]);
            HR = Integer.parseInt(parts[11]);
            RBI = Integer.parseInt(parts[12]);
            SB = Integer.parseInt(parts[13]);
            CS = Integer.parseInt(parts[14]);
            BB = Integer.parseInt(parts[15]);
            SO = Integer.parseInt(parts[16]);
            IBB = Integer.parseInt(parts[17]);
            HBP = Integer.parseInt(parts[18]);
            SH = Integer.parseInt(parts[19]);
            SF = Integer.parseInt(parts[20]);
            GIDP = Integer.parseInt(parts[21]);
            nameGiven = parts[22];
            masterID = parts[23];
            // Now add the player to the array list
            sortedArray.add(new BaseballBatter(playerID, yearID, stint, teamID, lgID, G, AB, R, H, Doubles,
                    Triples, HR, RBI, SB, CS, BB, SO, IBB, HBP, SH, SF, GIDP, nameGiven, masterID));
            shuffledArray.add(new BaseballBatter(playerID, yearID, stint, teamID, lgID, G, AB, R, H, Doubles,
                    Triples, HR, RBI, SB, CS, BB, SO, IBB, HBP, SH, SF, GIDP, nameGiven, masterID));
            reverseSortArray.add(new BaseballBatter(playerID, yearID, stint, teamID, lgID, G, AB, R, H, Doubles,
                    Triples, HR, RBI, SB, CS, BB, SO, IBB, HBP, SH, SF, GIDP, nameGiven, masterID));


        }

        // Now perform the respective ordering for these arrays
        Collections.sort(sortedArray);
        Collections.shuffle(shuffledArray);
        Collections.sort(reverseSortArray, Collections.reverseOrder());

        // Now make the sorted.txt file and write these arrays to it, this line directly below overwrites the previous file
        writeToFile("Sorted List, Shuffled Array, Reverse Sorted Array,\n", "./src/sorted.txt", false);
        // Now write the contents of the arrays such that is a column in a csv type format
        // Can pick any array as the size value since they are all the same size (or at least should be)
        for (int i = 0; i < sortedArray.size() - 1; i++){
            writeToFile(sortedArray.get(i) + "," + shuffledArray.get(i) + "," + reverseSortArray.get(i) + ",\n",
                    "./src/sorted.txt", true);
        }

        // Declare the time variables that will be used throughout these tests
        long startTime;
        long endTime;

        double elapsedMillisecondsSorted;
        double elapsedMillisecondsShuffled;
        double elapsedMillisecondsReverse;

        int comparisonsSorted; // Needed for the amount of comparisons made in the bubble sort and odd even transposition sort algorithms
        int comparisonsShuffled;
        int comparisonsReverse;

        // Now the portion where the selected sort method will be conditionally run
        if (sortMethod.equals("bubble")){
            // Find run time for the sorted array
            startTime = System.nanoTime();
            comparisonsSorted = bubbleSort(sortedArray, numLines);
            endTime = System.nanoTime();
            // Find the total time for sorted

            elapsedMillisecondsSorted = (endTime - startTime) / 1_000_000.0;

            // Find run time for the shuffled array
            startTime = System.nanoTime();
            comparisonsShuffled = bubbleSort(shuffledArray, numLines);
            endTime = System.nanoTime();
            // Find the total time for shuffled
            elapsedMillisecondsShuffled = (endTime - startTime) / 1_000_000.0;

            // Find run time for the reverse array
            startTime = System.nanoTime();
            comparisonsReverse = bubbleSort(reverseSortArray, numLines);
            endTime = System.nanoTime();
            // Find the total time for reverse
            elapsedMillisecondsReverse = (endTime - startTime) / 1_000_000.0;

            // Is it even sorting properly ?
            /*
            for (int i = 0; i < sortedArray.size(); i++){
                System.out.println(shuffledArray.get(i));
            }
             */

            // Now perform the nice to look at print out
            System.out.println("===========================================================================================");
            System.out.println("==============================Number of Lines Evaluated: " + numLines + "==============================");
            System.out.println("===========================================================================================");
            // Output the sorted array results
            System.out.println("----------------------------Sorted Number of Comparisons: " + comparisonsSorted + "-----------------------------");
            System.out.println("--------------------------------Sorted Array Run Time: " + elapsedMillisecondsSorted + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the shuffled array results
            System.out.println("----------------------------Shuffled Number of Comparisons: " + comparisonsShuffled + "-----------------------------");
            System.out.println("--------------------------------Shuffled Array Run Time: " + elapsedMillisecondsShuffled + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the reverse array results
            System.out.println("----------------------------Reverse Number of Comparisons: " + comparisonsReverse + "-----------------------------");
            System.out.println("--------------------------------Reverse Array Run Time: " + elapsedMillisecondsReverse + "-------------------------------");
            System.out.println("===========================================================================================");

            // Now write these results to the analysis.txt file in a csv format
            writeToFile("Bubble, " + numLines + ", " + elapsedMillisecondsSorted + ", " + comparisonsSorted + "," +
                    elapsedMillisecondsShuffled + ", " + comparisonsShuffled + ", " + elapsedMillisecondsReverse + ", " +
                    comparisonsReverse + ",\n", "./src/analysis.txt", true);
        }

        else if (sortMethod.equals("merge")){

            // Find run time for the sorted array
            startTime = System.nanoTime();
            mergeSort(sortedArray, 0,  sortedArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for sorted

            elapsedMillisecondsSorted = (endTime - startTime) / 1_000_000.0;

            // Find run time for the shuffled array
            startTime = System.nanoTime();
            mergeSort(shuffledArray, 0,  shuffledArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for shuffled
            elapsedMillisecondsShuffled = (endTime - startTime) / 1_000_000.0;

            // Find run time for the reverse array
            startTime = System.nanoTime();
            mergeSort(reverseSortArray, 0,  reverseSortArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for reverse
            elapsedMillisecondsReverse = (endTime - startTime) / 1_000_000.0;

            // Is it even sorting properly ?
            /*
            for (int i = 0; i < sortedArray.size(); i++){
                System.out.println(shuffledArray.get(i));
            }
             */

            // Now perform the nice to look at print out
            System.out.println("===========================================================================================");
            System.out.println("==============================Number of Lines Evaluated: " + numLines + "==============================");
            System.out.println("===========================================================================================");
            // Output the sorted array results
            System.out.println("--------------------------------Sorted Array Run Time: " + elapsedMillisecondsSorted + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the shuffled array results
            System.out.println("--------------------------------Shuffled Array Run Time: " + elapsedMillisecondsShuffled + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the reverse array results
            System.out.println("--------------------------------Reverse Array Run Time: " + elapsedMillisecondsReverse + "-------------------------------");
            System.out.println("===========================================================================================");

            // Now write these results to the analysis.txt file in a csv format
            writeToFile("Merge, " + numLines + ", " + elapsedMillisecondsSorted + ", " + " " + "," +
                    elapsedMillisecondsShuffled + ", " + " " + ", " + elapsedMillisecondsReverse + ", " +
                    " " + ",\n", "./src/analysis.txt", true);

        }

        else if(sortMethod.equals("quick")){

            // Find run time for the sorted array
            startTime = System.nanoTime();
            quickSort(sortedArray, 0, sortedArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for sorted

            elapsedMillisecondsSorted = (endTime - startTime) / 1_000_000.0;

            // Find run time for the shuffled array
            startTime = System.nanoTime();
            quickSort(shuffledArray, 0, shuffledArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for shuffled
            elapsedMillisecondsShuffled = (endTime - startTime) / 1_000_000.0;

            // Find run time for the reverse array
            startTime = System.nanoTime();
            quickSort(reverseSortArray, 0, reverseSortArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for reverse
            elapsedMillisecondsReverse = (endTime - startTime) / 1_000_000.0;

            // Is it even sorting properly ?
            /*
            for (int i = 0; i < sortedArray.size(); i++){
                System.out.println(shuffledArray.get(i));
            }
             */

            // Now perform the nice to look at print out
            System.out.println("===========================================================================================");
            System.out.println("==============================Number of Lines Evaluated: " + numLines + "==============================");
            System.out.println("===========================================================================================");
            // Output the sorted array results
            System.out.println("--------------------------------Sorted Array Run Time: " + elapsedMillisecondsSorted + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the shuffled array results
            System.out.println("--------------------------------Shuffled Array Run Time: " + elapsedMillisecondsShuffled + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the reverse array results
            System.out.println("--------------------------------Reverse Array Run Time: " + elapsedMillisecondsReverse + "-------------------------------");
            System.out.println("===========================================================================================");

            // Now write these results to the analysis.txt file in a csv format
            writeToFile("Quick, " + numLines + ", " + elapsedMillisecondsSorted + ", " + " " + "," +
                    elapsedMillisecondsShuffled + ", " + " " + ", " + elapsedMillisecondsReverse + ", " +
                    " " + ",\n", "./src/analysis.txt", true);

        }

        else if (sortMethod.equals("heap")){

            // Find run time for the sorted array
            startTime = System.nanoTime();
            heapSort(sortedArray, 0, sortedArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for sorted

            elapsedMillisecondsSorted = (endTime - startTime) / 1_000_000.0;

            // Find run time for the shuffled array
            startTime = System.nanoTime();
            heapSort(shuffledArray, 0, shuffledArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for shuffled
            elapsedMillisecondsShuffled = (endTime - startTime) / 1_000_000.0;

            // Find run time for the reverse array
            startTime = System.nanoTime();
            heapSort(reverseSortArray, 0, reverseSortArray.size() - 1);
            endTime = System.nanoTime();
            // Find the total time for reverse
            elapsedMillisecondsReverse = (endTime - startTime) / 1_000_000.0;

            // Is it even sorting properly ?
            /*
            for (int i = 0; i < sortedArray.size(); i++){
                System.out.println(shuffledArray.get(i));
            }
             */

            // Now perform the nice to look at print out
            System.out.println("===========================================================================================");
            System.out.println("==============================Number of Lines Evaluated: " + numLines + "==============================");
            System.out.println("===========================================================================================");
            // Output the sorted array results
            System.out.println("--------------------------------Sorted Array Run Time: " + elapsedMillisecondsSorted + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the shuffled array results
            System.out.println("--------------------------------Shuffled Array Run Time: " + elapsedMillisecondsShuffled + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the reverse array results
            System.out.println("--------------------------------Reverse Array Run Time: " + elapsedMillisecondsReverse + "-------------------------------");
            System.out.println("===========================================================================================");

            // Now write these results to the analysis.txt file in a csv format
            writeToFile("Heap, " + numLines + ", " + elapsedMillisecondsSorted + ", " + " " + "," +
                    elapsedMillisecondsShuffled + ", " + " " + ", " + elapsedMillisecondsReverse + ", " +
                    " " + ",\n", "./src/analysis.txt", true);

        }

        else if (sortMethod.equals("transposition")){

            // Find run time for the sorted array
            startTime = System.nanoTime();
            comparisonsSorted = transpositionSort(sortedArray, numLines);
            endTime = System.nanoTime();
            // Find the total time for sorted

            elapsedMillisecondsSorted = (endTime - startTime) / 1_000_000.0;

            // Find run time for the shuffled array
            startTime = System.nanoTime();
            comparisonsShuffled = transpositionSort(shuffledArray, numLines);
            endTime = System.nanoTime();
            // Find the total time for shuffled
            elapsedMillisecondsShuffled = (endTime - startTime) / 1_000_000.0;

            // Find run time for the reverse array
            startTime = System.nanoTime();
            comparisonsReverse = transpositionSort(reverseSortArray, numLines);
            endTime = System.nanoTime();
            // Find the total time for reverse
            elapsedMillisecondsReverse = (endTime - startTime) / 1_000_000.0;

            // Is it even sorting properly ?
            /*
            for (int i = 0; i < sortedArray.size(); i++){
                System.out.println(shuffledArray.get(i));
            }
             */

            // Now perform the nice to look at print out
            System.out.println("===========================================================================================");
            System.out.println("==============================Number of Lines Evaluated: " + numLines + "==============================");
            System.out.println("===========================================================================================");
            // Output the sorted array results
            System.out.println("----------------------------Sorted Number of Comparisons: " + comparisonsSorted + "-----------------------------");
            System.out.println("--------------------------------Sorted Array Run Time: " + elapsedMillisecondsSorted + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the shuffled array results
            System.out.println("----------------------------Shuffled Number of Comparisons: " + comparisonsShuffled + "-----------------------------");
            System.out.println("--------------------------------Shuffled Array Run Time: " + elapsedMillisecondsShuffled + "-------------------------------");
            System.out.println("===========================================================================================");
            //Output the reverse array results
            System.out.println("----------------------------Reverse Number of Comparisons: " + comparisonsReverse + "-----------------------------");
            System.out.println("--------------------------------Reverse Array Run Time: " + elapsedMillisecondsReverse + "-------------------------------");
            System.out.println("===========================================================================================");

            // Now write these results to the analysis.txt file in a csv format
            writeToFile("Transposition, " + numLines + ", " + elapsedMillisecondsSorted + ", " + comparisonsSorted + "," +
                    elapsedMillisecondsShuffled + ", " + comparisonsShuffled + ", " + elapsedMillisecondsReverse + ", " +
                    comparisonsReverse + ",\n", "./src/analysis.txt", true);

        }

    }

    // Generate or append to the specified file
    public static void writeToFile(String content, String filePath, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))){
            writer.write(content);
            //writer.newLine();
        }
        catch (IOException e){ // Error handling
            e.printStackTrace();
        }
    }
}


