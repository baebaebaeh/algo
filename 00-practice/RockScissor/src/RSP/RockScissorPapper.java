package RSP;

public class RockScissorPapper {
	public static void main(String[] args) {
		int lineFirstNum = 1;
		for (int lineIter = 5, A = 0; lineIter >= -5; lineIter -= 2, A++) {
			if (A == 1 | A == 4) {
				System.out.print(" ".repeat(4));
			} else if (A == 2) {
				System.out.print(" ".repeat(8));
			}
			if (lineIter > 1 | lineIter < 1) {
				for (int i = lineFirstNum, k = Math.abs(lineIter); k > 0; k--, i++) {
					System.out.printf("%-4d", i);
				}
				lineFirstNum += Math.abs(lineIter);
				System.out.println();
			} else if (lineIter == 1) {
				
			}
		}
	}
}