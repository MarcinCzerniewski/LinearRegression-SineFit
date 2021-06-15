/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tresholddetector;

/**
 *
 * @author mczerniewski
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;


/**
 * This abstract class defines representations of 2D objects used on the map.
 * e.g., lines, ellipse
 *
 * @author pk
 */
public abstract class GraphicsObj {

    protected Shape shape;
    protected Color color;
    protected BasicStroke stroke;



    /**
     * Update coordinates in viewer coordinate system
     * @param display
     * @param gc
     */
   // protected abstract void updateCoor(Display display, GridCoverage2D gc);

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public BasicStroke getStoke() {
        return stroke;
    }
}
