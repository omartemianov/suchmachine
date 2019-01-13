public class ZAAndOp extends ZABinOp{
    public static int priority = 11;
    public static String symbol = "∧";

    public ZAAndOp(Expression operand1, Expression operand2)
    {
        super(operand1, operand2);
    }

    @Override
    public void evaluate()
    {
        System.out.println(calculate().toString());
    }

    @Override
    public Boolean calculate()
    {
        return Boolean.valueOf(((Boolean) operand1.calculate()).booleanValue() && ((Boolean) operand2.calculate()).booleanValue());
    }

    public String toString()
    {
        return operand1.toString(priority, true) + " " + symbol
                + " " + operand2.toString(priority, false);
    }

    protected String toString(int prior, boolean isLeftOp)
    {
        if (prior < priority)
        {
            return "( " + operand1.toString(priority, true) + " " + symbol
                    + " " + operand2.toString(priority, false) + " )";
        }
        if (prior > priority)
        {
            return toString();
        }
        if (isLeftOp)
        {
            return toString();
        }
        return "( " + operand1.toString(priority, true) + " " + symbol
                + " " + operand2.toString(priority, false) + " )";
    }
}
