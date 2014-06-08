package week5;

import java.util.ArrayList;

/**
 * Created by GongLi on 8/6/14.
 */
public class graphNode {

    int id;
    int curDistance;

    ArrayList<graphEdge> edges = new ArrayList<graphEdge>();


    public graphNode(int id){
        this.id = id;
        curDistance = Integer.MAX_VALUE;
    }

}
