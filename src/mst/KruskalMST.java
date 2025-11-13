package mst;

import java.util.*;

public class KruskalMST {
    public static List<Edge> buildMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(graph.getEdges());
        UnionFind uf = new UnionFind(graph.vertices);

        for (Edge edge : graph.getEdges()) {
            int root1 = uf.find(edge.src);
            int root2 = uf.find(edge.dest);
            if (root1 != root2) {
                result.add(edge);
                uf.union(root1, root2);
            }
        }

        return result;
    }

    public static void printEdges(List<Edge> edges) {
        edges.forEach(System.out::println);
    }

    public static List<Set<Integer>> findComponents(int vertices, List<Edge> edges) {
        UnionFind uf = new UnionFind(vertices);
        for (Edge e : edges) uf.union(e.src, e.dest);

        Map<Integer, Set<Integer>> comps = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            int root = uf.find(i);
            comps.computeIfAbsent(root, k -> new HashSet<>()).add(i);
        }
        return new ArrayList<>(comps.values());
    }

    public static Edge findReplacementEdge(Graph g, List<Edge> mstEdges, Edge removed) {
        List<Set<Integer>> comps = findComponents(g.vertices, mstEdges);
        if (comps.size() != 2) return null;

        Set<Integer> compA = comps.get(0);
        Set<Integer> compB = comps.get(1);
        Edge best = null;
        for (Edge e : g.getEdges()) {
            boolean connects = (compA.contains(e.src) && compB.contains(e.dest))
                    || (compA.contains(e.dest) && compB.contains(e.src));
            if (connects) {
                if (best == null || e.weight < best.weight)
                    best = e;
            }
        }
        return best;
    }
}