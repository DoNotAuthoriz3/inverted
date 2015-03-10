package inverted.holdings.code.p006;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ImportYahooStock implements EquityQuoteImporter
{
    String baseURL = new String("http://finance.yahoo.com/d/quotes.csv?s=");

    public ImportYahooStock() { super(); }

    @Override
    public Quote getQuote(String ticker, ArrayList<StockAttributeType> tags)
    {
        Quote quote = new Quote(ticker, "unused");

        String line;
        URL url;
        InputStream is = null;
        BufferedReader br;

        try
        {
            url = new URL(baseURL + ticker + urlSuffixBuilder(tags));
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
            YahooTagMap tagMap = YahooTagMap.getInstance();
            int i = 0;

            while ((line = br.readLine()) != null)
            {
                System.out.println(line);

                for (String result : line.split(","))
                {
//                    Class type = tags.get(i);
                    tagMap.getTag(tags.get(i));
                    quote.put(tags.get(i), result);
                    i++;
                }
            }
        }
        catch (MalformedURLException mue) { mue.printStackTrace(); }
        catch (IOException ioe)
        {
            System.out.println("Unable to connect to the website");
            ioe.printStackTrace();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            try { if (is != null) is.close(); }
            catch (IOException ioe) { System.out.println("unhandled exception"); }
        }

        return quote;
    }

    /**
     * This produces the correct URL suffix for the requested tags.  It uses the YahooTagMap singleton to determine
     * the URL components that correspond to each tag and concatenates them to produce the completed URL suffix.
     * <p>
     * If the stockAttributes.xml file cannot be found in the project directory it will throw a FileNotFoundException.
     * This file is read by the YahooTagMap singleton and allows easy, dynamic correction/revision of the URL
     * component, should Yahoo's API change.
     *
     * @param tags
     * @return
     * @throws FileNotFoundException
     */
    private String urlSuffixBuilder(ArrayList<StockAttributeType> tags) throws FileNotFoundException
    {
        String suffix = "&f=";
        YahooTagMap tagMap = YahooTagMap.getInstance();

        for (StockAttributeType tagName : tags)
        {
            // get yahoo ID for this tag from the Attributes file
            suffix += tagMap.getTag(tagName);
        }

        return suffix;
    }
}
