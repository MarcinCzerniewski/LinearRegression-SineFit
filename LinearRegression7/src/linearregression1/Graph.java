/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linearregression1;

/**
 *
 * @author mczerniewski
 */
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.image.*;

public class Graph extends JPanel {

    LinRegGui lr1;
   
    private BufferedImage grid;
   // private HashMap<Integer, GraphicsObj> graphicsObjs;
    public Line2D.Double[] ch1Lines;
    public Line2D.Double[] ch2Lines;
    Graphics2D gc;
    Graphics2D g2;
    int count;
    boolean draw;
    /**
     * Note: this empty public constructor is necessary because we want to be
     * able to add the Graph class to the GUI Builder Palette in Netbeans.  This
     * way, we can use the features of the GUI builder to manipulate how the
     * Graph JPanel is displayed.
     *
     * To add the the Graph class to the GUI builder:
     * 1) make sure it has the empty public constructor
     * 2) Right-click on Graph.java, select Tools-->Add to Palette..
     * 3) Design normally in the GUI Design mode of TresholdDetectorView.java
     */
    /*
    public Graph() {
       System.out.println("general graph FFT constructor");
    }
    public Graph(LinearRegression1View lr1){
        System.out.println("graph FFT constructor");
        System.out.println("grid="+grid);
         int i;
         draw=false;
        ch1Lines = new Line2D.Double[3000];
        for (i = 0; i < ch1Lines.length; i++) {
            ch1Lines[i] = new Line2D.Double();
        }
        ch2Lines = new Line2D.Double[3000];
        for (i = 0; i < ch2Lines.length; i++) {
            ch2Lines[i] = new Line2D.Double();
        }
   }
    */
    public Graph(){
            

        lr1 = this.lr1;
        draw=true;
       // graphicsObjs = new HashMap<Integer, GraphicsObj>();
       // lr1.graph1.drawgrid();
        int i;
        ch1Lines = new Line2D.Double[3000];
        for (i = 0; i < ch1Lines.length; i++) {
            ch1Lines[i] = new Line2D.Double();
        }
        ch2Lines = new Line2D.Double[3000];
        for (i = 0; i < ch2Lines.length; i++) {
            ch2Lines[i] = new Line2D.Double();
        }
        count = 0;


    }

    public Graph(LinRegGui lr1) {

        System.out.println("graph constructor 2");
        /*
        dfs=new DFspikes();

        lr1 = this.lr1;
        draw=true;
       // graphicsObjs = new HashMap<Integer, GraphicsObj>();
       // lr1.graph1.drawgrid();
        int i;
        ch1Lines = new Line2D.Double[3000];
        for (i = 0; i < ch1Lines.length; i++) {
            ch1Lines[i] = new Line2D.Double();
        }
        ch2Lines = new Line2D.Double[3000];
        for (i = 0; i < ch2Lines.length; i++) {
            ch2Lines[i] = new Line2D.Double();
        }
        count = 0;
        */
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);  // paint background, borders
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // create grid as BufferedImage once, then reuse as necessary
        if (grid == null) {
            System.out.println("drw grid");
            makeGrid();
        }
        
        // draw grid
        g2.drawImage(grid, null, 0, 0);

        
//        //paint other items as necessary
//        if (graphicsObjs != null) {
//            for (GraphicsObj e : graphicsObjs.values()) {
//                g2.setColor(e.getColor());
//                g2.setStroke(e.getStoke());
//                g2.draw(e.getShape());
//            }
//        }

