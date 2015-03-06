package inverted.holdings.code.p006;
/* 
 * This class maintains a list of stocks to check
 * along with any parameters associated with those stocks.
 * 
 * This enables a class designed to retrieve stocks to
 * know what information to retrieve (such as ticker,
 * stock name, daily volume, min price, etc.) for all of the
 * stocks it should be retrieving. 
 * 
 * TODO: fix the "index" functionality so this is set up
 * for multiple traversal of the stocks in this list. */ 

import java.io.File;
import java.lang.Exception;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class stockQuoteList
{
	private stockQuote[] myQuotesList;
	private int index;
	
	public stockQuoteList()
	{
		//	this class does not make sense without
		//	a file to read from
		this("stocksQuotes/testStocks.xml");
	}

	// NOTE: this code expects certain universal elements,
	//	suck as ticker (without which stock data does not 
	//	make sense), and but also builds the stock data object
	//	with additional types as available
	public stockQuoteList(String fileOfQuotesToCheck)
	{
		index = 0;
		
		try 
		{
			File projectList = new File(fileOfQuotesToCheck);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(projectList);
			//	document must be valid XML described by #insertSchemaHere
			doc.getDocumentElement().normalize();
			
			NodeList nlist = doc.getElementsByTagName("stock");

			myQuotesList = new stockQuote[nlist.getLength()];
			
			for (int i = 0; i < nlist.getLength(); i++)
			{
				Node curElement = nlist.item(i);

				myQuotesList[i] = new stockQuote();
				
				if (curElement.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) curElement;
					myQuotesList[i].ticker = eElement.getAttribute("ticker");
					myQuotesList[i].name = eElement.getAttribute("name");
					myQuotesList[i].lang = eElement.getAttribute("lang");
					System.out.println(eElement.getAttribute("ticker") + " " 
							+ eElement.getElementsByTagName("name").item(0).getTextContent() + " - " 
						/*	+ eElement.getElementsByTagName("lang").item(0).getTextContent()*/);
				}
			}
		} 
		catch (Exception e) 
			{ e.printStackTrace(); }
	}
	
	public stockQuote getNext() throws Exception /* OutOfBoundsException */
	{ 
		index++; 
		if (index > 0 && index <= myQuotesList.length) return myQuotesList[index - 1]; 
		else throw new Exception() /* OutOfBoundsException() */;
	}
	
	// This is a dumb method.
	//	TODO: make it smarter.  Like enough to do something useful
	//	if either index or myQuotesList isn't set/is set to something bad/
	//	is set to something misleading
	public boolean fin() { 
	   return 0 == (index = index > myQuotesList.length ? 0 : index); }	
}
