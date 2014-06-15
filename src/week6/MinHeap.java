package week6;

/**
 * Created by GongLi on 15/6/14.
 */
public class MinHeap {

    int totalArraySize;
    int[] arr;
    int size;


    public MinHeap(int totalSize){
        totalArraySize = totalSize + 1;
        arr = new int[totalArraySize];
        size = 0;
    }

    public void insert(int x) throws Exception {
        if(size == totalArraySize - 1) throw new Exception("Heap is full");
        arr[++size] = x;
        swim(size);
    }

    public int deleteMin() throws Exception {
        if(size == 0) throw new Exception("Size is 0!");

        int ret = arr[1];
        swap(1, size);
        size --;
        sink(1);


        return ret;
    }

    public int getMin(){
        return arr[1];
    }

    private void sink(int k){

        while (2 * k <= size){
            int minIndex = k;
            if(arr[2*k] < arr[k]) minIndex = 2 * k;
            if(2 * k + 1 <= size && arr[2*k+1] < arr[minIndex]) minIndex = 2*k + 1;

            if(minIndex == k) return;

            swap(minIndex, k);
            k = minIndex;
        }
    }

    private void swim(int k){

        while(k >= 2){

            if(arr[k / 2] > arr[k]){
                swap(k, k /2);
                k = k /2;
            }
            else break;
        }
    }

    public void swap(int a, int b){
        int tem= arr[a];
        arr[a] = arr[b];
        arr[b] = tem;
    }
}
