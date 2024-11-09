package com.indracompany.treinamento.util.xml;

/**
 * Description: Excecao que estende RuntimeException  e e' lancada pela classe Elemento
 * na verificacao de obrigatoriedade de elementos e na conversao dos valores dos elementos
 * para tipos numericos e datas.
 *
 * @author Politec
 * @version 1.0
 */

public class ElementException extends RuntimeException 
		implements java.io.Serializable {

	/**
	 * Lista com os nomes de Elementos que lan�aram exce��o
	 */
	private String[] nomesElementos;
	
	/**
	 * Constante que representa o identificador �nico para interoperabilidade
	 * da JVM.
	 */
	private static final long serialVersionUID = -1248636444949584426L;
	
	/**
	 * Metodo que lanca uma mensagem padrao.
	 */
	public ElementException(){
		
		super("Elemento nao esta de acordo com o esperado");
	}

	/**
	 * Metodo que lanca a mensagem recebida no parametro.
	 * @param msg mensagem que sera lancada.
	 */
	public ElementException(String msg) {
	  
		super(msg);
	}


	
	/**
	 * Obt�m uma lista de nomes de elementos que lan�aram a exce��o.
	 * @return uma lista de nomes de elementos
	 */
	public String[] getNomesElementos() {
		return nomesElementos;
	}
}//ElementException
