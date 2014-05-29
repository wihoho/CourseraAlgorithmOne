package week4;

import java.io.File;
import java.util.*;

/**
 * Authored by GongLi at 19:33 on 29/5/14
 */


public class Test {

    Node[] graph;
    Node[] reverseGraph;
    Stack<Integer> TS;
    int count = 0;

    public static void main(String[] args) {

        Test test1 = new Test("SCC1.txt");
        test1.SCC();

        System.out.println();

        Test test2 = new Test("SCC2.txt");
        test2.SCC();
    }

    public Test(String fileName) {

        File f = new File(fileName);
        try {
            Scanner s = new Scanner(f);
            int cur = 0;
            int max = 0;

            while (s.hasNext()) {
                String[] edge = s.nextLine().split(" ");
                int start = Integer.valueOf(edge[0]);
                int end = Integer.valueOf(edge[1]);

                if (max < start)
                    max = start;
                if (max < end)
                    max = end;

                cur++;
            }

            // read the graph
            s = new Scanner(f);
            cur = 0;

            graph = new Node[max];
            reverseGraph = new Node[max];
            for (int i = 0; i < max; i++) {
                graph[i] = new Node(i);
                reverseGraph[i] = new Node(i);
            }


            while (s.hasNext()) {
                String[] edge = s.nextLine().split(" ");
                int start = Integer.valueOf(edge[0]) - 1;
                int end = Integer.valueOf(edge[1]) - 1;

                graph[start].out.add(graph[end]);
                reverseGraph[end].out.add(reverseGraph[start]);

                cur++;
            }

        } catch (Exception e) {

        }
    }

    public void SCC() {
        boolean[] visited = new boolean[graph.length];
        int[] labels = new int[graph.length];

        topologicalSeq();
        while (!TS.empty()) {
            int curId = TS.pop();
            Node curNode = graph[curId];

            if (!visited[curId])
                dfs(curNode, visited, labels);

            count++;
        }

        // statistics
        HashMap<Integer, Integer> statistics = new HashMap<Integer, Integer>();
        for (int value : labels) {
            if (!statistics.containsKey(value))
                statistics.put(value, 0);

            int temp = statistics.get(value);
            statistics.put(value, temp + 1);
        }


        ArrayList<Integer> values = new ArrayList<Integer>();
        for (Integer m : statistics.keySet())
            values.add(statistics.get(m));

        Integer[] vArr = values.toArray(new Integer[values.size()]);
        Arrays.sort(vArr);

        for(int i = 0; i < 10; i ++)
            System.out.println(i+": "+vArr[vArr.length - 1 - i]);

    }

    public void dfs(Node s, boolean[] visited, int[] labels) {

        visited[s.id] = true;
        labels[s.id] = count;

        for (Node n : s.out) {
            if (!visited[n.id])
                dfs(n, visited, labels);
        }

    }


    public void topologicalSeq() {

        boolean[] visited = new boolean[graph.length];
        TS = new Stack<Integer>();

        for (Node v : reverseGraph) {
            if (!visited[v.id])
                tsDFS(visited, v);
        }

    }

    public void tsDFS(boolean[] visited, Node v) {

        visited[v.id] = true;
        for (Node n : v.out) {
            if (!visited[n.id])
                tsDFS(visited, n);
        }

        TS.push(v.id);
    }
}
