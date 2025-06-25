// Vertex
public class Vertex {
    private final String name;
    private final double x, y;

    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }
}
