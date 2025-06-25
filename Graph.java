import java.util.*;

public class Graph {
    private Map<String, Vertex> vertices = new HashMap<>();
    private Map<String, Set<String>> adjList = new HashMap<>();

    public void addVertex(Vertex v) {
        vertices.put(v.getName(), v);
        adjList.putIfAbsent(v.getName(), new HashSet<>());
    }

    public boolean addEdge(String v1, String v2) {
        if (!vertices.containsKey(v1) || !vertices.containsKey(v2)) return false;
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1);
        return true;
    }

    public boolean hasCycles() {
        Set<String> visited = new HashSet<>();
        for (String v : vertices.keySet()) {
            if (!visited.contains(v)) {
                if (dfsCycle(v, null, visited)) return true;
            }
        }
        return false;
    }

    private boolean dfsCycle(String curr, String parent, Set<String> visited) {
        visited.add(curr);
        for (String neighbor : adjList.get(curr)) {
            if (!visited.contains(neighbor)) {
                if (dfsCycle(neighbor, curr, visited)) return true;
            } else if (!neighbor.equals(parent)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        if (vertices.isEmpty()) return true;
        Set<String> visited = new HashSet<>();
        dfs(vertices.keySet().iterator().next(), visited);
        return visited.size() == vertices.size();
    }

    private void dfs(String curr, Set<String> visited) {
        visited.add(curr);
        for (String neighbor : adjList.get(curr)) {
            if (!visited.contains(neighbor)) dfs(neighbor, visited);
        }
    }

    public List<String> depthFirstSearch(String start) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsOrder(start, visited, result);
        return result;
    }

    private void dfsOrder(String curr, Set<String> visited, List<String> result) {
        if (!vertices.containsKey(curr)) return;
        visited.add(curr);
        result.add(curr);
        for (String neighbor : adjList.get(curr)) {
            if (!visited.contains(neighbor)) dfsOrder(neighbor, visited, result);
        }
    }

    public List<String> breadthFirstSearch(String start) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        if (!vertices.containsKey(start)) return result;

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            result.add(curr);
            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    public Set<String> getNeighbors(String name) {
        return adjList.getOrDefault(name, new HashSet<>());
    }
}
