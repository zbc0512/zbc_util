package io.zbc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JsonUtil {

	/**
	 * @author zhangbaicheng
	 * @param 要拆分的多个json字符串
	 * @return 拆分后的json放在list中返回
	 */
	public static List<String> splitJson(String jsons) {
		List<String> jsonList = new ArrayList<>();
		int startIndex = 0;// 记录每次要截取字符串的位置
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < jsons.length(); i++) {
			if (jsons.charAt(i) == '{') {
				if (stack.isEmpty()) {
					startIndex = i;
				}
				stack.push(jsons.charAt(i));
			} else if (jsons.charAt(i) == '}') {
				stack.pop();
				if (stack.isEmpty()) {
					System.out.println(jsons.substring(startIndex, i + 1));
					jsonList.add(jsons.substring(startIndex, i + 1));
					startIndex = i + 1;
				}
			}
		}
		return jsonList;
	}

}
