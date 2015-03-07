package inverted.holdings.code.p006;
/* This class is badly named.
 * TODO: something about that. */

import java.util.HashMap;
import java.util.Map;

public class StockQuote implements Quote
{
	String ticker, name, lang;
    Map<StockAttributeType, StockAttribute> attributes = new HashMap<>();

	public StockQuote(String ticker, String name)
	{
		this.ticker = ticker;
        this.name = name;
	}

    @Override
    public Object getAttribute(StockAttributeType attr){ return attributes.get(attr); }

    @Override
    public void setAttribute(StockAttributeType attr, Object value)
    {
        Class type = value.getClass();
        attributes.put(attr, new StockAttribute<>(attr, value));
    }
}
