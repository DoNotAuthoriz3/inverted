package inverted.holdings.code.p006;
/* 
 * This class is silly.
 * 
 *  Really, it's literally designed to be extended.
 *  
 *  Just to give some cheap/cheesy console output access.
 *  
 *  Although a constructor and methods are provided
 *  for actual coders. */

public class jout
{	
	public final static void jout(String arg) 
		{ System.out.print(arg); }

	public final static void joutln(String arg) 
		{ System.out.println(arg); }
	
	/* Because only I am idiotic enough to extend this class.  
	 * It bothers me that it can't be final :P */
	public jout() {}
	
	public final void out(String arg) 
		{ System.out.print(arg); }

	public final void outln(String arg) 
		{ System.out.println(arg); }
}