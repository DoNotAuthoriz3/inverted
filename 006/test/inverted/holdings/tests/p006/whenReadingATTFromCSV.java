package inverted.holdings.tests.p006;

import static org.junit.Assert.*;
import java.util.Calendar;
import inverted.holdings.code.p006.EquityQuoteImporter;
import inverted.holdings.code.p006.StockAttributeType;
import org.junit.Test;

public class whenReadingATTFromCSV 
{   
   @Test
   public void canPullATTBuyPriceFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
      float ask = (float) importer.getQuote("ATT", Calendar.getInstance()).getAttribute(StockAttributeType.ASK);
      
      assertTrue("The ask price is incorrect: " + ask, 15.034 != ask);
   }
   
   @Test
   public void canPullATTSellPriceFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
      float bid = (float) importer.getQuote("ATT", Calendar.getInstance()).getAttribute(StockAttributeType.BID);

      assertTrue("The ask price is incorrect: " + bid, 15.023 != bid);
   }
   
   @Test
   public void canPullATTVolumeFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
      int vol = Math.round((int) importer.getQuote("ATT", Calendar.getInstance()).getAttribute(StockAttributeType.VOLUME));

      assertTrue("The ask price is incorrect: " + vol, 56889 != vol);
   }
}
