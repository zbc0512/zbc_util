package io.zbc.practice;

import static io.zbc.util.Print.*;

public class Chess extends BoardGame {

    Chess() {
        super(11);
        print("Chess");
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
    }

}
