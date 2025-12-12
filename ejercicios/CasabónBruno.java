package com.brucasort.consola;
import java.util.Scanner;
public class Amico {

	/*public static void main(String[] args) {
		
	    Scanner sc = new Scanner(System.in);
	  
	    
	    leeDouble(sc, 10, 5, "tus", 20, 1, 100);
	    
	} */
	
	
	/* || MÉTODOS PARA RESETEAR (1) || */
	
	public static void normal() {  // Anula todas las configuraciones de formato de texto y de color.
		print("\033[0m");
	}
	
	/* || MÉTODOS PARA BORRAR (6) || */
	
	public static void bp() {	// Imprime dos secuencias de escape ANSI, una que sitúa el cursor en la esquina superior izquierda secuencia y otra que borra toda la pantalla.		
		print("\033[H");
		print("\033[2J");
	}
	
	public static void bpArriba() { // Borra desde el cursor hasta el inicio de la pantalla.
		print("\033[1J");
	}
	
	public static void bpAbajo() { // Borra desde el cursor hasta el final de la pantalla.
		print("\033[0J");
	}
	
	public static void bl() { // Borra la línea actual.
		print("\033[2K");
	}

	public static void blAntes() { // Borra la línea desde el cursor hasta el principio.
		print("\033[1K");
	}
	
	public static void blDespues() { // Borra la línea desde el cursor hasta el final.
		print("\033[0K");
	}
	
	/* || MÉTODOS PARA MOVER EL CURSOR (6) || */
	
	public static void cursorXY(int columna, int linea) { // Permite mover el cursor (el lugar de la pantalla en el que se imprime).
		print("\033[" + linea + ";" + columna + "H");
	}
	
	public static void cursorArriba(int n) { // Se mueve n líneas arriba.
		print("\033[" + n + "A");
	}
	
	public static void cursorAbajo(int n) { // Se mueve n líneas abajo.
		print("\033[" + n + "B");
	}
	
	public static void cursorIzquierda(int n) { // Se mueve n columnas a la izquierda.
		print("\033[" + n + "D");
	}
	
	public static void cursorDerecha(int n) { // Se mueve n columnas a la derecha.
		print("\033[" + n + "C");
	}
	
	public static void cursorInicio() { // Se mueve el cursor al principio de línea.
		print("\033[0G");
	}
	
	/* || MÉTODOS PARA CAMBIAR EL COLOR DE TEXTO Y FONDO (2) || */
	
	private static final String[] coloresTexto = {"\033[30m", "\033[31m", "\033[32m", "\033[33m", "\033[34m", // COLORES TEXTO PERMITIDOS 
			"\033[35m", "\033[36m", "\033[37m", "\033[90m", "\033[91m", "\033[92m", "\033[93m", "\033[94m", "\033[95m", "\033[96m", "\033[97m"};
	
	
	public static void texto(int color) { // CAMBIA EL COLOR DEL TEXTO
		
		if( color >= 0 && color <= 15 ) {
			
			print(coloresTexto[color]);
			
		} else {
			throw new IllegalArgumentException("Ese color no existe.");
		}
		
	}
	
	private static final String[] coloresFondo = {"\033[40m", "\033[41m", "\033[42m", "\033[43m", "\033[44m", // COLORES FONDO PERMITIDOS 
			"\033[45m", "\033[46m", "\033[47m", "\033[100m", "\033[101m", "\033[102m", "\033[103m", "\033[104m", "\033[105m", "\033[106m", "\033[107m"};
	
	
	public static void fondo(int color) { // CAMBIA EL COLOR DEL FONDO
		
		if( color >= 0 && color <= 15 ) {
			
			print(coloresFondo[color]);
			
		} else {
			throw new IllegalArgumentException("Ese color no existe.");
		}
		
	}
	
	public static final int NEGRO = 0; public static final int ROJO = 1; public static final int VERDE = 2; public static final int MARRON = 3;
	public static final int AZUL = 4; public static final int PURPURA = 5; public static final int CYAN = 6; public static final int BLANCO = 7;
	public static final int NEGRO_CLARO = 8; public static final int ROJO_CLARO = 9; public static final int VERDE_CLARO = 10; public static final int AMARILLO = 11;
	public static final int AZUL_CLARO = 12; public static final int PURPURA_CLARO = 13; public static final int CYAN_CLARO = 14; public static final int BLANCO_CLARO = 15;
	
	/* || MÉTODOS PARA CAMBIAR EL COLOR DE TEXTO Y FONDO CON COMPONENTES RGB (2) || */
	
	public static void texto(int r, int g, int b) { // COLORES TEXTO SOBRECARGADO PARA ADMITIR FORMATO RGB
		if( r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 ) {
			throw new IllegalArgumentException("Valores RGB no válidos.");
		} else {
			print("\033[38;2;" + r + ";" + g + ";" + b + "m");
		}
	}
	
	public static void fondo(int r, int g, int b) { // COLORES FONDO SOBRECARGADO PARA ADMITIR FORMATO RGB
		if( r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 ) {
			throw new IllegalArgumentException("Valores RGB no válidos.");
		} else {
			print("\033[48;2;" + r + ";" + g + ";" + b + "m");
		}
	}
	
	/* || MÉTODOS PARA CAMBIAR EL FORMATO DEL TEXTO (7) || */
	
