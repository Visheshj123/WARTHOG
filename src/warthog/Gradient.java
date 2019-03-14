
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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Gradient {
    //used by buttons to get next histogram or next bin value
    int n = 0;
    int hashnum = 0;
    ArrayList<HashMap> Histolist = new ArrayList<HashMap>();
    BufferedImage img = null; //allows import of image with each pixel having coordinate point, starting with (0,0) at top left

    public static final String IMG = "/Users/visheshj/Desktop/rsz_hufflepuff.jpg";

    public static void main(String[] args){

        Gradient gradient = new Gradient();

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
            double [][] gArray = new double [gradient.img.getHeight()][gradient.img.getWidth()];
            double[][] DirArray = new double[gradient.img.getHeight()][gradient.img.getWidth()];

            System.out.println("Height of image is " + gradient.img.getHeight());
            System.out.println("Width of image is " + gradient.img.getWidth());

            BufferedImage greyScale = wartfunctions.getPixelData(gradient.img, pixelData);
            //System.out.print("The intensity value at 28,39 is " + pixelData[39][28]);


            //calculates gradient and magnitude array, normalizes angles to (0,180)
            wartfunctions.matrixMultiply(pixelData, gradient.img, gArray, DirArray);

            HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>();
            gradient.Histolist = wartfunctions.createHistoList(HistoMap, gArray, DirArray);



            //sending one 8x8 block to be displayed as histogram
           // wartfunctions.printStars(gradient.Histolist.get(0));

            //prints out all histograms in arraylist
            for(int i=0; i<gradient.Histolist.size(); i++){
                //System.out.print(Histolist.get(i).values());
                wartfunctions.printStars(gradient.Histolist.get(i));
            }

           // System.out.print(Histolist.get(0).values());



        }catch (IOException e){
            e.printStackTrace();
        }

//imported Jframe

        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final LinesComponent comp = new LinesComponent();
        comp.setPreferredSize(new Dimension(gradient.img.getWidth(), gradient.img.getHeight()));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();


        JButton newLineButton = new JButton("New Line");
        JButton resetButton = new JButton("Reset");
        JButton NextButton = new JButton("Next Histogram");
        JButton clearButton = new JButton("Clear");

        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(NextButton);
        buttonsPanel.add(resetButton);

        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        newLineButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                //run function to get next vector values from a specific histogram
                if (gradient.n < 180) {
                    wartfunctions.drawVector(gradient.Histolist.get(gradient.hashnum), gradient.n, gradient.img.getHeight(), gradient.img.getWidth());

                    double x1 = wartfunctions.x1;
                    double y1 = wartfunctions.y1;
                    double x2 = wartfunctions.x2;
                    double y2 = wartfunctions.y2;
                    //Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                    comp.addLine(x1, y1, x2, y2, Color.red);
                    gradient.n += 20;
                }
                else {
                    double x1 = 0.0;
                    double y1 = 0.0;
                    double x2 = 0.0;
                    double y2 = 0.0;
                    Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                    comp.addLine(x1, y1, x2, y2, randomColor);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearLines();
                gradient.n = 0;
                gradient.hashnum = 0;
            }
        });

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearLines();

            }
        });

        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradient.n = 0;
                gradient.hashnum++;
                //hashnum = hashnum + 1;
            }
        });
        testFrame.pack();
        testFrame.setVisible(true);


    }

}


                  
