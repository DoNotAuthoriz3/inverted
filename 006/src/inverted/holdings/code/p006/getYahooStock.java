package inverted.holdings.code.p006;
/* by: badmonkey */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class getYahooStock
{
	public getYahooStock() {}
	
	public static void main(String[] args)
	{	
		jout j = new jout();
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line, baseURL = new String("http://finance.yahoo.com/d/quotes.csv?s");
	    Date newTime;
	    Date lastTime = new Date();
	    stockQuoteList quotesToGet = new stockQuoteList();
	    resultsWriter outPutter = new resultsWriter("wesults.csv"); 
	    
	    while(true)
	    {
	    	newTime = Calendar.getInstance().getTime();
	    	lastTime = (newTime.getSeconds() == lastTime.getSeconds()) ? lastTime : newTime;
	    //	lastTime = Calendar.getInstance().getTime();
	    	System.out.println("Second is " + lastTime.getSeconds() + " and second mod 5 is " + 
	    			(lastTime.getSeconds() % 5) + " and 0 == second mod five is " + (0 == (lastTime.getSeconds() % 5)));
	    	
		    if(0 == (lastTime.getSeconds() % 5))
		    {
		    	try 
		    	{
			    	for (stockQuote currQuote = quotesToGet.getNext(); quotesToGet.fin() ; )
			    	{
					    try 
					    {
					        url = new URL(baseURL + currQuote.ticker + "&f=ngb3b2hv");
					        is = url.openStream();  // throws an IOException
					        br = new BufferedReader(new InputStreamReader(is));
				
					        while ((line = br.readLine()) != null) 
					        	{ System.out.println(line); outPutter.concat(line); }				        
					    } 
					    catch (MalformedURLException mue) 
					    	{ mue.printStackTrace(); } 
					    catch (IOException ioe) 
					    	{ j.outln("Unable to connect to the website"); ioe.printStackTrace(); } 
					    catch (Exception e) 
					    	{ e.printStackTrace(); } 
					    finally 
					    {
					        try { if (is != null) is.close(); } 
					        catch (IOException ioe) { }
					    }
			    	}
		    	}
		    	catch (Exception e) { }
		    }
		    
		    try { Thread.sleep(300); } 
		    catch (Exception e) { j.outln("Thread underwent insomnia: "); e.printStackTrace(); };
	    } 
	}
}
