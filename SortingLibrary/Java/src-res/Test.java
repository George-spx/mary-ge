import java.util.Comparator;
import java.io.*;
import java.util.ArrayList;
public class Test{

	/**
	 * executes the test over the class InsertionSort
	 */
	public void executeOnInteger() throws IOException{
			Comparator<Long> comparator = new Comparator<Long> (){
				@Override
				public int compare(Long i1, Long i2){
					long res = i1 - i2;
					if(res > 0){
						return 1;
					}else if(res == 0){
						return 0;
					}else return -1;
				}
			};
			InsertionSort<Long> sortMyArray = new InsertionSort<Long>(comparator);
			
		try{
			BufferedReader readFromFile = new BufferedReader(new FileReader("integers.csv"));		
						String s;	
			while((s = readFromFile.readLine()) !=  null){
				long x = Long.parseLong(s);
				try{
					sortMyArray.add(x);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		System.out.println("ORDINATO");
		System.out.println("Result: "+isArrayOrdered(sortMyArray.array, comparator));
	}

	/**
	 * returns true if the array is ordered, false otherwise
	 * @param array the array to be checked
	 * @param comparator the comparator used to check
	 */
	public boolean isArrayOrdered(ArrayList <Long> array, Comparator <Long> comparator){
		for(int i = 0; i < array.size() -1; i++){
			if((comparator.compare(array.get(i), array.get(i+1))) > 0){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		try{
			new Test().executeOnInteger();	
		}catch(IOException e){
			System.out.println(e.getMessage());	
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("Duration: "+duration);

	}
}
