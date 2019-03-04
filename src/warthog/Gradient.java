import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Gradient {

    public static final String IMG = "/Users/visheshj/Desktop/rsz_hufflepuff.jpg";

    public static void main(String[] args){

        WartHogMethods wartfunctions = new WartHogMethods();
        BufferedImage img = null; //allows import of image with each pixel having coordinate point, starting with (0,0) at top left
        File f = null;
        try {
            f = new File("/Users/visheshj/Desktop/rsz_hufflepuff.jpg");
            img = ImageIO.read(f);

            //will store every pixels rgb value in 3 columns, this is [y-axis][x-axis]
            int[][] pixelData = new int [img.getHeight()][img.getWidth()];
            int rgb_avg;
            int counter = 0;
            double [][] gArray = new double [img.getHeight()][img.getWidth()];
            double[][] DirArray = new double[img.getHeight()][img.getWidth()];

            System.out.println("Height of image is " + img.getHeight());
            System.out.println("Width of image is " + img.getWidth());

            BufferedImage greyScale = wartfunctions.getPixelData(img, pixelData);
            //System.out.print("The intensity value at 28,39 is " + pixelData[39][28]);


            //calculates gradient and magnitude array, normalizes angles to (0,180)
            wartfunctions.matrixMultiply(pixelData, img, gArray, DirArray);


			/*HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>();

			HistoMap.put(0,0);
			HistoMap.put(20,0);
			HistoMap.put(40,0);
			HistoMap.put(60,0);
			HistoMap.put(80,0);
			HistoMap.put(100,0);
			HistoMap.put(120,0);
			HistoMap.put(140,0);
			HistoMap.put(160,0);

			HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, 39, 28, HistoMap);
			System.out.println("The value at 60 is: " + HistoMap.get(60));
			System.out.println("The value at 80 is: " + HistoMap.get(80));

			*/

            HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>();
            int count = 1;

            ArrayList<HashMap> Histolist = new ArrayList<HashMap>();

            for (int y =0; y< 16; y+=8){
                for (int x=8; x< 16; x+=8){
                    //creates new instance of HashMap after every iteration

                    HistoMap = new HashMap<Integer, Integer>();
                    HistoMap.put(0,0);
                    HistoMap.put(20,0);
                    HistoMap.put(40,0);
                    HistoMap.put(60,0);
                    HistoMap.put(80,0);
                    HistoMap.put(100,0);
                    HistoMap.put(120,0);
                    HistoMap.put(140,0);
                    HistoMap.put(160,0);

                    //first 8-column
                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
                    System.out.println("Processing Data Point: " + x + "," + (y+7));

                    //second column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x+1, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x+1, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x+1, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x+1, HistoMap);
                    System.out.println("Processing Data Point: " + (x+1) + "," + (y+7));

                    //third column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x+2, HistoMap);
                    System.out.println("Processing Data Point: " + (x+2) + "," + (y+7));

                    //fourth column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, (x+3), HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x+3, HistoMap);
                    System.out.println("Processing Data Point: " + (x+3) + "," + (y+7));

                    //fifth column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x+4, HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, (x+4), HistoMap);
                    System.out.println("Processing Data Point: " + (x+4) + "," + (y+7));

                    //sixth column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x+5, HistoMap);
                    System.out.println("Processing Data Point: " + (x+5) + "," + (y+7));

                    //seventh column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x+6, HistoMap);
                    System.out.println("Processing Data Point: " + (x+6) + "," + (y+7));

                    //eighth column

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + y);

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+1, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+1));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+2, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+2));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+3, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+3));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+4, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+4));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+5, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+5));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+6, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+6));

                    HistoMap = wartfunctions.convertToHashmap(gArray, DirArray, y+7, x+7, HistoMap);
                    System.out.println("Processing Data Point: " + (x+7) + "," + (y+7));

                    Histolist.add(HistoMap);
                    System.out.print("Added: " + count + "instance of HistoMap to Histolist" + "at data point");
                    count++;

                }
            }
            //wartfunctions.printStars(Histolist.get(0));

            //System.out.print(Histolist.get(0).values());



        }catch (IOException e){
            e.printStackTrace();
        }

    }

}

