package io.zbc.spider.spiders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bwgrt {

	public static String getDownloadUrl(String content) {
		Pattern pattern = Pattern.compile("(?<=download\":\").*(?=\"};)");
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			return matcher.group(0).replace("\\", "");
		}
		return " no matcher...";
	}
}
