package week3;

/**
 * Authored by GongLi at 14:16 on 29/5/14
 */


public class UnionFind {

    int[] id;
    int size;

    public UnionFind(int size){
        id = new int[size];
        for(int i = 0; i < size; i ++){
            id[i] = i;
        }
        this.size = size;
    }

    public int root(int v){
        while(v != id[v]){
            id[v] = id[id[v]];
            v = id[v];
        }

        return v;
    }

    public void connect(int v, int w){
        int vId = root(v);
        int wId = root(w);

        if(vId != wId){
            id[vId] = wId;
            size --;
        }
    }

    public boolean connected(int w, int v){
        return root(w) == root(v);
    }
}
