package week5;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by GongLi on 8/6/14.
 */
public class DijkstraDistance {


    HashMap<Integer, graphNode> nodes = new HashMap<Integer, graphNode>();

    public static void main(String[] args) throws IOException{
        DijkstraDistance dd = new DijkstraDistance("dijkstraData.txt");

    }

    public DijkstraDistance(String fileName) throws IOException{
        File f = new File(fileName);
        Scanner s = new Scanner(f);

        while(s.hasNext()){
            int curIndex = s.nextInt();
            graphNode curNode = getNode(curIndex);


            String curLine = s.nextLine();
            String[] separations = curLine.split("\t");
            for(String edge: separations){
                if (edge.equals("")) continue;

                String[] temp = edge.split(",");
                graphNode next = getNode(Integer.valueOf(temp[0]));
                int distance = Integer.valueOf(temp[1]);

                graphEdge tempEdge = new graphEdge(curNode, next, distance);
                curNode.edges.add(tempEdge);
                next.edges.add(tempEdge);
            }
        }

        System.out.println("Hello World");

        shortestPath(1);
        int[] destinations = {7,37,59,82,99,115,133,165,188,197};
        for(int des: destinations){
            System.out.print(nodes.get(des).curDistance+",");
        }
    }

    public graphNode getNode(int index){
        if(nodes.containsKey(index))
            return nodes.get(index);
        else{
            graphNode node = new graphNode(index);
            nodes.put(index, node);
            return node;
        }
    }

    public void shortestPath(int start){

        graphNode startNode = nodes.get(start);
        startNode.curDistance = 0;

        PriorityQueue<graphNode> pq = new PriorityQueue<graphNode>(nodes.size(), new Comparator<graphNode>() {
            @Override
            public int compare(graphNode graphNode, graphNode graphNode2) {
                if(graphNode.curDistance <graphNode2.curDistance) return -1;
                else if (graphNode.curDistance == graphNode2.curDistance) return 0;
                else return 1;
            }
        });

        pq.add(startNode);
        while(! pq.isEmpty()){
            graphNode gN = pq.poll();
            relax(gN, pq);
        }

    }

    public void relax(graphNode node,  PriorityQueue<graphNode> pq){

        for(graphEdge edge: node.edges){

            graphNode nextNode = edge.otherNode(node);
            if(nextNode.curDistance > node.curDistance + edge.distance){
                nextNode.curDistance = node.curDistance + edge.distance;

                if(! pq.contains(nextNode))
                    pq.add(nextNode);
            }

        }

    }

}
