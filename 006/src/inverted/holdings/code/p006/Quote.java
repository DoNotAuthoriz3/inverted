package inverted.holdings.code.p006;

import inverted.holdings.code.p006.util.IllegalFormatException;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static inverted.holdings.code.p006.util.Jout.joutln;

public class Quote extends HashMap
{
    private static DateFormat dateFormat = null;

    public static void setDateFormat(DateFormat df)
    {
        dateFormat = df;
    }

    /**
     * All vehicles have a ticker. It's nice if they have a name too, but I guess we can let that slide.
     *
     * @param ticker
     */
    public Quote(String ticker)
    {
        this.put(StockAttributeType.TICKER, ticker);
    }

    /**
     * All vehicles have a ticker, and optionally a name to describe. The name isn't actually strictly necessary for
     * coding and takes up space, so it may be best not to store it.
     *
     * @param ticker
     * @param name
     */
    public Quote(String ticker, String name)
    {
        this.put(StockAttributeType.TICKER, ticker);
        this.put(StockAttributeType.NAME, name);
    }

    /**
     * All quotes must have a ticker
     */
    private Quote() {}

    public void insert(StockAttributeType type, String yResponse)
            throws FileNotFoundException, IllegalFormatException
    {
        Object datum = null;
        try
        {
            String dataType = YahooTagMap.getInstance().getTypeName(type);

            switch (dataType)
            {
                case "java.lang.String":
                    datum = yResponse;
                    break;
                case "java.lang.Double":
                    datum = Double.parseDouble(yResponse);
                    break;
                case "java.lang.Float":
                    datum = Float.parseFloat(yResponse);
                    break;
                case "java.lang.Long":
                    datum = Long.parseLong(yResponse);
                    break;
                case "java.util.Date":
                    Date d = null;

                    try
                    {
                        d = dateFormat.parse(yResponse);
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }

                    datum = d;
                    break;
            }

            if (null == datum)
            {
                throw new IllegalFormatException();
            }

            this.put(type, datum);
        }
        catch (NumberFormatException e)
        {
            joutln("ERROR: response contained " + yResponse + " for " + type);
        }
        catch (IllegalArgumentException e)
        {
            joutln
                    ("ERROR: Specified yResponse does not exist in list of enumerated yResponse types " + type);
            throw e;
        }
        catch (FileNotFoundException e)
        {
            joutln("ERROR: Unable to open the stock attributes definition file " + type);
            throw e;
        }
    }

    @Override public String toString()
    {
        String myAttributes = "";
        for (Object o : values())
            myAttributes = myAttributes + o +  ",";

        // Strip final comma
        if (!"".equals(myAttributes))
            myAttributes.substring(0, myAttributes.length() - 1);

        return super.toString();
    }
}

