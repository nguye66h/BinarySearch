import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A SingleTreePanel displays instructions and RecursiveTreePainting.
 * @author ponbarry
 * Revised by Haimi
 **/
public class SingleTreePanel extends JPanel
{
	 private JLabel instructions;
		
	/** 
	 * Constructor uses a border layout. The GUI is set up by
	 * putting a JLabel with instructions in the NORTH region, 
	 * and a new TreePainting object in the CENTER region.
	 **/
	public SingleTreePanel(String instructionsText)
	{
		// use a BorderLayout
		super( new BorderLayout() );
		
		// create instructions and put at the top
		instructions = new JLabel(instructionsText);
		instructions.setHorizontalAlignment(JLabel.CENTER);
		add( instructions, BorderLayout.NORTH );
		
		// use the rest of the panel for a RecursiveTreePainting object
		add( new RecursiveTreePainting(), BorderLayout.CENTER );	
	}
}