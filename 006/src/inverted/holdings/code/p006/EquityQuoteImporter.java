package inverted.holdings.code.p006;

import java.util.Calendar;

public interface EquityQuoteImporter
{
   public static EquityQuoteImporter getImporter(String importSource)
   {
      EquityQuoteImporter newImporter = null;
      switch (importSource)
      {
         case "yahoo" :
            newImporter = new ImportYahooStock();
             break;
         case "csv" :
            newImporter = (EquityQuoteImporter) new ImportCsvStock();
             break;
      }
      return newImporter;
   }
   
   public Quote getQuote(String ticker);
   
   public Quote getQuote(String ticker, Calendar time);
}
