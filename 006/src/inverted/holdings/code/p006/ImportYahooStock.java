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
    public Quote getQuote(String ticker, ArrayList<String> tags)
    {
        Quote quote = null;

        String line;
        URL url;
        InputStream is = null;
        BufferedReader br;

        try
        {
            url = new URL(baseURL + ticker + urlSuffixBuilder(tags));
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
     *
     * If the stockAttributes.xml file cannot be found in the project directory it will throw a FileNotFoundException.
     * This file is read by the YahooTagMap singleton and allows easy, dynamic correction/revision of the URL
     * component, should Yahoo's API change.
     *
     * @param tags
     * @return
     * @throws FileNotFoundException
     */
    private String urlSuffixBuilder(ArrayList<String> tags) throws FileNotFoundException
    {
        String suffix = "&f=";

        YahooTagMap tagMap = YahooTagMap.getInstance();

        for (String tag : tags)
        {
            // get yahoo ID for this tag from the Attributes file

        }

        return suffix; // "ngb3b2hv";
    }
}
