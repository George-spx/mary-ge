import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.*;

public class TestGraph{
  
  @Test
  public void testCreateGraphOneNode() throws GraphException{
    Graph<String,Float> myGraph = new Graph<>();
    Vertex<String, Float> myVertex = new Vertex<String, Float>("Roma");

    myGraph.addVertex(myVertex);

    assertTrue(myGraph.graph.get("Roma").equals(myVertex));
  }

  @Test
  public void testCreateEdge() throws GraphException{
    Graph<String,Float> myGraph = new Graph<>();
    Edge<String, Float> myEdge = new Edge<>("Fiumicino", (float)200);

    myGraph.addVertex(new Vertex<String, Float>("Roma"));
    myGraph.addEdge(myGraph.graph.get("Roma"), new Vertex<String, Float>("Fiumicino"),(float) 456);

    assertTrue(myGraph.getVertex("Roma").adjVertices.contains(myEdge));
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testRemoveVertex() throws GraphException{
    Graph<String,Float> myGraph = new Graph<>();
    Vertex<String, Float> myVertex1 = new Vertex<String, Float>("Roma");
    Vertex<String, Float> myVertex2 = new Vertex<String, Float>("Milano");

    myGraph.addVertex(myVertex1);
    myGraph.addVertex(myVertex2);
    myGraph.removeVertex(myGraph.graph.get("Roma"));

    thrown.expect(GraphException.class);
    thrown.expectMessage("Vertex does not exist");
    myGraph.getVertex("Roma");
  }
  
  @Test
  public void testWeight(){
    Graph<String,Float> myGraph = new Graph<>();
    myGraph.addVertex(new Vertex<String, Float>("Roma"));
    myGraph.addEdge(myGraph.graph.get("Roma"), new Vertex<String, Float>("Milano"), (float)400);
    myGraph.addEdge(myGraph.graph.get("Roma"), new Vertex<String, Float>("Indigogo"), (float)2000);
    myGraph.addEdge(myGraph.graph.get("Roma"), new Vertex<String, Float>("31ndigoddw"), (float)3000);
    myGraph.addEdge(myGraph.graph.get("Milano"), new Vertex<String, Float>("Susa"), (float)200);
    myGraph.addEdge(myGraph.graph.get("Susa"), new Vertex<String, Float>("Romania"), (float)200);

    System.out.println(myGraph.getWeight());
    assertTrue(myGraph.getWeight() ==  (float)5800);
  }

  @Test
  public void testAddTwiceTheSameVertex(){
    Graph<String,Float> myGraph = new Graph<>();
    myGraph.addVertex(new Vertex<String, Float>("Roma"));
    myGraph.addVertex(new Vertex<String, Float>("Roma"));

    assertEquals(myGraph.numberOfVertices(), 1);
  }
}
