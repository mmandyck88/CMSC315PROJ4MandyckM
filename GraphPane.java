import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.*;

public class GraphPane extends Pane {
    private Graph graph = new Graph();
    private char nextLabel = 'A';
    private Map<String, Circle> vertexCircles = new HashMap<>();

    public GraphPane() {
        setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent e) {
        if (nextLabel > 'Z') return;
        String name = String.valueOf(nextLabel++);
        Vertex v = new Vertex(name, e.getX(), e.getY());
        graph.addVertex(v);

        Circle circle = new Circle(e.getX(), e.getY(), 15, Color.LIGHTBLUE);
        Text label = new Text(e.getX() - 4, e.getY() + 4, name);
        vertexCircles.put(name, circle);

        getChildren().addAll(circle, label);
    }

    public boolean addEdge(String v1, String v2) {
        if (!graph.addEdge(v1, v2)) return false;

        Vertex from = graph.getVertex(v1);
        Vertex to = graph.getVertex(v2);
        Line line = new Line(from.getX(), from.getY(), to.getX(), to.getY());
        getChildren().add(0, line);
        return true;
    }

    public Graph getGraph() {
        return graph;
    }
}
