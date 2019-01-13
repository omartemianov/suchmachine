public abstract class BinOp<E> extends Expression
{
    Expression operand1;  // Operand 1
    Expression operand2;  // Operand 2

    public BinOp(Expression operand1, Expression operand2)
    {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }
}
