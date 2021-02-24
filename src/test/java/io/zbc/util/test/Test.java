package io.zbc.util.test;

public class Test {
    public static void main(String[] args) {
        System.out.println(test());
    }

    // 使用char追加Character
    public static String test() {
        StringBuffer strb = new StringBuffer("aaa");
        strb.append('\'');
        return strb.toString();
    }

    // 保留Stack Trace
    public static String exception() {
        StringBuffer strb = null;
        String str = "aa";
        try {
            str = strb.toString();
        } catch (Exception e) {// 抛出空指针异常
            // throw new RuntimeException("context");// 会丢失空指针异常
            throw new RuntimeException("context", e);// 不会丢失
        }
        return str;
    }
}
