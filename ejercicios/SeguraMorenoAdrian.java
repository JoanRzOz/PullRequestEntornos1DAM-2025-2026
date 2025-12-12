package com.adrsegmor.p1;
 
 import java.io.Console;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Date;
 import java.util.Scanner;
 
 public class AmigoInvisible {
 
 private static String amigo = "";
 private static String participanteActual = "";
 private static int[] fechaReparto = null;
 private static ArrayList<String> participantes = new ArrayList<>();
 private static ArrayList<String> passwords = new ArrayList<>();
 private static boolean repartoRealizado = false;
 private static ArrayList<Integer> asignacion = new ArrayList<>();
 private static String repartoPor = "";
 private static Scanner sc = new Scanner(System.in);
 
 public static void main(String[] args) {
 boolean salir = false;
 while (!salir) {
 mostrarMenu();
 System.out.print("\nEscoja una opción (1-6): ");
 String opcion = sc.nextLine().trim();
 switch (opcion) {
 case "1": crearOAbrirAmigoInvisible(); break;
 case "2": participar(); break;
 case "3": noParticipar(); break;
 case "4": realizarReparto(); break;
 case "5": consultarDatos(); break;
 case "6":
 if (!amigo.isEmpty()) guardarAmigoInvisible();
 salir = true;
 System.out.println("\nSaliendo...");
 break;
 default: printError("Opción no válida.");
 }
 }
 }
 
 private static void mostrarMenu() {
 System.out.println("\nAMIGO INVISIBLE [amigo:" + amigo + ", participante:" + participanteActual + "]");
 System.out.println("1. Crear/Abrir un amigo invisible.");
 System.out.println("2. Participar.");
 System.out.println("3. No participar.");
 System.out.println("4. Realizar reparto.");
 System.out.println("5. Consultar datos.");
 System.out.println("6. Salir.");
 }
 
 private static void crearOAbrirAmigoInvisible() {
 if (!amigo.isEmpty()) {
 guardarAmigoInvisible();
 participanteActual = "";
 fechaReparto = null;
 participantes.clear();
 passwords.clear();
 asignacion.clear();
 repartoRealizado = false;
 repartoPor = "";
 amigo = "";
 }
 
 System.out.print("\nNombre del amigo invisible: ");
 String nombre = sc.nextLine().trim();
 if (nombre.isEmpty()) {
 printError("Nombre vacío.");
 pausar();
 return;
 }
 
 File f = new File(nombre + ".ai");
 if (f.exists()) {
 System.out.print("El archivo ya existe. ¿Desea sobrescribirlo? (s/n): ");
 String r = sc.nextLine().trim().toLowerCase();
 if (!r.equals("s")) {
 printError("Operación cancelada.");
 pausar();
 return;
 }
 f.delete();
 }
 
 int[] fechaVal = null;
 while (fechaVal == null) {
 System.out.print("Fecha reparto (dd/mm/aaaa): ");
 fechaVal = validaFecha(sc.nextLine().trim());
 if (fechaVal == null) printError("Fecha incorrecta.");
 }
 
 fechaReparto = new int[]{fechaVal[2], fechaVal[1], fechaVal[0]};
 amigo = nombre;
 participantes.clear();
 passwords.clear();
 asignacion.clear();
 repartoRealizado = false;
 repartoPor = "";
 guardarAmigoInvisible();
 
 System.out.println("Amigo invisible creado correctamente.");
 pausar();
 }
 
 private static boolean guardarAmigoInvisible() {
 try (PrintWriter pw = new PrintWriter(new File(amigo + ".ai"))) {
 String fecha = String.format("%02d/%02d/%04d", fechaReparto[2], fechaReparto[1], fechaReparto[0]);
 StringBuilder body = new StringBuilder();
 body.append(fecha).append("\n");
 body.append(participantes.size()).append("\n");
 for (int i = 0; i < participantes.size(); i++) {
 body.append(participantes.get(i)).append("\n");
 body.append(passwords.get(i)).append("\n");
 }
 body.append(repartoRealizado ? "1" : "0").append("\n");
 body.append(repartoPor == null ? "" : repartoPor).append("\n");
 if (repartoRealizado) {
 for (int i = 0; i < asignacion.size(); i++) {
 if (i > 0) body.append(",");
 body.append(asignacion.get(i));
 }
 }
 String cuerpo = body.toString();
 String hash = Permuneitor.hash(cuerpo);
 String plain = hash + " " + cuerpo;
 String clave = amigo + "_clave123";
 String cipher = Permuneitor.encriptar(plain, clave);
 pw.print(cipher);
 return true;
 } catch (Exception e) {
 return false;
 }
 }
 
 private static boolean leerAmigoInvisible(String nombreArchivo) {
 File f = new File(nombreArchivo + ".ai");
 if (!f.exists()) return false;
 
 try (Scanner fs = new Scanner(f)) {
 StringBuilder sb = new StringBuilder();
 while (fs.hasNextLine()) {
 sb.append(fs.nextLine());
 if (fs.hasNextLine()) sb.append("\n");
 }
 String contenido = sb.toString();
 String clave = nombreArchivo + "_clave123";
 String dec = Permuneitor.desencriptar(contenido, clave);
 if (dec == null || dec.length() < 11) return false;
 
 String hash = dec.substring(0, 10);
 String cuerpo = dec.substring(11);
 if (!Permuneitor.hash(cuerpo).equals(hash)) return false;
 
 Scanner s = new Scanner(cuerpo);
 String fechaLine = s.nextLine();
 int[] fval = validaFecha(fechaLine);
 if (fval == null) return false;
 fechaReparto = new int[]{fval[2], fval[1], fval[0]};
 
 int np = Integer.parseInt(s.nextLine().trim());
 participantes.clear();
 passwords.clear();
 for (int i = 0; i < np; i++) {
 participantes.add(s.nextLine());
 passwords.add(s.nextLine());
 }
 
 repartoRealizado = s.nextLine().trim().equals("1");
 repartoPor = s.nextLine();
 asignacion.clear();
 
 if (repartoRealizado && s.hasNextLine()) {
 String[] parts = s.nextLine().trim().split(",");
 for (String p : parts) asignacion.add(Integer.parseInt(p));
 }
 
 amigo = nombreArchivo;
 return true;
 
 } catch (Exception e) {
 return false;
 }
 }
 
 private static int[] validaFecha(String f) {
 try {
 String[] p = f.split("/");
 int d = Integer.parseInt(p[0]);
 int m = Integer.parseInt(p[1]);
 int y = Integer.parseInt(p[2]);
 if (m < 1 || m > 12 || d < 1 || d > 31) return null;
 if ((m==4||m==6||m==9||m==11) && d>30) return null;
 boolean b = (y%4==0&&y%100!=0)||(y%400==0);
 if (m==2) {
 if (b && d>29) return null;
 if (!b && d>28) return null;
 }
 return new int[]{d,m,y};
 } catch (Exception e) {
 return null;
 }
 }
 
 private static void participar() {
 if (amigo.isEmpty()) { printError("Debe crear o abrir primero."); pausar(); return; }
 if (repartoRealizado) { printError("El reparto ya se realizó."); pausar(); return; }
 
 int[] hoy = fechaActual();
 if (esPosterior(hoy, fechaReparto)) { printError("La fecha ya pasó."); pausar(); return; }
 
 System.out.print("Nombre: ");
 String n = sc.nextLine().trim();
 for (String s : participantes) if (s.equalsIgnoreCase(n)) { printError("Ya existe."); pausar(); return; }
 
 String p1, p2;
 Console cons = System.console();
 if (cons != null) {
 do {
 p1 = new String(cons.readPassword("Password: "));
 p2 = new String(cons.readPassword("Repita password: "));
 if (!p1.equals(p2)) printError("No coinciden.");
 } while (!p1.equals(p2));
 } else {
 System.out.println("(Password visible en pantalla)");
 do {
 System.out.print("Password: "); p1 = sc.nextLine();
 System.out.print("Repita: "); p2 = sc.nextLine();
 if (!p1.equals(p2)) printError("No coinciden.");
 } while (!p1.equals(p2));
 }
 
 participantes.add(n);
 passwords.add(p1);
 guardarAmigoInvisible();
 System.out.println("Participante añadido.");
 pausar();
 }
 
 private static void noParticipar() {
 if (amigo.isEmpty()) { printError("Debe crear/abrir primero."); pausar(); return; }
 if (repartoRealizado) { printError("Reparto ya hecho."); pausar(); return; }
 
 System.out.print("Nombre: ");
 String n = sc.nextLine().trim();
 int idx = indexIgnore(n);
 if (idx == -1) { printError("No existe."); pausar(); return; }
 
 System.out.print("Password: ");
 String pw = sc.nextLine().trim();
 if (!passwords.get(idx).equals(pw)) { printError("Incorrecto."); pausar(); return; }
 
 participantes.remove(idx);
 passwords.remove(idx);
 guardarAmigoInvisible();
 System.out.println("Participante eliminado.");
 pausar();
 }
 
 private static void realizarReparto() {
 if (amigo.isEmpty()) { printError("Abra/cree primero."); pausar(); return; }
 if (repartoRealizado) { System.out.println("Ya realizado por " + repartoPor); pausar(); return; }
 if (participantes.size() < 3) { printError("Mínimo 3 participantes."); pausar(); return; }
 
 System.out.print("Nombre: ");
 String n = sc.nextLine().trim();
 int idx = indexIgnore(n);
 if (idx == -1) { printError("Incorrecto."); pausar(); return; }
 
 System.out.print("Password: ");
 if (!passwords.get(idx).equals(sc.nextLine().trim())) { printError("Incorrecto."); pausar(); return; }
 
 int N = participantes.size();
 ArrayList<Integer> ind = new ArrayList<>();
 for (int i = 0; i < N; i++) ind.add(i);
 
 boolean ok;
 do {
 Collections.shuffle(ind);
 ok = true;
 for (int i = 0; i < N; i++) {
 if (ind.get(i) == i) ok = false;
 }
 } while (!ok);
 
 asignacion.clear();
 asignacion.addAll(ind);
 repartoRealizado = true;
 repartoPor = participantes.get(idx);
 guardarAmigoInvisible();
 System.out.println("Reparto realizado por " + repartoPor + ".");
 pausar();
 }
 
 private static void consultarDatos() {
 if (amigo.isEmpty()) { printError("Cree o abra primero."); pausar(); return; }
 
 int[] hoy = fechaActual();
 if (esPosterior(hoy, fechaReparto)) {
 System.out.println("Participantes: " + participantes.size());
 System.out.printf("Fecha reparto: %02d/%02d/%04d%n", fechaReparto[2], fechaReparto[1], fechaReparto[0]);
 System.out.println("Reparto: " + (repartoRealizado ? "Realizado por " + repartoPor : "No realizado"));
 System.out.printf("%-20s %-20s%n", "PARTICIPANTE", "AMIGO");
 
 for (int i = 0; i < participantes.size(); i++) {
 String am = "?";
 if (repartoRealizado) {
 int ai = asignacion.get(i);
 am = participantes.get(ai);
 }
 System.out.printf("%-20s %-20s%n", participantes.get(i), am);
 }
 pausar();
 return;
 }
 
 System.out.print("Nombre: ");
 String n = sc.nextLine().trim();
 int idx = indexIgnore(n);
 if (idx == -1) { printError("No existe."); pausar(); return; }
 
 System.out.print("Password: ");
 if (!passwords.get(idx).equals(sc.nextLine().trim())) { printError("Incorrecto."); pausar(); return; }
 
 if (!repartoRealizado) { printError("Reparto no realizado."); pausar(); return; }
 
 int ai = asignacion.get(idx);
 System.out.println("Tu amigo invisible es: " + participantes.get(ai));
 pausar();
 }
 
 private static int[] fechaActual() {
 Date d = new Date();
 @SuppressWarnings("deprecation")
 int y = d.getYear() + 1900;
 @SuppressWarnings("deprecation")
 int m = d.getMonth() + 1;
 @SuppressWarnings("deprecation")
 int day = d.getDate();
 return new int[]{y, m, day};
 }
 
 private static boolean esPosterior(int[] h, int[] r) {
 if (h[0] > r[0]) return true;
 if (h[0] < r[0]) return false;
 if (h[1] > r[1]) return true;
 if (h[1] < r[1]) return false;
 return h[2] > r[2];
 }
 
 private static int indexIgnore(String n) {
 for (int i = 0; i < participantes.size(); i++)
 if (participantes.get(i).equalsIgnoreCase(n)) return i;
 return -1;
 }
 
 private static void printError(String m) {
 System.out.println("\u001B[31m" + m + "\u001B[0m");
 }
 
 private static void pausar() {
 System.out.println("\nENTER para continuar...");
 sc.nextLine();
 }
 
 private static class Permuneitor {
 
 private static final String ALF =
 "aáàbcçdeéèfghiíïjklmnñoóòpqrstuúùüvwxyz \t!¡>€<.,;:+-*/^_%&(){}[]@~|\\\\ºª0123456789?¿#$·\"'" +
 "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
 
 private static String perm(String clave) {
 StringBuilder p = new StringBuilder();
 for (int i = 0; i < clave.length(); i++) {
 char c = clave.charAt(i);
 if (ALF.indexOf(c) >= 0 && p.indexOf("" + c) == -1) p.append(c);
 }
 for (int i = ALF.length() - 1; i >= 0; i--) {
 char c = ALF.charAt(i);
 if (p.indexOf("" + c) == -1) p.append(c);
 }
 return p.toString();
 }
 
 public static String encriptar(String msg, String clave) {
 String p = perm(clave);
 StringBuilder out = new StringBuilder();
 for (int i = 0; i < msg.length(); i++) {
 char c = msg.charAt(i);
 int pos = ALF.indexOf(c);
 out.append(pos == -1 ? c : p.charAt(pos));
 }
 return out.toString();
 }
 
 public static String desencriptar(String msg, String clave) {
 String p = perm(clave);
 StringBuilder out = new StringBuilder();
 for (int i = 0; i < msg.length(); i++) {
 char c = msg.charAt(i);
 int pos = p.indexOf(c);
 out.append(pos == -1 ? c : ALF.charAt(pos));
 }
 return out.toString();
 }
 
 public static String hash(String msg) {
 long primo = 17;
 int t = msg.length();
 int m = t / 2;
 long h1 = t;
 long h2 = Integer.MAX_VALUE - m;
 for (int i = 0; i < m; i++) {
 char c1 = msg.charAt(i);
 char c2 = msg.charAt(t - i - 1);
 h1 = h1 * primo + (c1 ^ c2);
 h2 = (h2 - c2) - c1 * primo;
 }
 long r = h1 ^ h2;
 if (r < 0) r += Integer.MAX_VALUE;
 r = r % Integer.MAX_VALUE;
 String s = Long.toString(r);
 if (s.length() > 10) s = s.substring(s.length() - 10);
 return String.format("%010d", Long.parseLong(s));
 }
 }
 }