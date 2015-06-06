package inverted.holdings.code.p006;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static inverted.holdings.code.p006.Jout.joutln;

public class Puller
{
    public Puller() {}

    @SuppressWarnings("deprecation")
    public static void pull()
    {
        Jout j = new Jout();
        InputStream is = null;
        Date newTime;
        Date lastTime = new Date();
        StockQuoteList quotesToGet = new StockQuoteList();
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
//        ResultsWriter writer = new ();

        ArrayList<StockAttributeType> tags = new ArrayList<>();
        tags.add(StockAttributeType.ASK);
        tags.add(StockAttributeType.BID);
        tags.add(StockAttributeType.VOLUME);
        Quote quote;

        while (true)
        {
            newTime = Calendar.getInstance().getTime();
            lastTime = (newTime.getSeconds() == lastTime.getSeconds()) ? lastTime : newTime;

            joutln("Second is " + lastTime.getSeconds() + " and second mod 5 is " + (lastTime.getSeconds() % 5) + " " +
                   "and 0 == second mod five is " + (0 == (lastTime.getSeconds() % 5)));

            if (0 == (lastTime.getSeconds() % 5))
            {
                try
                {
                    for (String ticker : getTickers("stocksQuotes/testStocks.xml"))
                    {
                        try
                        {
                            quote = importer.getQuote(ticker, tags);
//                            writer.writeQuote(quote);
                        }
                        catch (Exception e) { e.printStackTrace(); }
                        finally
                        {
                            try { if (is != null) is.close(); }
                            catch (IOException ioe) { System.out.println("unhandled exception"); }
                        }
                    }
                }
                catch (Exception e) { quotesToGet.fin(); }
            }

            try { Thread.sleep(300); }
            catch (Exception e)
            {
                j.outln("Thread underwent insomnia: ");
                e.printStackTrace();
            }
        }
    }

    private static List<String> getTickers(String fromFile)
    {
        List tickers = new ArrayList<String>();

        File projectList = new File(fromFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc;

        try
        {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(projectList);
        }
        catch (Exception e)
        {
            joutln("Error: unable to open the stock file list: " + e);
            return null;
        }

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
            }
        }

        return tickers;
    }
}
