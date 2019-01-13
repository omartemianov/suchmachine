public class ATest {
    public static void main(String[] args)
    {

        System.out.println(new NegOp<Boolean>(new AndOp<Boolean>(new Const<Boolean>(true),new OrOp<Boolean>(new
                Const<Boolean>(false), new Const<Boolean>(true)))));
        (new NegOp<Boolean>(new AndOp<Boolean>(new Const<Boolean>(true),new OrOp<Boolean>(new
                Const<Boolean>(false), new Const<Boolean>(true))))).evaluate();

        System.out.println((new MulOp<Integer>(new Const<Integer>(3), new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2)))));
        System.out.println("Is");
        (new MulOp<Integer>(new Const<Integer>(3), new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2)))).evaluate();

        System.out.println((new ZAEQOp<Boolean>(new MulOp<Integer>(new Const<Integer>(3), new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2))), new Const<Integer>(9))));

        (new ZAEQOp<Boolean>(new MulOp<Integer>(new Const<Integer>(3), new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2))), new Const<Integer>(9))).evaluate();

    }
}
