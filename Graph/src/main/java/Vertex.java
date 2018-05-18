import java.util.*;
public class Vertex<T, E extends Number>{
  public final int inf = -23;
  T key;
  int state;
  int d;
  Set<Edge<T, E>> adjVertices;
  
  public Vertex(T key){
    this.key = key;
    this.state = 0;
    this.d = inf;
    adjVertices = new HashSet<>();
  }
}
