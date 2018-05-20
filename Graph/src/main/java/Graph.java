import java.util.*;


/**
 * @author George Andrei Varga
 */
public class Graph<T, E extends Number>{
  private HashMap<T, Vertex<T,E>> graph;
  private boolean directed;
  private boolean weighted;
  private int nEdges = 0;

  /**
   * instantiates the HashMap graph
   *
   */ 
  public Graph(boolean weighted, boolean directed){
    this.weighted = weighted;
    this.directed = directed;
    graph = new HashMap<>();
  }

  /**
   * @return true if the graph is weighted, false otherwise
   */
  public boolean isWeighted(){
    return weighted;
  }

  /**
   * @return true if the graph is directed, false otherwise
   */
  public boolean isDirected(){
    return directed;
  }
 
  /**
   *
   * @return returns the vertex having the key in input if present
   * @param key the key of the vertex
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
   * @param the vertex key to be added
   */ 
  public void addVertex(T key) throws GraphException{
    if(!(graph.containsKey(key))){
      graph.put(key, new Vertex<>(key));
    }
  }

  /**
   *
   * adds the vertex to the graph
   * @param the vertex object to be added
   */ 
  public void addVertex(Vertex<T,E> vertex){
    if(!(graph.containsKey(vertex.key))){
      graph.put(vertex.key, vertex);
    }
  }


/**
   *
   * adds the edge (source,destination) to the non-weighted graph
   * @param source the source vertex
   * @param destination the destination vertex
   */
  public void addEdge(Vertex<T,E> source, Vertex<T,E> destination){
    addEdge(source, destination, null);
  }


  /**
   *
   * adds the edge (source,destination) to the weighted graph
   * @param source the source vertex
   * @param destination the destination vertex
   * @param weight the weight of the edge
   */
  public void addEdge(Vertex<T,E> source, Vertex<T,E> destination, E weight){
    try{
      addVertex(source.key);
      addVertex(destination.key);
    }catch(Exception e){}
      if(isDirected() == false){
        graph.get(source.key).adjVertices.put(destination.key, new Edge<T,E>(destination.key, weight)); 
        graph.get(destination.key).adjVertices.put(source.key, new Edge<T,E>(source.key, weight));
      }
      else{
        graph.get(source.key).adjVertices.put(destination.key,new Edge<T,E>(destination.key, weight));
      }
        nEdges++;
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
  * @return true if the node was successfully removed, false otherwise
  */  
  public boolean removeVertex(Vertex<T,E> vertex){
    if(graph.containsKey(vertex.key)){
      graph.remove(vertex.key);
      return true;
    }else{
      return false;
    }
  }

  public int getNEdges(){
    return nEdges;
  }

  /**
   * calculates the weight of the Graph
   * @return the weight of the graph
   */
  public float getWeight(){
    if(isWeighted()){
      float sum = 0;
        for(T key : graph.keySet()){
          HashMap<T, Edge<T,E>> edges = graph.get(key).adjVertices;
            for(T keyEdge: edges.keySet()){
              sum = sum + (edges.get(keyEdge).weight.floatValue());
            }
        }
      return sum;
    }else{
      return 0;
    }
  }

  /**
   * calculates the weight of the edge (u,v)
   * @param u the source vertex
   * @param v the destination vertex
   * @return the weight of the edge (u,v)
   */
  public E getEdgeWeight(Vertex<T,E> u, Vertex<T,E> v){
    return graph.get(u.key).adjVertices.get(v.key).weight;
  }

  /**
   * returns the previous vertex of u in the path
   * @param u the vertex
   * @return the previous vertex of u
   */
  private Vertex<T,E> getPrevious(Vertex <T,E> u){
    return u.previous;
  }
 
 /**
  * sets v as the previous vertex of u in the path
  * @param u the first vertex
  * @param v the new previous vertex
  */
  private void setPrevious(Vertex<T,E> u, Vertex<T,E> v){
    graph.get(u.key).previous = v; 
  }

  /**
   * returns the set of the adjacent Vertices of u
   * @param u the vertex
   * @return the set of adj vertices of u
   */
  private Set<T> getAdj(Vertex<T,E> u){
    HashMap<T,Edge<T,E>> adj = graph.get(u.key).adjVertices;
    return adj.keySet();
  }


  /**
   * calculates the number of vertices in the graph
   * @return the number of vertices
   */
  public int numberOfVertices(){
    return graph.size(); 
  }

  /**
   * creates an arrayList containing all the vertices
   * @return vertices as an arrayList
   */
  public ArrayList<Vertex<T,E>> getVertices(){
    ArrayList<Vertex<T,E>> arrayVertices = new ArrayList<>();
    for(T key : graph.keySet()){
      arrayVertices.add(graph.get(key)); 
    }
    return arrayVertices;
  }

  
  /**
   * create a priority queue containing all the vertices
   * @return the so populated priorityQueue
   */
  public PriorityQueue<T,E> createQueue(){
    Comparator<QueueNode<T,E>> comparator = initializeQueueComparator();
    PriorityQueue<T,E> q = new PriorityQueue<>(comparator);
    for(T key: graph.keySet()){
      try{
        q.enqueue(graph.get(key)); 
      }catch(PriorityQueueException e){System.out.println(e);}
    } 
    return q;
  }

  
/**
 * creates the minimum spanning tree using the prim algorithm
 * @param s the beginning vertex
 * @return the minimum spanning tree
 */
  public Graph<T,E> prim(Vertex<T,E> s){
    Graph<T,E> MST = new Graph<T,E>(true,true);
    PriorityQueue<T,E> q = createQueue();
    Comparator<E> comparator = initializeEComparator();
    try{
      q.decreaseKey(s.key, (E)(Float)(float)(0)); 
      while(!q.isEmpty()){
        Vertex<T,E> u = (Vertex<T,E>)q.dequeue();
        Vertex<T,E> uPr = getPrevious(u);
        if(uPr != null){
          MST.addEdge(uPr, u, getEdgeWeight(uPr, u));
        }
        Set<T> adjU = getAdj(u);
        for(T vKey : adjU){
          if(q.contains(vKey)){
            Vertex<T,E> v = getVertex(vKey);
            E weightUV = getEdgeWeight(u, v);
            E vDistance = v.value;
            if((comparator.compare(weightUV, vDistance)) <= 0){
              setPrevious(v, u);
              v.value = weightUV;
              q.decreaseKey(vKey, weightUV);
            }
          }
        }
      }
      return MST;
    }catch(PriorityQueueException |GraphException e){System.out.println(e);return null;}
  }

  /**
   * initializes a comparator for the PriorityQueue
   * @return the so initialized comparator
   */
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
 
 /**
  * initializes a comparator for the weight of the edges
  * @return the so initialized comparator
  */ 
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

}
