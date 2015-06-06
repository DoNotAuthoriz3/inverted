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
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StockQuoteList extends ArrayList
{
    private int index;

    public StockQuoteList()
    {
        //	this class does not make sense without a file to read from
        this("stocksQuotes/testStocks.xml");
    }

    // NOTE: this code expects certain universal elements, such as ticker (without which stock data does not make
    // sense), and but also builds the stock data object with additional types as available
    public StockQuoteList(String fileOfQuotesToCheck)
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

            for (int i = 0; i < nlist.getLength(); i++)
            {
                Node curElement = nlist.item(i);

                if (curElement.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element e = (Element) curElement;
                    String ticker = e.getAttribute("ticker");
                    String name = e.getElementsByTagName("name").item(0).getTextContent();
                    this.add(new Quote(ticker, name));
                    System.out.println(e.getAttribute("ticker") + " "
                                       + e.getElementsByTagName("name").item(0).getTextContent());
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public Quote getNext() throws Exception /* OutOfBoundsException */
    {
        index++;
        if (index > 0 && index <= this.size()) return (Quote) get(index - 1);
        else throw new Exception() /* OutOfBoundsException() */;
    }

    public List<String> getTickers()
    {
        List tickers = new ArrayList<String>();
        // Must iterate the for loop over an array of objects, because Java will not let us cast an Object[]
        for ( Object q : this.toArray() ) { tickers.add(((Quote) q).get(StockAttributeType.TICKER)); }

        return tickers;
    }

    public List<String> getAllQuotes()
    {
        List tickers = new ArrayList<String>();
        // Must iterate the for loop over an array of objects, because Java will not let us cast an Object[]
        for ( Object q : this.toArray() ) { tickers.add((Quote) q); }

        return tickers;
    }

    public Quote getQuote(String ticker)
    {
        // Must iterate the for loop over an array of objects, because Java will not let us cast an Object[]
        for ( Object q : this.toArray() )
            if( ((Quote) q).get(StockAttributeType.TICKER).equals(ticker)) return (Quote) q;

        return null;
    }

    // This is a dumb method.
    // TODO: make it smarter.  Like enough to do something useful if either index or myQuotesList isn't set/is set to
    // something bad/is set to something misleading
    public boolean fin()
    {
        return 0 == (index = index > this.size() ? 0 : index);
    }
}
