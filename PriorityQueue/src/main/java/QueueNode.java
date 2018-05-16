public class QueueNode<T,E>{
  T key;
  E value;

  public QueueNode(T key, E value){
    this.key = key;
    this.value = value;
  }

  public String toString(){
    return (String)this.key;
  }
}
