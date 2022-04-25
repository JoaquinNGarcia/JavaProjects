package generadordemillas;

public enum Est {
	Disponible, Suspendido, Caduco;
	
	@Override
	public String toString(){
		switch(this){
		case Disponible:
			return "Disponible";
		case Suspendido:
			return "Suspendido";
		case Caduco:
			return "Caduco";
		default:
			throw new IllegalArgumentException();
		}
	}
}
