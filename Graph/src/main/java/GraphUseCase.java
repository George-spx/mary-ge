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
      Graph<String, Float> myGraph = new Graph<>(true, false);
      try{
        BufferedReader file = new BufferedReader(new FileReader("../resources/italian_dist_graph.csv"));
        populateGraph(myGraph, file);
        System.out.println("Weight of the graph (km): "+myGraph.getWeight()/1000);
        System.out.println("Number of edges: "+myGraph.getNEdges());
        System.out.println("Number of Vertices: "+myGraph.numberOfVertices());
        System.out.println("_____");
        Graph<String,Float> mst = myGraph.prim(new Vertex<String, Float>("abadia a isola"));
        System.out.println("Weight of the MST (km): "+mst.getWeight()/1000);
        System.out.println("Edges of the MST: "+mst.getNEdges());
        System.out.println("Number of vertices of the MST: "+mst.numberOfVertices());
      }catch(Exception e){
        System.out.println(e);
      }
    }
}
