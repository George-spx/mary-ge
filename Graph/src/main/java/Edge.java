public class Edge<T,E> extends QueueNode<T,E>{
  E d; 
  int color; // 0 = white, 1 = gray, 2 = black

  public Edge(T key, E value){
    super(key,value);
    this.d = null;
    this.color = 0;
  }
}
