public class Edge<T,E extends Number>{
  T key;
  E weight;

  public Edge(T key, E weight){
    this.key = key;
    this.weight = weight;
  }

  /*@Override
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
  }*/
}
