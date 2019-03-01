package warthog;
import java.util.HashMap;

public class WartHogTest {
	
	public static void main(String[] args){
	testarray();
		/*public void testarray(){
			for (int y=0; y<205; y+=8){
				for(int x=0; x<240; x+=8){
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//second column
					x++;	
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//third column
					x++;
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//fourth column
					x++;
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//fifth column
					x++;
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//sixth column
					x++;
					HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//seventh column
					x++;
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					//eighth column
					x++;
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+1);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+2);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+3);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+4);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+5);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+6);
					//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
					System.out.println("Processing Data Point: " + x + "," + y+7);
					
					
				}
			}*/
		
		
		
	/*	HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>(); //cannot use primitive int cause HashMap constraints
		HistoMap.put(0,0);
		HistoMap.put(20,10);
		HistoMap.put(40,0);
		HistoMap.put(60,0);
		HistoMap.put(80,0);
		HistoMap.put(100,0);
		HistoMap.put(120,0);
		HistoMap.put(140,0);
		HistoMap.put(160,0);
		
		HistoMap.put(20, HistoMap.get(20) + 30);
		Integer a = HistoMap.get(20);
		System.out.println("The value of bucket 20 should be 40: " + a);
		Histogram(HistoMap);
		
	}
	public static void Histogram(HashMap HistoMap){
		int array[] = {0,20,40,60,80,100,120,140,160};
		for (int range=0; range<array.length; range++){
			int label = array[range];
			//int num = arra;
			System.out.println(label + ": " + printStars((Integer) HistoMap.get(array[range])));
			
		}
	}
	
	public static String printStars(Integer NumStars){
		StringBuilder builder = new StringBuilder();
		for (int j=0; j< NumStars; j++){
			builder.append('*');
		}
		
		return builder.toString();
	}*/
	}

	private static void testarray() {
		//int yval = 0; 
		//int xval = 0;
		for (int y=0; y<24; y+=8){
			System.out.print("NEXT Y INTERATION");
			for(int x=0; x<24; x+=8){
				
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+1);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+2);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+3);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+4);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+5);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+6);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
				System.out.println("Processing Data Point: " + x + "," + y+7);
				
				//second column
				//x++;	
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+1) + "," + (y+7));
				
				//third column
				//xval++;
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+2) + "," + (y+7));
				
				//fourth column
				//xval++;
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+3) + "," + (y+7));
				
				//fifth column
				//xval++;
				System.out.println("Processing Data Point: " + (x+4) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+4) + "," + (y+7));
				
				//sixth column
				//xval++;
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+5) + "," + (y+7));
				
				//seventh column
				//xval++;
				System.out.println("Processing Data Point: " + (x+6) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+6) + "," + (y+7));
				
				//eighth column
			//	xval++;
				System.out.println("Processing Data Point: " + (x+7) + "," + y);
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+1, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+1));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+2, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+2));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+3, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+3));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+4, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+4));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+5, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+5));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+6, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+6));
				//HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, yval+7, x, HistoMap);
				System.out.println("Processing Data Point: " + (x+7) + "," + (y+7));
				
				System.out.print("NEXT X INTERATION");
				
				
			}
		}
		// TODO Auto-generated method stub
		
	}
	
}
