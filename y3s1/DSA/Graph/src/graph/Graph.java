package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph<E, W> {
    private List<Vertex<E, W>> verticies = new ArrayList<>();
    private List<Edge<E, W>> edges = new ArrayList<>();

    public static <E, W> Graph<E, W> fromString(String source, Class<E> elementClass, Class<W> weightClass)
            throws IllegalArgumentException {
        Graph<E, W> result = new Graph<>();

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
                W weight = weightClass.cast(edges[i + 2]);

                Vertex<E, W> from = null;
                Vertex<E, W> to = null;

                for (Vertex<E, W> v : result.verticies) {
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

    public boolean addVertex(Vertex<E, W> vertex) {
        if (!verticies.contains(vertex)) {
            verticies.add(vertex);
            vertex.position = verticies.size() - 1;
            return true;
        }
        return false;
    }

    public boolean removeVertex(Vertex<E, W> vertex) {
        if (verticies.get(vertex.position).equals(vertex)) {
            vertex.incidents.forEach(this::removeEdge);
            verticies.remove(vertex.position);
            return true;
        }
        return false;
    }

    public void addEdge(Vertex<E, W> from, Vertex<E, W> to) {
        addEdge(from, to, null);
    }

    public void addEdge(Vertex<E, W> from, Vertex<E, W> to, W weight) {
        Edge<E, W> newEdge = new Edge<>(from, to, weight);
        edges.add(newEdge);
        newEdge.position = edges.size() - 1;
        newEdge.origin.incidents.add(newEdge);
        newEdge.destination.incidents.add(newEdge);
    }

    public boolean removeEdge(Vertex<E, W> from, Vertex<E, W> to) {
        Edge<E, W> targetEdge = null;
        for (Edge<E, W> e : edges) {
            if (e.origin.equals(from) && e.destination.equals(to)) {
                targetEdge = e;
                break;
            }
        }
        return targetEdge != null && removeEdge(targetEdge);
    }

    private boolean removeEdge(Edge<E, W> edge) {
        if (edges.get(edge.position).equals(edge)) {
            edge.origin.incidents.remove(edge);
            edge.destination.incidents.remove(edge);
            edges.remove(edge.position);
            return true;
        }
        return false;
    }

    public List<Vertex<E, W>> getAdjacent(Vertex<E, W> vertex) {
        return vertex.incidents.stream().map(e -> e.origin.equals(vertex) ? e.destination : e.origin).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        List<String> firstLine = verticies.stream().map(Vertex::toString).collect(Collectors.toList());
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

        @Override
        public String toString() {
            return origin.toString() + " " + destination.toString() + " " + weight.toString();
        }
    }

    public static class Vertex<E, W> {
        protected E element;
        protected int position = -1;
        private List<Edge<E, W>> incidents = new ArrayList<>();

        public Vertex(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
