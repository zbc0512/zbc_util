package io.zbc.util.spider.spiders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.zbc.util.spider.Spider;

public class Zhihu {
    public String question;// 问题
    public String questionDescription;// 问题描述
    public String zhihuUrl;// 网页链接
    public ArrayList<String> answers;// 存储所有回答的数组

    // 初始化数据
    public Zhihu(String url) {
        question = "";
        questionDescription = "";
        zhihuUrl = "";
        answers = new ArrayList<String>();
        // 判断url是否合法
        if (getRealUrl(url)) {
            System.out.println("正在抓取" + zhihuUrl);
            // 根据url获取该问答的细节
            String content = Spider.sendGet(zhihuUrl);
            Pattern pattern;
            Matcher matcher;
            // 匹配标题
            pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
            matcher = pattern.matcher(content);
            if (matcher.find()) {
                question = matcher.group(1);
            }
            // 匹配描述
            pattern = Pattern.compile("zh-question-detail.+?<div.+?>(.*?)</div>");
            matcher = pattern.matcher(content);
            if (matcher.find()) {
                questionDescription = matcher.group(1);
            }
            // 匹配答案
            pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
            matcher = pattern.matcher(content);
            boolean isFind = matcher.find();
            while (isFind) {
                answers.add(matcher.group(1));
                isFind = matcher.find();
            }
        }
    }

    @Override
    public String toString() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/spider?useUnicode=true&characterEncoding=UTF-8";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String jdbcUser = "root";
        String jdbcPassword = "0512";
        String sql = "insert into zhihu (question, questionDescription, zhihuUrl, answers) values ('"
                + question + "', '" + questionDescription + "', '" + zhihuUrl + "', '" + answers
                + "')";
        Connection conn = null;
        try {
            new com.mysql.jdbc.Driver();
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            Statement st = conn.createStatement();
            int result = st.executeUpdate(sql);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "问题：" + question + "\n" + "描述：" + questionDescription + "\n" + "链接：" + zhihuUrl
                + "\n回答：" + answers + "\n";
    }

    // 根据自己的url抓取自己的问题和描述和答案
    public boolean getAll() {
        return true;
    }

    // 处理url
    boolean getRealUrl(String url) {
        // 将http://www.zhihu.com/question/22355264/answer/21102139
        // 转化成http://www.zhihu.com/question/22355264
        // 否则不变
        Pattern pattern = Pattern.compile("question/(.*?)/");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            zhihuUrl = "https://www.zhihu.com/question/" + matcher.group(1);
        } else {
            return false;
        }
        return true;
    }

    // 获取所有的编辑推荐的知乎内容
    public static ArrayList<Zhihu> getRecommendations(String content) {
        // 预定义一个ArrayList来存储结果
        ArrayList<Zhihu> results = new ArrayList<Zhihu>();
        // 用来匹配url，也就是问题的链接
        Pattern pattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
        Matcher matcher = pattern.matcher(content);
        // 是否存在匹配成功的对象
        Boolean isFind = matcher.find();
        while (isFind) {
            // 定义一个知乎对象来存储抓取到的信息
            Zhihu zhihuTemp = new Zhihu(matcher.group(1));
            // 添加成功匹配的结果
            results.add(zhihuTemp);
            // 继续查找下一个匹配对象
            isFind = matcher.find();
        }
        return results;
    }

    public static ArrayList<Zhihu> getZhihu(String content) {
        // 预定义一个ArrayList来存储结果
        ArrayList<Zhihu> results = new ArrayList<Zhihu>();
        // 用来匹配标题
        Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
        Matcher questionMatcher = questionPattern.matcher(content);
        // 用来匹配url，也就是问题的链接
        Pattern urlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
        Matcher urlMatcher = urlPattern.matcher(content);
        // 问题和链接要均能匹配到
        boolean isFind = questionMatcher.find() && urlMatcher.find();
        while (isFind) {
            // 定义一个知乎对象来存储抓取到的信息
            Zhihu zhuhuTemp = new Zhihu(urlMatcher.group(1));
            zhuhuTemp.question = questionMatcher.group(1);
            zhuhuTemp.zhihuUrl = "https://www.zhihu.com" + urlMatcher.group(1);
            // 添加成功匹配的结果
            results.add(zhuhuTemp);
            // 继续查找下一个匹配对象
            isFind = questionMatcher.find() && urlMatcher.find();
        }
        return results;
    }
}