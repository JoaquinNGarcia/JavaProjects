package reportes;

import java.io.Serializable;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class estadisticas implements Serializable{
	private int conthot, contvia, contcom, contcon;
	private double acumhot, acumvia, acumcom, acumcon;
	
	public estadisticas(){
		conthot = contvia = contcom = contcon = 0;
		acumhot = acumvia = acumcom = acumcon = 0;
	}


	public int getConthot() {
		return conthot;
	}



	public void setConthot(int conthot) {
		this.conthot = conthot;
	}



	public int getContvia() {
		return contvia;
	}



	public void setContvia(int contvia) {
		this.contvia = contvia;
	}



	public int getContcom() {
		return contcom;
	}



	public void setContcom(int contcom) {
		this.contcom = contcom;
	}



	public int getContcon() {
		return contcon;
	}



	public void setContcon(int contcon) {
		this.contcon = contcon;
	}



	public double getAcumhot() {
		return acumhot;
	}



	public void setAcumhot(double acumhot) {
		this.acumhot = acumhot;
	}



	public double getAcumvia() {
		return acumvia;
	}



	public void setAcumvia(double acumvia) {
		this.acumvia = acumvia;
	}



	public double getAcumcom() {
		return acumcom;
	}



	public void setAcumcom(double acumcom) {
		this.acumcom = acumcom;
	}



	public double getAcumcon() {
		return acumcon;
	}



	public void setAcumcon(double acumcon) {
		this.acumcon = acumcon;
	}



	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		return ("hola" + " " + df.format(acumcon)+ " " + df.format(acumcom)+ " " + df.format(acumvia)+ " " + df.format(acumhot) + " " + contcon + " " + contcom + " " + contvia + " " + conthot);
	}
	
	
	
}


