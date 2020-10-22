public class ObjectAdapter extends Base1 {
    Base2 b2;
    public ObjectAdapter(Base2 b) {
        b2 = b;
    }
    @Override
    public void op1() {
        b2.op1;
    }
    @Override
    public void op2() {
        b2.op2();
    }
}