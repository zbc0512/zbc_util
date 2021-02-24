package io.zbc.util.encoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class GBKtoUTF8 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 获取系统默认编码
        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
        // 输入/输出的txt文件路径
        String folder = "D:\\Workspaces\\eclipse\\zbc-util\\src\\io\\zbc\\encoding\\";
        File filea = new File(folder + "a.txt");
        File fileb = new File(folder + "b.txt");
        String str = "";
        try {
            // 以GBK编码格式读出
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filea), "GBK"));
            // 以UTF-8编码格式写入
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileb), "UTF-8"));
            String gbk = "";
            while ((gbk = reader.readLine()) != null) {
                str += gbk + "\n";
            }
            writer.write(getUTF8StringFromGBKString(str));
            System.out.println(str);
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * GBK->UTF-8
     * 
     * @param gbk编码格式的String
     * @return utf-8格式的String
     */
    public static String getUTF8StringFromGBKString(String gbkStr) {
        try {
            return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalError();
        }
    }

    /**
     * 返回UTF-8字节码
     * 
     * @param gbk编码格式的String
     * @return utf-8格式的字节码
     */
    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128 && m >= 0) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            return tmp;
        }
        return utfBytes;
    }

    /**
     * 获取字符串的编码格式
     * 
     * @param String
     * @return encode name
     */
    public static String getEncoding(String str) {
        String encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
            encode = "UTF-8";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
            encode = "GB2312";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
            encode = "ISO-8859-1";
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception) {
        }
        return "";
    }
}
