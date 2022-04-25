package Juego;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import Region.Region;
import Servidor.Servidor;
import Jugador.Historial;
import Jugador.Jugador;
import Funciones.Funciones;
import Partida.BigWar;
import Partida.Ffa;
import Partida.Teams;
import Partida.Partida;
import Jugador.Reporte;

/**
 * La clase juego permite obtener datos como region, jugadores,
 * matriz, y setearlas.
 * 
 * @author
 */
//Excesivo acoplamiento de interfaz-lógica de negocios!!! 
//Esta clase maneja los menúes y la lógica principal del juego. Debería tener ambas tareas separadas en 2 clases al menos, de forma que si más adelante se quiere cambiar interfaz consola por swing se pueda realizar de forma simple sin tener que tocar clases que manejen lógica de negocios.
//La clase "Juego" debería únicamente centrarse en proveer los métodos necesarios para que la interfaz de usuario los llame para realizar las operaciones necesarias y obtener los datos/resultados a mostrar.
//La lógica de creación/ejecución de partida debería manejarse mediante la clase "Partida" y sus derivadas y no mediante "Juego". De esta forma cada clase se encarga de su funcionalidad y lógica interna y a la vez que se reduce la cantidad de código entre clases.
//Idem del punto anterior para los reportes!
//Comparar objetos (Region) mediante su propio Equals en lugar de acceder a su "ID" y compararlos desde otras clases.
public class Juego implements Serializable {
	private static ArrayList<Region> regiones;
	private static ArrayList<Jugador> jugadores;
	Jugador jugadorLogeado;
	Jugador jugador_1;
	Jugador jugador_2;
	private Boolean login;
	
	public Juego() {
		this.login = false;
		this.regiones = new ArrayList<Region>();
		this.jugadores = new ArrayList<Jugador>();
	}

	//No hacer getters innecesarios (no se llama).
	public ArrayList<Region> getRegion() {
		return regiones;
	}

	public void setRegion(Region region) {
		this.regiones.add(region);
	}

	public ArrayList<Jugador> getJugador() {
		return jugadores;
	}

	public void setJugador(Jugador player) {
		this.jugadores.add(player);
	}

	/**
	 * Informa datos sobre el juego
	 */
	public void mostrar() {
		System.out.println("Juego unico ");
		int lengthJug = jugadores.size();
		System.out.println("Tamaño de jugadores = " + lengthJug);
		for (Region p : regiones) {
			System.out.println("Region");
			p.mostrar();
		}
	}

