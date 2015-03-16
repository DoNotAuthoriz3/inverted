package inverted.holdings.tests.p006;

import static org.junit.Assert.*;

import inverted.holdings.code.p006.EquityQuoteImporter;
import inverted.holdings.code.p006.StockAttributeType;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class whenReadingATTFromYahoo
{
    ArrayList<StockAttributeType> tags = new ArrayList<>();

    @Test
    public void canPullATTBuyPriceFromYahoo()
    {
        tags.add(StockAttributeType.ASK);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
        float ask = ((Float) importer.getQuote("T", tags).get(StockAttributeType.ASK)).floatValue();

        assertTrue("The ask price is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);
    }

    @Test
    public void canPullATTSellPriceFromYahoo()
    {
        tags.add(StockAttributeType.BID);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
        float bid = ((Float) importer.getQuote("T", tags).get(StockAttributeType.BID)).floatValue();

        assertTrue("The bid price is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);
    }

    @Test
    public void canPullATTVolumeFromYahoo()
    {
        tags.add(StockAttributeType.VOLUME);
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
        int vol = Math.round(((Integer) importer.getQuote("T", tags).get(StockAttributeType.VOLUME)).intValue());

        assertTrue("The volume price is out of range (0, 100000000): " + vol, vol >= 0 && vol < 10000000.0);
    }
}
