import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class LinesComponent extends JComponent{
    Gradient gradient = new Gradient();
    LinesComponent(Gradient gradient){
        this.gradient = gradient;
    }
   // WartHogMethods points = new WartHogMethods();
   // double x1val = points.x1;
  //  System.out.println("The value of x1 in linescomponent is: " + x1val);

    public class Line {
        WartHogMethods points = new WartHogMethods();
        double x1;
        double y1;
        double x2;
        double y2;
        double x3;
        double y3;
        public Color color;
        float stroke;



        public Line(double x1, double y1, double x2, double y2, double x3, double y3, Color color, float stroke) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.x3 = x3;
            this.y3 = y3;
            this.color = color;
            this.stroke = stroke;
        }
    }


    private final LinkedList<Line> lines = new LinkedList<Line>();

    public void addLine(double x1, double y1, double x2, double y2, double x3, double y3, float stroke) {
        addLine(x1, y1, x2, y2, x3, y3, Color.red, stroke);
    }

    public void addLine(double x1, double y1, double x2, double y2, double x3, double y3, Color color, float stroke) {
        lines.add(new Line(x1,y1,x2,y2, x3, y3, color, stroke));
        repaint();
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    //takes in image to draw on top off, also goes through each line to draw
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        try {

            File f = new File(gradient.img);
            BufferedImage img = ImageIO.read(f);
            g2.drawImage(img, 0,0, this);
            for (Line line : lines) {

                g2.setColor(Color.red);
                g2.setStroke(new BasicStroke(line.stroke));
                g2.draw(new Line2D.Double(line.x1, line.y1, line.x2, line.y2));
                g2.draw(new Line2D.Double(line.x1, line.y1, line.x3, line.y3));
                //g.drawLine(line.x1, line.y1, line.x2, line.y2);
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }



}
