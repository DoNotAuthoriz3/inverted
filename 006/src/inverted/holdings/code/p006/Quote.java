package inverted.holdings.code.p006;

public interface Quote
{
    public Object getAttribute(StockAttributeType attr);
    public void setAttribute(StockAttributeType attr, Object value);
}
