package io.zbc.practice;

import static io.zbc.util.Print.*;

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
