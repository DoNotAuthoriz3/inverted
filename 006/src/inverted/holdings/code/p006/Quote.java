package inverted.holdings.code.p006;
/* This class is badly named.
 * TODO: something about that. */


import java.util.HashMap;

public class Quote extends HashMap
{
    /**
     * All vehicles have a ticker and name to refer to them by. The name isn't actually strictly necessary for coding
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
}
