package RSP;

import java.util.Scanner;

public class GuGuDan {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요 : ");
		int dan = sc.nextInt();
		while (dan != 0) {
			for (int i = 1; i < 10; i += 1) {
				System.out.println(dan + " * " + i + " = " + dan * i);
			}
			System.out.print("다음 숫자 입력 : ");
			dan = sc.nextInt();

		}
		System.out.println("프로그램 종료");
	}
}