        //paint both channels
      //start drawing at i=2 not 1 to avoid return traces 2/11/2012
        if ((ch1Lines != null) && (ch1Lines[1] != null)) {
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
          
            for (int i = 2; i < ch1Lines.length; i++) {
               
                    g2.draw(ch1Lines[i]);

            }
        }
        if ((ch2Lines != null) && (ch2Lines[1] != null)) {
            g2.setColor(Color.blue);
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 2; i < ch2Lines.length; i++) {
                g2.draw(ch2Lines[i]);
            }
        }


    }


    private void makeGrid() {

        int w = this.getWidth(); //tdv.graphSize.width;
        int h = this.getHeight(); //tdv.graphSize.height;
w=1256;
        grid = (BufferedImage) (this.createImage(w, h));
        //Graphics2D
                gc = grid.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gc.clearRect(0, 0, w, h);
        gc.setColor(new Color(245,245,245));
        gc.fillRect(0,0, w, h);

        for (int index = 0; index < 35; index++) {
            // draw horizontal gray lines
            Color lightHor = new Color(220, 220, 220);           
            gc.setColor(new Color(245,245,245));

            if (index % 2 == 0) {
                Color darkHor = new Color(160, 160, 160);
                gc.setColor(darkHor);
            } else {
                gc.setColor(lightHor);
            }

            int gridy = 5 + index * 15;

            if (index == 16) {
                gc.setColor(Color.red);
            }
            //for now there is no plan for ch2 trace to display
            if (index == 24) {
                //gc.setColor(Color.blue);
            }
            gc.drawLine(0, gridy, w, gridy);
            gc.setColor(Color.black);
            String theStr = String.valueOf(360 - index * 20);
        }
    //}

   // draw vertical lines



//String time_str;
int decpoint=0;
int gridx=0;
int grid_step=32;

		int index = 0; int z=0; String theStr="";

		for (index = 0; index <= 44; index++){

			// draw vertical grid lines
                     gridx=grid_step*index;
                     if(gridx % 128 == 0){gc.setColor(Color.lightGray);
                         gc.drawLine(gridx, 0, gridx, 525);
                      } else 	{gc.setColor(Color.lightGray);gc.drawLine(gridx, 0, gridx, 520);}
 			
                }//end for(index..

                addString("1","22","1","22","1","22","1","22","9");
    }

public void addString(String s1,String s2, String s3,String s4, String s5,String s6, String s7,String s8,String s9){

    gc.clearRect(0, 505, 505,1256);
    gc.setColor(new Color(245,245,245));
    gc.fillRect(0, 504,1256,504);
    gc.setColor(Color.black);
    //0
    gc.drawString(s1, 5,540);
    //128
    gc.drawString(s2, 120,540);
    //256 
    gc.drawString(s3, 248,540);
    //384
    gc.drawString(s4, 376,540);
    //512
    gc.drawString(s5, 505,540);
    //640
    gc.drawString(s6, 632,540);
    //768
    gc.drawString(s7, 762,540);
    //896 
    gc.drawString(s8,888,540);
    //1024  
    gc.drawString(s9, 1011,540);
    this.repaint();
}

//    public void addLine(int N, float x1, float y1, float x2, float y2) {
//        gc.setColor(Color.blue);
//        if (N>1024)N=0;
//        //pk graphicsObjs.put(N, new Line(x1, y1, x2, y2, Color.RED, 2));
//        graphicsObjs.put(N, new Line(x1, y1, x2, y2, Color.GREEN, 2));
//        this.repaint();
//    }

    public void addPoint(int channel, int N, int x, int y) {


         if (y!=125) //System.out.println("Graph add point ch="+channel+" N="+N+" x="+x+" y="+y);
        if (N>1256)N=1;

        if ((N < 1) || (N > ch1Lines.length) || (N > ch2Lines.length)) {
            System.err.print("N out of range");
           // System.out.println("Graph N="+N);
           // System.out.println("Graph ch1Lines.length="+ch1Lines.length);
            return;
        }

        //create lines using the previous value as the starting point
        //and the passed-in x and y and the ending point
        if (channel == 1) {

           ///if( ch1Lines[N - 1].getX2()<x){
          //  System.out.println("Graph set line");

            ch1Lines[N].setLine(ch1Lines[N - 1].getX2(),
                                 ch1Lines[N - 1].getY2(),
                                 x, y);
          // }
    draw=true;

        } else if (channel == 2) {
            ch2Lines[N].setLine(ch2Lines[N - 1].getX2(),
                                 ch2Lines[N - 1].getY2(),
                                 x, y);
        }
        
        count++;
        if (count == 2048||draw) {
            this.repaint();
            count = 0;
        }
    }

public void startDrawing(boolean d){

    draw=d;

}
//    public void addLine1(int x1, int y1, int x2,int y2) {
//
     // g2.drawLine(x1, y1, x2, y2);
      //this.repaint();
//    }


}
