import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GraphPane graphPane = new GraphPane();
        graphPane.setPrefSize(600, 400);

        // === Top controls (Add Edge section) ===
        TextField v1Field = new TextField();
        TextField v2Field = new TextField();
        v1Field.setPromptText("Vertex 1");
        v2Field.setPromptText("Vertex 2");
        v1Field.setPrefWidth(60);
        v2Field.setPrefWidth(60);

        Button addEdgeBtn = new Button("Add Edge");
        HBox topControls = new HBox(10, addEdgeBtn, v1Field, v2Field);
        topControls.setAlignment(Pos.CENTER);
        topControls.setPadding(new Insets(10));

        // === Bottom controls (analysis + output) ===
        Button isConnectedBtn = new Button("Is Connected?");
        Button hasCyclesBtn = new Button("Has Cycles?");
        Button dfsBtn = new Button("Depth First Search");
        Button bfsBtn = new Button("Breadth First Search");

        HBox analysisControls = new HBox(10, isConnectedBtn, hasCyclesBtn, dfsBtn, bfsBtn);
        analysisControls.setAlignment(Pos.CENTER);
        analysisControls.setPadding(new Insets(5));

        TextField output = new TextField();
        output.setEditable(false);
        output.setPrefWidth(580);

        VBox bottomControls = new VBox(5, analysisControls, output);
        bottomControls.setAlignment(Pos.CENTER);
        bottomControls.setPadding(new Insets(10));

        // === Layout setup ===
        BorderPane layout = new BorderPane();
        layout.setTop(topControls);
        layout.setCenter(graphPane);
        layout.setBottom(bottomControls);

        // === Event Handlers ===
        addEdgeBtn.setOnAction(e -> {
            String v1 = v1Field.getText().trim();
            String v2 = v2Field.getText().trim();
            if (!graphPane.addEdge(v1, v2)) {
                output.setText("Invalid vertices specified.");
            } else {
                output.setText("");
            }
        });

        isConnectedBtn.setOnAction(e -> {
            boolean connected = graphPane.getGraph().isConnected();
            output.setText("Graph is " + (connected ? "connected." : "not connected."));
        });

        hasCyclesBtn.setOnAction(e -> {
            boolean cycles = graphPane.getGraph().hasCycles();
            output.setText("The graph " + (cycles ? "has cycles." : "doesn't have cycles."));
        });

        dfsBtn.setOnAction(e -> {
            output.setText("DFS: " + graphPane.getGraph().depthFirstSearch("A"));
        });

        bfsBtn.setOnAction(e -> {
            output.setText("BFS: " + graphPane.getGraph().breadthFirstSearch("A"));
        });

        // === Final Scene Setup ===
        Scene scene = new Scene(layout, 700, 550);
        primaryStage.setTitle("Project 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//ends CMSC315PROJ4MandyckM Java File
