public class ZAEQOp<E> extends BinOp{

    public static int priority = 6;
    public static String symbol = "==";

    public ZAEQOp(Expression op1, Expression op2)
    {
        super(op1, op2);
    }

    @Override
    public void evaluate()
    {
        System.out.println(calculate().toString());
    }

    @Override
    public Boolean calculate()
    {
        return Boolean.valueOf(((Integer) operand1.calculate()).intValue() == ((Integer) operand1.calculate()).intValue());
    }

    // we don't really care if the operand is right or left
    public String toString()
    {
        return operand1.toString(priority, false) + " " + symbol + " " + operand2.toString(priority, false);
    }

    protected String toString(int prior, boolean isLeftOp)
    {
        if (prior < priority)
        {
            return "( " + operand1.toString(priority, false) + " " + symbol + " " + operand2.toString(priority, false) + " )";
        }
        if (prior > priority)
        {
            return toString();
        }
        if (isLeftOp)
        {
            return toString();
        }
        return "( " + operand1.toString(priority, false) + " "
                + symbol + " " + operand2.toString(priority, false) + " )";
    }
}