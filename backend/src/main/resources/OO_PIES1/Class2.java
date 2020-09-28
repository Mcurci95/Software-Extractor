package com.sotwareextractor.cecs547.OO_PIES1;

public class Class2 extends Class1 {
    private int myInt2;
    public int AddMine2() {
        return myInt2 + super.getmyInt();
    }

    public Class2() {
        super(true);
        myInt2 = 5;
    }
}