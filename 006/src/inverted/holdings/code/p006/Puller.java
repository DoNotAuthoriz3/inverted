package inverted.holdings.code.p006;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static inverted.holdings.code.p006.util.Jout.joutln;

public class Puller
{
    public Puller() {}

    @SuppressWarnings("deprecation")
    public static void pull()
    {
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
                    for (String ticker : quotesToGet.getTickers() )//getTickers("stocksQuotes/testStocks.xml"))
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
                joutln("Thread underwent insomnia: ");
                e.printStackTrace();
            }
        }
    }
}
