package week6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by GongLi on 15/6/14.
 */
public class TwoSum {

    public static void solve(String fileName) throws FileNotFoundException {

        File f = new File(fileName);
        Scanner s = new Scanner(f);

        HashSet<Double> set = new HashSet<Double>();
        ArrayList<Double> lst = new ArrayList<Double>();
        while(s.hasNext()){
            Double temp = s.nextDouble();

            if(set.contains(temp))
                continue;

            set.add(temp);
            lst.add(temp);
        }

        Collections.sort(lst);
        System.out.println("sorted");

        set = new HashSet<Double>();
        for(int i = 0; i < lst.size() - 1; i ++){
            double tHigh = 10000.0 - lst.get(i);
            double tLow = -10000.0 - lst.get(i);

            int index = binarySearch(lst, tHigh);

            for(int j = index; j >= 0; j --){
                if(i ==j) continue;

                if(lst.get(j) < tLow) break;

                double curValue = lst.get(j) + lst.get(i);
                set.add(curValue);
            }

        }

        System.out.println(set.size());

    }

    public static int binarySearch(ArrayList<Double> arr, double target){
        int low = 0;
        int high = arr.size() - 1;

        if(target >= arr.get(high))
            return high;

        if(target <= arr.get(low))
            return low;

        while (low <= high){
            int mid = (low + high) / 2;
            double compare = arr.get(mid) - target;
            if(Math.abs(compare) < 0.01) return mid;
            else if(compare < 0) low = mid;
            else high = mid;

            if(high - low == 1) break;
        }

        return low;
    }

    public static void main(String[] args) throws FileNotFoundException {

        solve("algo1-programming_prob-2sum.txt");

    }
}
