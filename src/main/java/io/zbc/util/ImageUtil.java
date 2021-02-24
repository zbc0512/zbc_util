package io.zbc.util;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class ImageUtil {
    public static void main(String[] args) {
        System.out.println(getImageStr("D:\\aaa\\JVM运行时数据区.png"));
    }

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imgFile
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            // 读取图片字节数组
            if (StringUtils.isNotBlank(imgFile)) {
                in = new FileInputStream(imgFile);
                if (in != null) {
                    data = new byte[in.available()];
                    in.read(data);
                    in.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr
     * @param imgFile
     * @return
     * @throws Exception
     */
    public static BufferedImage generateImage(String imgStr) throws IOException {
        if (imgStr == null) { // 图像数据为空
            return null;
        }
        Decoder decoder = Base64.getDecoder();
        // Base64解码
        byte[] b = decoder.decode(imgStr);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        ByteArrayInputStream in = new ByteArrayInputStream(b); // 将b作为输入流；
        BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
        return image;
    }

}

