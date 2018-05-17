import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.*;

public class TestGraph{
  
  @Test
  public void testCreateGraphOneNode(){
    Graph<String,Float> myGraph = new Graph<>();
    myGraph.addNode(new Vertex<String>("Roma"), null);
    myGraph.printNodes(); 
  }

  @Test
  public void testCreateAdj(){
    Graph<String,Float> myGraph = new Graph<>();
    ArrayList<Edge<String, Float>> adj = new ArrayList<Edge<String,Float>>();
    adj.add(new Edge<String,Float>("Fiumicino",(float)2.8565));
    adj.add(new Edge<String,Float>("Fiume",(float)443.8565));
    adj.add(new Edge<String,Float>("Bernini",(float)2124.8565));
    myGraph.addNode(new Vertex<String>("Roma"), adj);
    myGraph.printNodes(); 
  }

  @Test
  public void testAddTwoNodes(){
    Graph<String,Float> myGraph = new Graph<>();
    ArrayList<Edge<String, Float>> adj = new ArrayList<Edge<String,Float>>();
    adj.add(new Edge<String,Float>("Fiumicino",(float)2.8565));
    adj.add(new Edge<String,Float>("Fiume",(float)443.8565));
    adj.add(new Edge<String,Float>("Bernini",(float)2124.8565));
    myGraph.addNode(new Vertex<String>("Roma"), adj);
    myGraph.addNode(new Vertex<String>("Roma2"), adj);
    myGraph.printNodes(); 
  }

  @Test
  public void testRemoveNode(){
    Graph<String,Float> myGraph = new Graph<>();
    ArrayList<Edge<String, Float>> adj = new ArrayList<Edge<String,Float>>();
    adj.add(new Edge<String,Float>("Fiumicino",(float)2.8565));
    adj.add(new Edge<String,Float>("Fiume",(float)443.8565));
    myGraph.addNode(new Vertex<String>("Roma"), adj);
    myGraph.printNodes(); 
    myGraph.removeNode(new Vertex<String>("Roma"));
    myGraph.printNodes(); 
  }



}
