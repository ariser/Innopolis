package graph;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Graph<E, W extends Comparable<W>> {
    private List<Vertex<E, W>> vertices = new ArrayList<>();
    private List<Edge<E, W>> edges = new ArrayList<>();

    public static <E> Graph<E, Integer> fromString(String source, Class<E> elementClass)
            throws IllegalArgumentException {
        Graph<E, Integer> result = new Graph<>();

        String[] lines = source.split("\n");
        if (lines.length != 2) {
            throw new IllegalArgumentException("Invalid input string");
        }

        try {
            for (String vertexElem : lines[0].split("\\s+")) {
                E val = elementClass.cast(vertexElem);
                result.addVertex(new Vertex<>(val));
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid input string");
        }

        try {
            String[] edges = lines[1].split("\\s+");
            for (int i = 0; i < edges.length; i += 3) {
                E fromElem = elementClass.cast(edges[i]);
                E toElem = elementClass.cast(edges[i + 1]);
                int weight = Integer.parseInt(edges[i + 2]);

                Vertex<E, Integer> from = null;
                Vertex<E, Integer> to = null;

                for (Vertex<E, Integer> v : result.vertices) {
                    if (v.element.equals(fromElem)) {
                        from = v;
                    }
                    if (v.element.equals(toElem)) {
                        to = v;
                    }
                }

                if (from == null || to == null) {
                    throw new IllegalArgumentException("Invalid input string");
                }

                result.addEdge(from, to, weight);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid input string");
        }

        return result;
    }

    public boolean addVertex(E element) {
        return addVertex(new Vertex<>(element));
    }

    public boolean addVertex(Vertex<E, W> vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            vertex.position = vertices.size() - 1;
            return true;
        }
        return false;
    }

    public Vertex<E, W> getVertex(E element) {
        for (Vertex<E, W> vertex : vertices) {
            if (vertex.element.equals(element)) {
                return vertex;
            }
        }
        return null;
    }

    public boolean removeVertex(Vertex<E, W> vertex) {
        if (vertices.get(vertex.position).equals(vertex)) {
            vertex.incidents.forEach(this::removeEdge);
            vertices.remove(vertex.position);
            return true;
        }
        return false;
    }

    public void addEdge(E from, E to) {
        addEdge(from, to, null);
    }

    public void addEdge(E from, E to, W weight) throws IllegalArgumentException, NullPointerException {
        if (from == null || to == null) {
            throw new NullPointerException("One of the given vertices is not defined");
        }

        Vertex<E, W> origin = getVertex(from);
        Vertex<E, W> destination = getVertex(to);

        if (origin == null || destination == null) {
            throw new IllegalArgumentException("Invalid input string");
        }

        addEdge(origin, destination, weight);
    }

    public void addEdge(Vertex<E, W> from, Vertex<E, W> to) {
        addEdge(from, to, null);
    }

    public void addEdge(Vertex<E, W> from, Vertex<E, W> to, W weight) throws NullPointerException {
        if (from == null || to == null) {
            throw new NullPointerException("One of the given vertices is not defined");
        }

        addEdge(new Edge<>(from, to, weight));
    }

    private void addEdge(Edge<E, W> edge) {
        edges.add(edge);
        edge.position = edges.size() - 1;
        edge.origin.incidents.add(edge);
        edge.destination.incidents.add(edge);
    }

    private Edge<E, W> getEdge(Vertex<E, W> from, Vertex<E, W> to) {
        return getEdge(from, to, true);
    }

    private Edge<E, W> getEdge(Vertex<E, W> from, Vertex<E, W> to, boolean strictDirection) {
        Edge<E, W> targetEdge = null;
        for (Edge<E, W> e : edges) {
            if (e.origin.equals(from) && e.destination.equals(to)
                    || (!strictDirection && e.origin.equals(to) && e.destination.equals(from))) {
                targetEdge = e;
                break;
            }
        }
        return targetEdge;
    }

    public boolean removeEdge(Vertex<E, W> from, Vertex<E, W> to) {
        return removeEdge(from, to, true);
    }

    public boolean removeEdge(Vertex<E, W> from, Vertex<E, W> to, boolean strictDirection) {
        Edge<E, W> targetEdge = getEdge(from, to, strictDirection);
        return targetEdge != null && removeEdge(targetEdge);
    }

    private boolean removeEdge(Edge<E, W> edge) {
        if (edges.get(edge.position).equals(edge)) {
            edge.origin.incidents.remove(edge);
            edge.destination.incidents.remove(edge);
            edges.remove(edge.position);
            for (int i = edge.position; i < edges.size(); i++) {
                edges.get(i).position = i;
            }
            return true;
        }
        return false;
    }

    public List<Vertex<E, W>> getAdjacent(Vertex<E, W> vertex) {
        return vertex.incidents.stream().map(e -> e.origin.equals(vertex) ? e.destination : e.origin).collect(Collectors.toList());
    }

    private void resetVisitState() {
        for (Vertex<E, W> vertex : vertices) {
            vertex.visited = false;
            vertex.distance = 0;
            vertex.parent = null;
        }
    }

    public void DFS(Consumer<Vertex<E, W>> visit) {
        DFSRecursive(visit, getFirstVertex());
        resetVisitState();
    }

    private void DFSRecursive(Consumer<Vertex<E, W>> visit, Vertex<E, W> vertex) {
        if (vertex == null) return;
        vertex.visited = true;
        visit.accept(vertex);
        getAdjacent(vertex).stream().filter(adjacent -> !adjacent.visited).forEach(adjacent -> DFSRecursive(visit, adjacent));
    }

    public void BFS(Consumer<Vertex<E, W>> visit) {
        BFS(getFirstVertex(), visit);
    }

    public void BFS(Vertex<E, W> startingVertex, Consumer<Vertex<E, W>> visit) {
        Queue<Vertex<E, W>> queue = new ArrayBlockingQueue<>(100);
        Vertex<E, W> current;

        if (startingVertex != null) {
            queue.add(startingVertex);
            startingVertex.visited = true;
            startingVertex.distance = 0;
            visit.accept(startingVertex);
        }

        while (!queue.isEmpty()) {
            current = queue.poll();
            for (Vertex<E, W> vertex : getAdjacent(current)) {
                if (!vertex.visited) {
                    vertex.visited = true;
                    vertex.distance = current.distance + 1;
                    vertex.parent = current;
                    visit.accept(vertex);
                    queue.add(vertex);
                }
            }
            if (queue.isEmpty()) {
                break;
            }
        }
        resetVisitState();
    }

    private MSTNode getMinVertex(Map<Vertex<E, W>, MSTNode> nodes, W maxValue) {
        MSTNode min = new MSTNode(null, null, maxValue);
        for (Map.Entry<Vertex<E, W>, MSTNode> node : nodes.entrySet()) {
            if (!node.getValue().visited && node.getValue().key.compareTo(min.key) < 0) {
                min = node.getValue();
            }
        }
        return min;
    }

    public Graph<E, W> getMinimumSpanningTree(Vertex<E, W> startingVertex, W minWeight, W maxWeight) {
        Graph<E, W> MST = new Graph<>();

        Map<Vertex<E, W>, MSTNode> dist = new HashMap<>(vertices.size());

        dist.put(startingVertex, new MSTNode(startingVertex, null, minWeight));
        vertices.stream().filter(vertex -> vertex != startingVertex).forEach(vertex -> {
            dist.put(vertex, new MSTNode(vertex, null, maxWeight));
        });
        int visitedCount = 0;

        while (visitedCount < vertices.size()) {
            MSTNode node = getMinVertex(dist, maxWeight);
            node.visited = true;
            MST.addVertex(node.vertex);
            if (node.edge != null) {
                MST.addEdge(node.edge);
            }
            for (Edge<E, W> edge : node.vertex.incidents) {
                Vertex<E, W> anotherVertex = node.vertex.equals(edge.origin) ? edge.destination : edge.origin;
                MSTNode currentNode = dist.get(anotherVertex);
                if (edge.weight.compareTo(currentNode.key) < 0) {
                    currentNode.key = edge.weight;
                    currentNode.edge = edge;
                }
            }
            visitedCount++;
        }

        return MST;
    }

    public Graph<E, W> getMinimumSpanningTree(W minWeight, W maxWeight) {
        return getMinimumSpanningTree(getFirstVertex(), minWeight, maxWeight);
    }

    /**
     * Find the first available vertex.
     * Can't just .get(0) as long as it could be deleted.
     *
     * @return the first available vertex
     */
    private Vertex<E, W> getFirstVertex() {
        Vertex<E, W> startingVertex = null;
        for (Vertex<E, W> vertex : vertices) {
            startingVertex = vertex;
            if (startingVertex != null) {
                break;
            }
        }
        return startingVertex;
    }


    @Override
    public String toString() {
        List<String> firstLine = vertices.stream().map(Vertex::toString).collect(Collectors.toList());
        List<String> secondLine = edges.stream().map(Edge::toString).collect(Collectors.toList());
        return String.join(" ", firstLine) + "\n" + String.join(" ", secondLine);
    }

    protected static class Edge<E, W> {
        protected W weight = null;
        protected Vertex<E, W> origin;
        protected Vertex<E, W> destination;
        protected int position = -1;

        Edge(Vertex<E, W> origin, Vertex<E, W> destination) {
            this.origin = origin;
            this.destination = destination;
        }

        Edge(Vertex<E, W> origin, Vertex<E, W> destination, W weight) {
            this(origin, destination);
            this.weight = weight;
        }

        public W getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return origin.toString() + " " + destination.toString();
        }
    }

    public static class Vertex<E, W> {
        protected E element;
        protected int position = -1;
        protected int distance = -1; // for BFS
        protected Vertex<E, W> parent = null; // for BFS
        protected boolean visited = false;
        private List<Edge<E, W>> incidents = new ArrayList<>();

        public Vertex(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public int getDistance() {
            return distance;
        }

        public Vertex<E, W> getParent() {
            return parent;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private class MSTNode {
        W key;
        Vertex<E, W> vertex;
        Edge<E, W> edge;
        boolean visited = false;

        MSTNode(Vertex<E, W> vertex, Edge<E, W> edge, W key) {
            this.vertex = vertex;
            this.edge = edge;
            this.key = key;
        }
    }
}