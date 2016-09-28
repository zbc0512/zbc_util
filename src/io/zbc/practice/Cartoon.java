package io.zbc.practice;

import static io.zbc.util.PrintUtil.print;

public class Cartoon extends Drawing {
    Cartoon() {
        print("Cartoon");
    }

    public static void main(String[] args) {
        Cartoon cartoon = new Cartoon();
    }
}
