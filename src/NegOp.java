public class NegOp<E> extends UnOp {
    public static int priority = 1;
    public static String symbol = "¬";

    public NegOp(Expression operand)
    {
        super(operand);
    }

    @Override
    public void evaluate()
    {
        System.out.println(calculate().toString());
    }

    @Override
    public Boolean calculate()
    {
        return !(Boolean.valueOf(((Boolean) op.calculate()).booleanValue()));
    }

    public String toString()
    {
        return symbol + op.toString(priority, false);
    }

    protected String toString(int prior, boolean isLeftOp)
    {
        if (prior > priority)
        {
            return toString();
        }
        return "(" + symbol + op.toString(priority, false) + ")";
    }

}
