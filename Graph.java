import java.util.List;

/**
 * Created by cdxu0 on 2017/7/9.
 */
public interface Graph<V> {
    //return the number of vertices in the graph
    public int getSize();
    //return the set of the vertices in the graph
    public List<V> getVertices();
    //return the object of specific vertex index
    public V getVertex(int index);
    //return the index of the specific vertex
    public int getIndex(V v);
    //return the neighbour of specific index
    public List<Integer> getNeighbors(int index);
    //return the degree of specific vertices
    public int getDegree(int index);
    //print the edge
    public void printEdges();
    //remove the graph
    public void clear();
    //return true if vertices inserted to the map
    //if the vertices is already in the map return false
    public boolean addVertex(V v);
    //add the edge between u to v to the map, if failed return IllegalArgumentException
    //return true if success, return false if the edge is already in the map
    public boolean addEdge(int u, int v);
    //start from v, obtain a depth-first search tree
    public AbstractGraph<V>.Tree dfs(int v);
    //start from v, obtain a breadth-first search tree
    public AbstractGraph<V>.Tree bfs(int v);
}
