package ejercicios;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

// nombre (obligatorio, no nulo) 
// nia (autogenerar aleatoria y no se repite)
// nace (obligatorio)
// curso actual (String)
// un alumno es igual a otro si coincide el nia
// restriccion:
//  - edad mínima es 18.
// Json: AlumnoCFGS = {nia: 12345, nombre:"lolo" nace:{lololo}, curso:""}

public class AlumnoCFGS {
	private String nombre = "Joan";
	private int nia = (int)(Math.random() * 100000000);
	private static ArrayList<Integer> nias = new ArrayList<>(); 
	private LocalDate nace;
	private String curso;
	
	private static void listaNias() {
		try(PrintWriter pw = new PrintWriter(new File("nias.txt"))) {
			for(Integer n : nias)
				pw.print(n + " ");
		} catch(Exception e) {}
	}
	static {
		try(Scanner sc = new Scanner(new File("nias.txt"))) {
			while(sc.hasNextInt())
				nias.add(sc.nextInt());
		} catch(Exception e) {}
	}
	
	public AlumnoCFGS(String nombre, LocalDate nace, String curso) {
		if(nombre == null || nombre.isEmpty() || nombre.isBlank())
			throw new IllegalArgumentException("Debe tener nombre.");
		if(nace == null)
			throw new IllegalArgumentException("Debe tener fecha de nacimiento.");
		LocalDate ahora = LocalDate.now();
		int y = Period.between(nace, ahora).getYears();
		if(y < 18)
			throw new IllegalArgumentException("Debe ser mayor de edad.");
		
		if(!nias.contains(nia)) {
			nias.add(nia);
			listaNias();
		} else {
			nias.remove(nia);
			nia = (int)(Math.random() * 100000000);
			nias.add(nia);
			listaNias();
		}
		
		
		this.nombre = nombre;
//		this.nia = nia;
		this.nace = nace;
		this.curso = curso;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre == null || nombre.isEmpty() || nombre.isBlank())
			throw new IllegalArgumentException("Debe tener nombre.");
		this.nombre = nombre;
	}
	public LocalDate getNace() {
		return nace;
	}
	public void setNace(LocalDate nace) {
		if(nace == null)
			throw new IllegalArgumentException("Debe tener fecha de nacimiento.");
		LocalDate ahora = LocalDate.now();
		int y = Period.between(nace, ahora).getYears();
		if(y < 18)
			throw new IllegalArgumentException("Debe ser mayor de edad.");
		this.nace = nace;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getNia() {
		return nia;
	}
	
	@Override
	public String toString() {
		return String.format("AlumnoCFGS={nia:%d, nombre=\"%s\", nace:{%tY}, curso:\"%s\"}", nia, nombre, nace, curso);
	}
	
	public static void main(String[] args) {
		AlumnoCFGS a = new AlumnoCFGS("Dani", LocalDate.of(2007, 11, 15), "1DAM");
		System.out.println(a);
	}
}
