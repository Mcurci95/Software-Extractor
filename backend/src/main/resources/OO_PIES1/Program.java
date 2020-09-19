public class Program {
    public static void main(String[] args) {
        System.out.println("OO-PIES validation code");
        Class1 c1 = new Class1(true);

        c1.myFunc1(3, 4);
        if (c1.getmyBool())
            System.out.println(c1.getmyInt());
        Class3 c3 = new Class3();
        c3.myFunc();
        c3.gather();
    }
}