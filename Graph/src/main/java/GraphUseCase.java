

import java.io.*;
public class GraphUseCase{
   
      
  
 
    public static void populateGraph(Graph <String, Float> myGraph, BufferedReader file) throws Exception{
      String rowRead;
      while((rowRead = file.readLine())!= null){
        String[] parts = rowRead.split(",");
        myGraph.addEdge(new Vertex<String, Float>(parts[0]), new Vertex<String, Float>(parts[1]), Float.valueOf(parts[2])); 
      } 
    }

    public static void main(String[] args){
      Graph<String, Float> myGraph = new Graph<>();
      try{
        BufferedReader file = new BufferedReader(new FileReader("../resources/italian_dist_graph.csv"));
        populateGraph(myGraph, file);
        System.out.println("Weight: "+myGraph.getWeight());
        System.out.println("ARCHI: "+myGraph.n_archi);
        System.out.println("nVertices: "+myGraph.numberOfVertices());

        Graph<String,Float> mst = myGraph.prim(new Vertex<String, Float>("abadia a isola"));
        System.out.println("Weight: "+mst.getWeight());
        System.out.println("ARCHI: "+mst.n_archi);
        System.out.println("nVertices: "+mst.numberOfVertices());
      }catch(Exception e){
        System.out.println(e);
      }
    }
}
