public abstract class UnOp<E> extends Expression
{
    Expression op;

    public UnOp(Expression op)
    {
        this.op = op;
    }
}
