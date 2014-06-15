package week6;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by GongLi on 15/6/14.
 */
public class Median {

    MaxHeap maxHeap;
    MinHeap minHeap;

    public Median(int totalSize){
        maxHeap = new MaxHeap(totalSize);
        minHeap = new MinHeap(totalSize);
    }

    public void insert(int x) throws Exception {

        if (maxHeap.size == 0){
            maxHeap.insert(x);
            return;
        }

        if(maxHeap.size == minHeap.size){
            if(x <= minHeap.getMin())
                maxHeap.insert(x);
            else{
                maxHeap.insert(minHeap.deleteMin());
                minHeap.insert(x);
            }
        }

        else{

            if(x < maxHeap.getMax()){
                minHeap.insert(maxHeap.deleteMax());
                maxHeap.insert(x);
            }
            else
                minHeap.insert(x);
        }
    }

    public int getMedian() throws Exception {
        if(maxHeap.size == 0) throw new Exception("No item received yet");
        return maxHeap.getMax();
    }

    public static void test(String fileName) throws Exception {
        File f = new File(fileName);
        Scanner s = new Scanner(f);

        ArrayList<Integer> lst = new ArrayList<Integer>();
        while(s.hasNext()){

            lst.add(s.nextInt());
        }

        Median m = new Median(lst.size());

        int i = 1;
        int sum = 0;
        for(Integer a: lst){
            m.insert(a);
            System.out.println(i + ": " + m.getMedian());
            sum += m.getMedian();
            i++;
        }

        System.out.println("Final Result: " + sum % 10000);
    }

    public static void main(String[] args) throws Exception {

        test("Median.txt");
    }
}
