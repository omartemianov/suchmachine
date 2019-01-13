
public class ZAConst<E> extends Expression {
    E konst;

    void evaluate()
    {
        System.out.println(konst.toString());
    }

    public ZAConst(E konst)
    {
        this.konst = konst;
    }

    public ZAConst(boolean bool)
    {
        this.konst = (E) Boolean.valueOf(bool);
    }

    @Override
    protected String toString(int prior, boolean isLeftOp) {
        return konst.toString();
    }

    @Override
    public String toString()
    {
        if (konst instanceof Boolean)
        {
            return String.valueOf(((Boolean) konst).booleanValue());
        }
        if (konst instanceof Integer)
        {
            return String.valueOf(((Integer) konst).intValue());
        }
        return "not valid Expression";
    }

    E calculate()
    {
        return konst;
    }
}
