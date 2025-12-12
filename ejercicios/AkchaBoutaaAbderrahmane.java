package ProblemasU3;

import java.util.Scanner;

public class Practica {
	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		System.out.println("introduce un numero entre 0 y 5:");
		
		int num = t.nextInt();
		if (num>=5 && num<=0) {
			for (int i = 0; i<num ; i++ ) {
				System.out.println("Hola mundo");
				}
		}else {
			System.err.println("error");
		}
	}
}
