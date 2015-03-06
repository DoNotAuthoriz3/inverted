package inverted.holdings.code.p006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ImportYahooStock implements EquityQuoteImporter
{
   String         line        = "";
   String         baseURL     = new String("http://finance.yahoo.com/d/quotes.csv?s=");
   URL            url;
   InputStream    is          = null;
   BufferedReader br;
   stockQuoteList quotesToGet = new stockQuoteList();
   resultsWriter  outPutter   = new resultsWriter("wesults.csv");
   
   public ImportYahooStock() { super(); }
   
   @Override
   public Quote getQuote(String ticker)
   {
      Quote quote = null;
      
      try
      {
         url = new URL(baseURL + ticker + urlBuilder());
         is = url.openStream(); // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));
         
         while ((line = br.readLine()) != null)
         {
            System.out.println(line);
            outPutter.concat(line);
         }
      } catch (Exception e)
      {
         // TODO: research. Fix. I mean how should I handle this anyhow? Some kind of elaborate network error-handling
         // class? Basically this needs a lot of thought, I think.
      }
      
      return quote;
   }
   
   @Override
   public Quote getQuote(String ticker, Calendar time)
   {
      Quote quote = null;
      
      return quote;
   }
   
   private String urlBuilder()
   {
      
      return "&f=ngb3b2hv";
   }
}
