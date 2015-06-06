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

    public void insert(StockAttributeType type, String yResponse) throws FileNotFoundException
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
            }

            this.put(type, datum);
        }
        catch (NumberFormatException e)
        {
            System.out.println("ERROR: response contained " + yResponse + " for " + type);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(
                    "ERROR: Specified yResponse does not exist in list of enumerated yResponse types " + type);
            throw e;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(
                    "ERROR: Unable to open the stock attributes definition file " + type);
            throw e;
        }
    }
}

