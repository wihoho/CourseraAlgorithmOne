package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Authored by GongLi at 19:12 on 27/5/14
 */


public class quickSortComparision {

    public int count = 0;

    public static void main(String[] args) {

//        runTest("10.txt", 0);
//        runTest("10.txt", 1);
//        runTest("10.txt", 2);
//
//        runTest("100.txt", 0);
//        runTest("100.txt", 1);
//        runTest("100.txt", 2);
//
//
//        runTest("1000.txt", 0);
//        runTest("1000.txt", 1);
//        runTest("1000.txt", 2);

        runTest("QuickSort.txt", 0);
        runTest("QuickSort.txt", 1);
        runTest("QuickSort.txt", 2);

    }

    // type
    // 0 - low
    // 1 - high
    // 2 = median
    public static void runTest(String fileName, int type){

        File f = new File(fileName);
        try {
            Scanner s = new Scanner(f);
            ArrayList<Integer> arrList = new ArrayList<Integer>();
            while(s.hasNext()){
                int temp = s.nextInt();
                arrList.add(temp);
            }

            int[] arr = new int[arrList.size()];
            for(int i = 0; i < arr.length; i ++)
                arr[i] = arrList.get(i);

            quickSortComparision qc = new quickSortComparision();
            qc.quickSort(arr, 0, arr.length-1, type);
            System.out.println(qc.count);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void quickSort(int[] arr, int low, int high, int type){
        if(low < high){
            int pos = partition(arr, low, high, type);
            count += high - low;

            quickSort(arr, low, pos-1, type);
            quickSort(arr, pos+1, high, type);

        }
    }

    public static int  partition(int[] arr, int low, int high, int type){





        if(type == 1)

            swap(arr, low, high);

        else if(type == 2) {
            int indexChosen = findMedian(arr, low, high);
            swap(arr, low, indexChosen);
        }

        int pivot = arr[low];

        int i = low + 1;
        for(int j = low + 1; j <= high; j++){
            if(arr[j] < pivot){
                swap(arr, j, i);
                i ++;
            }
        }

        swap(arr, low, i - 1);
        return i - 1;



    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int arr[]){

        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static int findMedian(int[] arr, int low, int high){
        int middle = (low + high) / 2;

        Node[] nodeArr = {new Node(low, arr[low]), new Node(middle, arr[middle]), new Node(high, arr[high])};
        Arrays.sort(nodeArr);
        return nodeArr[1].index;


    }



}
