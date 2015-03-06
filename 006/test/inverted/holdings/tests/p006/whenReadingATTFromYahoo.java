package inverted.holdings.tests.p006;

import static org.junit.Assert.*;
import java.util.Calendar;
import inverted.holdings.code.p006.EquityQuoteImporter;
import org.junit.Test;

public class whenReadingATTFromYahoo 
{   
   @Test
   public void canPullATTBuyPriceFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
      float ask = importer.getQuote("ATT").getAsk();
      
      assertTrue("The ask price is out of range (0, 100000): " + ask, ask >= 0 && ask < 100000.0);
   }
   
   @Test
   public void canPullATTSellPriceFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
      float bid = importer.getQuote("ATT").getBid();
      
      assertTrue("The bid price is out of range (0, 100000): " + bid, bid >= 0 && bid < 100000.0);
   }
   
   @Test
   public void canPullATTVolumeFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("yahoo");
      int vol = Math.round(importer.getQuote("ATT").getVol());
      
      assertTrue("The volume price is out of range (0, 100000000): " + vol, vol >= 0 && vol < 10000000.0);
   }
}
