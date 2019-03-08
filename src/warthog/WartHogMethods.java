import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.geom.*;




public class WartHogMethods {
    public double x1, y1, x2, y2;
    WartHogMethods(){
        this.x1 = 0.0;
        this.y1 = 0.0;
        this.x2 = 0.0;
        this.y2 = 0.0;
    }





    //calculates intensity values, input is image and coordinates
    public BufferedImage getPixelData(BufferedImage img, int[][] pixelData) {
        for (int y=0; y<img.getHeight(); y++){
            for (int x=0; x< img.getWidth(); x++){

                int argb = img.getRGB(x, y); //have to use greyscale values NOT rgb
                int avg = 0;


                int rgb[] = new int[] {
                        (argb >> 16) & 0xff, //red
                        (argb >> 8) & 0xff, //green
                        (argb) & 0xff //blue
                };

                avg = (rgb[0] + rgb[1] + rgb[2])/3; //gets us intensity values
                //System.out.print("the value at " + x + "," + y + "is" + avg + " ");
                pixelData[y][x] = avg;

                argb = (avg<<16) | (avg<<8) | avg;
                img.setRGB(x, y, argb); //writes intensity value into a new RGB value of img
                //System.out.println("rgb: " + rgb[0] + " " + rgb[1] + " " + rgb[2]);


            }


        }

        try{
            File f = new File("/Users/visheshj/Desktop/output.jpg");
            ImageIO.write(img, "jpg", f);
        }catch(IOException e){
            System.out.print(e);
        }
        return img;
    }

    //calculates gradient and magnitude array, normalizes angles to (0,180)
    public int matrixMultiply(int[][] fileImage, BufferedImage img, double[][] gArray, double[][] DirArray){

        int pixVal;
        double [][]HorizontalgVal = new double [img.getHeight()][img.getWidth()];
        double [][]VerticalgVal = new double [img.getHeight()][img.getWidth()];
        // double [][] gArray = new double [img.getHeight()][img.getWidth()];
        //double[][] DirArray = new double[img.getHeight()][img.getWidth()];
        //actual multiplication logic

        //nestedloop that ignores the edges
        for(int y=1; y<img.getHeight()-1; y++){
            for(int x=1; x<img.getWidth()-1; x++){
                //places horizontal gradient result into new 2D array
                HorizontalgVal[y][x] = (fileImage[y][x+1] *1) + (fileImage[y][x-1] *-1);
                VerticalgVal[y][x] = (fileImage[y+1][x] * -1) + (fileImage[y-1][x] * 1);

                //Magnitude Array and Direction Array
                gArray[y][x] = Math.sqrt(Math.pow(HorizontalgVal[y][x], 2.0) + Math.pow(VerticalgVal[y][x], 2.0));
                DirArray[y][x] = (int)normalizeAngle(Math.toDegrees(Math.atan(VerticalgVal[y][x]/HorizontalgVal[y][x])));

            }
        }
        // System.out.print("the value at (28,39) is: " +DirArray[39][28]);
        return 0;
    }

    //converts angle range to (0,180) and returns double value into DirArray
    public double normalizeAngle(double angle){
        double newAngle = angle;
        while(newAngle <= -180){
            newAngle += 360;
        }
        while(newAngle > 180){
            newAngle -= 360;
        }

        Math.abs(newAngle);
        return newAngle;
    }

