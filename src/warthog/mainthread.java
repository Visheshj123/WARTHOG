import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class mainthread extends Thread {
    Gradient gradient = new Gradient();
    mainthread(Gradient gradient){
        this.gradient = gradient;

    }

    public void run(){


        try {
            System.out.println(gradient.img);
            gradient.IMG = ImageIO.read(new File(gradient.img));





        } catch (IOException s) {
            s.printStackTrace();
        }



        WartHogMethods wartfunctions = new WartHogMethods();
        JFrame ButtonFrame = new JFrame();
        JFrame ImageFrame = new JFrame();

        JTextField blockSize = new JTextField();
        blockSize.setBounds(200, 200, 50, 10);

        JButton blockEnter = new JButton("Enter");
        blockEnter.setBounds(200, 400, 50, 50);


        ButtonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        final LinesComponent comp = new LinesComponent(gradient);
        final LinesComponent imagecomp = new LinesComponent(gradient);

        comp.setPreferredSize(new Dimension(gradient.IMG.getWidth(), gradient.IMG.getHeight()));
        imagecomp.setPreferredSize(new Dimension(gradient.IMG.getWidth(), gradient.IMG.getHeight()));

        ButtonFrame.getContentPane().add(comp, BorderLayout.CENTER);
        ImageFrame.getContentPane().add(imagecomp, BorderLayout.CENTER);


        JPanel buttonsPanel = new JPanel();
        JPanel imagePanel = new JPanel();


        JButton VectorButton = new JButton("Create Vectors");
        JButton resetButton = new JButton("Reset");
        JButton NextButton = new JButton("Next Histogram");
        JButton clearButton = new JButton("Clear");

        JLabel label1 = new JLabel("Enter Block Size, must be power of 2");

        buttonsPanel.add(label1);
        buttonsPanel.add(VectorButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(NextButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(blockEnter);

        ButtonFrame.add(blockSize, BorderLayout.CENTER);


        ButtonFrame.getContentPane().setSize(800,400);
        ButtonFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        ButtonFrame.getContentPane().add(label1, BorderLayout.NORTH);
        VectorButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                float stroke;
                // float stroke = 0.003F;
                //run function to get next vector values from a specific histogram
                int x = 0;
                wartfunctions.x1 = gradient.blocksize/2;
                wartfunctions.y1 = gradient.blocksize/2;
                System.out.println("First point is: " + wartfunctions.x1);
                System.out.println("First point is: " + wartfunctions.y1);
                while(x < ((gradient.IMG.getWidth() * gradient.IMG.getHeight())/(gradient.blocksize * gradient.blocksize))) {
                    while (gradient.n < 180) {
                        stroke = (float) ((wartfunctions.drawVector(gradient.Histolist.get(gradient.hashnum), gradient.n)) / 1000);

                        //determines the x1,x2,y1,y2 coordinates
                        wartfunctions.drawVector(gradient.Histolist.get(gradient.hashnum), gradient.n);

                        double x1 = wartfunctions.x1;
                        double y1 = wartfunctions.y1;
                        double x2 = wartfunctions.x2;
                        double y2 = wartfunctions.y2;
                        double x3 = wartfunctions.x3;
                        double y3 = wartfunctions.y3;
                        //Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                        //Integer stroke = gradient.Histolist.get(gradient.hashnum).get(gradient.n);
                        imagecomp.addLine(x1, y1, x2, y2,x3, y3, Color.red, stroke);
                        gradient.n += 20;
                    }
                   /* double x1 = 0.0;
                    double y1 = 0.0;
                    double x2 = 0.0;
                    double y2 = 0.0;
                    stroke = 0.0F;
                    Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                    imagecomp.addLine(x1, y1, x2, y2, randomColor, stroke);*/
                    gradient.n = 0;
                    gradient.hashnum++;
                    if ((wartfunctions.x1 + gradient.blocksize) < (gradient.IMG.getWidth())) { //move 16 to get to center of new block
                        wartfunctions.x1 += gradient.blocksize;
                    } else { //if x1 cannot move anymore, then go to next row
                        wartfunctions.x1 = gradient.blocksize/2; //should reset back to left-hand center
                        wartfunctions.y1 += gradient.blocksize;
                    }
                    x++;
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                imagecomp.clearLines();
                gradient.n = 0;
                gradient.hashnum = 0;
                wartfunctions.x1 = 0.0;
                wartfunctions.y1 = 0.0;
                wartfunctions.x2 = 0.0;
                wartfunctions.y2 = 0.0;
                wartfunctions.x3 = 0.0;
                wartfunctions.y3 = 0.0;

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
                if ((wartfunctions.x1 + gradient.blocksize) < (gradient.IMG.getWidth())) { //move 16 to get to center of new block
                    wartfunctions.x1 += gradient.blocksize;
                } else { //if x1 cannot move anymore, then go to next row
                    wartfunctions.x1 = gradient.blocksize/2; //should reset back to left-hand center
                    wartfunctions.y1 += gradient.blocksize;
                }

                //needs to shift where histogram is printed


            }
        });

        blockEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                File f = null;

                    //will store every pixels rgb value in 3 columns, this is [y-axis][x-axis]
                    int[][] pixelData = new int[gradient.IMG.getHeight()][gradient.IMG.getWidth()];
                    int rgb_avg;
                    int counter = 0;
                    gradient.gArray = new double[gradient.IMG.getHeight()][gradient.IMG.getWidth()];
                    gradient.DirArray = new double[gradient.IMG.getHeight()][gradient.IMG.getWidth()];

                    System.out.println("Height of image is " + gradient.IMG.getHeight());
                    System.out.println("Width of image is " + gradient.IMG.getWidth());


                    //creates greyscale to capture intensity values, populates pixelData array
                    BufferedImage greyScale = wartfunctions.getPixelData(gradient.IMG, pixelData);


                    //calculates gradient and magnitude array, normalizes angles to (0,180)
                    wartfunctions.matrixMultiply(pixelData, gradient.IMG, gradient.gArray, gradient.DirArray);

                    // System.out.print(Histolist.get(0).values());



                String s1 = blockSize.getText();


                gradient.blocksize = Integer.parseInt(s1);
                System.out.println("Block size: " + gradient.blocksize);

                HashMap<Integer, Integer> HistoMap = new HashMap<Integer, Integer>();
                gradient.Histolist = wartfunctions.createHistoList(HistoMap, gradient.gArray, gradient.DirArray, gradient.IMG.getWidth(), gradient.IMG.getHeight(), gradient.blocksize);


                //prints out all histograms in arraylist
                for (int i = 0; i < gradient.Histolist.size(); i++) {
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

