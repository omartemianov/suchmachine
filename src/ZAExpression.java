public abstract class ZAExpression <E,K>{

    abstract void evaluate();

    abstract E calculate();

    protected abstract String toString(int prior, boolean isLeftOp);

}
