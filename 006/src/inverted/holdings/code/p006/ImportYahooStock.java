package inverted.holdings.code.p006;

import inverted.holdings.code.p006.util.IllegalFormatException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static inverted.holdings.code.p006.util.Jout.joutln;

public class ImportYahooStock implements EquityQuoteImporter
{
    String baseURL = new String("http://finance.yahoo.com/d/quotes.csv?s=");

    private static DateFormat dayf  = new SimpleDateFormat("M/d/yyyy");
    private static DateFormat timef = new SimpleDateFormat("h:mma");

    public ImportYahooStock() { super(); }

    /**
     * This retrieves a quote for a specific vehicle.  The information retrieved depends on the tag list that is
     * passed in; the resulting quote contains whatever data is retrieved by polling in the input tags.
     *
     * @param ticker
     * @param tags
     * @return
     */
    @Override
    public Quote getQuote(String ticker, List<StockAttributeType> tags) throws IllegalFormatException
    {
        Quote quote = new Quote(ticker, "unused");

        String line;
        URL url;
        InputStream is = null;
        BufferedReader br;

        try
        {
            // connect to yahoo and download the data
            url = new URL(baseURL + ticker + urlSuffixBuilder(tags));
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            // parse the resulting data and store it in a Quote.
            while ((line = br.readLine()) != null)
            {
                int i = 0;
                joutln(line);

                for (String result : line.split(","))
                {
                    quote.insert(tags.get(i), result);
                    i++;
                }
            }
        }
        catch (MalformedURLException mue) { mue.printStackTrace(); quote = null; }
        catch (IOException ioe)
        {
            joutln("Unable to connect to the website");
            ioe.printStackTrace();
            quote = null;
        }
        finally
        {
            try { if (is != null) is.close(); }
            catch (IOException ioe) { joutln("unhandled exception"); }
        }

        return quote;
    }

    // TODO: Make sure initializing the arraylist with the size then doing adds does what I thinks it does.
    @Override
    public List<Quote> getQuotes(StockQuoteList quotes, List<StockAttributeType> tags)
            throws IllegalFormatException
    {
        String urlBuilder = baseURL;
        List<String> tickers = quotes.getTickers();

        for (String t : tickers)
        {
            urlBuilder = urlBuilder + t + ",";
        }

        return retrieve(quotes, tags, urlBuilder);
    }

    private List<Quote> retrieve(List quotes, List<StockAttributeType> tags, String urlBuilder)
            throws IllegalFormatException
    {
        URL url;
        InputStream is = null;

        try
        {
            BufferedReader br;
            String line;

            // connect to yahoo and download the data
            url = new URL(urlBuilder + urlSuffixBuilder(tags));
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
            int i = 0;

            // parse the resulting data and store it in a Quote.
            while ((line = br.readLine()) != null)
            {
                int j = 0;
                joutln(line);

                String date = "";
                String time = "";
                for (String result : line.split(","))
                {
                    StockAttributeType type = tags.get(j);

                    if (StockAttributeType.TIME == type && date.equals(""))
                    {
                        result = result.replace("\"", "");
                        date = result;

                        j--;
                    }
                    else if (StockAttributeType.TIME == type)
                    {
                        Quote.setDateFormat(new SimpleDateFormat("M/d/yyyy h:mma"));

                        result = result.replace("\"", "");
                        time = result;

                        if (!date.equals(""))
                            ((Quote) quotes.get(i)).insert(type, date  + " " + time);
                    }
                    else
                    {
                        ((Quote) quotes.get(i)).insert(type, result);
                    }

                    j++;
                }

                i++;
            }
        }
        catch (MalformedURLException mue) { mue.printStackTrace(); quotes = null; }
        catch (IOException ioe)
        {
            joutln("Unable to connect to the website");
            ioe.printStackTrace();
            quotes = null;
        }
        finally
        {
            try { if (is != null) is.close(); }
            catch (IOException ioe) { joutln("unhandled IOException"); }
        }

        return quotes;
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
    private String urlSuffixBuilder(List<StockAttributeType> tags) throws FileNotFoundException
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
