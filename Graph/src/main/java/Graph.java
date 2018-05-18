import java.util.*;

public class Graph<T,E extends Number>{
  HashMap<T, Vertex<T>> vertices;
  HashMap<T, ArrayList<Edge<T,E>>> graph;

  /**
   * instantiates the HashMap graph
   *
   */ 
  public Graph(){
    vertices = new HashMap<>();
    graph = new HashMap<>();
  }

  /**
   *
   * adds the node to the graph
   * @param key the key of the node to be added
   * @param adj the list of adjacent nodes
   *
   */ 
  public void addVertex(Vertex<T> vertex){
    if(!(graph.containsKey(vertex.key))){
      graph.put(vertex.key, null);
      if(!(vertices.containsKey(vertex.key))){
        vertices.put(vertex.key, vertex);
      }
    }
  }

  public void addIfNotContained(Vertex<T> source, Vertex<T> destination){
    if(!(vertices.containsKey(source.key))){
      vertices.put(source.key, source);
    }
    if(!(vertices.containsKey(destination.key))){
      vertices.put(destination.key, destination);
    } 
    if(!(graph.containsKey(source.key))){
      graph.put(source.key, null);
    }
    if(!(graph.containsKey(destination.key))){
      graph.put(destination.key, null);
    }
  }

  public void addEdge(Vertex<T> source, Vertex<T> destination, E weight){
    addIfNotContained(source,destination);
    graph.get(source.key).add(new Edge<T,E>(destination.key, weight)); 
  }
 
 /**
  *
  * removes the node with key in input from the graph
  * @param key the key of the node to be removed
  * @return true if the node was successfully removed
  */  
  public boolean removeNode(Vertex<T> vertex){
    if(graph.containsKey(vertex.key)){
      graph.remove(vertex.key);
      vertices.remove(vertex.key);
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
      ArrayList<Edge<T,E>> adjEl = graph.get(key);
      if(adjEl!=null){
        for(Edge<T,E> node: adjEl){
          sum = sum + node.value.floatValue();
        }
      }
    }
    return sum;
  }

  public int numberOfVertexes(){
    return vertices.size(); 
  }


  /**
   * returns all the vertices as an arrayList
   */
  public ArrayList<Vertex<T>> getVertices(){
    ArrayList<Vertex<T>> arrayVertices = new ArrayList<>();
    for(T key : vertices.keySet()){
      arrayVertices.add(vertices.get(key)); 
    }
    return arrayVertices;
  }

  /**
   *
   * just a test method that will be removed
   */ 
  public void printNodes(){
    for(T key: vertices.keySet()){
      ArrayList<Edge<T,E>> adjEl = graph.get(key);
      if(adjEl!=null){
        for(Edge<T,E> node: adjEl){
          System.out.println(node.key+", "+node.value);
        }
      }
    } 
  }

  public void primAlgorithm(){
  
  }

  

}
