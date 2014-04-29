package inverted.holdings.code.p005;
import java.util.Scanner;

public class readName
{	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String firstName, lastName;
		int firstNum, lastNum, total;
		firstNum = 5;
		lastNum = -13;
		total = firstNum + lastNum;		
		
		if ((lastNum > total) && (firstNum < total)) 
		{ 
			System.out.println(total + " is greater than both firstNum and lastNum");
			System.out.println("Total - firstNum = " + (total - firstNum));
			System.out.println("Total - lastNum = " + (total - lastNum));
			
			if (lastNum > firstNum)
				System.out.println(Integer.toString(total) + " > " + lastNum + " > " + firstNum);
			else
				System.out.println(Integer.toString(total) + " > " + firstNum + " > " + lastNum);
			
		}
		else if (true)
			{ System.out.println(total + " is less than " + lastNum); }
		else
			System.out.println(firstNum + " is greater than " + total);
				
		System.out.println("Do you want to take branch one?");
		firstName = in.nextLine();

		
		if (firstName.equals("yes"))
		{
			System.out.println("Do you want to take branch two?");
			lastName = in.nextLine();
			if(lastName.equals("yes"))
				System.out.println("You made it to three!");
		}
		else
		{
			System.out.println("Are you sure?");
			lastName = in.nextLine();
			if(lastName.equals("yes"))
				System.out.println("Awww!");
			else
			{
				System.out.println("Do you want to take branch two?");
				lastName = in.nextLine();
			}
		}

		System.out.println("What is your last name?");
		lastName = in.nextLine();
		
		in.close();
	}	
}

/*
 * stdin
 * stdout
 * 
 * 
 */