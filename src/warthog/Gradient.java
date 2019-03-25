
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


public class Gradient {
    //used by buttons to get next histogram or next bin value
    int n = 0;
    int hashnum = 0;
    ArrayList<HashMap> Histolist = new ArrayList<HashMap>();
    BufferedImage img = null; //allows import of image with each pixel having coordinate point, starting with (0,0) at top left
    int blocksize = 16;
    double [][] gArray;
    double [][]DirArray;


    public static final String IMG = "/Users/visheshj/Desktop/rsz_hufflepuff.jpg";

    public static void main(String[] args){

        Gradient gradient = new Gradient();
        System.out.println(gradient.blocksize);

       WartHogMethods wartfunctions = new WartHogMethods();

       // BufferedImage img = null; //allows import of image with each pixel having coordinate point, starting with (0,0) at top left
        File f = null;
        try {
            f = new File("/Users/visheshj/Desktop/rsz_hufflepuff.jpg");
            gradient.img = ImageIO.read(f);


            //will store every pixels rgb value in 3 columns, this is [y-axis][x-axis]
            int[][] pixelData = new int [gradient.img.getHeight()][gradient.img.getWidth()];
            int rgb_avg;
            int counter = 0;
            gradient.gArray = new double [gradient.img.getHeight()][gradient.img.getWidth()];
            gradient.DirArray = new double[gradient.img.getHeight()][gradient.img.getWidth()];

            System.out.println("Height of image is " + gradient.img.getHeight());
            System.out.println("Width of image is " + gradient.img.getWidth());


            //creates greyscale to capture intensity values, populates pixelData array
            BufferedImage greyScale = wartfunctions.getPixelData(gradient.img, pixelData);



            //calculates gradient and magnitude array, normalizes angles to (0,180)
            wartfunctions.matrixMultiply(pixelData, gradient.img, gradient.gArray, gradient.DirArray);

           // System.out.print(Histolist.get(0).values());



        }catch (IOException e){
            e.printStackTrace();
        }

//imported Jframe

        JFrame ButtonFrame = new JFrame();
        JFrame ImageFrame = new JFrame();

        JTextField blockSize = new JTextField();
        blockSize.setBounds(200, 200, 50, 10);

        JButton blockEnter = new JButton("Enter");
        blockEnter.setBounds(200, 400, 50, 50);


        ButtonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        final LinesComponent comp = new LinesComponent();
        final LinesComponent imagecomp = new LinesComponent();

        comp.setPreferredSize(new Dimension(gradient.img.getWidth(), gradient.img.getHeight()));
        imagecomp.setPreferredSize(new Dimension(gradient.img.getWidth(), gradient.img.getHeight()));

        ButtonFrame.getContentPane().add(comp, BorderLayout.CENTER);
        ImageFrame.getContentPane().add(imagecomp, BorderLayout.CENTER);


        JPanel buttonsPanel = new JPanel();
        JPanel imagePanel = new JPanel();


        JButton newLineButton = new JButton("New Line");
        JButton resetButton = new JButton("Reset");
        JButton NextButton = new JButton("Next Histogram");
        JButton clearButton = new JButton("Clear");

        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(NextButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(blockEnter);

        ButtonFrame.add(blockSize, BorderLayout.CENTER);


        ButtonFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        newLineButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                 float stroke;
               // float stroke = 0.003F;
                //run function to get next vector values from a specific histogram
                if (gradient.n < 180) {
                    stroke = (float) ((wartfunctions.drawVector(gradient.Histolist.get(gradient.hashnum), gradient.n))/1000);

                    //determines the x1,x2,y1,y2 coordinates
                    wartfunctions.drawVector(gradient.Histolist.get(gradient.hashnum), gradient.n);

                    double x1 = wartfunctions.x1;
                    double y1 = wartfunctions.y1;
                    double x2 = wartfunctions.x2;
                    double y2 = wartfunctions.y2;
                    //Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                    //Integer stroke = gradient.Histolist.get(gradient.hashnum).get(gradient.n);
                    imagecomp.addLine(x1, y1, x2, y2, Color.red, stroke);
                    gradient.n += 20;
                }
                else {
                    double x1 = 0.0;
                    double y1 = 0.0;
                    double x2 = 0.0;
                    double y2 = 0.0;
                    stroke = 0.0F;
                    Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                    imagecomp.addLine(x1, y1, x2, y2, randomColor, stroke);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                imagecomp.clearLines();
                gradient.n = 0;
                gradient.hashnum = 0;
            }
        });

        clearButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                imagecomp.clearLines();

            }
        });

        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradient.n = 0;
                gradient.hashnum++;
                if ((wartfunctions.x1 + gradient.blocksize) < (gradient.img.getWidth())){ //move 16 to get to center of new block
                    wartfunctions.x1 += gradient.blocksize;
                } else{ //if x1 cannot move anymore, then go to next row
                    wartfunctions.x1 = gradient.blocksize/2; //should reset back to left-hand center
                    wartfunctions.y1 +=gradient.blocksize;
                }

                //needs to shift where histogram is printed



            }
        });

        blockEnter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String s1 = blockSize.getText();

                gradient.blocksize = Integer.parseInt(s1);
                System.out.println("Block size: " + gradient.blocksize);

                HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>();
                gradient.Histolist = wartfunctions.createHistoList(HistoMap, gradient.gArray, gradient.DirArray, gradient.img.getWidth(), gradient.img.getHeight(), gradient.blocksize);


                //prints out all histograms in arraylist
                for(int i=0; i<gradient.Histolist.size(); i++){
                    //System.out.print(Histolist.get(i).values());
                    wartfunctions.printStars(gradient.Histolist.get(i));
                }


            }

        });
        ButtonFrame.pack();
        ImageFrame.pack();
        ButtonFrame.setVisible(true);
        ImageFrame.setVisible(true);


    }

}
