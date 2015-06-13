package inverted.holdings.code.p006;

import inverted.holdings.code.p006.util.RequiresTimeException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by pilot on 6/9/15.
 * The file name will be determined by the date; if it already exists, data will be appended to the file. If the
 * attributes do not include StockAttributeType.TIME, then the writer will throw an exception.
 */
public class Csv implements ResultsWriter
{
    private static String baseFileName  = "quoteRecords";
    private static String fileExtension = ".csv";

    @Override public void appendQuotes(StockQuoteList quotes, List<StockAttributeType> attributes)
            throws RequiresTimeException
    {
        // Make sure there are not multiple of the same quote if it is being supplied without a time; if so, except.
        if (!attributes.contains(StockAttributeType.TIME))
        {
            // TODO: check for duplicates.
            // TODO: if there aren't duplicates, insert a TIME set to NOW

            throw new RequiresTimeException();
        }

        // Sort by time. Praise be to anonymous inner classes.
        Collections.sort(quotes, new Comparator<Quote>()
        {
            @Override public int compare(Quote q1, Quote q2)
            {
                Date d1 = (Date) q1.get(StockAttributeType.TIME);
                Date d2 = (Date) q2.get(StockAttributeType.TIME);
                return d1.compareTo(d2);
            }
        });

        FileWriter fw = null;
        try
        {
            String date = String.valueOf(Calendar.getInstance().getTime().getDay()) +
                          String.valueOf(Calendar.getInstance().getTime().getMonth() +
                          String.valueOf(Calendar.getInstance().getTime().getYear()));

            fw = new FileWriter(baseFileName + date + fileExtension, true);

            for (Quote q : quotes)
            {
                fw.append(q.toString() + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != fw) try
            {
                fw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
