package inverted.holdings.code.p005;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class helloGUI extends JFrame
{	
	public helloGUI() throws HeadlessException
	{	
		this.setSize(200, 100);
		this.setTitle("Hello World");	
	}
	
	public static void main(String[] args)
	{	
		helloGUI window = new helloGUI();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}	
}