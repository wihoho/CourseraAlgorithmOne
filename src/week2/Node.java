package week2;

/**
 * Authored by GongLi at 20:13 on 27/5/14
 */


public class Node implements Comparable {

    int index;
    int value;

    @Override
    public int compareTo(Object o) {
        Node compare = (Node) o;
        if(value < compare.value)
            return -1;
        else if(value > compare.value)
            return 1;
        else
            return 0;
    }

    public Node(int index, int value){
        this.index = index;
        this.value = value;
    }
}
