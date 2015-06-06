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
import java.util.HashMap;

public class YahooTagMap extends HashMap<StockAttributeType, YahooTagMap.Attribute>
{
    private static YahooTagMap inst;

    static class Attribute
    {
        String tag;
        Class  type;

        Attribute(String tag, Class type)
        {
            this.tag = tag;
            this.type = type;
        }
    }

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
     * a new instance loaded with attributes from the input file
     *
     * @param fileOfQuotesToCheck
     * @return
     */
    public static YahooTagMap getInstance(String fileOfQuotesToCheck) throws FileNotFoundException
    {
        inst = new YahooTagMap(fileOfQuotesToCheck);
        return inst;
    }

    /**
     * Private constructor enforces the singleton pattern.
     *
     * @throws NoClassDefFoundError
     */
    private YahooTagMap() throws NoClassDefFoundError
    {
        throw new NoClassDefFoundError();
    }

    /**
     * Private constructor enforces the singleton pattern. Loads the instance with the attributes specified in the
     * input file.
     *
     * @param fileOfQuotesToCheck
     */
    private YahooTagMap(String fileOfQuotesToCheck) throws FileNotFoundException
    {
        super();
        importTags(fileOfQuotesToCheck);
    }

    private void importTags(String fileOfQuotesToCheck) throws FileNotFoundException
    {
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
                    Element element = (Element) curElement;
                    String attribute = element.getAttribute("name").toUpperCase();
                    StockAttributeType name;
                    try
                    {
                        name = StockAttributeType.valueOf(attribute);
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println(
                                "ERROR: Specified tag does not exist in list of enumerated tag types " + attribute);
                        throw e;
                    }

                    NodeList typeList = element.getElementsByTagName("type");
                    NodeList nodeList = element.getElementsByTagName("yahoo_tag");

                    String tag;
                    String dataType;

                    if (typeList.item(0) != null && nodeList.item(0) != null)
                    {
                        dataType = typeList.item(0).getTextContent();
                        Class type = Class.forName(dataType);
                        tag = nodeList.item(0).getTextContent();

                        Attribute attr = new Attribute(tag, type);
                        super.put(name, attr);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR: Unable to open file " + fileOfQuotesToCheck);
            throw e;
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public String getTag(StockAttributeType name)
    {
        return this.get(name).tag;
    }

    public Class getType(StockAttributeType name)
    {
        return this.get(name).type;
    }

    public String getTypeName(StockAttributeType name)
    {
        return this.get(name).type.getName();
    }
}
