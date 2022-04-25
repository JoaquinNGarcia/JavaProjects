 package Funciones;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Jugador.Jugador;

/**
 * Esta clase contiene algunas de las funciones que utiliza el programa.
 */
//Esta función debería estar asociada directamente con la interfaz dado que es muy específica en cuanto a lo que valida (evaluar posibilidad de utilizar Enum y de esa forma hacerla más genérica para usarse con otro tipo de interfaces).
public class Funciones {	
	public static Boolean validadorGenerico(String opcValida, String opc, String tipo, Boolean login){
		if( opc.length() == 0 || opc.indexOf(",") != -1 ) 
			return false;
		if(opcValida == "") {
			switch(tipo) {
				case "menu":
					opcValida = "I, R, A, 1, 2"; //Es muy raro comparar un String para evaluar si las opciones ingresadas son correctas....convendría validar paso a paso para evitar este tipo de manejos.
					if(login)
						opcValida = "C, A, 1, 2";
				break;
				case "region":
					opcValida = "NA, EU"; //Y si hay una tercera región? O 20? Las regiones no tendrían que estar definidas en Strings, Enums ni constantes, son dato de entrada!
				break;
			}
		}
		return( opcValida.indexOf(opc) != -1 );
	}
	/**
	 * Valida el mail del usuario
	 * @return
	 */
	public static Boolean validadorDeMail(String email) {
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		return mather.find();
	}
	/**
	 * Metodo que se utiliza para obtener un numero aleatorio y da funcionalidad al juego
	 * @param max
	 * @return
	 */
	//Bien!
	public static Integer getRamdomNumber(int max){
		return (int) ((Math.random() * max));
	}
	
	/**
	 * Comprueba que el numero sea correcto
	 * @param numero
	 * @return
	 */
	public static Boolean validaNumeros(String numero){
		try {
			int numero1 = Integer.parseInt(numero);
			return (numero1 > 0) ? true : false;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * Calcula e informa segun la cantidad de puntos ingresada el rango del jugador
	 * @param puntos
	 * @return
	 */
	//Las franjas de probabilidades y las categorías deberían estar almacenadas en constantes o Enums!
	public static String getRango(int puntos) {
		if (puntos <= 5)
			return "Novice";
		else {
			if (puntos <= 15)
				return "Silver";
			else {
				if (puntos <= 30)
					return "Gold";
				else
					return "Platinum";
			}
		}
	}
}
