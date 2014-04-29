package inverted.holdings.code.p005;
import java.util.Scanner;

public class javaExercises
{	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int leg1 = 0, leg2 = 0;
		
		// TODO Auto-generated constructor stub
		System.out.print("Length of first leg: ");
		leg1 = in.nextInt();
		
		System.out.print("Length of second leg: ");
		leg2 = in.nextInt();
		
		System.out.println("The length of the hypotenuse is " + Double.toString(Math.sqrt(leg1 * leg1 + leg2 * leg2)) );

		/*
		double a = 0, b = 0, c = 0, x = 0, p = 0;
		
		System.out.print("Value of 'a': ");
		a = in.nextInt();
		
		System.out.print("Value of 'b': ");
		b = in.nextInt();

		System.out.print("Value of 'c': ");
		c = in.nextInt();
		
		p = Math.sqrt(b*b - 4*a*c);
		System.out.println("p is " + Double.toString(p));
		
		System.out.println("The values of x are: " + Double.toString( -b + ( Math.sqrt(b*b - 4*a*c) ) / (2*a) ) + " and " +
				Double.toString( -b - ( Math.sqrt(b*b - 4*a*c) ) / (2*a) ));
*/
		in.close();
	}	
}