	/**
	 * Convierte la informacion de un archivo a bytes para su uso en el codigo.
	 */
	static void serializarJugador() {
		String absoluteR = new File("").getAbsolutePath();
		try {
			ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(absoluteR + "/txt/juego.txt")); //No hace falta guardarlo con ".txt".
			escribir.writeObject(jugadores);
			escribir.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * Carga los datos de un archivo txt y los agrega a una lista de jugadores.
	 */
	public static void cargaJugadores() {
		String absoluteR = new File("").getAbsolutePath();
		try {
			ObjectInputStream leer = new ObjectInputStream(new FileInputStream(absoluteR + "/txt/juego.txt"));
			jugadores = (ArrayList<Jugador>) leer.readObject();
			leer.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
	
	public Integer[][] getmatrizHistoria(ArrayList<Historial> aux_historial) {
		//Integer[][] matrizHistorial = new Integer[2][3];
		Integer[][] matrizHistorial = {{0,0},{0,0},{0,0}};
		String pos_tipo[]=new String[] {"FFA","2P","Big War"}; //Utilizar Enums para los tipos de partida
		int fila_historia=-1;
		for (Historial h : aux_historial) {
			fila_historia=java.util.Arrays.asList(pos_tipo).indexOf(h.getTipoPartida());
			if(fila_historia!= -1 ) {
				if(h.getVictoria()) {
					matrizHistorial[fila_historia][0]++;
				}else {
					matrizHistorial[fila_historia][1]++;
				}
			}
		}
		return matrizHistorial;
	}
	
	/**
	 * 
	 */
	//Porqué manejar la funcionalidad de generación de un reporte en la clase "Juego" (generación de listados, ordenamiento en base a criterios, etc)??? 
	//Debería modularizar más y aprovechar a que esta funcionalidad se implemente en métodos de la clase "Reporte" y desde "Juego" simplemente llamar esos métodos públicos...
	public void getReporte1() {
		Integer[][] matrizHistorial = new Integer[2][3];
		Integer perdidas = 0;
		Integer ganadas = 0;
		ArrayList<Reporte> jugadoresAptos = new ArrayList<Reporte>();
		for (Jugador j : jugadores){
			matrizHistorial = getmatrizHistoria(j.getHistorialJugador());
			if( matrizHistorial[0][1] != null && matrizHistorial[1][1] != null ) {
				ganadas = matrizHistorial[0][0] + matrizHistorial[1][0];
				perdidas = matrizHistorial[0][1] + matrizHistorial[1][1];
				if(ganadas > perdidas)
					jugadoresAptos.add(new Reporte(j.getNick(), j.getPuntosGlobal(), ganadas, perdidas, 0.0));
			}
		}
		if(jugadoresAptos.isEmpty()) 
			System.out.println("Nadie ha calificado"); //Acoplamiento de interfaz
		else {
			int j = 0;
			jugadoresAptos.sort(Comparator.comparing(Reporte::getGlobalpuntos).reversed());
			System.out.println("JUGADORES CALIFICADOS");
			for (Reporte jj : jugadoresAptos) {
				System.out.println( jj.getNick()+" [ "+jj.getGlobalpuntos()+" PUNTOS ]"+" Ganadas: "+jj.getGanadas()+" Perdidas: "+jj.getPerdidas() ); //Acoplamiento de interfaz
			}
		}
	}
	
	/**
	 * 
	 */
	//Porqué manejar la funcionalidad de generación de un reporte en la clase "Juego" (generación de listados, ordenamiento en base a criterios, etc)??? 
    //Debería modularizar más y aprovechar a que esta funcionalidad se implemente en métodos de la clase "Reporte" y desde "Juego" simplemente llamar esos métodos públicos...
	public void getReporte2() {
		Integer[][] matrizHistorial = new Integer[3][2];
		Integer perdidas = 0;
		Integer ganadas = 0;
		Double porcentaje = 0.0;
		Integer todos = 0;
		ArrayList<Reporte> jugadoresAptos = new ArrayList<Reporte>();
		System.out.println("Agregar tipo de partida "); //Acoplamiento con interfaz
		Scanner render = new Scanner(System.in); //Acoplamiento con interfaz (lectura!!)
		String opTipo = "";
		Boolean ffa_on = false;
		Boolean team_on = false;
		Boolean bw_on = false;
		do {
			System.out.println("1) FREE FOR ALL"); //Acoplamiento con interfaz
			System.out.println("2) 2P"); //Acoplamiento con interfaz
			System.out.println("3) BIG WAR"); //Acoplamiento con interfaz
			System.out.println("4) Listo"); //Acoplamiento con interfaz
			opTipo = render.nextLine().trim();
			switch (opTipo) {
				case "1":
					ffa_on = true;
				break;
				case "2":
					team_on = true;
				break;
				case "3":
					bw_on = true;
				break;
			}
		} while (!opTipo.equals("4"));
		for (Region reg : regiones) {
			String key_reg = reg.getRegionKey();
			System.out.println("Region: " + key_reg); //Acoplamiento con interfaz
			jugadoresAptos.clear();
			for (Jugador j : jugadores){
				ganadas = 0;
				perdidas = 0;
				todos = 0;
				porcentaje = 0.0;
				if(j.getRegion().equals(key_reg)) { //No! Comparar objetos de tipo "Region" mediante Equals desarrollado en esa clase, no comparar Strings!!
					matrizHistorial = getmatrizHistoria(j.getHistorialJugador());
					if(ffa_on) { //Poco flexible, si se agregan varios modos de juego más hay que extender el if con más condiciones...para mantener las matrices buscaría tener un listado de modos y manejar los índices con ese listado...
						ganadas += matrizHistorial[0][0];
						perdidas += matrizHistorial[0][1];
					}
					if(team_on) {
						ganadas += matrizHistorial[1][0];
						perdidas += matrizHistorial[1][1];
					}
					if(bw_on) {
						ganadas += matrizHistorial[2][0];
						perdidas += matrizHistorial[2][1];
					}
					todos=ganadas + perdidas;
					if(ganadas != 0)
						porcentaje = (double) (ganadas*100/(todos));
					jugadoresAptos.add(new Reporte(j.getNick(), j.getPuntosGlobal(),ganadas , perdidas,porcentaje ));
				}
			}
			if(jugadoresAptos.isEmpty()) 
				System.out.println("Nadie ha calificado"); //Acoplamiento con interfaz
			else {
				jugadoresAptos.sort(Comparator.comparing(Reporte::getEfectividad).reversed());
				System.out.println("JUGADORES CALIFICADOS"); //Acoplamiento con interfaz
				for (Reporte jj : jugadoresAptos) {
					System.out.println( jj.getNick() + " [ "+jj.getGlobalpuntos() + " PUNTOS ]" + " Efectividad: " + jj.getEfectividad() ); //Acoplamiento con interfaz
				}
			}
		}
	}

	/**
	 * Al terminar todas las partidas se desconectan los jugadores que parciticiparon de la misma
	 * @param jugadores_off
	 */
	void DesconectarJugadores( ArrayList<Jugador> jugadores_off) {
		int cont = 0;
		for (Jugador j : jugadores_off) {
			cont++;
			j.setEnPartida(false);
		}
		System.out.println("SE DESCONECTARON: " + cont); //Acoplamiento con interfaz
	}

	//No! Manejar las regiones como parámetros de tipo "Region", no mediante sus ids!
	public boolean jugadoresDisponibles(String reg) {
		int disponibles = 0;
		for (Jugador j : jugadores) {
			if (j.getRegion().equals(reg) && !j.getEnPartida()) //Evitar comparar regiones de esta forma y comparar objetos de tipo "Region" con su propio Equals!
				disponibles++;
		}
		System.out.println("- Cantidad de Jugadores en " + reg + " disponibles: " +disponibles); //Acoplamiento con interfaz
		return disponibles >= 2;
	}

	//Este método debería ser parte de otra clase de interfaz "Menu"!!!!
	//Si quiero cambiar la interfaz del proyecto por Swing tengo que depurar o remover completamente funcionalidad de este estilo!!!
	public void menu() {
		System.out.println("--------------------------------------------------------------");
		
		if (!login) {
			System.out.println("A) Admin");
			System.out.println("1) Reporte 1");
			System.out.println("2) Reporte 2");
			System.out.println("I) Iniciar Sesion");
			System.out.println("R) Registrarse");
		} else {
			System.out.println("MIS DATOS");
			System.out.println("--------------------------------------------------------------");
			System.out.println("USUARIO: " + jugadorLogeado.getNick());
			System.out.println("RANGO: " + Funciones.getRango(jugadorLogeado.getPuntosGlobal()));
			System.out.println("PUNTOS: " + jugadorLogeado.getPuntosGlobal());
			System.out.println("HISTORIA: ");
			jugadorLogeado.getHistorialJugadorText();
			System.out.println("LOGROS: ");
			jugadorLogeado.getLogrosJugadorText();
			System.out.println("--------------------------------------------------------------\n");
			System.out.println("A) Admin");
			System.out.println("1) Reporte 1");
			System.out.println("2) Reporte 2");
			System.out.println("C) Cerrar Sesion");
		}
		System.out.println("Elija una opcion: ");
		Scanner render = new Scanner(System.in);
		String op = render.nextLine();
		op = op.toUpperCase().trim();
		if (Funciones.validadorGenerico("", op, "menu", login))
			options(op);
		else
			menu();
	}

	/**
	 * Procedimiento que muestra las opciones y toma los valores ingresados por el usuario (admin)
	 * @param opt
	 */
	//Fuerte acoplamiento de interfaz-lógica de negocios!!!
	//Idem al método anterior!
	//Creación de partidas muy poco flexible! 
	//Debería proveer métodos en cada tipo de partida que validen y generen la partida según sus requerimientos puntuales sin implementar esa lógica desde otra clase (Juego)!!!
	//Agrupar llamadas de creación de "Partida" en una único método público (se llama el mismo bloque de métodos para cada tipo de partidas) y cambiar el resto de los métodos a privados para que se llamen internamente desde este método.
	//De esta forma "Partida" y cada uno de sus sub-tipos llaman los métodos necesarios en orden para crearse, ejecutarse y actualizar datos y proveen los métodos públicos justos y necesarios para que las clases como "Juego" no deban entrar en su lógica de ejecución.
	public void options(String opt) {
		serializarJugador();
		switch (opt) {
		case "A":
			boolean continua = true;
			do {
				System.out.println("		"); //Acoplamiento con interfaz
				System.out.println("GENERANDO PARTIDA ... "); //Acoplamiento con interfaz
				System.out.println("		"); //Acoplamiento con interfaz
				System.out.println("CARGANDO REGIONES..."); //Acoplamiento con interfaz
				System.out.println("		"); //Acoplamiento con interfaz
				String region_this = "";
				String validadorGenerador = "";
				String selectRegion = "";
				Region objetoRegion=null;
				for (Region r : regiones) {
					region_this = r.getRegionKey();
					if (r.servidorDisponible() && jugadoresDisponibles(region_this)) { //Utilizar directamente la región de tipo "Region", no mediante un String con su ID
						validadorGenerador += (", " + region_this);

					}
				}				
				if (validadorGenerador != "") {
					Scanner render2 = new Scanner(System.in); //Acoplamiento con interfaz (lectura!!)
					validadorGenerador = validadorGenerador.replaceFirst(",", "");
					String renderRegion = "";
					do {
						System.out.println("	");
						System.out.println("Ingresa una region");
						System.out.println("[ " + validadorGenerador + " ]");
						renderRegion = render2.nextLine();
						renderRegion = renderRegion.toUpperCase().trim();
						selectRegion = renderRegion;

					} while (!Funciones.validadorGenerico(validadorGenerador, renderRegion, "", false));
					for (Region r : regiones) {
						if(r.getRegionKey()==selectRegion);
							objetoRegion=r;
					}	
					//Cambia el primer server que no este en true a true -> por ende a ocupado 
					objetoRegion.cambiarEstadoPrimerServer(true);
					System.out.println("		"); //Acoplamiento con interfaz
					System.out.println("1) FREE FOR ALL"); //Acoplamiento con interfaz
					System.out.println("2) 2P"); //Acoplamiento con interfaz
					System.out.println("3) BIG WAR"); //Acoplamiento con interfaz
					String opc_juego;
					do {
						System.out.println("		"); //Acoplamiento con interfaz
						System.out.println("Ingrese una opc valida: "); //Acoplamiento con interfaz
						Scanner render3 = new Scanner(System.in); //Acoplamiento con interfaz
						opc_juego = render3.nextLine();
					} while (!Funciones.validadorGenerico("1,2,3", opc_juego, "", false) );
					ArrayList<Jugador> jugadores_aptos = new ArrayList<Jugador>();
					if(opc_juego.equals("3")) { //Muy poco flexible! Encapsular validaciones y armado de partida en la clase "Partida" y sus subclases correspondientes!
						jugadores.sort(Comparator.comparing(Jugador::getPuntosGlobal).reversed());
					} else {
						Collections.shuffle(jugadores);
					}
					int min_partida = 2;
					if (opc_juego.compareTo("2") == 0)
						min_partida = 4;
					int cant_jugadores = 0;
					String aux_jugadores = "";
					boolean cant_correcta = false;
					System.out.println("	"); //Acoplamiento con interfaz
					do {
						do {
							System.out.println("Indica la cantidad de jugadores: "); //Acoplamiento con interfaz
							Scanner render4 = new Scanner(System.in); //Acoplamiento con interfaz
							aux_jugadores = render4.nextLine(); //Acoplamiento con interfaz
						} while (!Funciones.validaNumeros(aux_jugadores));
						
						if(Integer.parseInt(aux_jugadores) < min_partida) {
							System.out.println("El minimo de jugadores para este modo de juego es: " + min_partida); //Acoplamiento con interfaz
							System.out.println("	"); //Acoplamiento con interfaz
						}
						else {
							cant_correcta = true;
							System.out.println("	"); //Acoplamiento con interfaz
						}
					} while (!cant_correcta);
					cant_jugadores = Integer.parseInt(aux_jugadores);
					int cant_jugadores_aux = cant_jugadores;
					
					for (Jugador j : jugadores) {
						if (j.getRegion().equals(selectRegion) && !j.getEnPartida() && cant_jugadores_aux != 0) { //Evitar comparar regiones de esta forma y comparar objetos de tipo "Region" con su propio Equals!
							cant_jugadores_aux--;
							jugadores_aptos.add(j);
							j.setEnPartida(true);
						}
					}
					if (cant_jugadores_aux > 0) {
						System.out.println("		"); //Acoplamiento con interfaz
						System.out.println("No se pudieron completar todos los jugadores, la"); //Acoplamiento con interfaz
						System.out.println("partida se va a armar con jugadores de otra reg..."); //Acoplamiento con interfaz
						System.out.println("		"); //Acoplamiento con interfaz
						for (Jugador j : jugadores) {
							if (!j.getRegion().equals(selectRegion) && !j.getEnPartida() && cant_jugadores_aux != 0) { //Evitar comparar regiones de esta forma y comparar objetos de tipo "Region" con su propio Equals!
								cant_jugadores_aux--;
								jugadores_aptos.add(j);
							}
						}
						jugadores_aptos.sort(Comparator.comparing(Jugador::getPuntosGlobal).reversed());
					}
					if (cant_jugadores_aux > 0) {
						System.out.println("Se va jugar con los equipos sin completar..."); //Acoplamiento con interfaz
						cant_jugadores -= cant_jugadores_aux;
						if(cant_jugadores_aux == 1)
							System.out.println("Faltaron " + cant_jugadores_aux + " jugadores..."); //Acoplamiento con interfaz
						else
							System.out.println("Falto " + cant_jugadores_aux + " jugador..."); //Acoplamiento con interfaz
						System.out.println("Tu peticion se redujo a " + cant_jugadores + " jugadores..."); //Acoplamiento con interfaz
						System.out.println("		"); //Acoplamiento con interfaz
					}
					switch (opc_juego) {
					case "1":
						System.out.println("MODO DE JUEGO: FREE FOR ALL");
						Ffa juegoFFA = new Ffa(cant_jugadores, selectRegion, 0, 200);
						juegoFFA.armarMatrices(0, 1, cant_jugadores, jugadores_aptos); //Si se van a llamar todos los mismos métodos por cada tipo de juego no tendría más sentido que sea 1 solo método de "Partida" que luego internamente llame a estos métodos privados puntuales?
						juegoFFA.muestraMatriz();									   //No está bien armar la matriz según el tipo de partida...la idea es manejar la creación de forma polimórfica y que el tipo de instancia creada meneje las diferencias
						juegoFFA.jugar();
						juegoFFA.actualizarHistorial("FFA"); //Este parámetro no debería ser necesario si el método es polimórfico...
						juegoFFA.desbloqueoLogros();
						juegoFFA.actualizarPuntos();
						DesconectarJugadores(jugadores_aptos);
						objetoRegion.cambiarEstadoPrimerServer(false);
						serializarJugador();
						break;

					case "2":
						System.out.println("MODO DE JUEGO: 2PLAYERS");
						Teams juego2P = new Teams(cant_jugadores, selectRegion, 0, 200);
						juego2P.armarMatrices(0, 2, cant_jugadores, jugadores_aptos); //No está bien armar la matriz según el tipo de partida...la idea es manejar la creación de forma polimórfica y que el tipo de instancia creada meneje las diferencias
						juego2P.muestraMatriz();
						juego2P.jugar();
						juego2P.actualizarHistorial("2P"); //Este parámetro no debería ser necesario si el método es polimórfico...
						juego2P.desbloqueoLogros();
						juego2P.actualizarPuntos();
						DesconectarJugadores(jugadores_aptos);
						objetoRegion.cambiarEstadoPrimerServer(false);
						serializarJugador();
						break;

					case "3":
						System.out.println("MODO DE JUEGO: BIGWAR");
						BigWar juegoBW = new BigWar(cant_jugadores, selectRegion);
						juegoBW.armarMatrices(2, 0, cant_jugadores, jugadores_aptos); //No está bien armar la matriz según el tipo de partida...la idea es manejar la creación de forma polimórfica y que el tipo de instancia creada meneje las diferencias
						juegoBW.muestraMatriz();
						juegoBW.jugar();
						juegoBW.actualizarHistorial("Big War"); //Este parámetro no debería ser necesario si el método es polimórfico...
						juegoBW.desbloqueoLogros();
						DesconectarJugadores(jugadores_aptos);
						objetoRegion.cambiarEstadoPrimerServer(false);
						serializarJugador();
						break;
					}
				} else {
					System.out.println("No hay regiones disponibles"); //Acoplamiento con interfaz
					continua = false;
				}
				System.out.println("	"); //Acoplamiento con interfaz
				System.out.println("Quieres cargar otra partida?"); //Acoplamiento con interfaz
				System.out.println("1) Si"); //Acoplamiento con interfaz
				System.out.println("2) No"); //Acoplamiento con interfaz
				String opc_sec;
				do {
					System.out.println("		"); //Acoplamiento con interfaz
					System.out.println("Ingrese una opc valida:"); //Acoplamiento con interfaz
					Scanner rendersec = new Scanner(System.in);
					opc_sec = rendersec.nextLine();
				} while (!Funciones.validadorGenerico("1,2", opc_sec, "", false) );
				if (opc_sec.compareTo("2") == 0)
					continua = false;
				System.out.println("	");
			}while (continua == true);
			break;
		case "I":
			System.out.println("Ingresa tu nick name: ");
			Scanner render = new Scanner(System.in);
			String userLogin = render.nextLine();
			for (Jugador p : jugadores) {
				if (p.getNick().equals(userLogin)) {
					System.out.println("Ingresa tu contraseï¿½a: ");
					render = new Scanner(System.in);
					String userPassw = render.nextLine();
					while (!p.getPassword().equals(userPassw)) {
						System.out.println("Contraseña erronea");
						System.out.println("Ingresa tu contraseña: ");
						render = new Scanner(System.in);
						userPassw = render.nextLine();
					}
					jugadorLogeado = p;
					login = true;
					System.out.println("Bienvenido " + userLogin + "!");
					break;
				}
			}
			if (!login) {
				System.out.println("Usuario no encontrado...");
			}
			break;
		case "R":
			System.out.println("REGISTRO");
			for (Region r : regiones) {
				System.out.println(r.getRegionKey() + " - " + r.getRegionDes());
			}
			Scanner render2 = new Scanner(System.in);
			String renderRegion = "";
			do {
				System.out.println("Ingresa el codigo de tu region: ");
				renderRegion = render2.nextLine();
				renderRegion = renderRegion.toUpperCase().trim();
			} while (!Funciones.validadorGenerico("", renderRegion, "region", login));
			Scanner render3 = new Scanner(System.in);
			String renderNick = "";
			Boolean nickIgual = true;
			while (nickIgual) {
				System.out.println("Ingresa tu Nick");
				renderNick = render3.nextLine();
				renderNick = renderNick.trim();
				if (renderNick.length() > 3) {
					nickIgual = false;
					for (Jugador p : jugadores) {
						if (p.getNick().equals(renderNick)) {
							nickIgual = true;
							break;
						}
					}
					if (nickIgual)
						System.out.println("Nick Repetido!");
				} else {
					System.out.println("Nick tiene que tener al menos 4 caracteres!");
				}
			}
			Scanner render4 = new Scanner(System.in);
			String renderMail = "";
			Boolean mailIgual = true;
			while (mailIgual) {
				System.out.println("Ingresa tu mail:");
				renderMail = render4.nextLine();
				renderMail = renderMail.trim();
				if (Funciones.validadorDeMail(renderMail)) {
					mailIgual = false;
					for (Jugador p : jugadores) {
						if (p.getMail().equals(renderMail)) {
							mailIgual = true;
							break;
						}
					}
					if (mailIgual)
						System.out.println("Mail Repetido!");

				} else {
					System.out.println("Mail erroneo!");
				}
			}
			Scanner render5 = new Scanner(System.in);
			String renderPassword = "";
			Boolean passwordLength = false;
			while (!passwordLength) {
				System.out.println("Ingresa tu contraseña");
				renderPassword = render5.nextLine();
				renderPassword = renderPassword.trim();
				if (renderPassword.length() > 3) {
					passwordLength = true;
				} else {
					System.out.println("La contraseña tiene que tener al menos 4 caracteres!");
				}
			}
			System.out.println("REGITRO CORRECTO ");
			System.out.println("Region " + renderRegion);
			System.out.println("Nick " + renderNick);
			System.out.println("Mail " + renderMail);
			Jugador jugadorForm = new Jugador(renderNick, renderMail, renderRegion, renderPassword, false, 0);
			setJugador(jugadorForm);
			serializarJugador();
			break;
		case "C":
			System.out.println("CERRANDO SESION ");
			jugadorLogeado = null;
			login = false;
			break;
		case "1":
			System.out.println("REPORTE 1");
			System.out.println("--------------------------------------------------------------");
			getReporte1();
			
			break;
		case "2":
			System.out.println("REPORTE 2");
			System.out.println("--------------------------------------------------------------");
			getReporte2();
			break;
		default: {
		}
		}
		menu();
	}
}
