package Viajero;

public class simple extends viajero{
	
	public simple(String n, int d, double mnc){
		super(n, d, mnc);
	}
	
	public void DescAcumulable(double millas){
		setDescuento((int) millas/1000);
	}
}