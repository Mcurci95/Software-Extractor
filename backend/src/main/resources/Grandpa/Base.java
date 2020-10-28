package ic_tester;

public abstract class Base implements YYYY {
    String s;
    abstract  public void set();
    public void use() {
        System.out.println("S=" + s);
    }
}