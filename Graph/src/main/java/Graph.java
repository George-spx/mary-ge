import java.util.*;


/**
 * @author George Andrei Varga
 */
public class Graph<T, E extends Number>{
  HashMap<T, Vertex<T,E>> graph;
  public int n_archi = 0;

  /**
   * instantiates the HashMap graph
   *
   */ 
  public Graph(){
    graph = new HashMap<>();
  }

 
  /**
   *
   * @return returns the vertex having the key in input if present
   * @throws throws GraphExpcetion if the vertex is not present
   */ 
  public Vertex<T,E> getVertex(T key) throws GraphException{
    if(graph.containsKey(key)){
      return graph.get(key);
    }else throw new GraphException("Vertex does not exist");
  }

  /**
   *
   * adds the vertex to the graph
   *
   * @param the vertex key to be added
   */ 
  public void addVertex(T key) throws GraphException{
    if(graph.containsKey(key)){
      graph.put(key, new Vertex<>(key));
    }
  }

  /**
   *
   * adds the vertex to the graph
   *
   * @param the vertex object to be added
   */ 
  public void addVertex(Vertex<T,E> vertex){
    if(!(graph.containsKey(vertex.key))){
      graph.put(vertex.key, vertex);
    }
  }

  /**
   * adds the vertices in input to the graph if it does not exist
   *
   * @param v1 the vertex number one to be added
   * @param v2 the vertex number two to be added
   */
  private void addIfNotExist(Vertex<T,E> v1, Vertex<T,E> v2){
    addVertex(v1);
    addVertex(v2);
  }

  public void addEdge(Vertex<T,E> source, Vertex<T,E> destination, E weight){
    Edge<T,E> toAdd = new Edge<T,E>(destination.key, weight);
    addIfNotExist(source,destination);
    if(!(graph.get(source.key).adjVertices.contains(toAdd))){
      graph.get(source.key).adjVertices.add(toAdd); 
      n_archi++;
    }else{}
  }
 
 /**
  *
  * removes the input vertex from the graph
  * @param vertex the vertex to be removed
  * @return true if the node was successfully removed
  */  
  public boolean removeVertex(T key){
    if(graph.containsKey(key)){
      graph.remove(key);
      return true;
    }else{
      return false;
    }
  }
 
  /**
  *
  * removes the input vertex from the graph
  * @param key key of the vertex to be removed
  * @return true if the node was successfully removed
  */  
  public boolean removeVertex(Vertex<T,E> vertex){
    if(graph.containsKey(vertex.key)){
      graph.remove(vertex.key);
      return true;
    }else{
      return false;
    }
  }


  /**
   * calculates the weight of the Graph
   * @return the weight of the graph
   */
  public float getWeight(){
  float sum = 0;
    for(T key : graph.keySet()){
      Set<Edge<T,E>> edges = graph.get(key).adjVertices;
      if(edges!=null){
        for(Edge<T,E> edge: edges){
          sum = sum + edge.weight.floatValue();
        }
      }
    }
    return sum;
  }

  /**
   * calculates the number of vertices in the graph
   * @return the number of vertices
   */
  public int numberOfVertices(){
    return graph.size(); 
  }


  /**
   * returns all the vertices as an arrayList
   */
  public ArrayList<Vertex<T,E>> getVertices(){
    ArrayList<Vertex<T,E>> arrayVertices = new ArrayList<>();
    for(T key : graph.keySet()){
      arrayVertices.add(graph.get(key)); 
    }
    return arrayVertices;
  }

  /**
   *
   * just a test method that will be removed
   */ 
  public void printNodes(){
    for(T key: graph.keySet()){
      Set<Edge<T,E>> edges = graph.get(key).adjVertices;
      System.out.println("KEY: "+key);
      if(edges!=null){
        for(Edge<T,E> edge: edges){
          System.out.println(edge.key+" --> "+edge.value);
        }
      }
    } 
  }

  public Comparator<QueueNode<T,E>> initializeQueueComparator(){
    Comparator<QueueNode<T,E>> comparator = new Comparator<QueueNode<T,E>>(){
      @Override
      public int compare(QueueNode<T,E> e1, QueueNode<T,E> e2){
        float diff = e1.value.floatValue() - e2.value.floatValue();
        if(diff < 0){
          return 1;
        }else if (diff > 0){
          return -1;
        }else return 0;
      }
    };
    return comparator;
  }
  
  public Comparator<E> initializeEComparator(){
    Comparator<E> comparator = new Comparator<E>(){
      @Override
      public int compare(E e1, E e2){
        float diff = e1.floatValue() - e2.floatValue();
        if(diff < 0){
          return -1;
        }else if (diff > 0){
          return 1;
        }else return 0;
      }
    };
    return comparator;
  }

  public PriorityQueue<T,E> populateQueue(){
    Comparator<QueueNode<T,E>> comparator = initializeQueueComparator();
    PriorityQueue<T,E> Q = new PriorityQueue<>(comparator);
    for(T key: graph.keySet()){
      HashSet<Edge<T,E>> edges = graph.get(key).adjVertices;
      if(edges != null){
        for(Edge<T,E> edge : edges){
          try{
            Q.enqueue(edge); 
          }catch(Exception e){
            System.out.println(e);
          }
        }
      } 
    }
    return Q;
  }

  public Graph<T,E> primAlgorithm(Vertex<T,E> s){
    Graph <T,E> MST = new Graph<>();
    PriorityQueue<T,E> Q = populateQueue();
    Comparator<E> comparator = initializeEComparator();
    try{
      Q.decreaseKey((T)((graph.keySet()).toArray()[0]), (E)(Float)(float)0);
    }catch(Exception e){System.out.println(e);}
    while(!Q.isEmpty()){
      try{
        Edge<T,E> u =(Edge<T,E>) Q.dequeue();
        MST.addEdge(graph.get(u.key), graph.get(u.key), u.weight);
        System.out.println(u.key);
        HashSet<Edge<T,E>> adjU = (graph.get(u.key)).adjVertices;
        System.out.println(adjU.size());
        for(Edge<T,E> v : adjU){
          System.out.println("V "+v.key);
          if(Q.contains(v.key)){
            if(comparator.compare(v.weight, Q.getValue(v.key)) < 0){
              Q.decreaseKey(v.key, v.weight); 
              graph.get(v.key).previous = graph.get(u.key);
            } 
          }
        }
      }catch(Exception e){ System.out.println(e);
      }
    }
  return MST;
  }
}
