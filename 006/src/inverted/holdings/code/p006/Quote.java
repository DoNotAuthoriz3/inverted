package inverted.holdings.code.p006;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Quote extends HashMap
{
    /**
     * All vehicles have a ticker.
     *
     * @param ticker
     */
    public Quote(String ticker)
    {
        this.put(StockAttributeType.TICKER, ticker);
    }

    /**
     * All vehicles have a ticker, and optionally a name to describe. The name isn't actually strictly necessary for coding
     * and takes up space, so it may be best not to store it.
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

    public void insert(StockAttributeType type, String tag) throws FileNotFoundException
    {
        Object datum = null;
        try
        {
            String dataType = YahooTagMap.getInstance().getTypeName(type);

            switch (dataType)
            {
                case "java.lang.Double":
                    datum = Double.parseDouble(tag);
                    break;
                case "java.lang.String":
                    datum = tag;
                    break;
                case "java.lang.Float":
                    datum = Float.parseFloat(tag);
                    break;
                case "java.lang.Integer":
                    datum = Integer.parseInt(tag);
                    break;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("ERROR: response contained " + tag + " for " + type);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(
                    "ERROR: Specified tag does not exist in list of enumerated tag types " + type);
            throw e;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(
                    "ERROR: Unable to open the stock attributes definition file " + type);
            throw e;
        }
        finally
        {
            this.put(type, datum);
        }
    }
}

