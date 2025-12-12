package EjerciciosTema2;

import java.util.Scanner;

public class E5 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner (System.in);
		int cincuentacent = 50;
		int veintecent = 20;
		int diezcent = 10;
		int cincocent = 5;
		int doscent =2;
		int uncent = 1;
		
		System.out.print("¿Cuantas monedas de 50 centimos tienes? ");
		cincuentacent *= teclado.nextInt();
		System.out.print("¿Cuantas monedas de 20 centimos tienes? ");
		veintecent *= teclado.nextInt();
		System.out.print("¿Cuantas monedas de 10 centimos tienes? ");
		diezcent *= teclado.nextInt();
		System.out.print("¿Cuantas monedas de 5 centimos tienes? ");
		cincocent *= teclado.nextInt();
		System.out.print("¿Cuantas monedas de 2 centimos tienes? ");
		doscent *= teclado.nextInt();
		System.out.print("¿Cuantas monedas de 1 centimos tienes? ");
		uncent *= teclado.nextInt();
		double suma = cincuentacent+veintecent+diezcent+cincocent+doscent+uncent;
		double enEuros = suma / 100;
		System.out.print("\nEn total tiene: "+ enEuros+" euros");
	}

}
