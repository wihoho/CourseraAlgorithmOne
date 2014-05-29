package week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by GongLi at 16:35 on 29/5/14
 */


public class Node {

    int id;
    List<Node> out;

    public Node(int id) {
        this.id = id;
        this.out = new ArrayList<Node>();
    }
}
