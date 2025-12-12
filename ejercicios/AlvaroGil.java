package com.alvgilmun.consola;

import java.util.Scanner;

public class Amico {
	public static final String [] coloresTexto = {"\033[30m","\033[31m","\033[32m","\033[33m","\033[34m","\033[35m","\033[36m","\033[37m","\033[90m","\033[91m","\033[92m","\033[93m","\033[94m","\033[95m","\033[96m","\033[97m"};
	public static final String [] coloresFondo = {"\033[40m","\033[41m","\033[42m","\033[43m","\033[44m","\033[45m","\033[46m","\033[47m","\033[100m","\033[101m","\033[102m","\033[103m","\033[104m","\033[105m","\033[106m","\033[107m"};
	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		normal();
		bp();
		System.out.println("Si bp() funciona, esto desaparece cuando pulses ENTER...");
		t.nextLine();
		bp();
		for(int i = 0; i < 20; i++) {
			int x = (int)(Math.random() * 20 + 1);
			int y = (int)(Math.random() * 70 + 1);
			cursorXY(x,y);
			texto(7);
			fondo(0);
			System.out.printf("(%d,%d)",x,y);
		}
		System.out.println("Pulsa ENTER para borrar hacia abajo...");
		t.nextLine();
		bpAbajo();
		cursorXY(1,4);
		System.out.println("Una linea X a borrar después de la X al pulsar ENTER...");
		t.nextLine();
		cursorXY(12,4);
		blDepues();
		normal();
	}
	public static void normal() {
		System.out.print("\033[0m");
		System.out.flush();
	}
	public static void bp(){
		System.out.print("\033[10;6H\033[2J");
		System.out.flush();
	}
	public static void cursorXY(int x, int y) {
		System.out.print("\033["+x+";"+y+"H");
		System.out.flush();
	}
	public static void bpAbajo() {
		System.out.print("\033[0J");
		System.out.flush();
	}
	public static void bpArriba() {
		System.out.print("\033[1J");
		System.out.flush();
	}
	public static void blDepues() {
		System.out.print("\033[0K");
		System.out.flush();
	}
	public static void blAntes() {
		System.out.print("\033[1K");
		System.out.flush();
	}
	public static void bl() {
		System.out.print("\033[2K");
		System.out.flush();
	}
	public static void cursorArriba(int n) {
		System.out.print("\033["+n+"A");
		System.out.flush();
	}
	public static void cursorAbajo(int n) {
		System.out.print("\033["+n+"B");
		System.out.flush();
	}
	public static void cursorIzquierda(int n) {
		System.out.print("\033["+n+"D");
		System.out.flush();
	}
	public static void cursorDerecha(int n) {
		System.out.print("\033["+n+"C");
		System.out.flush();
	}
	public static void cursorInicio() {
		System.out.print("\033[0G");
		System.out.flush();
	}
	public static void texto(int colorTexto) {
		System.out.print(coloresTexto[colorTexto]);
		System.out.flush();
	}
	public static void fondo(int colorFondo) {
		System.out.print(coloresFondo[colorFondo]);
		System.out.flush();
	}
}
