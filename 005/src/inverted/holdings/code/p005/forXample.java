package inverted.holdings.code.p005;

public class forXample
{	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// 1, 1, 2, 3, 5, 8, 13, 21, ...
		for(int i = 0; i < args.length; i++)
			System.out.println(args[i]);
		
		int x = 1, y = 0;
		
		for (int count = 1; count < 15; count++)
		{
			System.out.print("" + x + ", ");
			
			x = y + x;
			y = x - y;
			count = count + 1;
		}
		
		String[] muhArrays = new String[10];
		muhArrays[0] = "w";
		muhArrays[1] = "o";
		muhArrays[2] = "r";
		muhArrays[3] = "d";
		
		String guess = "a";
	 //   guess = in.readLine();
		
		for (int i = 0; i < 10; i++)
		{
			if(muhArrays[i].equals(guess))
				System.out.println("They got one right!");
			else
				System.out.println("They suck!");
		}
		
	}
}










		/* Shit code; don't do this, you know for loops now!
		do 
		{	
			System.out.print("" + x + ", ");
			
			x = y + x;
			y = x - y;
			count = count + 1;
			
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} while (count < 15); */
		
