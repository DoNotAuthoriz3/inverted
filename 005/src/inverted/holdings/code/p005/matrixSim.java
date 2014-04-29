package inverted.holdings.code.p005;
import java.util.Random;

public class matrixSim
{	
	public static void main(String[] args)
	{
		Random myRand = new Random(15);
		boolean myBool = true;
		char[] randOM = new char [100];
		int counter = 0;

		while( (myBool || !myBool) && (!myBool ^ myBool ) )
		{
			if(counter < 100)
			{
				randOM[counter] = (char) (myRand.nextInt() % 224 + 32);
				counter++;
			}
			else
			{
				System.out.println(randOM);
				counter = 0;			
			}
		}
	}
}
