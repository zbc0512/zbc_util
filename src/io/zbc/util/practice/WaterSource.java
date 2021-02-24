package io.zbc.util.practice;

import static io.zbc.util.PrintUtil.print;

public class WaterSource {
    private String s;

    WaterSource() {
        print("WaterSource");
        s = "Constructed";
    }

    public String toString() {
        return s;
    }
}
