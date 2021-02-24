package io.zbc.util.practice;

import static io.zbc.util.PrintUtil.print;

public class Chess extends BoardGame {

    Chess() {
        super(11);
        print("Chess");
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
    }

}
