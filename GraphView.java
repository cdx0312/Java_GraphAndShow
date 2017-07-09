import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Created by cdxu0 on 2017/7/9.
 */
public class GraphView extends Pane{
    private Graph<? extends Display> graph;
    public GraphView(Graph<? extends Display> graph) {
        this.graph = graph;
        List<? extends Display> vertices = graph.getVertices();
        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();
            getChildren().add(new Circle(x,y,16));
            getChildren().add(new Text(x - 8, y - 18, name));
        }

        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();
            for (int v:neighbors){
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();
                getChildren().add(new Line(x1,y1,x2,y2));
            }
        }
    }
}
