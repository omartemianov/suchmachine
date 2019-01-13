public abstract class Expression <E>{

   abstract void evaluate();

    abstract E calculate();

    protected abstract String toString(int prior, boolean isLeftOp);

}
