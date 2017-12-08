import javax.swing.JFrame;

/** 
 * Main application for starting up the Tree Painting GUI.
 * 
 * Command line usage:
 * 
 * To create a single tree painting:
 *     java -cp bin TreeApplication
 *       
 *     	
 * @author ponbarry
 * Revised by Haimi
 **/
public class TreeApplication
{
  	// Edit this line if you want to change the instruction text
	public static final String INSTRUCTIONS_TEXT = "<html>Click and drag to start a tree painting. <br> Be patient, as painting is a lot of work! </html>";

		
	/**
	 * Create a JFrame that holds the TreePaintings.
	 * 
	 **/
	public static void main( String[] args ){

		JFrame guiFrame;
	
		// create a new JFrame to hold a single TreePainting
		guiFrame = new JFrame( "Tree Painter");
		
		// set size
		guiFrame.setSize( 400, 500 );

		// create a TreePanel and add it
		guiFrame.add( new SingleTreePanel(INSTRUCTIONS_TEXT ));

		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );
	}
}