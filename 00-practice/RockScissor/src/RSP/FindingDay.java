package RSP;

import java.util.Scanner;

public class FindingDay {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("월과 일을 입력하세요.");
		int month = sc.nextInt();
		int day = sc.nextInt();
		int date = 0;
		switch (month) {
		case 12 : date += 30;
		case 11 : date += 31;
		case 10 : date += 30;
		case 9  : date += 31;
		case 8  : date += 31;
		case 7  : date += 30;
		case 6  : date += 31;
		case 5  : date += 30;
		case 4  : date += 31;
		case 3  : date += 28;
		case 2  : date += 31;
		case 1  : date += 0; break;
		}
		System.out.println(day + date);
	}
}
