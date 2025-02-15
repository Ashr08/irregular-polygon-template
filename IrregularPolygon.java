import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool


public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }
    

    public double perimeter() {
        double perimeter = 0.0;
        
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size()); // Wrap around to the first point
            
            // Calculate distance between current and next
            double dx = next.getX() - current.getX();
            double dy = next.getY() - current.getY();
            perimeter += Math.sqrt(dx * dx + dy * dy);
        }
        
        return perimeter;
    }
    

    public double area() {
        double area = 0.0;
        
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size()); // Wrap around to the first point
            
            // Shoelace formula part
            area += current.getX() * next.getY() - current.getY() * next.getX();
        }
        
        return Math.abs(area) / 2.0;
    }
    

    public void draw()
    {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            // TODO: Draw the polygon.
            // Documents: https://pavao.org/compsci/gpdraw/html/gpdraw/DrawingTool.html
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            pen.move(50, 50);
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
