package Tabladestino;

import java.io.Serializable;

public class lugar implements Serializable{
	private String nomb, desc;
	private int desveceselegido;
	
	public lugar(String n, String d){
		nomb=n;
		desc=d;
		desveceselegido = 0;
	}
	public String getNomb() {
		return nomb;
	}
	public void setNomb(String nomb) {
		this.nomb = nomb;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getDesveceselegido() {
		return desveceselegido;
	}

	public void setDesveceselegido(int desveceselegido) {
		this.desveceselegido = desveceselegido;
	}
	
	@Override
	public String toString(){
		return (nomb + " " + desc + "   " + desveceselegido);
	}
}

