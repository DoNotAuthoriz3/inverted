import javax.swing.JFrame;

public class HelloGUI extends JFrame
{	public HelloGUI() 
	{	this.setSize(200, 100);
		this.setTitle("Hello World");	}
	
	public static void main(String[] args)
	{	HelloGUI window = new HelloGUI();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	}	}

