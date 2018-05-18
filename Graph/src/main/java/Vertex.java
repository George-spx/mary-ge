import java.util.*;
public class Vertex<T, E extends Number>{

  Vertex<T,E> previous;
  T key;
  HashSet<Edge<T, E>> adjVertices;
  
  public Vertex(T key){
    this.previous = null;
    this.key = key;
    adjVertices = new HashSet<>();
  }
}
