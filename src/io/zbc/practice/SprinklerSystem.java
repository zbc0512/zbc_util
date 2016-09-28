package io.zbc.practice;

import static io.zbc.util.PrintUtil.print;

public class SprinklerSystem {
    private String v1, v2, v3, v4;
    private WaterSource source = new WaterSource();
    private int i;
    private float f;

    public String toString() {
        return "v1 = " + v1 + " " + "v2 = " + v2 + " " + "v3 = " + v3 + " " + "v4 = " + v4 + "\n"
                + "i = " + i + " " + "f = " + f + " " + "source = " + source;
    }

    public static void main(String[] args) {
        SprinklerSystem s = new SprinklerSystem();
        print(s);
    }
}
