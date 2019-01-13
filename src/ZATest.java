public class ZATest {
    public static void main(String[] args)
    {

        System.out.println((new ZAEQOp<Boolean>(new MulOp<Integer>(new Const<Integer>(3),
                new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2))),
                new Const<Integer>(9))));
        System.out.println("Is ");
        new ZAEQOp<Boolean>(new MulOp<Integer>(new Const<Integer>(3),
                new AddOp<Integer>(new Const<Integer>(1), new Const<Integer>(2))),
                new Const<Integer>(9)).evaluate();
        System.out.println((new ZAGTOp<Boolean>(new AddOp<Integer>(new Const<Integer>(4),
                new DivOp(new Const<Integer>(4), new Const<Integer>(2))),
                new Const<Integer>(5))));
        System.out.println("Is ");
        new ZAGTOp<Boolean>(new AddOp<Integer>(new Const<Integer>(4),
                new DivOp(new Const<Integer>(4), new Const<Integer>(2))),
                new Const<Integer>(5)).evaluate();
        System.out.println((new ZAGTOp<Boolean>(new AddOp<Integer>(new Const<Integer>(4),
                new DivOp(new Const<Integer>(4), new Const<Integer>(2))),
                new Const<Integer>(7))));
        System.out.println("Is ");
        new ZAGTOp<Boolean>(new AddOp<Integer>(new Const<Integer>(4),
                new DivOp(new Const<Integer>(4), new Const<Integer>(2))),
                new Const<Integer>(7)).evaluate();




    }
}