    public HashMap<Integer, Integer> convertToHashmap(double[][] gArray, double[][] DirArray, int y, int x, HashMap<Integer, Integer> HistoMap){

        //initialize HashMap
        //HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>(); //cannot use primitive int cause HashMap constraints


        //place 8x8 cells into histogram


        //create logic for angles in-between values
        double magnitude;
        double direction;
        double counter = 0;
        double temp;
        int bucketVal;
        double lowerBucketVal = 0;
        double upperBucketVal = 0;
        //temp = (int)direction;
        //need create histogram in 8x8chunc

        magnitude = gArray[y][x];
        direction = DirArray[y][x];
        temp = Math.abs(direction);
        //System.out.println("The magnitude at (28,39) is " + magnitude + "direction is: " + direction + "temp is: " + temp);

        //if direction is <10
        if(temp > 0 && temp < 20){ //Direction was >0 and <20

            lowerBucketVal= (1-((temp - 0)/20)) * magnitude;
            upperBucketVal = (1-((20 - temp)/20)) * magnitude;
            HistoMap.put(0, HistoMap.get(0) + (int)lowerBucketVal);
            HistoMap.put(20, HistoMap.get(20) + (int)upperBucketVal);
        }

        if (temp == 20){ //Direction was >20 and <40
            HistoMap.put(20, HistoMap.get(20) + (int)magnitude);
        }

        if (temp > 20 && temp < 40){
            lowerBucketVal= (1-((temp - 20)/20)) * magnitude;
            upperBucketVal = (1-((40 - temp)/20)) * magnitude;
            HistoMap.put(20, HistoMap.get(20) + (int)lowerBucketVal);
            HistoMap.put(40, HistoMap.get(40) + (int)upperBucketVal);
        }

        if (temp==40){ //Direction was >40 and <60
            HistoMap.put(40, HistoMap.get(40) + (int)magnitude);
        }

        if (temp > 40 && temp < 60){
            lowerBucketVal= (1-((temp - 40)/20)) * magnitude;  //goes into lower bucket
            upperBucketVal = (1-((60 - temp)/20)) * magnitude;
            HistoMap.put(40, HistoMap.get(40) + (int)lowerBucketVal);
            HistoMap.put(60, HistoMap.get(60) + (int)upperBucketVal);
        }
        if (temp == 60){
            HistoMap.put(60, HistoMap.get(60) + (int)magnitude);


        }
        if (temp > 60 && temp < 80){
            lowerBucketVal= (1-(temp - 60.0)/20.0) * magnitude; //goes into lower bucket
            upperBucketVal = (1-((80 - temp)/20)) * magnitude;
            //System.out.println("upperbucketvalue is : " + upperBucketVal);
            HistoMap.put(60, HistoMap.get(60) + (int)lowerBucketVal);
            HistoMap.put(80, HistoMap.get(80) + (int)upperBucketVal);
        }
        if (temp == 80){
            HistoMap.put(80, HistoMap.get(80) + (int)magnitude);
        }

        if (temp > 80 && temp < 100){
            lowerBucketVal= (1-((temp - 80)/20)) * magnitude;  //goes into lower bucket
            upperBucketVal = (1-((100 - temp)/20)) * magnitude;
            HistoMap.put(80, HistoMap.get(80) + (int)lowerBucketVal);
            HistoMap.put(100, HistoMap.get(100) + (int)upperBucketVal);
        }
        if (temp == 100){
            HistoMap.put(100, HistoMap.get(100) + (int)magnitude);

        }

        if(temp > 100 && temp < 120){
            lowerBucketVal= (1-((temp - 100)/20)) * magnitude;  //goes into lower bucket
            upperBucketVal = (1-((120 - temp)/20)) * magnitude;
            HistoMap.put(100, HistoMap.get(100) + (int)lowerBucketVal);
            HistoMap.put(120, HistoMap.get(120) + (int)upperBucketVal);
        }
        if (temp == 120){
            HistoMap.put(120, HistoMap.get(120) + (int)magnitude);
        }

        if (temp > 120 && temp < 140){
            lowerBucketVal= (1-((temp - 120)/20)) * magnitude;  //goes into lower bucket
            upperBucketVal = (1-((140 - temp)/20)) * magnitude;
            HistoMap.put(120, HistoMap.get(120) + (int)lowerBucketVal);
            HistoMap.put(140, HistoMap.get(140) + (int)upperBucketVal);
        }
        if (temp == 140){
            HistoMap.put(140, HistoMap.get(140) + (int)magnitude);
        }

        if (temp > 140 && temp < 160){
            lowerBucketVal= (1-((temp - 140)/20)) * magnitude;  //goes into lower bucket
            upperBucketVal = (1-((160 - temp)/20)) * magnitude;
            HistoMap.put(140, HistoMap.get(140) + (int)lowerBucketVal);
            HistoMap.put(160, HistoMap.get(160) + (int)upperBucketVal);
        }
        if (temp == 160){
            HistoMap.put(160, HistoMap.get(160) + (int)magnitude);
        }
        if (temp > 160 && temp < 180){
            lowerBucketVal= (1-((temp - 160)/20)) * magnitude;  //goes into lower bucket
            upperBucketVal = (1-((180 - temp)/20)) * magnitude;
            HistoMap.put(160, HistoMap.get(160) + (int)lowerBucketVal);
            HistoMap.put(0, HistoMap.get(0) + (int)upperBucketVal);
        }

        return HistoMap;
    }

    	public void resetHashMap(HashMap <Integer, Integer> HistoMap){
            HistoMap.clear();
            HistoMap.put(0,0);
            HistoMap.put(20,0);
            HistoMap.put(40,0);
            HistoMap.put(60,0);
            HistoMap.put(80,0);
            HistoMap.put(100,0);
            HistoMap.put(120,0);
            HistoMap.put(140,0);
            HistoMap.put(160,0);
        }

        /*public void printHashMap(HashMap <Integer, Integer> HistoMap){
            System.out.println("0" + ": " + printStars((Integer) HistoMap.get(0)));
            System.out.println("20" + ": " + printStars((Integer) HistoMap.get(20)));
            System.out.println("40" + ": " + printStars((Integer) HistoMap.get(40)));
            System.out.println("60" + ": " + printStars((Integer) HistoMap.get(60)));
            System.out.println("80" + ": " + printStars((Integer) HistoMap.get(80)));
            System.out.println("100" + ": " + printStars((Integer) HistoMap.get(100)));
            System.out.println("120" + ": " + printStars((Integer) HistoMap.get(120)));
            System.out.println("140" + ": " + printStars((Integer) HistoMap.get(140)));
            System.out.println("160" + ": " + printStars((Integer) HistoMap.get(160)));


        }*/

