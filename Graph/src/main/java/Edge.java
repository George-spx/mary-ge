public class Edge<T,E extends Number>{
  T key;
  E weight;

  public Edge(T key, E weight){
    this.key = key;
    this.weight = weight;
  }
}
