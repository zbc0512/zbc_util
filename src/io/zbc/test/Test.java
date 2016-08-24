package io.zbc.test;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
        String b = null;
        if(!"".equals(b)){
            System.out.println(b);
        }
        String a = "   ";
        if(a != null && !"".equals(a.trim()) && StringUtils.isNotEmpty(a)){
            System.out.println(a);
        }
    }
}
