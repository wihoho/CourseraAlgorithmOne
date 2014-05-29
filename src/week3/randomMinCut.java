package week3;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Authored by GongLi at 14:14 on 29/5/14
 */


public class randomMinCut {

    ArrayList<ArrayList<Integer>> graph;
    UnionFind uf;


    public static void main(String[] args) {
        randomMinCut mc = new randomMinCut("kargerMinCut.txt");
    }

    public randomMinCut(String fileName){
        graph = readGraph(fileName);
        uf = new UnionFind(graph.size());
        int minValue = random();

        for(int i = 0; i < graph.size() * graph.size(); i ++){
            uf = new UnionFind(graph.size());
            int testValue = random();
            System.out.println(i +": "+ testValue);
            if(minValue > testValue)
                minValue = testValue;
        }

        System.out.println(minValue);
    }

    public int random(){
        while (uf.size != 2){
            randomTry();
        }

        // count the crossing edges
        int crossingEdges = 0;
        for(int i = 0; i < graph.size(); i ++){
            ArrayList<Integer> neighbours = graph.get(i);
            for(int j : neighbours){
                if(! uf.connected(i,j))
                    crossingEdges += 1;
            }

        }

        return crossingEdges / 2;
    }


    public void randomTry(){
        int size = graph.size();

        Random rd = new Random();
        int v = rd.nextInt(size);
        int w = graph.get(v).get(rd.nextInt(graph.get(v).size()));

        while (uf.connected(v,w)){
            v = rd.nextInt(size);
            w = graph.get(v).get(rd.nextInt(graph.get(v).size()));
        }

        uf.connect(v,w);
    }

    public ArrayList<ArrayList<Integer>> readGraph(String fileName){
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        File f = new File(fileName);
        try{
            Scanner s = new Scanner(f);
            while(s.hasNext()){
                String line = s.nextLine();
                String[] r = line.split("\t");
                ArrayList<Integer> neighbours = new ArrayList<Integer>();
                for(int i = 1; i < r.length; i ++)
                    neighbours.add(Integer.valueOf(r[i]) - 1);
                ret.add(neighbours);
            }
        }
        catch(Exception e){

        }

        return ret;
    }
}
