import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cdxu0 on 2017/7/9.
 */
public class TestGraph {
    public static void main(String[] args) {
        String[] vertices = {"Seattle", "San Francisco", "Los Angels", "Denver", "Kansans City",
                "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston"};
        int[][] edges = {
                {0,1},{0,3},{0,5},
                {1,0},{1,2},{1,3},
                {2,1},{2,3},{2,4},{2,10},
                {3,0},{3,1},{3,2},{3,4},{3,5},
                {4,2},{4,3},{4,5},{4,7},{4,8},{4,10},
                {5,0},{5,3},{5,4},{5,6},{5,7},
                {6,5},{6,7},
                {7,4},{7,5},{7,6},{7,8},
                {8,4},{8,7},{8,9},{8,10},{8,11},
                {9,8},{9,11},
                {10,2},{10,4},{10,8},{10,11},
                {11,8},{11,9},{11,10}
        };
        Graph<String> graph1 = new UnweightedGraph<>(vertices,edges);
        System.out.println("The number of vertices in graph1 is " + graph1.getSize());
        System.out.println("The vertex with index 1 is " + graph1.getVertex(1));
        System.out.println("the index for Miami is  " + graph1.getIndex("Miami"));
        System.out.println("THe edges for graph1 " );
        graph1.printEdges();

        String[] names = {"Peter", "Jane", "Mark", "Cindy", "Wendy"};
        ArrayList<AbstractGraph.Edge> edgeList = new ArrayList<>();
        edgeList.add(new AbstractGraph.Edge(0,2));
        edgeList.add(new AbstractGraph.Edge(1,2));
        edgeList.add(new AbstractGraph.Edge(2,4));
        edgeList.add(new AbstractGraph.Edge(3,4));
        Graph<String> graph2 = new UnweightedGraph<>(Arrays.asList(names),edgeList);
        System.out.println("\nThe number of vertices in graph2: " + graph2.getSize());
        System.out.println("The edges for graph2 ");
        graph2.printEdges();

    }
}

/*
The number of vertices in graph1 is 12
The vertex with index 1 is San Francisco
the index for Miami is  9
THe edges for graph1
Seattle (0): (Seattle, San Francisco)(Seattle, Denver)(Seattle, Chicago)
San Francisco (1): (San Francisco, Seattle)(San Francisco, Los Angels)(San Francisco, Denver)
Los Angels (2): (Los Angels, San Francisco)(Los Angels, Denver)(Los Angels, Kansans City)(Los Angels, Dallas)
Denver (3): (Denver, Seattle)(Denver, San Francisco)(Denver, Los Angels)(Denver, Kansans City)(Denver, Chicago)
Kansans City (4): (Kansans City, Los Angels)(Kansans City, Denver)(Kansans City, Chicago)(Kansans City, New York)(Kansans City, Atlanta)(Kansans City, Dallas)
Chicago (5): (Chicago, Seattle)(Chicago, Denver)(Chicago, Kansans City)(Chicago, Boston)(Chicago, New York)
Boston (6): (Boston, Chicago)(Boston, New York)
New York (7): (New York, Kansans City)(New York, Chicago)(New York, Boston)(New York, Atlanta)
Atlanta (8): (Atlanta, Kansans City)(Atlanta, New York)(Atlanta, Miami)(Atlanta, Dallas)(Atlanta, Houston)
Miami (9): (Miami, Atlanta)(Miami, Houston)
Dallas (10): (Dallas, Los Angels)(Dallas, Kansans City)(Dallas, Atlanta)(Dallas, Houston)
Houston (11): (Houston, Atlanta)(Houston, Miami)(Houston, Dallas)

The number of vertices in graph2: 5
The edges for graph2
0 (0): (0, 2)
1 (1): (1, 2)
2 (2): (2, 4)
3 (3): (3, 4)
4 (4):
 */
