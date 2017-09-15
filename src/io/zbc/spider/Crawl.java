package io.zbc.spider;

import java.util.ArrayList;

import io.zbc.spider.spiders.Zhihu;

public class Crawl {
    public static void main(String[] args) {
        ArrayList list = recommendations();
    }

    static ArrayList<Zhihu> recommendations() {
        // 定义即将访问的链接
        String url = "https://www.zhihu.com/explore/recommendations";
        // 访问链接并获取页面内容
        String content = Spider.sendGet(url);
        // 获取该页面的所有的知乎对象
        ArrayList<Zhihu> zhihus = Zhihu.getRecommendations(content);
        // 打印结果
        for (Zhihu zhihu : zhihus) {
            System.out.println(zhihu);
        }
        return zhihus;
    }

    static ArrayList<Zhihu> zhihu() {
        // 定义即将访问的链接
        String url = "http://www.zhihu.com/explore/recommendations";
        // 访问链接并获取页面内容
        String content = Spider.sendGet(url);
        // 获取该页面的所有的知乎对象
        ArrayList<Zhihu> zhihus = Zhihu.getZhihu(content);
        // 打印结果
        for (Zhihu zhihu : zhihus) {
            System.out.println(zhihu);
        }
        return zhihus;
    }
}
