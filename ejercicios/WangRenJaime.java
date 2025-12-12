package t2;
import java.util.Scanner;
public class pitagora {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		float h;
		System.out.println("teclle el primer cateto: ");
		float c1 = teclado.nextInt();
		System.out.println("teclle el segundo cateto: ");
		float c2 = teclado.nextInt();
		h = ((c1 * c1) + (c2* c2));
		h = (float)Math.sqrt(h);
		System.out.println("la hipotenusa es :" + h);
		
		
		
		

	}

}
