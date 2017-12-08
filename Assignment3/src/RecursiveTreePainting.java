import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.BasicStroke; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
** RecursiveTreePainting class uses recursion to draw a treee
** @author Haimi Nguyen
** @Date 02/22/17
*/

public class RecursiveTreePainting extends JComponent implements MouseListener{
	/** Number of generations to create branches. Play with this for coarser/finer detail. **/
	public static final int NUM_GENERATIONS = 8;
 
	/** Number of children for each branch. Play with this for sparser/fluffier trees. **/
	public static final int NUM_CHILDREN = 3; 
 
	/** Golden ratio makes the child branches aesthetically appealing **/
	public static final double GOLDEN_RATIO = 1.618;
 
	/** Maximum branching angle of children from a parent stick **/
	public static final double MAX_BRANCHING_ANGLE = .5*Math.PI;
 
	/** Diameter of the blossoms. **/
	public static final int BLOSSOM_DIAM = 4;


	/** Points where mouse is pressed and released **/
	public static Point2D pressedPoint, releasedPoint;


	/** constructor of RecursiveTreePainting class **/
	public RecursiveTreePainting(){
		
	}

	/** implement the mouseClicked method from MouseListener interface **/
	public void mouseClicked (MouseEvent e){
		
	}

	/** implement the mousePressed method from MouseListener interface
	get coordinates of the point where mouse is pressed **/
	public void mousePressed (MouseEvent e){
		pressedPoint = new Point2D.Double(e.getX(),e.getY());
		
	}

	/** implement the mouseReleased method from MouseListener interface
	get coordinates of the point where mouse is released **/
	public void mouseReleased (MouseEvent e){
		releasedPoint = new Point2D.Double(e.getX(),e.getY());
		repaint();
		
	}

	/** implement the mouseEntered method from MouseListener interface **/
	public void mouseEntered (MouseEvent e){

	}

	/** implement the mouseExited method from MouseListener interface **/
	public void mouseExited (MouseEvent e){

	}

	/**
	* Compute the x and y coordinates of the point that is length away
	* from point p at an angle
	* @param p the original point
	* @param length distance from original point and new point
	* @param angle the angle between line connecting two points and horizontal line through p
	* @return a 2D point
	**/

	public Point2D computeEndpoint(Point2D p, double length, double angle){
		Point2D branchTip = new Point2D.Double(p.getX()+length*Math.cos(angle),	//calculate x from cos
								  p.getY()+length*Math.sin(angle));	//calcualte y from sin
		return branchTip;
	}

	/** override paint method from JComponent class
	paint method will execute painting of trees and background
	based on the points from mouse's behaviors
	* @param g graphics g to draw on */

	public void paint (Graphics g){
		
		// paint the background
		paintBackground(g);

		// evoke methods in MouseListener
		addMouseListener(this);

	

		if (releasedPoint != null){		// if user has released mouse
			paintTrunk(g); // then paint the trunk

			// calculate length of trunk
			double distance = Math.sqrt(Math.pow(pressedPoint.getX()-releasedPoint.getX(),2)
									+Math.pow(pressedPoint.getY()-releasedPoint.getY(),2));

			// calculate the angle of the trunk
			double theta = Math.atan2(pressedPoint.getY()-releasedPoint.getY(), 
								  pressedPoint.getX()-releasedPoint.getX());

			// draw branches
			paintBranch(g, releasedPoint, distance, theta, NUM_GENERATIONS-1);				// then paint the branches (+blossoms)

		}

	}

	/** paintTrunk method paints a trunk
	* @param g graphics to draw on **/

	public void paintTrunk(Graphics g){
		
		g.setColor(new Color(36,150,13));		// set color of trunk to be green
		Graphics2D g2D = (Graphics2D) g;		// cast g to a Graphics2D object
		g2D.setStroke(new BasicStroke(1));		// set the thickness of trunk
		g2D.drawLine((int)pressedPoint.getX(),(int)pressedPoint.getY(),		// draw trunk from pressed point
					(int)releasedPoint.getX(),(int)releasedPoint.getY());	// to released point
	}

	/** paintBranch method paints branches starting out radially
	from the tip of trunk and use recursion to draw subsequent branches
	* @param g the graphics to draw 
	* @param start point at which branch starts
	* @param length length of branch
	* @param angle angle to draw trunk
	* @param generations number of lower levels of branches to draw
	**/

	public void paintBranch(Graphics g, Point2D start, double length, double angle, int generations){
		
		Graphics2D g2D = (Graphics2D) g;	// casting g to Graphics2D
		
		if (generations == 0){			// base case 
			paintBlossoms(g, start);
		}
		else{
			
			// loop to create 3 branches per generation
			for (int i = 0; i < NUM_CHILDREN; i++){	

				// branches are within certain angle range 
				double newAngle = angle - MAX_BRANCHING_ANGLE/2 + Math.random()*3*MAX_BRANCHING_ANGLE/2 + Math.PI; 
				g.setColor(new Color(36,150,13));	// set color of branches to be green
				g2D.setStroke(new BasicStroke(1));			// set thickness of branches
				g2D.drawLine((int)start.getX(),(int)start.getY(),		// draw branches using angle and length
						  (int)(start.getX()+length*Math.cos(newAngle)),
						  (int)(start.getY()+length*Math.sin(newAngle)));
				
				
				// use recursion to draw more branches
				paintBranch(g, new Point2D.Double(start.getX()+length*Math.cos(newAngle),
												  start.getY()+length*Math.sin(newAngle)),
													length/GOLDEN_RATIO, newAngle + Math.PI, generations-1) ;
			}


		}
	}

	/** paintBlossoms method paints circle at a point given
	* @param g graphics to draw
	* @param start the center of the circle **/

	public void paintBlossoms(Graphics g, Point2D start){
		g.setColor(new Color((int)(Math.random() * 0x1000000)));			// set random color for circles

		g.fillOval((int)(start.getX()-BLOSSOM_DIAM/4),(int)(start.getY()-BLOSSOM_DIAM/4),(int)BLOSSOM_DIAM,(int)BLOSSOM_DIAM); // draw circles with start point at center and fixed diameter
	}

	/** paintBackground method gives the background a certain color 
	* @param g graphics to draw **/

	protected void paintBackground(Graphics g){
		g.setColor(new Color(0,0,0));			// set color to black
		g.fillRect(0,0,getWidth(),getHeight());	// draw a black rectangle as big as the background
	}


}