package inverted.holdings.tests.p006;

import static org.junit.Assert.*;

import inverted.holdings.code.p006.EquityQuoteImporter;
import inverted.holdings.code.p006.StockAttributeType;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class whenReadingATTFromYahoo
{
    ArrayList<String> tags = new ArrayList<>();

    @Test
    public void canPullATTBuyPriceFromYahoo()
    {
        tags.add("ask");
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
        float ask = (float) importer.getQuote("ATT", tags).get(StockAttributeType.ASK);

        assertTrue("The ask price is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);
    }

    @Test
    public void canPullATTSellPriceFromYahoo()
    {
        tags.add("bid");
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
        float bid = (float) importer.getQuote("ATT", tags).get(StockAttributeType.BID);

        assertTrue("The bid price is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);
    }

    @Test
    public void canPullATTVolumeFromYahoo()
    {
        tags.add("volume");
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
        int vol = Math.round((int) importer.getQuote("ATT", tags).get(StockAttributeType.VOLUME));

        assertTrue("The volume price is out of range (0, 100000000): " + vol, vol >= 0 && vol < 10000000.0);
    }
}
