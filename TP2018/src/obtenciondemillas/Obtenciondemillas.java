package obtenciondemillas;

public class Obtenciondemillas {
	String id;
	int dni;
	double valor;
	
	public Obtenciondemillas(int d, String i, double v){
		dni = d;
		id = i;
		valor = v;
	}

	public String getId() {
		return id;
	}

	public int getDni() {
		return dni;
	}

	public double getValor() {
		return valor;
	}
	
	
}
