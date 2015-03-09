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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YahooTagMap
{
    private static Map<String, String> tags = new HashMap<>();
    private static YahooTagMap inst;

    /**
     * Returns the existing instance, or creates a new one using the default file if no instance exists
     *
     * @return
     */
    public static YahooTagMap getInstance() throws FileNotFoundException
    {
        if (inst == null)
            inst = new YahooTagMap("stocksQuotes/stockAttributes.xml");

        return inst;
    }

    /**
     * Reloads the tag list using the input file then returns the existing instance.  If no instance exists, creates
     * a new instance loaded with tags from the input file
     *
     * @param fileOfQuotesToCheck
     * @return
     */
    public static YahooTagMap getInstance(String fileOfQuotesToCheck) throws FileNotFoundException
    {
        if (inst == null)
            inst = new YahooTagMap(fileOfQuotesToCheck);
        else
            importTags(fileOfQuotesToCheck);

        return inst;
    }

    /**
     * Private constructor enforces the singleton pattern. Loads the instance with the tags specified in the input file.
     *
     * @param fileOfQuotesToCheck
     */
    private YahooTagMap(String fileOfQuotesToCheck) throws FileNotFoundException
    {
        importTags(fileOfQuotesToCheck);
    }

    private static void importTags(String fileOfQuotesToCheck) throws FileNotFoundException
    {
        tags = new HashMap<>();

        try
        {
            File projectList = new File(fileOfQuotesToCheck);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(projectList);
            //	document must be valid XML described by #insertSchemaHere
            doc.getDocumentElement().normalize();

            NodeList nlist = doc.getElementsByTagName("stockAttribute");

            for (int i = 0; i < nlist.getLength(); i++)
            {
                Node curElement = nlist.item(i);

                if (curElement.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element e = (Element) curElement;
                    String name = e.getAttribute("name");
                    String tag = e.getElementsByTagName("yahoo_tag").item(0).getTextContent();
                    tags.put(name, tag);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR: Unable to open file " + fileOfQuotesToCheck); throw e;
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
