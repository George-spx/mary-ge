import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author George
 */

public class TestEditDistance{

 @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testNullStringDistance() throws EditDistanceException{
    String testString = "Casa";
        
    thrown.expect(EditDistanceException.class);
    thrown.expectMessage("Strings cannot be null");
    
    EditDistance.iterativeStringDistance(null, testString);
  } 

  @Test
  public void testEditDistanceZero() throws EditDistanceException{
    String testString = "Casa";
   
    assertEquals(0, EditDistance.iterativeStringDistance(testString, testString)); 
  }

  @Test
  public void testCaseInsensitive() throws EditDistanceException{
    String testStringOne = "TeSTInG";
    String testStringTwo = "TESTing";
   
    assertEquals(0, EditDistance.iterativeStringDistance(testStringOne, testStringTwo)); 

  }

  @Test
  public void testOneCancellation()throws EditDistanceException{

    String testStringOne = "TEAST";
    String testStringTwo = "TEASTI";
   
    assertEquals(1, EditDistance.iterativeStringDistance(testStringOne, testStringTwo)); 

  }

  @Test
  public void testOneInsertion()throws EditDistanceException{

    String testStringOne = "TEAST";
    String testStringTwo = "TEAS";
   
    assertEquals(1, EditDistance.iterativeStringDistance(testStringOne, testStringTwo)); 

  }

  @Test
  public void testOneSwap()throws EditDistanceException{

    String testStringOne = "TEAS";
    String testStringTwo = "TEBS";
   
    assertEquals(2, EditDistance.iterativeStringDistance(testStringOne, testStringTwo)); 

  }
  @Test
  public void testGeneral()throws EditDistanceException{

    String testStringOne = "testStringWord";
    String testStringTwo = "testWordString";
   
    assertEquals(8, EditDistance.iterativeStringDistance(testStringOne, testStringTwo)); 

  }
}
