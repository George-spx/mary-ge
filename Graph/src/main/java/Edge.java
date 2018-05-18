public class Edge<T,E extends Number> extends QueueNode<T,E>{
  E d; 
  int color; // 0 = white, 1 = gray, 2 = black

  public Edge(T key, E value){
    super(key,value);
    this.d = null;
    this.color = 0;
  }

  @Override
  public boolean equals(Object other) {
    if(other != null && other instanceof Edge){
      if(((Edge)other).key.equals(this.key)){
        return true;
      }else{
        return false;
      }
    }else return false;
  }
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + key.hashCode();
    return result;
  }
}
