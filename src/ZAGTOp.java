public class ZAGTOp<E> extends BinOp{

    public static int priority = 5;
    public static String symbol = ">";

    public ZAGTOp(Expression op1, Expression op2)
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
        return Boolean.valueOf(((Integer) operand1.calculate()).intValue() > ((Integer) operand2.calculate()).intValue());
    }

    // doesn't matter the order of operands
    public String toString()
    {
        return operand1.toString(priority, false) + " "
                + symbol + " " + operand2.toString(priority, false);
    }

    protected String toString(int prior, boolean isLeftOp) {
        if (prior < priority)
        {
            return "( " + operand1.toString(priority, false)
                    + " " + symbol + " " + operand2.toString(priority, false) + " )";
        }
        if (prior > priority)
        {
            return toString();
        }
        if (isLeftOp)
        {
            return toString();
        }
        return "( " + operand1.toString(priority, false) + " " + symbol + " " + operand2.toString(priority, false) + " )";
    }
}