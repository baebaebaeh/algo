package com.ssafy.class1;

import java.util.Random;

/*
 class : 
 method : 
 variable :
 constant : 
 */

public class Test06_ClassType {
	public static void main(String[] args) {
		Member m = new Member();
		Member m2 = new Member();
		Member m3 = m;
		int i = 100;
		
		System.out.println(m.age + "-" + m.name);
		System.out.println(m2.age + "-" + m2.name);
		System.out.println(m3.age + "-" + m3.name);
		
		m.age = 100;
		m.name = "hong";
		System.out.println(m.age + "-" + m.name);
		System.out.println(m2.age + "-" + m2.name);
		System.out.println(m3.age + "-" + m3.name);
		
	}
}


















