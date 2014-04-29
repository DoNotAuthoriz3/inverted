package inverted.holdings.code.p005;

public class helloWorld
{
	public static void main(String[] args)
	{
		printHelloWorld();
		int myIntegerArray[] = new int[2];
		myIntegerArray[0] = 5;
		thisTakesTwo(myIntegerArray);
	}
	
	public static void jout(String myThingToPrint)
	{
		System.out.print(myThingToPrint);
	}
	
	static void joutln(String jimmyjams)
	{
		jout(jimmyjams + "\n");
	}
	
	public static void printHelloWorld()
	{
		joutln("Hello World");
	}
	
	public static void thisTakesTwo(int[] myInts)
	{
		if (myInts[0] < myInts[1])
			joutln("A < B");
		else
			joutln("B < A");
	}
}

/*
 * 
 * 
 * 
 * joutln
 *  String jimmyjams = "Hello World!";
 *  
 *  jout
 *  		String myThingToPrint = "Hello World!\n";
 * 
 * printHelloWorld
 *    
 * thisTakesTwo
 * 		int a = 5 
 * 		int b = 2
 * 
 * ----
 * addTwo
 * 		int x =
 * 
 * return int[] myIntArray;
 * ----
 * 
 * 
 * System.out.println(xArray);
 * 
 * 
 * int x = 0x2322[    ]
 * int[] xArray = 0x34343[    ] [    ]
 * 
 * 
 * [    ][    ][    ]
 * 
 */