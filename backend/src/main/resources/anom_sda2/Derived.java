package anom_sda;

public static class Derived extends Base implements Yo {
    public void m1() {
        System.out.println("Not doing anything");
        super.m1();
    }
}