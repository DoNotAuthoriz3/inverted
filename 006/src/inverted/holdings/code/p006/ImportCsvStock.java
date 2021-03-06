package inverted.holdings.code.p006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import static inverted.holdings.code.p006.util.Jout.joutln;

public class ImportCsvStock //implements EquityQuoteImporter
{

    public ImportCsvStock() { }

    public static void main(String[] args)
    {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line, baseURL = new String("http://finance.yahoo.com/d/quotes.csv?s=");
        Date newTime;
        Date lastTime = new Date();
        StockQuoteList quotesToGet = new StockQuoteList();
//        ResultsWriter outPutter = new ResultsWriter("wesults.csv");

        while (true)
        {
            newTime = Calendar.getInstance().getTime();
            lastTime = (newTime.getSeconds() == lastTime.getSeconds()) ? lastTime : newTime;

            // lastTime = Calendar.getInstance().getTime();
            System.out.println(
                    "Second is " + lastTime.getSeconds() + " and second mod 5 is " + (lastTime.getSeconds() % 5) + " " +
                    "" + "and 0 == second mod five is " + (0 == (lastTime.getSeconds() % 5)));

            if (0 == (lastTime.getSeconds() % 5))
            {
                try
                {
                    for (Quote currQuote = quotesToGet.getNext(); !quotesToGet.fin(); currQuote = quotesToGet.getNext())
                    {
                        try
                        {
                            url = new URL(baseURL + currQuote.get(StockAttributeType.TICKER) + "&f=ngb3b2hv");
                            is = url.openStream(); // throws an IOException
                            br = new BufferedReader(new InputStreamReader(is));

                            while ((line = br.readLine()) != null)
                            {
                                System.out.println(line);
                            }
                        }
                        catch (MalformedURLException mue) { mue.printStackTrace(); }
                        catch (IOException ioe)
                        {
                            joutln("Unable to connect to the website");
                            ioe.printStackTrace();
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
            } ;
        }
    }
}
