package inverted.holdings.code.p006;
/* This class is badly named.
 * TODO: something about that. */

public class stockQuote implements Quote
{
	String ticker, name, lang;
	float ask, bid;
    int vol;
	
	public stockQuote()
	{
		// TODO Auto-generated constructor stub
	}

    @Override
    public float getAsk() {
        return ask;
    }

    @Override
    public float getBid() {
        return bid;
    }

    @Override
    public int getVol() {
        return vol;
    }
}
