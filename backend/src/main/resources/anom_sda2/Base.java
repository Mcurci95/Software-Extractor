package anom_sda;

public abstract class Base {
    private int x,y;
    public void m1() {
        x = 7;
    }
    public void m2() {
        y = x + 5;
        System.out.println("In Base.m2, y=" + y);
    }
}