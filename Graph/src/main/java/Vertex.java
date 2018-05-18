import java.util.*;
public class Vertex<T, E extends Number>{
  T key;
  int state;
  Set<Edge<T, E>> adjVertices;
  
  public Vertex(T key){
    this.key = key;
    this.state = 0;
    adjVertices = new HashSet<>();
  }
}
