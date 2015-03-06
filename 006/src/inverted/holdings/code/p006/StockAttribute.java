package inverted.holdings.code.p006;

public class StockAttribute<T>
{
	StockAttributeType type;
    T value;

	public StockAttribute(StockAttributeType type)
	{
		this.type = type;
	}

    public StockAttribute(StockAttributeType type, T value)
    {
        this.type = type;
        this.value = value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return this.value;
    }
}
