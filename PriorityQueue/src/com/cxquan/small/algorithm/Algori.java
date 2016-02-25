package com.cxquan.small.algorithm;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class Algori {

	// 反转字符串
	public static String reverseString(String string) {
		if (string==null || string.length() <= 1) {
			return string;
		}
		// 递归反转
//		return reverseString(string.substring(1)) + string.charAt(0);
		
		// StringBuilder
		return new StringBuilder(string).reverse().toString();
		
		// stack
//		Stack<Character> stack = new Stack<Character>();
//		int index = 0;
//		while (index < string.length()) {
//			stack.push(string.charAt(index++));
//		}
//		StringBuilder rst = new StringBuilder("");
//		while (!stack.isEmpty()) {
//			rst.append(stack.pop());			
//		}
//		return rst.toString();
	}
	
	// 寻找第一个不重复的字符
	public static char firstNonRepeatedChar(String string) {
		Set<Character> set = new LinkedHashSet();
		char a;
		for(int i=0; i<string.length();i++) {
			a = string.charAt(i);
			if(set.contains(a)){
				set.remove(a);
				continue;
			}
			set.add(a);
		}
		return set.iterator().next();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "asdsa";
		System.out.println(reverseString(string)=="asdsa"?1:0);
		
		System.out.println(reverseString(string).equals(string)? 1 : 0);
		
		System.out.println(firstNonRepeatedChar(string));

	}

}
