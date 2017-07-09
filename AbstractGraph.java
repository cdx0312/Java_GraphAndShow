import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cdxu0 on 2017/7/9.
 */
public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<V>();//vertices
    protected List<List<Edge>> neighbors = new ArrayList<>(); //adjacency lists

    protected AbstractGraph() {
    }

    //constuct a graph from vertoces and edges
    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++)
            addVertex(vertices[i]);
        createAdjacencyList(edges, vertices.length);
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V) (new Integer(i)));
        createAdjacencyList(edges, numberOfVertices);
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V) (new Integer(i)));
        createAdjacencyList(edges, numberOfVertices);
    }

    private void createAdjacencyList(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++)
            addEdge(edges[i][0], edges[i][1]);
    }

    private void createAdjacencyList(List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges)
            addEdge(edge.u, edge.v);
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index))
            result.add(e.v);
        return result;
    }

    @Override
    public int getDegree(int index) {
        return neighbors.get(index).size();
    }

    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) {
                System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ")");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            neighbors.add(new ArrayList<Edge>());
            return true;
        } else
            return false;
    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    protected boolean addEdge(Edge edge) {
        if (edge.u < 0 || edge.u > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + edge.u);
        if (edge.v < 0 || edge.v > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + edge.v);
        if (!neighbors.get(edge.u).contains(edge)) {
            neighbors.get(edge.u).add(edge);
            return true;
        } else {
            return false;
        }
    }

    public static class Edge {
        public int u;
        public int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public boolean equals(Object object) {
            return u == ((Edge) object).u && v == ((Edge) object).v;
        }
    }

    @Override
    public Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1;
        boolean[] isVisited = new boolean[vertices.size()];
        dfs(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchorder, boolean[] isVisited) {
        searchorder.add(u);
        isVisited[u] = true;
        for (Edge edge:neighbors.get(u)) {
            if (!isVisited[edge.v]){
                parent[edge.v] = u;
                dfs(edge.v, parent, searchorder, isVisited);
            }
        }
    }

    @Override
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1;
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);
        isVisited[v] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    parent[edge.v] = u;
                    isVisited[edge.v] = true;
                }
            }
        }
        return new Tree(v, parent, searchOrder);

    }


    public class Tree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        public List<V> getPath(int index) {
            List<V> path = new ArrayList<V>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);
            return path;
        }

        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root)
                    + " to " + vertices.get(index) + " : ");
            for (int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " ");
        }

        public void printTree() {
            System.out.println("Root is " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i])
                            + ", " + vertices.get(i) + ")");
                }
            }
            System.out.println();
        }
    }
}
