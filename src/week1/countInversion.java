package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Authored by GongLi at 0:21 on 20/5/14
 */


public class countInversion {

    private int[] arr;

    public static void main(String[] args) {
        countInversion ci = new countInversion("IntegerArray.txt");
    }

    public countInversion(String fileName){

        //Read file and form the array

        ArrayList<Integer> list = new ArrayList<Integer>();
        File file = new File(fileName);
        try {
            Scanner s = new Scanner(file);
            while(s.hasNext()){

                int a = s.nextInt();
                list.add(a);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        if(list.size() != 0){

            arr = new int[list.size()];
            for(int i = 0; i < list.size(); i ++)
                arr[i] = list.get(i);

            double result = count(0, arr.length-1);
            System.out.println(result);



        }

    }

    public double count(int start, int end){

        if(start < end){
            int middle = (start + end) / 2;
            double l = count(start, middle);
            double r = count(middle + 1, end);
            double mer = merge(start, middle, end);


            double sum = l + r + mer;
            System.out.println(l +" "+ r+ " " + mer +" " + sum + " ");

            return l + r + mer;
        }

        return 0;

    }

    public double merge(int start, int middle, int end){

        double count = 0;

        int i = start;
        int j = middle + 1;

        int[] tempArray = new int[end - start + 1];

        int counter = 0;

        boolean flag = false;
        while(i <= middle && j <= end){
            if(arr[i] > arr[j]){
                tempArray[counter] = arr[j++];

                if(!flag)
                    flag = true;
            }
            else{

                if(flag){
                    flag = false;
                    count += j -1 - middle;
                }
                else{

                    // go back from j -1 to middle + 1
                    for(int special = j - 1; special >= middle + 1; special --){
                        if(arr[special] < arr[i]){
                            count += special - middle;
                            break;
                        }

                    }
                }

                tempArray[counter] = arr[i++];

            }

            counter ++;
        }

        if(i == middle + 1){

            for(; j <= end; j ++, counter ++)
                tempArray[counter] = arr[j];


        }
        else{


            for(; i <= middle; i ++, counter ++){
                tempArray[counter] = arr[i];
                count += end - middle;
            }


        }




        for(i = start; i <= end; i ++)
            arr[i] = tempArray[i - start];

        return count;

    }




}
