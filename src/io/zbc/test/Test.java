package io.zbc.test;

import static io.zbc.util.Print.*;
import static io.zbc.util.StringUtil.*;

public class Test {
    public static void main(String[] args) {
        Object a = "test";
        if (isNotEmpty(a.toString())) {
            print(a);
        }
    }
}
