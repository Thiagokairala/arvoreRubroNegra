package util;

import java.util.Scanner;

public class Keyboard
{
	private static Scanner scanner;

	public static int getNumber() {
		scanner = new Scanner(System.in);
		
		int number = scanner.nextInt();
		
		return number;
}
}