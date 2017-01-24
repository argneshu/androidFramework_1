package App_Functions;

import java.util.Random;

public class randomNumber {
	public static String generate10DigitNumber() {
		String no;
		try {
			Random random = new Random();
			int START = 1000000000;
			long END = 9999999999L;

			long range = (long) END - (long) START + 1;
			long fraction = (long) (range * random.nextDouble());
			no = "" + fraction;
			if (no.length() != 10) {
				no = generate10DigitNumber();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return no;
	}

}