    public void drawVector(HashMap<Integer, Integer> HistoMap, int n){
        //implying a radius of 3

            this.x1 = 160.0;
            this.y1 = 100.0;
            this.x2 = 160.0 + 3 * (Math.cos(Math.toRadians(n)));
            this.y2 = 100.0 - 3 * (Math.sin(Math.toRadians(n)));

    }

    public void printStars(HashMap<Integer, Integer> HistoMap){
        StringBuilder builder = new StringBuilder();
        int n=0;
        System.out.println("Value of 20 is: " + (HistoMap.get(20)));
        for (int i=0; i<9; i++) {

            int NumStar = HistoMap.get(n); //amount in each bin
            builder = new StringBuilder();



            //should be only drawing one line per bin, does NOT account for value of each bin

             System.out.print(n + ": ");
             n += 20; //holds bin value aka degree value
            for (int j = 0; j < NumStar; j++) {
                builder.append('*');


            }

            System.out.println(builder.toString());
        }

    }

    public ArrayList<HashMap> createHistoList(HashMap<Integer, Integer> HistoMap, double [][] gArray, double [][] DirArray ){
        ArrayList<HashMap> Histolist = new ArrayList<HashMap>();
        int count = 1;
        HistoMap.put(0,0);
        HistoMap.put(20,0);
        HistoMap.put(40,0);
        HistoMap.put(60,0);
        HistoMap.put(80,0);
        HistoMap.put(100,0);
        HistoMap.put(120,0);
        HistoMap.put(140,0);
        HistoMap.put(160,0);

        for (int y =0; y< 128; y+=8){
            for (int x=0; x< 128; x+=8){
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
                HistoMap = convertToHashmap(gArray, DirArray, y, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x, HistoMap);
                //System.out.println("Processing Data Point: " + x + "," + (y+7));

                //second column

                HistoMap = convertToHashmap(gArray, DirArray, y, x+1, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x+1, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x+1, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x+1, HistoMap);
                //System.out.println("Processing Data Point: " + (x+1) + "," + (y+7));

                //third column

                HistoMap = convertToHashmap(gArray, DirArray, y, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x+2, HistoMap);
                //System.out.println("Processing Data Point: " + (x+2) + "," + (y+7));

                //fourth column

                HistoMap = convertToHashmap(gArray, DirArray, y, (x+3), HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x+3, HistoMap);
                //System.out.println("Processing Data Point: " + (x+3) + "," + (y+7));

                //fifth column

                HistoMap = convertToHashmap(gArray, DirArray, y, (x+4), HistoMap);
                //System.out.println("Processing Data Point: " + (x+4) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, (x+4), HistoMap);
                //System.out.println("Processing Data Point: " + (x+4) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, (x+4), HistoMap);
                //System.out.println("Processing Data Point: " + (x+4) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x+4, HistoMap);
                // System.out.println("Processing Data Point: " + (x+4) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, (x+4), HistoMap);
                //System.out.println("Processing Data Point: " + (x+4) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, (x+4), HistoMap);
                //System.out.println("Processing Data Point: " + (x+4) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, (x+4), HistoMap);
                //System.out.println("Processing Data Point: " + (x+4) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, (x+4), HistoMap);
                // System.out.println("Processing Data Point: " + (x+4) + "," + (y+7));

                //sixth column

                HistoMap = convertToHashmap(gArray, DirArray, y, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x+5, HistoMap);
                //System.out.println("Processing Data Point: " + (x+5) + "," + (y+7));

                //seventh column

                HistoMap = convertToHashmap(gArray, DirArray, y, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x+6, HistoMap);
                //System.out.println("Processing Data Point: " + (x+6) + "," + (y+7));

                //eighth column

                HistoMap = convertToHashmap(gArray, DirArray, y, x+7, HistoMap);
                //System.out.println("Processing Data Point: " + (x+7) + "," + y);

                HistoMap = convertToHashmap(gArray, DirArray, y+1, x+7, HistoMap);
                //System.out.println("Processing Data Point: " + (x+7) + "," + (y+1));

                HistoMap = convertToHashmap(gArray, DirArray, y+2, x+7, HistoMap);
                //System.out.println("Processing Data Point: " + (x+7) + "," + (y+2));

                HistoMap = convertToHashmap(gArray, DirArray, y+3, x+7, HistoMap);
                //System.out.println("Processing Data Point: " + (x+7) + "," + (y+3));

                HistoMap = convertToHashmap(gArray, DirArray, y+4, x+7, HistoMap);
                //System.out.println("Processing Data Point: " + (x+7) + "," + (y+4));

                HistoMap = convertToHashmap(gArray, DirArray, y+5, x+7, HistoMap);
                // System.out.println("Processing Data Point: " + (x+7) + "," + (y+5));

                HistoMap = convertToHashmap(gArray, DirArray, y+6, x+7, HistoMap);
                // System.out.println("Processing Data Point: " + (x+7) + "," + (y+6));

                HistoMap = convertToHashmap(gArray, DirArray, y+7, x+7, HistoMap);
                // System.out.println("Processing Data Point: " + (x+7) + "," + (y+7));

                Histolist.add(HistoMap);
                System.out.print("Added: " + count + "instance of HistoMap to Histolist" + "at data point");
                count++;

            }
        }
        return Histolist;
    }






}


