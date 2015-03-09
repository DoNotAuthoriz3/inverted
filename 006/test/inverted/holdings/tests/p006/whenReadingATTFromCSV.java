package inverted.holdings.tests.p006;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import inverted.holdings.code.p006.EquityQuoteImporter;
import inverted.holdings.code.p006.StockAttributeType;
import org.junit.Test;

public class whenReadingATTFromCSV 
{
    ArrayList<String> tags = new ArrayList<>();

    @Test
    public void canPullATTBuyPriceFromYahoo()
    {
        tags.add("ask");
        EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
        float ask = (float) importer.getQuote("ATT", tags).get(StockAttributeType.ASK);

        assertTrue("The ask price is incorrect: " + ask, 15.034 != ask);
   }
   
   @Test
   public void canPullATTSellPriceFromYahoo()
   {
       tags.add("bid");
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
       float bid = (float) importer.getQuote("ATT", tags).get(StockAttributeType.BID);

      assertTrue("The ask price is incorrect: " + bid, 15.023 != bid);
   }
   
   @Test
   public void canPullATTVolumeFromYahoo()
   {
       tags.add("volume");
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
       int vol = Math.round((int) importer.getQuote("ATT", tags).get(StockAttributeType.VOLUME));

      assertTrue("The ask price is incorrect: " + vol, 56889 != vol);
   }
}
