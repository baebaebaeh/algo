package com.ssafy.class1;

public class Test08_Array2 {
	public static void main(String[] args) {
		{
			int[] arr = new int[4];
			arr[0] = 10;
			arr[1] = 20;
			arr[2] = 30;
			arr[3] = 40;
			
			System.out.println(arr[0]);
			System.out.println(arr[1]);
			System.out.println(arr[2]);
			System.out.println(arr[3]);
		}
		{
			int[] arr = {10, 20, 30, 40};
//			arr = {50, 60, 70, 80};
			System.out.println(arr[0]);
			System.out.println(arr[1]);
			System.out.println(arr[2]);
			System.out.println(arr[3]);
		}
		{
			int[] arr = new int[] {10, 20, 30, 40};
			System.out.println(arr[0]);
			System.out.println(arr[1]);
			System.out.println(arr[2]);
			System.out.println(arr[3]);
		}
		
		/*
		int[] arr = new int[4];
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		arr[3] = 40;

		int[] arr = {10, 20, 30, 40};
		
		int[] arr = new int[] {10, 20, 30, 40};
		*/
		
		
//		int[] arr = new int[4];
//		arr[0] = 10;
//		arr[1] = 20;
//		arr[2] = 30;
//		arr[3] = 40;
		
//		int i = 100;
//		intTest(i);
//		intTest(100);
		

//		int[] arr = new int[] {10, 20, 30, 40};
//		int[] arr = {10, 20, 30, 40};
//		arrayTest(arr);

		int[] arr = new int[] {10, 20, 30, 40};
		arrayTest(arr);
		arrayTest(new int[] {10, 20, 30, 40});
	}
	
	static void intTest(int v) {
		System.out.println("들어온값 : " + v);
	}
	
	static void arrayTest(int[] arr) {
		System.out.println(arr.length);
	}
	
}











