package com.polfervar.consola;

import java.util.*;

public class Amico {
	
	public static void texto(int color) {
		if (color < 0 || color > 15)
			throw new IllegalArgumentException("El valor del color, es incorrecto");
		String[] coloresTexto = {"\033[30m", "\033[31m", "\033[32m", "\033[33m", "\033[34m", "\033[35m", "\033[36m", "\033[37m",
				"\033[90m", "\033[91m", "\033[92m", "\033[93m", "\033[94m", "\033[95m", "\033[96m", "\033[97m"};
		print(coloresTexto[color]);
	}
	
	public static void fondo(int color) {
		if (color < 0 || color > 15)
			throw new IllegalArgumentException("El valor del color, es incorrecto");
		String[] coloresFondo = {"\033[40m", "\033[41m", "\033[42m", "\033[43m", "\033[44m", "\033[45m", "\033[46m", "\033[47m",
				"\033[100m", "\033[101m", "\033[102m", "\033[103m", "\033[104m", "\033[105m", "\033[106m", "\033[107m"};
		print(coloresFondo[color]);
	}
	
	public static void texto(int r, int g, int b) {
		print("\033[38;2;" + r + ";" + g + ";" + b + "m");
	}
	
	public static void fondo(int r, int g, int b) {
		print("\033[48;2;" + r + ";" + g + ";" + b + "m");
	}
	
	public static final int NEGRO = 0, ROJO = 1, VERDE = 2, MARRON = 3, AZUL = 4, PURPURA = 5, CYAN = 6, BLANCO = 7,
			NEGRO_CLARO = 8, ROJO_CLARO = 9, VERDE_CLARO = 10, AMARILLO = 11, AZUL_CLARO = 12, PURPURA_CLARO = 13,
			CYAN_CLARO = 14, BLANCO_CLARO = 15;

	public static void negrita(boolean activar) {
		if (activar) print("\033[1m");
		else print("\033[22m");
	}
	
	public static void cursiva(boolean activar) {
		if (activar) print("\033[3m");
		else print("\033[23m");
	}
	
	public static void subraya(boolean activar) {
		if (activar) print("\033[4m");
		else print("\033[24m");
	}
	
	public static void parpadeo(boolean activar) {
		if (activar) print("\033[5m");
		else print("\033[25m");
	}
	
	public static void tachado(boolean activar) {
		if (activar) print("\033[9m");
		else print("\033[29m");
	}
	
	public static void inverso(boolean activar) {
		if (activar) print("\033[7m");
		else print("\033[27m");
	}
	
	public static void oculto(boolean activar) {
		if (activar) print("\033[8m");
		else print("\033[28m");
	}
	
	public static double leeDouble(Scanner sc, int posX, int posY, String pregunta, int ancho, double min, double max) {
		int dondeLeerX =  posX + pregunta.length() + 2;
		double valor = 0.0;
		boolean esValido = false;
		
		do {
			cursorXY(posX, posY);
			bl();
			System.out.print(pregunta);
			System.out.print("  ");
			inverso(true);
			for (int i = 0; i < ancho; i++) System.out.print(" ");
			cursorXY(dondeLeerX, posY);
			
			try {
				valor = Double.parseDouble(sc.nextLine());
				inverso(false);
				if (valor < min || valor > max) {
					valor = 0.0;
					continue;
				} else {
					esValido = true;
				}
			} catch(Exception e) {
				valor = 0.0;
				continue;
			}
		
		} while (!esValido);
		
		return valor;
	}
	
	public static double leeDouble(Scanner sc, int posX, int posY, String pregunta, int ancho) {
		return leeDouble(sc, posX, posY, pregunta, ancho, Double.MIN_VALUE, Double.MAX_VALUE);
	}
	
	public static int leeInt(Scanner sc, int posX, int posY, String pregunta, int ancho, int min, int max) {
		int dondeLeerX =  posX + pregunta.length() + 2;
		int valor = 0;
		boolean esValido = false;
		
		do {
			cursorXY(posX, posY);
			bl();
			System.out.print(pregunta);
			System.out.print("  ");
			inverso(true);
			for (int i = 0; i < ancho; i++) System.out.print(" ");
			inverso(false);
			cursorXY(dondeLeerX, posY);
			
			try {
				valor = Integer.parseInt(sc.nextLine());
				if (valor < min || valor > max) {
					valor = 0;
					continue;
				} else {
					esValido = true;
				}
				break;
			} catch(Exception e) {
				valor = 0;
				continue;
			}
		
		} while (!esValido);
		
		return valor;
	}
	
	public static int leeInt(Scanner sc, int posX, int posY, String pregunta, int ancho) {
		return leeInt(sc, posX, posY, pregunta, ancho, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static void print(String s) {
		System.out.print(s);
		System.out.flush();
	}
	
	public static void normal() { // Resetear las configuraciones de formato de texto y de color
		print("\033[0m"); 
	}
	
	public static void bp() {
		print("\033[H"); // Sitúa el cursor en la esquina superior izquierda 
		print("\033[2J"); // Borra toda la pantalla
	}
	
	public static void bpArriba() { // Borra desde el cursor hasta el inicio de la pantalla
		print("\033[1J");
	}
	
	public static void bpAbajo() { // Borra desde el cursor hasta el final de la pantalla
		print("\033[0J");
	}
	
	public static void bl() { // Borra la línea actual
		print("\033[2K");
	}
	
	public static void blDespues() { // Borra la línea desde el cursor hasta el final
		print("\033[0K");
	}
	
	public static void blAntes() { // Borra la línea desde el cursor hasta el principio
		print("\033[1K");
	}
	
	public static void cursorXY(int columna, int linea) { // Permite mover el cursor
		print("\033[" + linea + ";" + columna + "H");
	}
	
	public static void cursorArriba(int n) { // Se mueve n líneas arriba
		print("\033[" + n + "A");
	}
	
	public static void cursorAbajo(int n) { // Se mueve n líneas abajo
		print("\033[" + n + "B");
	}
	
	public static void cursorIzquierda(int n) { // Se mueve n columnas a la izquierda
		print("\033[" + n + "D");
	}
	
	public static void cursorDerecha(int n) { // Se mueve n columnas a la derecha
		print("\033[" + n + "C");
	}
	
	public static void cursorInicio() { // Se mueve el cursor al principio de línea
		print("\033[0G");
	}
	
	public static void printError(String texto) {
		System.out.print("\n\t\033[31m" + texto + "\033[0m\n");
		System.out.flush();
	}
	
}
