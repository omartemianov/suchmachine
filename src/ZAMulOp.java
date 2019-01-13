public class ZAMulOp<E,K> extends ZABinOp {
    public static int priority = 2;

    public ZAMulOp(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }

    @Override
    public void evaluate()
    {
        System.out.println(calculate().toString());
    }

    @Override
    public Integer calculate()
    {
        return Integer.valueOf(((Integer) operand1.calculate()).intValue() * ((Integer) operand2.calculate()).intValue());
    }

    public String toString()
    {
        return operand1.toString(priority, true) + " * " + operand2.toString(priority, false);
    }

    protected String toString(int prior, boolean isLeftOp)
    {
        if (prior < priority)
        {
            return "( " + operand1.toString(priority, true)
                    + " + " + operand2.toString(priority, false) + " )";
        }
        if (prior > priority)
        {
            return toString();
        }
        if (isLeftOp)
        {
            return toString();
        }
        return "( " + operand1.toString(priority, true)
                + " + " + operand2.toString(priority, false) + " )";
    }
}
