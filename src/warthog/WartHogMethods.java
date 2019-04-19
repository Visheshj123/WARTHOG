

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

    public double x1, y1, x2, y2, x3, y3;
    WartHogMethods(){
        Gradient gradient = new Gradient();
        this.x1 = gradient.blocksize/2; //x1, y1 should start at half the block size
        this.y1 = gradient.blocksize/2;
        this.x2 = 0.0;
        this.y2 = 0.0;
        this.x3 = 0.0;
        this.y3 = 0.0;
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

    public float drawVector(HashMap<Integer, Integer> HistoMap, int n){
        //implying a radius of 3

            //create nested loop that takes in width and height and starts each vector at the (8,8) of each block
           // this.x1 = 50.0;
            //this.y1 = 50.0;
            this.x2 = this.x1 + 3 * (Math.cos(Math.toRadians(n)));
            this.y2 = this.y1 - 3 * (Math.sin(Math.toRadians(n)));
            //TEST THIS
            this.x3 = this.x1 - 3 * (Math.cos(Math.toRadians(n)));
            this.y3 = this.y1 + 3 * (Math.sin(Math.toRadians(n)));
            return (float)HistoMap.get(n);

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

    public ArrayList<HashMap> createHistoList(HashMap<Integer, Integer> HistoMap, double [][] gArray, double [][] DirArray, int width, int height, int blocksize ){
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

        //16x16 block, need to find more efficent way to solve this (Think COMP ARCH email)
        for(int ii=0; ii<height; ii+=blocksize){
            for(int jj=0; jj<width; jj+=blocksize){
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
                for(int y =ii; y< ii+blocksize; y++){
                    for (int x=jj; x< jj +blocksize; x++){
                        HistoMap = convertToHashmap(gArray, DirArray, y, x, HistoMap);
                    }
                }
                Histolist.add(HistoMap);
            }
        }

        return Histolist;
    }






}

