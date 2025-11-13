package mst;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Graph g = new Graph(6);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 5, 6);

        System.out.println("Building MST using Kruskal’s algorithm:");
        List<Edge> mstEdges = KruskalMST.buildMST(g);

        System.out.println("MST edges:");
        KruskalMST.printEdges(mstEdges);

        Edge removed = mstEdges.get(2);
        mstEdges.remove(removed);
        System.out.println("\nRemoved edge: " + removed);

        List<Set<Integer>> comps = KruskalMST.findComponents(g.vertices, mstEdges);
        System.out.println("\nComponents after removal:");
        for (Set<Integer> comp : comps)
            System.out.println(comp);

        Edge replacement = KruskalMST.findReplacementEdge(g, mstEdges, removed);
        if (replacement != null) {
            mstEdges.add(replacement);
            System.out.println("\nReplacement edge found: " + replacement);
        } else {
            System.out.println("\nNo replacement edge found.");
        }

        System.out.println("\nNew MST edges:");
        KruskalMST.printEdges(mstEdges);
    }
}
