import java.awt.geom.*;
import java.util.*;
import gpdraw.*;
import java.awt.Color;

public class IrregularPolygon
{
    DrawingTool brush;
    SketchPad canvas;
    private ArrayList <Point2D.Double> myPolygon;
    
    public IrregularPolygon()
    {
       canvas = new SketchPad(600, 400);
       brush = new DrawingTool(canvas);
       myPolygon = new ArrayList<Point2D.Double>();
    }

    public static Point2D.Double createPoint(double x, double y)
    {
        return new Point2D.Double(x,y);
    }
    
    public void addPoint(Point2D.Double aPoint) 
    {
        myPolygon.add(aPoint);
    }
    
    public void addCoordinates(double x, double y)
    {
        Point2D.Double aPoint = new Point2D.Double(x,y);
        myPolygon.add(aPoint);
    }

    public void addSampleData()
    {
        myPolygon.add(createPoint(20.0,10.0));
        myPolygon.add(createPoint(70.0,20.0));
        myPolygon.add(createPoint(50.0,50.0));
        myPolygon.add(createPoint(0.0,40.0));
    }
    
    public void draw()
    {
        brush.up();
        brush.move(myPolygon.get(0).getX(), myPolygon.get(0).getY());
        brush.down();
 
        for(int i = 1; i < myPolygon.size(); i++)
        {
            brush.move(myPolygon.get(i).getX(), myPolygon.get(i).getY());
        }
        brush.move(myPolygon.get(0).getX(), myPolygon.get(0).getY());
    }
    
    private double distance(int a, int b)
    {
        return Math.sqrt(Math.pow((myPolygon.get(a).getX() - myPolygon.get(b).getX()),2) 
        + Math.pow((myPolygon.get(a).getY() - myPolygon.get(b).getY()),2));
    }
    
    public double perimeter()
    {
        double rim = distance(0, myPolygon.size()-1);
        for(int i = 1; i < myPolygon.size(); i++)
            rim += distance(i, i-1);
        
        return rim;
    }

    public double area() 
    {
        double a1 = (myPolygon.get(myPolygon.size()-1).getX() * myPolygon.get(0).getY());
        double a2 = (myPolygon.get(myPolygon.size()-1).getY() * myPolygon.get(0).getX());
        for(int i = 1; i < myPolygon.size(); i++)
        {
            a1 += (myPolygon.get(i - 1).getX() * myPolygon.get(i).getY());
            a2 += (myPolygon.get(i - 1).getY() * myPolygon.get(i).getX());
        }
        
        return Math.abs((a1-a2)/2);
    }
}

