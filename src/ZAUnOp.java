public abstract class ZAUnOp<E,K> extends  ZAExpression {
    Expression op;

    public ZAUnOp(Expression op)
    {
        this.op = op;
    }
}
