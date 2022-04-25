package Archivos;

import obtenciondemillas.Obtenciondemillas;
import java.io.*;
import java.util.LinkedList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Obdemillas {
	public static LinkedList<Obtenciondemillas> cargaob(){
		Document document=null;
		LinkedList<Obtenciondemillas> lista = new LinkedList<>();
		try {
	        //Cargamos el document del fichero XML existente
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        document = db.parse(new File("Obdemillas.xml"));
	        document.getDocumentElement().normalize();
	    } catch (ParserConfigurationException e) {
	        e.printStackTrace();
	    } catch (SAXException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		NodeList listaNodos = document.getDocumentElement().getElementsByTagName("dni");
	    Node nodo;
	    for(int i=0; i<listaNodos.getLength(); i++){
	    	int dni;
	    	String id = null;
        	double valor;
	        nodo = listaNodos.item(i);
	        dni = Integer.parseInt(nodo.getAttributes().getNamedItem("DNI").getNodeValue());
	        NodeList listaCaracteristicas = nodo.getChildNodes();
	    	int n = 0;
	        for(int z=0; z<listaCaracteristicas.getLength();z++){
	        	Node nodo2 = listaCaracteristicas.item(z);
	        	if (nodo2.getNodeType() == Node.ELEMENT_NODE){
	        		if ((n % 2) == 0){
	        			id = listaCaracteristicas.item(z).getTextContent();
	        		}
	        		else{
	        			valor = Double.parseDouble(listaCaracteristicas.item(z).getTextContent());
	    	            lista.add(new Obtenciondemillas(dni, id, valor));
	        		}
	        		n++;
	        	}
	        }
	    }
	    return lista;
	}
}
