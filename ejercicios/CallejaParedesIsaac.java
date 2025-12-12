package iniciales;
import java.util.Scanner;
public class Arrays {

	public static void main(String[] args) {
	Scanner t = new Scanner(System.in);
	String[] nombres = new String [5];
	System.out.println("--- INGRESO DE NOMBRES ---");
	
	for  (int i = 0; i < nombres.length; i++) {
	System.out.print("Introduce el nombre #" + (i + 1) + ": ");
		nombres[i] = t.nextLine();

	}
	System.out.println("\n--- Lista de nombres ---");
	
	for (int i = 0; i < nombres.length; i++) {
		System.out.println("Posicion " + i + ": " + nombres[i]);
	}
	
 }
}
