public abstract class ZABinOp<E,K> extends ZAExpression
{
    Expression operand1;  // Operand 1
    Expression operand2;  // Operand 2

    public ZABinOp(Expression operand1, Expression operand2)
    {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

}
