import java.util.*;

public class Graph<T,E>{
  HashMap<Vertex<T>, ArrayList<Edge<T,E>>> graph;

  /**
   * instantiates the HashMap graph
   *
   */ 
  public Graph(){
    graph = new HashMap<Vertex<T>, ArrayList<Edge<T,E>>>();
  }

  /**
   *
   * adds the node to the graph
   * @param key the key of the node to be added
   * @param adj the list of adjacent nodes
   *
   */ 
  public void addNode(Vertex<T> vertex, ArrayList<Edge<T,E>> adj){
    graph.put(vertex, adj);
  }
 
 /**
  *
  * removes the node with key in input from the graph
  * @param key the key of the node to be removed
  * @return true if the node was successfully removed
  */  
  public boolean removeNode(Vertex<T> vertex){
    if(graph.containsKey(vertex)){
      graph.remove(vertex);
      return true;
    }else{
      return false;
    }
  }


  public E DFSWeight(Vertex<T> vertex){
    E weight = null;
    vertex.color = 1; //gray color- discovered node
    ArrayList<Edge<T,E>> adjV = graph.get(vertex);
    for(Edge<T,E> adj : adjV){
      if(adj.color == 0){
        //weigth = weigth + DFSWeight(adj);
      }
    }
    vertex.color = 2;
    return null;
  }

  /**
   * calculates the weight of the Graph
   * @return the weight of the graph
   */
  public void getWeight(){
    for(Vertex<T> vertex: graph.keySet()){
      if(vertex.color == 0 ){
        DFSWeight(vertex); 
      }
    }
  }

  public int numberOfVertexes(){
    return graph.size(); 
  }

  /**
   *
   * just a test method that will be removed
   */ 
  public void printNodes(){
    for(Vertex<T> vertex:graph.keySet()){
      System.out.println("key: "+vertex.key);
      ArrayList<Edge<T,E>> adjEl = graph.get(vertex);
      if(adjEl!=null){
        System.out.println("Adj-key");
        for(Edge<T,E> node: adjEl){
          System.out.println(node.key+", "+node.value);
        }
        System.out.println("_____");
      }
    } 
  }

  

}
