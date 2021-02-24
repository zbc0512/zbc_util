package io.zbc.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIO {
    public static void main(String[] args) {

        // 输入/输出的txt文件路径
        String folder = "C:\\Users\\z\\Desktop\\aaa\\js_log\\";
        // 输入/输出的txt文件名（无后缀）
        String fileName = "debug_for_perform2016-07-27-7026";
        File fileOld = new File(folder + fileName + ".log");
        File fileNew = new File(folder + fileName + "_3s.txt");
        String str = "";
        try {
            // 读
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileOld)));
            // 写
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileNew)));
            String s = "";
            String[] list;
            while ((s = reader.readLine()) != null) {
                list = s.split("] ");
                if(list.length > 8){
                    String tmp = list[8].substring(1);
                    if(Integer.parseInt(tmp) > 2999){
                        str += s + "\n";
                    }
                }
            }
            writer.write(str);
            System.out.println(str);
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