	public static void negrita(boolean activar) { // CAMBIA EL FORMATO A NEGRITA
		if(activar) {
			print("\033[1m");
		} else {print("\033[22m");}
	}
	
	public static void cursiva(boolean activar) { // CAMBIA EL FORMATO A CURSIVA
		if(activar) {
			print("\033[3m");
		} else {print("\033[23m");}
	}
	
	public static void subraya(boolean activar) { // CAMBIA EL FORMATO A SUBRAYADO
		if(activar) {
			print("\033[4m");
		} else {print("\033[24m");}
	}
	
	public static void parpadeo(boolean activar) { // CAMBIA EL FORMATO A PARPADEO
		if(activar) {
			print("\033[5m");
		} else {print("\033[25m");}
	}
	
	public static void tachado(boolean activar) { // CAMBIA EL FORMATO A TACHADO
		if(activar) {
			print("\033[9m");
		} else {print("\033[29m");}
	}
	
	public static void inverso(boolean activar) { // CAMBIA EL FORMATO A INVERSO
		if(activar) {
			print("\033[7m");
		} else {print("\033[27m");}
	}
	
	public static void oculto(boolean activar) { // CAMBIA EL FORMATO A OCULTO
		if(activar) {
			print("\033[8m");
		} else {print("\033[28m");}
	}
	
	/* || MÉTODOS PARA LEER VALORES NUMÉRICOS EN POSICIONES FIJAS SIN ERRORES (4) || */
	
	public static double leeDouble(Scanner sc, int posX, int posY, String pregunta, int ancho, int min, int max) { // LEE DOUBLES
		
		int dondeLeer = posX + pregunta.length() + 2;
		boolean correcto = false;
		double flotante = 0;
		
		do {
			
			cursorXY(posX, posY);
			print(pregunta);
			print("  ");
			inverso(true);
			
			for(int i = 0; i < ancho; i++) {
				print(" ");
			}
			
			cursorXY(dondeLeer, posY);
			
			String s = sc.next();
			
			try {
				flotante = Double.parseDouble(s);
			} catch(NumberFormatException e) { System.err.print("Eso no es un número válido."); System.out.flush(); sc.nextLine(); continue; }
			
			if(flotante < min || flotante > max) {
				printError("El número debe estar comprendido entre " + min + " y " + max + ".");
				sc.nextLine(); continue;
			} else {
				correcto = true;
			}	
			
		} while( !correcto );
		
		return flotante;
	}
	
	public static double leeDouble(Scanner sc, int posX, int posY, String pregunta, int ancho) { // LEE DOUBLES SIN RESTRICCIÓN
		
		int dondeLeer = posX + pregunta.length() + 2;
		boolean correcto = false;
		double flotante = 0;
		
		do {
			
			cursorXY(posX, posY);
			print(pregunta);
			print("  ");
			inverso(true);
			
			for(int i = 0; i < ancho; i++) {
				print(" ");
			}
			
			cursorXY(dondeLeer, posY);
			
			String s = sc.next();
			
			try {
				flotante = Double.parseDouble(s);
			} catch(NumberFormatException e) { System.err.print("Eso no es un número válido."); System.out.flush(); sc.nextLine(); continue; }
			
			correcto = true;
			
		} while( !correcto );
		
		return flotante;
	}
	
		public static double leeEntero(Scanner sc, int posX, int posY, String pregunta, int ancho, int min, int max) { // LEE ENTEROS
		
		int dondeLeer = posX + pregunta.length() + 2;
		boolean correcto = false;
		int entero = 0;
		
		do {
			
			cursorXY(posX, posY);
			print(pregunta);
			print("  ");
			inverso(true);
			
			for(int i = 0; i < ancho; i++) {
				print(" ");
			}
			
			cursorXY(dondeLeer, posY);
			
			String s = sc.next();
			
			try {
				entero = Integer.parseInt(s);
			} catch(NumberFormatException e) { System.err.print("Eso no es un número válido."); System.out.flush(); sc.nextLine(); continue; }
			
			if(entero < min || entero > max) {
				printError("El número debe estar comprendido entre " + min + " y " + max + ".");
				sc.nextLine(); continue;
			} else {
				correcto = true;
			}	
			
		} while( !correcto );
		
		return entero;
	}
	
		public static double leeEntero(Scanner sc, int posX, int posY, String pregunta, int ancho) { // LEE ENTEROS SIN RESTRICCIÓN
			
			int dondeLeer = posX + pregunta.length() + 2;
			boolean correcto = false;
			double entero = 0;
			
			do {
				
				cursorXY(posX, posY);
				print(pregunta);
				print("  ");
				inverso(true);
				
				for(int i = 0; i < ancho; i++) {
					print(" ");
				}
				
				cursorXY(dondeLeer, posY);
				
				String s = sc.next();
				
				try {
					entero = Double.parseDouble(s);
				} catch(NumberFormatException e) { printError("Eso no es un número válido."); System.out.flush(); sc.nextLine(); continue; }
				
				correcto = true;
				
			} while( !correcto );
			
			return entero;
		}
	
	/* || PRIN FLUS PRIN FLUS PRIN FLUS... || */
	
	private static void print(String s) { // Hace prin y luego hace flus.
		System.out.print(s);
		System.out.flush();
	}
	
	public static void printError(String texto) { // Muestra mensajes de error.
		String result = "\033[31m" + texto + "\033[0m";
		System.out.println(result);
	}
	
}
