package Constructores;

public class Complejo {
	private double r, i; // parte real y parte imaginaria
	//-----Constructores
	public Complejo(double real, double imaginaria) {
		r = real;
		i = imaginaria;
}
	
//-----Sobreescrituras
	public String toString() {
		// 0 + 0i --> 0
		// 0 + 4i ---> 4i
		// -3	-----> -3
		// a + -bi ---> No ver a + -bi
		if(r == 0 && i == 0)
			return "0";
		else if (r == 0)
			return i + "i";
		else if (i == 0)
			return r + "";
		else if(1<0)
			return r + "" + i + "i";
		else return r + "+" + i + "i";
	}
	
	@Override
	
	public boolean equals(Object otro) {
		if(otro == null) return false;
		if(otro ==  this) return true;
		if(otro.getClass() == Complejo.class) {
			Complejo c = (Complejo)otro;
			return r == c.r && i == c.i; 
			
		}else if(i == 0 && otro instanceof Double) {
			Double d = (Double)otro;
			return r == d.doubleValue();
		}
		return false;
	}
	
	//----Operaciones
	
	public Complejo mas(Complejo otro) {
		if(otro == null)
			throw new IllegalArgumentException("complejo no existe");
		return new Complejo(r + otro.r, i + otro.i);
	}
	
	public Complejo menos(Complejo otro) {
		if(otro == null)
			throw new IllegalArgumentException("complejo no existe");
		return new Complejo(r - otro.r, i - otro.i);
	}

	public Complejo por(Complejo otro) {
		if(otro == null)
			throw new IllegalArgumentException("complejo no existe");
		return new Complejo( r *otro.r - i *otro.i, r * otro.i + otro.r * i );
	}
	
	public static Complejo mas(Complejo c1, Complejo c2) {
		if(c1 == null || c2 == null)
			throw new IllegalArgumentException("Alguno de los complejos no existe");
		return new Complejo(c1.r + c2.r, c1.i + c2.i);
	}
	
	public static void main(String[] args) {
		Complejo c1 = new Complejo(8, 5);
		Complejo c2 = new Complejo(1, 1);
		System.out.println(c1.por(c2));
		
		
	}

}
