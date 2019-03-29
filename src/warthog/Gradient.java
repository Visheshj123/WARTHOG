
import java.io.IOException;
import java.nio.file.Path;
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
    String img = null; //allows import of image with each pixel having coordinate point, starting with (0,0) at top left
    int blocksize = 16;
    double [][] gArray;
    double [][]DirArray;
    BufferedImage IMG;


    public static void main(String[] args) {

        Gradient gradient = new Gradient();
        System.out.println(gradient.blocksize);

        WartHogMethods wartfunctions = new WartHogMethods();


        JFrame Pathframe = new JFrame();
        Pathframe.pack();
        JTextField imgpath = new JTextField();
        imgpath.setPreferredSize( new Dimension( 200, 24 ));
        JButton imgEnter = new JButton("Enter");

        Pathframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pathPanel = new JPanel();
        pathPanel.add(imgEnter);
        pathPanel.add(imgpath);
        Pathframe.getContentPane().add(pathPanel);

        Pathframe.setVisible(true);

        imgEnter.addActionListener(new ActionListener() {

                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           gradient.img = imgpath.getText();
                                           mainthread m = new mainthread(gradient);
                                           m.start();

                                       }

                                   });

        /*while(gradient.img != null){
            mainthread m = new mainthread();
            m.start();
        }


            System.out.println("You must enter a file path");*/



        // BufferedImage img = null; //allows import of image with each pixel having coordinate point, starting with (0,0) at top left


//imported Jframe

    }
}


