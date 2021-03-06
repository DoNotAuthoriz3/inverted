package inverted.holdings.code.p005;
import java.util.Scanner;

public class BooleanExamples
{
	public static void main(String[] args)
	{
		boolean myFirstBool, b2, vegetable, Doppelkeplungstriebe;
		Scanner myInput = new Scanner(System.in);
		
		System.out.print("Is it true? (y/n)");
		
		// This makes myFirstBool true if the user answers "y" and false if they answer anything else.
		myFirstBool = myInput.next().equals("y");
		
		// This makes b2 the opposite of myFirstBool
		if (myFirstBool)
			b2 = false;
		else
			b2 = true;
		
		// This makes Doppelkeplungstriebe false (myFirstBool && b2 is always false because b2 is the opposite of myFirstBool)
		Doppelkeplungstriebe = myFirstBool && b2;
		
		// This makes Doppelkeplungstriebe true
		Doppelkeplungstriebe = myFirstBool || b2;

		// This makes Doppelkeplungstriebe false
		Doppelkeplungstriebe = !(myFirstBool || b2);
		
		// This makes vegetable true
		vegetable = (Doppelkeplungstriebe) || !(Doppelkeplungstriebe);
		
		// This makes myFirstBool true
		myFirstBool = myFirstBool || b2;

		// This makes myFirstBool false
		// Note that the second line is only separated by a newline, not a semicolon, so it's still part of the first
		myFirstBool = (myFirstBool || b2) && ((Doppelkeplungstriebe) && !((Doppelkeplungstriebe || 
				(vegetable && (myFirstBool && !myFirstBool))))) ;
		
		do {
			System.out.print("Is it true now? (y/n)");
			myFirstBool = myInput.next().equals("y");
		} while (!myFirstBool);
		
		while (myFirstBool)
		{
			System.out.print("Say it's not! (not/anything else)");
			myFirstBool = myInput.next().equals("not");
		}
	}
	
}
