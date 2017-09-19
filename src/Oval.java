import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Oval extends JFrame {

   public Oval(String args[]){
      super("Dragging Oval");
      setSize(800,800);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().add(new DrawPanel());
   }

   public static void main(String args[]) {
      Oval drawOval = new Oval(args);
      drawOval.setVisible(true);
   }

   private class DrawPanel extends JComponent {

      int startX, startY, endX, endY; // start coordinates to draw oval
      int  width, height; // oval's width and height
      int x1,y1; // coordinates for drawn oval
      int x2, y2; // coordinates for drawn oval which will be moved
      int pointX, pointY; // coordinates of the point where mouse button pressed
      int newPointX, newPointY; //coordinates of the point to where oval must be moved
      Point startPosition, endPosition, pointPosition;


    public DrawPanel() {
         addMouseListener(new MouseAdapter() {
            private boolean dragging = true;

            public void mousePressed(MouseEvent e) {
               pointPosition = e.getPoint();
               pointX = e.getX();
               pointY = e.getY();
               dragging = isInsideOval(pointPosition);
               if (!dragging) {
                  startX = pointX;
                  startY = pointY;
                  startPosition = pointPosition;
                  endPosition = startPosition;
               }
            }
         });
            addMouseMotionListener(new MouseMotionAdapter() {
               private boolean dragging = false;

               public void mouseDragged(MouseEvent e) {
                     dragging = isInsideOval(pointPosition);
                     if (!dragging)         // draw oval
                     {
                        endX = e.getX();
                        endY = e.getY();
                       endPosition = new Point(endX, endY);

                        if (endX > startX) {
                           x1=startX;
                           x2=startX;
                           width = endX - startX;
                        }
                        else {width = startX - endX;
                           x1=endX;
                        x2=endX;}
                        if (endY > startY) {
                           height = endY - startY;
                           y1=startY;
                           y2=startY;
                        }
                        else {height = startY - endY;
                           y1=endY;
                        y2=endY;}

                        repaint();

                     }
                    else                         // move oval
                        {
                      newPointX = e.getX();
                      newPointY = e.getY();

                       if (newPointX > pointX)
                           x2 = x1 + newPointX-pointX;
                         else
                          x2 = x1 - ( pointX - newPointX);

                        if (newPointY > pointY)
                           y2 = y1 + newPointY-pointY;

                         else
                           y2=y1- (pointY-newPointY);

                        repaint();}
                     }            });}

      public boolean isInsideOval(Point point) {
        return new Ellipse2D.Float(x1, y1, width, height).contains(point);
      }

      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         getContentPane().setBackground(Color.pink);
         drawMe(g);

      }

      public void drawMe(Graphics g){
         if (startPosition != null && endPosition != null) {
         g.setColor(Color.cyan);
         g.fillOval(x2, y2, width, height);
         g.setColor(Color.black);
         g.drawOval(x2, y2, width, height);
      }}
   }}



