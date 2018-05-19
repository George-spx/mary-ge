import java.util.*;
public class Vertex<T, E extends Number> extends QueueNode<T,E>{

  Vertex<T,E> previous;
  HashMap<T, Edge<T, E>> adjVertices;
  
  public Vertex(T key){
    super(key, (E)(Float)Float.POSITIVE_INFINITY);
    this.previous = null;
    adjVertices = new HashMap<>();
  }
}
