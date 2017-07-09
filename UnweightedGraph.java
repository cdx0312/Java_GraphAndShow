import java.util.List;

/**
 * Created by cdxu0 on 2017/7/9.
 */
public class UnweightedGraph<V> extends AbstractGraph<V> {
    public UnweightedGraph() {
    }

    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List<V> vertices, List<Edge> edges) {
        super(edges, vertices.size());
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
}
