package inverted.holdings.code.p006;
/* This class is badly named.
 * TODO: something about that. */

import java.util.HashMap;
import java.util.Map;

public class stockQuote implements Quote
{
	String ticker, name, lang;
    Map<StockAttributeType, StockAttribute> attributes = new HashMap<>();
	Float ask, bid;
    Integer vol;
	
	public stockQuote()
	{
		// TODO Auto-generated constructor stub
	}

    @Override
    public Object getAttribute(StockAttributeType attr)
    {
        switch (attr) {
            case ASK :
                return ask;
            case BID :
                return bid;
            case VOLUME :
                return vol;
        }

        return null;
    }

    @Override
    public void setAttribute(StockAttributeType attr, Object value) { attributes.put(attr, new StockAttribute(attr)); }
}
