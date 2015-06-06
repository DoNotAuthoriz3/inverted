package inverted.holdings.tests.p006;

import static inverted.holdings.code.p006.util.Jout.joutln;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import inverted.holdings.code.p006.EquityQuoteImporter;
import inverted.holdings.code.p006.Quote;
import inverted.holdings.code.p006.StockAttributeType;
import inverted.holdings.code.p006.util.IllegalFormatException;
import org.junit.Test;

public class whenReadingATTFromCSV
{
    ArrayList<StockAttributeType> tags = new ArrayList<>();

    @Test
    public void canPullATTBuyPriceFromYahoo()
    {
        tags.add(StockAttributeType.ASK);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");

        Quote q = null;
        try { q = importer.getQuote("T", tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'ASK' not a valid float"); }

        float ask = (float) q.get(StockAttributeType.ASK);

        assertTrue("The ask price is incorrect: " + ask, 15.034 != ask);
    }

    @Test
    public void canPullATTSellPriceFromYahoo()
    {
        tags.add(StockAttributeType.BID);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");

        Quote q = null;
        try { q = importer.getQuote("T", tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'ASK' not a valid float"); }

        float bid = (float) q.get(StockAttributeType.BID);

        assertTrue("The ask price is incorrect: " + bid, 15.023 != bid);
    }

    @Test
    public void canPullATTVolumeFromYahoo()
    {
        tags.add(StockAttributeType.VOLUME);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");

        Quote q = null;
        try { q = importer.getQuote("T", tags); }
        catch (IllegalFormatException IFE) { joutln("Returned value for 'ASK' not a valid float"); }

        int vol = Math.round((int) q.get(StockAttributeType.VOLUME));

        assertTrue("The ask price is incorrect: " + vol, 56889 != vol);
    }
}
