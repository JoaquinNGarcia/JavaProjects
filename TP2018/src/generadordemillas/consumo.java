package generadordemillas;

import Viajero.viajero;
import reportes.estadisticas;

@SuppressWarnings("serial")
public class consumo extends generadordemillas{
	private double costoconsumo;
	
	public consumo(String id, String nomb, String desc,double factor, Est e){
		super(id,nomb,desc,factor, e);
		costoconsumo = 0;
	}

	public double getCostoconsumo() {
		return costoconsumo;
	}

	public void setCostoconsumo(double costoconsumo) {
		this.costoconsumo = costoconsumo;
	}
	
	@Override
	public double gananciamillas(double valor, viajero via, estadisticas es){
		double millas=0;
		millas = valor * getFactor();
		via.setMillasCons(millas);
		es.setAcumcon(es.getAcumcon() + millas);
		es.setContcon(es.getContcon() + 1);
		return millas;
	}
	
	@Override
	public String toString(){
		return (super.toString() + "costoconsumo: " + costoconsumo);
	}
}
