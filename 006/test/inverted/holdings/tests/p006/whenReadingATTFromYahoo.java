package inverted.holdings.tests.p006;

import static inverted.holdings.code.p006.util.Jout.joutln;
import static org.junit.Assert.*;

import inverted.holdings.code.p006.*;
import inverted.holdings.code.p006.util.IllegalFormatException;
import inverted.holdings.code.p006.util.RequiresTimeException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class whenReadingATTFromYahoo
{
    ArrayList<StockAttributeType> tags = new ArrayList<>();

    @Test
    public void canPullATTBuyPriceFromYahoo()
    {
        tags.add(StockAttributeType.ASK);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        Quote q = null;
        try { q = importer.getQuote("T", tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'ASK' not a valid float"); }

        double ask = ((Double) q.get(StockAttributeType.ASK)).floatValue();

        assertTrue("The ask price is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);
    }

    @Test
    public void canPullATTSellPriceFromYahoo()
    {
        tags.add(StockAttributeType.BID);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        Quote q = null;
        try { q = importer.getQuote("T", tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'BID' not a valid float"); }

        double bid = ((Double) q.get(StockAttributeType.BID)).floatValue();

        assertTrue("The bid price is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);
    }

    @Test
    public void canPullATTVolumeFromYahoo()
    {
        tags.add(StockAttributeType.VOLUME);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        Quote q = null;
        try { q = importer.getQuote("T", tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'VOLUME' not a valid float"); }

        long vol = (Long) q.get(StockAttributeType.VOLUME);

        assertTrue("The volume price is out of range (0, 1000000000): " + vol, vol >= 0 && vol <  100000000000l);
    }

    @Test
    public void canPullMultipleAskPricesFromYahoo()
    {
        tags.add(StockAttributeType.ASK);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        StockQuoteList quotes = new StockQuoteList();

        try { importer.getQuotes(quotes, tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'ASK' not a valid float"); }

        Quote q = quotes.getQuote("T");
        double ask = ((Double) q.get(StockAttributeType.ASK)).floatValue();
        assertTrue("The ask price for T is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);

        q = quotes.getQuote("DIS");
        ask = ((Double) q.get(StockAttributeType.ASK)).floatValue();
        assertTrue("The ask price for DIS is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);

        q = quotes.getQuote("TWC");
        ask = ((Double) q.get(StockAttributeType.ASK)).floatValue();
        assertTrue("The ask price for TWC is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);
    }

    @Test
    public void canPullMultipleBuyPricesFromYahoo()
    {
        tags.add(StockAttributeType.BID);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        StockQuoteList quotes = new StockQuoteList();

        try { importer.getQuotes(quotes, tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'BID' not a valid float"); }

        Quote q = quotes.getQuote("T");
        double bid = ((Double) q.get(StockAttributeType.BID)).floatValue();
        assertTrue("The bid price for T is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);

        q = quotes.getQuote("DIS");
        bid = ((Double) q.get(StockAttributeType.BID)).floatValue();
        assertTrue("The bid price for DIS is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);

        q = quotes.getQuote("TWC");
        bid = ((Double) q.get(StockAttributeType.BID)).floatValue();
        assertTrue("The bid price for TWC is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);
    }

    @Test
    public void canPullMultipleVolumesFromYahoo()
    {
        tags.add(StockAttributeType.VOLUME);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        StockQuoteList quotes = new StockQuoteList();

        try { importer.getQuotes(quotes, tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'VOLUME' not a valid long"); }

        Quote q;
        long vol;

        String[] tickers = {"T", "DIS", "TWC"};
        for (String t : tickers)
        {
            q = quotes.getQuote(t);
            vol = (Long) q.get(StockAttributeType.VOLUME);
            assertTrue("The volume price for " + t + " is out of range (0, 1000000000): " + vol, vol >= 0 && vol <  100000000000l);
        }
    }

    @Test
    public void canPulMultipleTagsFromYahoo()
    {
        tags.add(StockAttributeType.TIME);
        tags.add(StockAttributeType.VOLUME);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");

        StockQuoteList quotes = new StockQuoteList();

        try { importer.getQuotes(quotes, tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'VOLUME' not a valid long"); }

        Quote q;
        Date date, today, lastWeek;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        today = c.getTime();
        c.add(Calendar.DATE, -7);
        lastWeek = c.getTime();
        long vol;

        String[] tickers = {"T", "DIS", "TWC"};
        for (String t : tickers)
        {
            q = quotes.getQuote(t);
            date = (Date) q.get(StockAttributeType.TIME);
            assertTrue("The last trade date for " + t + " is not between now and one week ago: " + date,
                       date.after(lastWeek) && date.before(today));

            vol = (Long) q.get(StockAttributeType.VOLUME);
            assertTrue("The volume price for " + t + " is out of range (0, 1000000000): " + vol,
                       vol >= 0 && vol <  100000000000l);
        }
    }

    @Test
    public void canWriteStocksToFile()
    {
        ResultsWriter rw = ResultsWriter.getInstance("csv");

        // Set up a list of things to have in our quote
        List<StockAttributeType> attributes = buildAttributeList();

        // Set up a dummy quote to write
        Quote q = buildDummyQuote();

        // Create our list
        StockQuoteList quotes = new StockQuoteList();
        quotes.add(q);

        try
        {
            rw.appendQuotes(quotes, attributes);
        }
        catch (RequiresTimeException e)
        {
            e.printStackTrace();
        }

        // TODO: read and verify
    }

    private List<StockAttributeType> buildAttributeList()
    {
        List<StockAttributeType> attributes = new ArrayList<>();

        attributes.add(StockAttributeType.TICKER);
        attributes.add(StockAttributeType.ASK);
        attributes.add(StockAttributeType.BID);
        attributes.add(StockAttributeType.VOLUME);
        attributes.add(StockAttributeType.TIME);

        return attributes;
    }

    private Quote buildDummyQuote()
    {
        Quote q = new Quote("XOXO");

        q.put(StockAttributeType.BID, 23.456);
        q.put(StockAttributeType.ASK, 23.467);
        q.put(StockAttributeType.VOLUME, 192034);
        Date d = Calendar.getInstance().getTime();
        q.put(StockAttributeType.TIME, d);

        return q;
    }
}
