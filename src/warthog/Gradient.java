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
    //variables used by WarthogMethods,mainthread, and LinesComponenent.java
    int n = 0;
    int hashnum = 0;
    ArrayList<HashMap> Histolist = new ArrayList<HashMap>();
    String img = null;
    int blocksize = 0;
    double [][] gArray;
    double [][]DirArray;
    BufferedImage IMG;


    public static void main(String[] args) {

        Gradient gradient = new Gradient();
        System.out.println(gradient.blocksize);


        //GUI to enter img path
        JFrame Pathframe = new JFrame();
        JTextField imgpath = new JTextField();
        imgpath.setPreferredSize( new Dimension( 200, 24 ));
        JButton imgEnter = new JButton("Enter");
        JLabel label = new JLabel("Enter Image Path, Image size must be power of 2");
        label.setBounds(0,0,100,30);

        Pathframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pathPanel = new JPanel();
        pathPanel.add(label);
        pathPanel.add(imgpath);
        pathPanel.add(imgEnter);

        Pathframe.getContentPane().setSize(800,400);
        Pathframe.getContentPane().add(pathPanel);

        Pathframe.setVisible(true);

        imgEnter.addActionListener(new ActionListener() {

                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           gradient.img = imgpath.getText();
                                           //runs mainthread.java class after entering img path
                                           mainthread m = new mainthread(gradient);
                                           m.start();

                                       }

                                   });


    }
}



