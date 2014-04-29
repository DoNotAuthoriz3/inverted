package inverted.holdings.code.p006;
/* by: badmonkey
 * 
 * This class records the provided information about a
 * stock in a file.
 * 
 * TODO: Prototype for this class will just take a whole string
 * representing the result and stick that in a single
 * CSV entry and concatenate that on to a file.
 * 
 * TODO: This class should eventually take a dynamic set of fields
 * associated with a stock result and write them to a file
 * in a more useful way--whether that's some kind of smarter CSV
 * or another data setup entirely. */

public class resultsWriter
{
	private String myFile;
	
	public resultsWriter() {}

	public resultsWriter(String fileName)
		{ myFile = fileName; }

	public void concat(String stockResults) 
		{ concat(myFile, stockResults); }
	
	public static void concat(String fileName, String stockResults) 
	{
		// TODO: some stuff here.
	}
}
