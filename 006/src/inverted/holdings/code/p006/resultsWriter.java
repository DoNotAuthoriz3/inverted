package inverted.holdings.code.p006;
/* 
 * This class records the provided information about a
 * stock in a file.
 * 
 * TODO: Prototype for this class will just take a whole string
 * representing the result and stick that in a single
 * CSV entry and concatenate that on to a file.
 * 
 * TODO: This class should eventually take a dynamic set of fields
 * associated with a stock result and write them to a file
 * in a more useful way--whether that's some kind of smarter CSV
 * or another data setup entirely. */

public interface ResultsWriter
{
    // This will add whatever
    public void writeQuote(Quote quote);
}
