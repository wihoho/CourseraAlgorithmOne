package week5;

/**
 * Created by GongLi on 8/6/14.
 */
public class graphEdge {

    graphNode v;
    graphNode w;
    int distance;

    public graphEdge(graphNode v, graphNode w, int distance){
        this.v = v;
        this.w = w;
        this.distance = distance;
    }

    public graphNode otherNode(graphNode cur){
        if(cur == v) return w;
        else return v;
    }
}
