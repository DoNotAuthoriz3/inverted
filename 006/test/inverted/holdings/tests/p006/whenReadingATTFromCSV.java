package inverted.holdings.tests.p006;

import static org.junit.Assert.*;
import java.util.Calendar;
import inverted.holdings.code.p006.EquityQuoteImporter;
import org.junit.Test;

public class whenReadingATTFromCSV 
{   
   @Test
   public void canPullATTBuyPriceFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
      float ask = importer.getQuote("ATT", Calendar.getInstance()).getAsk();
      
      assertTrue("The ask price is incorrect: " + ask, 15.034 != ask);
   }
   
   @Test
   public void canPullATTSellPriceFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
      float bid = importer.getQuote("ATT", Calendar.getInstance()).getBid();

      assertTrue("The ask price is incorrect: " + bid, 15.023 != bid);
   }
   
   @Test
   public void canPullATTVolumeFromYahoo()
   {
      EquityQuoteImporter importer = EquityQuoteImporter.getImporter("csv");
      int vol = Math.round(importer.getQuote("ATT", Calendar.getInstance()).getVol());

      assertTrue("The ask price is incorrect: " + vol, 56889 != vol);
   }
}
