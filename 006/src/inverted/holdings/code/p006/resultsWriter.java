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

import inverted.holdings.code.p006.util.RequiresTimeException;

import java.util.List;

public interface ResultsWriter
{
    public static ResultsWriter getInstance(String writer)
    {
        ResultsWriter rw = null;
        switch (writer)
        {
            case "csv":
                rw = new Csv();
                break;
        }
        return rw;
    }

    // TODO: Decide if I want this method
    // This will store a specified set of quotes to a file.
//    public void writeQuotes(StockQuoteList quotes, List<StockAttributeType> attributes) throws RequiresTimeException;

    // This will append a specified set of quotes to a file; it assumes that the file only contains quotes from an
    // earlier time period which are already correctly sorted. If a TIME attribute is not provided it assumes the
    // current time.
    public void appendQuotes(StockQuoteList quotes, List<StockAttributeType> attributes) throws RequiresTimeException;
}
