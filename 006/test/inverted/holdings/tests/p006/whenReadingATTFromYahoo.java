package inverted.holdings.tests.p006;

import static inverted.holdings.code.p006.util.Jout.joutln;
import static org.junit.Assert.*;

import inverted.holdings.code.p006.EquityQuoteImporter;
import inverted.holdings.code.p006.Quote;
import inverted.holdings.code.p006.StockAttributeType;
import inverted.holdings.code.p006.StockQuoteList;
import inverted.holdings.code.p006.util.IllegalFormatException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

        Quote q = quotes.getQuote("T");
        long vol = (Long) q.get(StockAttributeType.VOLUME);
        assertTrue("The volume price for T is out of range (0, 1000000000): " + vol, vol >= 0 && vol <  100000000000l);

        q = quotes.getQuote("DIS");
        vol = (Long) q.get(StockAttributeType.VOLUME);
        assertTrue("The volume price for DIS is out of range (0, 1000000000): " + vol, vol >= 0 && vol <  100000000000l);

        q = quotes.getQuote("TWC");
        vol = (Long) q.get(StockAttributeType.VOLUME);
        assertTrue("The volume price for T is out of range (0, 1000000000): " + vol, vol >= 0 && vol <  100000000000l);
    }
}
