package Viajero;

public class frecuente extends viajero{
	
	public frecuente(String n, int d, double mnc){
		super(n, d, mnc);
	}
	
	public void DescAcumulable(double millas){
		setDescuento((int)(millas/1000)*2);
	}
}
