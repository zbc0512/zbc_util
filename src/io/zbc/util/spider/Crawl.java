package io.zbc.util.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.zbc.util.spider.spiders.Bwgrt;
import io.zbc.util.spider.spiders.Zhihu;

public class Crawl {
	public static void main(String[] args) {
		// ArrayList list = recommendations();
		List<String> urls = getDownloadUrl();
		for (String url : urls) {
			System.out.println(url);
		}
	}

	static List<String> getDownloadUrl() {
		List<String> downloadUrls = new ArrayList<String>();
		File urlFile = new File("DownloadUrl.txt");
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(urlFile);
			writer = new BufferedWriter(fw);
			for (int i = 17; i <= 7062; i++) {
				String url = "http://p.bwgrt.com/d/";
				url += i;
				String content = Spider.sendGet(url);
				String downloadUrl = "http://p.bwgrt.com" + Bwgrt.getDownloadUrl(content);
				// 写入
				for (int j = 0; j < downloadUrl.length(); j++) {
					char c = downloadUrl.charAt(j);
					writer.write(c);
				}
				writer.newLine();// 换行
				writer.flush();
				downloadUrls.add(downloadUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return downloadUrls;
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
