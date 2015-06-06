package inverted.holdings.code.p006;

import inverted.holdings.code.p006.util.IllegalFormatException;

import java.util.List;

public interface EquityQuoteImporter
{
    public static EquityQuoteImporter getImporter(String importSource)
    {
        EquityQuoteImporter newImporter = null;
        switch (importSource)
        {
            case "yahoo":
                newImporter = new ImportYahooStock();
                break;
            case "csv":
                newImporter = (EquityQuoteImporter) new ImportCsvStock();
                break;
        }
        return newImporter;
    }

    public Quote getQuote(String ticker, List<StockAttributeType> tags) throws IllegalFormatException;

    public List<Quote> getQuotes(StockQuoteList quotes, List<StockAttributeType> tags)
            throws IllegalFormatException;
}
