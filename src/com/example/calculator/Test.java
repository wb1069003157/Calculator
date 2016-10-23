package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test {
	String str=new String();
	public Test(String str) {
		this.str=str;
	}
	/*
	 * 将中缀表达式转为后缀表达式
	 */
//	List postfix() {
//		Stack<Object> s = new Stack<Object>();
//		List listIn = new ArrayList();
//		List listPost = new ArrayList();
//		List listIndex = new ArrayList();
//		listIndex.add(-1);
//		for (int i = 0; i < str.length(); i++) {
//			if (isOperator(str.charAt(i))) {
//				listIndex.add(i);
//			}
//		}
//		listIndex.add(str.length());
//		// System.out.println(listIndex);// 检验listIndex中的值
//		for (int i = 0; i < listIndex.size() - 1; i++) {
//			listIn.add(str.substring(
//					Integer.parseInt((listIndex.get(i).toString()) )+1,
//					Integer.parseInt(listIndex.get(i + 1).toString())      )  );
//			System.out.println(listIn);
//			if (i != listIndex.size() - 2)
//				listIn.add(str.substring(Integer.parseInt(listIndex.get(i + 1).toString()),
//						Integer.parseInt((listIndex.get(i + 1))
//						.toString()) + 1));
//		}
//		listIn.add("#");
//		// System.out.println(listIn);// 检验listIn中的值
//
//		String ch = "#", ch1;
//		s.push(ch);
//		for (int i = 0; i < listIn.size() && s.isEmpty() == false; i++) {
//			ch = (String) listIn.get(i);
//			if (isOperator((String) ch) == false) {
//				listPost.add(ch);
//			} else {
//				ch1 = (String) s.peek();
//				if (getisp((String) ch1) < geticp((String) ch)) {
//					s.push(ch);
//				} else if (getisp((String) ch1) > geticp((String) ch)) {
//					listPost.add(s.pop());
//					i--;
//				} else {
//					s.pop();
//				}
//			}
//		}
//		// System.out.println(listPost);// 检验listPost中的值
//		return listPost;
//	}
	
	List postfix() {
		Stack<Object> s = new Stack<Object>();
		List listIn = new ArrayList();
		List listPost = new ArrayList();
		
		for(int i=0,j=1;j<str.length();){
			if(isOperator(str.charAt(j))==false){
				j++;
				if(j==str.length())
					listIn.add(str.substring(i));
			}
			else{
				listIn.add(str.substring(i, j));
				i=j;
				listIn.add(str.substring(i,i+1));
				i++;j++;
			}
		}
		listIn.add("#");

		String ch = "#", ch1;
		s.push(ch);
		for (int i = 0; i < listIn.size() && s.isEmpty() == false; i++) {
			ch = (String) listIn.get(i);
			if (isOperator((String) ch) == false) {
				listPost.add(ch);
			} else {
				ch1 = (String) s.peek();
				if (getisp((String) ch1) < geticp((String) ch)) {
					s.push(ch);
				} else if (getisp((String) ch1) > geticp((String) ch)) {
					listPost.add(s.pop());
					i--;
				} else {
					s.pop();
				}
			}
		}
		// System.out.println(listPost);// 检验listPost中的值
		return listPost;
	}

	/*
	 * 判断字符为数字还是操作符
	 */
	public boolean isOperator(char ch) {
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '#') {
			return true;
		} else
			return false;
	}

	public boolean isOperator(String ch) {
		if (ch.equals("+") || ch.equals("-") || ch.equals("*")
				|| ch.equals("/") || ch.equals("#")) {
			return true;
		} else
			return false;
	}

	/*
	 * 获取操作符的in stack priority
	 */

	int getisp(String ch) {
		int isp = 0;
		if (ch.equals("+") || ch.equals("-"))
			isp = 3;
		else if (ch.equals("*") || ch.equals("/"))
			isp = 5;
		else if (ch.equals("#"))
			isp = 0;
		return isp;
	}

	/*
	 * 获取操作符的in coming priority
	 */

	int geticp(String ch) {
		int icp = 0;
		if (ch.equals("+") || ch.equals("-"))
			icp = 2;
		else if (ch.equals("*") || ch.equals("/"))
			icp = 4;
		else if (ch.equals("#"))
			icp = 0;
		return icp;
	}


	protected List exp1=new ArrayList();
	protected Stack<Double> s=new Stack<Double>();

	/*
	 * 从stack中取出两个操作数
	 */

	protected double[] getTwoNum() {
		double[] a = new double[3];
		if (s.isEmpty() == false) {
			a[1] = s.pop();
			a[2] = 1;
		} else
			a[2] = 0;
		if (s.isEmpty() == false) {
			a[0] = s.pop();
			a[2] = 1;
		} else
			a[2] = 0;
		return a;
	}

	/*
	 * 计算取出的2个操作数
	 */
	protected void doCalcu(String op) {
		double left = 0, right = 0, result = 0, flag = 0;
		double[] temp = getTwoNum();
		left = temp[0];
		right = temp[1];
		flag = temp[2];
		if (flag == 1) {
			if (op.equals("+")) {
				result = left + right;
				s.push(result);
			} else if (op.equals("-")) {
				result = left - right;
				s.push(result);
			} else if (op.equals("*")) {
				result = left * right;
				s.push(result);
			} else if (op.equals("/")) {
				if (right == 0) {
					result = 0;
					s.push(result);
				} else {
					result = left / right;
					s.push(result);
				}
			}
		}
	}

	/*
	 * 从一个后缀表达式中计算结果
	 */
	public double run() {
		exp1.addAll(postfix());
		double res = 0;
		for (int i = 0; i < exp1.size(); i++) {
			if (isOperator((String) exp1.get(i)) == false) {
				s.push(Double.parseDouble(exp1.get(i).toString()));
			} else {
				doCalcu((String) exp1.get(i));
			}
		}
		res = s.pop();
		return res;
	}
}
