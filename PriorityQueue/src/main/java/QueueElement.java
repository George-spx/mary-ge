public class QueueElement<T,E>{
  T element;
  E priority;
  int c;
  public QueueElement(T element, E priority){
    this.element = element;
    this.priority = priority;
    this.c = -1;
  }

  public String toString(){
    return (String)this.element;
  }
}
