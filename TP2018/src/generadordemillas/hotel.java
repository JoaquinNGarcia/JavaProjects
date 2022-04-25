package generadordemillas;

import Viajero.viajero;
import reportes.estadisticas;

@SuppressWarnings("serial")
public class hotel extends generadordemillas{
	private double precionoche;
	private int cantnoches, cat;
	public hotel(String i, String n, String d, double f, Est e, int c, double pn, int cn){
		super(i,n,d,f, e);
		cat = c;
		precionoche = pn;
		cantnoches = cn;
	}
	public int getCat() {
		return cat;
	}
	public void setCat(int cat) {
		this.cat = cat;
	}
	public double getPrecionoche() {
		return precionoche;
	}
	public void setPrecionoche(double precionoche) {
		this.precionoche = precionoche;
	}
	public int getCantnoches() {
		return cantnoches;
	}
	public void setCantnoches(int cantnoches) {
		this.cantnoches = cantnoches;
	}
	
	@Override
	public double gananciamillas(double valor, viajero via, estadisticas es){
		double millas;
		millas = getFactor() * cantnoches * precionoche;
		via.setMillasHotel(millas);
		es.setAcumhot(es.getAcumhot() + millas);
		es.setConthot(es.getConthot() + 1);
		return millas;
	}

	@Override
	public String toString(){
		return (super.toString() + "Categoria: " + cat + "Precio noche: " + precionoche + "Cant. de noches: " + cantnoches);
	}
	
}