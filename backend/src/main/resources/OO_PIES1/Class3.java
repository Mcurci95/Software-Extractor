package com.sotwareextractor.cecs547.OO_PIES1;

public class Class3 {
    Class1 myClass1Obj;
    private int mynum1, mynum2;
    public Class3() {
        myClass1Obj = new Class1(false);
        mynum1 = 14;
        mynum2 = 5;
    }
    public void myFunc1() {
        myClass1Obj.myFunc1(3, 8);
    }
    public void gather() {
        int W = myClass1Obj.getmyInt();
        myFunc1();
        System.out.println("Hello from Class 3");
        mynum1  = W;
    }
}