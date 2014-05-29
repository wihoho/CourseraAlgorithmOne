package week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Authored by GongLi at 16:35 on 29/5/14
 */


public class Graph {
    Collection<Node> vertices;
    HashMap<Integer, Node> map;
    Collection<Node> reverseGraph;
    Stack<Integer> TS = new Stack<Integer>();
    HashMap<Node, Integer> ret;
    int count = 0;

    public static void main(String[] args) {
        Graph g = new Graph("SCC1.txt");
        g.SCC();

        System.out.println();

        Graph g1 = new Graph("SCC2.txt");
        g1.SCC();
    }

    public Graph(String fileName) {
        File f = new File(fileName);

        try {
            HashMap<Integer, Node> map = new HashMap<Integer, Node>();
            HashMap<Integer, Node> reverseMap = new HashMap<Integer, Node>();

            Scanner s = new Scanner(f);

            int curLine = 0;
            while (s.hasNext()) {
                String[] edge = s.nextLine().split(" ");
                int start = Integer.valueOf(edge[0]);
                int end = Integer.valueOf(edge[1]);

                // Find the node for start and end
                Node startNode, endNode;
                if (!map.containsKey(start))
                    map.put(start, new Node(start));
                startNode = map.get(start);

                if (!map.containsKey(end))
                    map.put(end, new Node(end));
                endNode = map.get(end);

                startNode.out.add(endNode);

                // Reverse map
                Node rStartNode, rEndNode;
                if (!reverseMap.containsKey(start))
                    reverseMap.put(start, new Node(start));
                rStartNode = reverseMap.get(start);

                if (!reverseMap.containsKey(end))
                    reverseMap.put(end, new Node(end));
                rEndNode = reverseMap.get(end);

                rEndNode.out.add(rStartNode);


                System.out.println("Line: " + curLine);
                curLine++;
            }

            this.map = map;
            vertices = (Collection<Node>) map.values();
            reverseGraph = (Collection<Node>) reverseMap.values();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void SCC() {
        HashMap<Node, Boolean> visited = new HashMap<Node, Boolean>();
        ret = new HashMap<Node, Integer>();


        for (Node v : vertices)
            visited.put(v, false);

        topologicalSeq();
        while (!TS.empty()) {
            int currentId = TS.pop();
            Node curretNode = map.get(currentId);

            if (!visited.get(curretNode)) {
                dfs(curretNode, visited);
            }

            count++;
        }


        // statistics of scc
        HashMap<Integer, Integer> statistics = new HashMap<Integer, Integer>();
        for (Integer v : ret.values()) {
            if (!statistics.containsKey(v))
                statistics.put(v, 0);

            int temp = statistics.get(v);
            statistics.put(v, temp + 1);
        }

        for (Integer m : statistics.keySet()) {
            System.out.println(m + ": " + statistics.get(m));
        }
    }

    public void dfs(Node s, HashMap<Node, Boolean> visited) {

        visited.put(s, true);
        ret.put(s, count);

        for (Node n : s.out) {
            if (!visited.get(n))
                dfs(n, visited);
        }

    }

    public void topologicalSeq() {
        HashMap<Node, Boolean> visited = new HashMap<Node, Boolean>();
        for (Node v : reverseGraph)
            visited.put(v, false);

        for (Node v : reverseGraph) {
            if (!visited.get(v)) {
                tsDFS(visited, v);
            }
        }
    }

    public void tsDFS(HashMap<Node, Boolean> visited, Node s) {

        visited.put(s, true);
        for (Node n : s.out) {
            if (!visited.get(n))
                tsDFS(visited, n);
        }

        TS.push(s.id);
    }
}
