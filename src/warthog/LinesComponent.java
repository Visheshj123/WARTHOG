import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class LinesComponent extends JComponent{
   // WartHogMethods points = new WartHogMethods();
   // double x1val = points.x1;
  //  System.out.println("The value of x1 in linescomponent is: " + x1val);

    public class Line {
        WartHogMethods points = new WartHogMethods();
        double x1;
        double y1;
        double x2;
        double y2;
        public Color color;



        public Line(double x1, double y1, double x2, double y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }


    private final LinkedList<Line> lines = new LinkedList<Line>();

    public void addLine(double x1, double x2, double x3, double x4) {
        addLine(x1, x2, x3, x4, Color.red);
    }

    public void addLine(double x1, double x2, double x3, double x4, Color color) {
        lines.add(new Line(x1,x2,x3,x4, color));
        repaint();
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        for (Line line : lines) {
            g2.setColor(Color.red);
            g2.draw(new Line2D.Double(line.x1, line.y1, line.x2, line.y2));
            //g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }



}
