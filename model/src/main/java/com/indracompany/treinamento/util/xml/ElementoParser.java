package com.indracompany.treinamento.util.xml;


/**
 * Description: Esta classe nao deve ser usada diretamente, e' usada apenas
 * pela classe Elemento.
 * ElementoParser e' responsavel por, percorrer e montar uma estrura xml utilizando corretamente
 * os caracteres  "<" e "/>".
 *
 */


class ElementoParser implements java.io.Serializable {

	private final StringBuilder xml_;

	public final int openTagInicio_;
	private final int openTagFim_;

	public int closeTagInicio_;
	private int closeTagFim_;

	private java.lang.String nomeElemento_;
	private java.lang.String valorElemento_;
	private java.util.Vector filhos_;

	private boolean isVazio_ = false;
	private boolean isValorado_ = false;
	private boolean isContainer_ = false;


	public ElementoParser(StringBuilder xml) {
		this(xml, 0);
	}
	
	public ElementoParser(StringBuilder xmlEntrada, int openTagInicio) {
		xml_ = xmlEntrada;
	
		if (xml_.charAt(openTagInicio) != '<') {
			throw new RuntimeException("Malformed document: "
							+ xml_.substring(openTagInicio));
		}
	
	
		int pos = openTagInicio;
		while( (pos != -1) && (pos + 1 != xml_.length()) &&
			   (xml_.charAt(pos+1) == '?')) {
	
			pos = xml_.indexOf("<", pos+1);
		}
	
		if( pos == -1) {
			throw new RuntimeException("Malformed tag: "
						+ xml_.substring(openTagInicio));
		}
	
		openTagInicio_ = pos;
	
		openTagFim_ = xml_.indexOf(">", openTagInicio_);
	
		if (openTagFim_ == -1) {
			throw new RuntimeException("Malformed tag: "
						+ xml_.substring(openTagInicio));
		}
	
		setNome();
	
		setTipo();
	}
	
	
	public ElementoParser[] getFilhos() {
		if ((filhos_ == null) || (filhos_.size() == 0)) {
			return null;
		}
	
		int numFilhos = filhos_.size();
		ElementoParser[] filhos = new ElementoParser[numFilhos];
	
		for (int i = 0; i < numFilhos; i++) {
			filhos[i] = (ElementoParser) filhos_.elementAt(i);
		}
	
		return filhos;
	}
	
	public int getIndiceProximoElemento() {
	
		if (isVazio()) {
			return xml_.indexOf("<", openTagFim_);
		}
	
		return xml_.indexOf("<", closeTagFim_);
	}
	
	private boolean isCloseTag(int indiceInicioTag) {
	
		if (xml_.charAt(indiceInicioTag) != '<') {
			return false;
		}
	
		if (xml_.charAt(indiceInicioTag + 1) == '/') {
			return true;
		}
	
		return false;
	}
	
	
	public boolean isContainer() {
		return isContainer_;
	}
	
	
	public boolean isValorado() {
		return isValorado_;
	}
	
	
	public boolean isVazio() {
		return isVazio_;
	}
	
	
	private void parseFilhos() {
	
		int inicioProximaTag = xml_.indexOf("<", openTagFim_);
	
		while (! isCloseTag(inicioProximaTag)) {
			if (filhos_ == null) {
				filhos_ = new java.util.Vector();
			}
	
			ElementoParser ep = new ElementoParser(xml_, inicioProximaTag);
	
			filhos_.addElement(ep);
	
			inicioProximaTag = ep.getIndiceProximoElemento();
		}
	
		closeTagInicio_ = inicioProximaTag;
		closeTagFim_ = xml_.indexOf(">", closeTagInicio_);
	
	}
	
	
	private void setNome() {
	
		nomeElemento_ = xml_.substring(openTagInicio_ + 1, openTagFim_);
	}
	
	
	private void setTipo() {
	
		int lenNome = nomeElemento_.length();
	
		if (nomeElemento_.charAt(lenNome - 1) == '/') {
			isVazio_ = true;
			nomeElemento_ = nomeElemento_.substring(0, lenNome - 1);
			return;
		}
	
		if (nomeElemento_.charAt(0) == '/') {
			throw new RuntimeException("Um ElementoParser n�o pode receber uma closing tag (</nome-da-tag>).");
		}
	
	
		int inicioTextoAposOpenTag = openTagFim_ + 1;
	
		/* Pula espa�os em branco. */
		while (xml_.charAt(inicioTextoAposOpenTag) == ' ') {
			inicioTextoAposOpenTag++;
		}
	
	        /** @todo Mostrar a mudan�a. */ // FS_DEP
		if (xml_.charAt(inicioTextoAposOpenTag) == '<') {
	          if (xml_.charAt(inicioTextoAposOpenTag+1) == '/') {
			isValorado_ = true;
			setValor();
	          }
	          else{
	            isContainer_ = true;
	            parseFilhos();
	          }
		} else {
			isValorado_ = true;
			setValor();
		}
		return;
	}
	
	
	private void setValor() {
		closeTagInicio_ = xml_.indexOf("<", openTagFim_);
		closeTagFim_ = xml_.indexOf(">", closeTagInicio_);
	
		valorElemento_ = xml_.substring(openTagFim_ + 1, closeTagInicio_);
	}
	
	
	public Elemento toElemento() {
	
		if (isVazio()) {
			return new Elemento(nomeElemento_);
		}
	
		if (isValorado()) {
			return new Elemento(nomeElemento_, valorElemento_);
		}
	
		if (isContainer()) {
			Elemento elementoPai = new Elemento(nomeElemento_);
			ElementoParser[] filhos = getFilhos();
	
			if (filhos != null) {
				for (int i = 0; i < filhos.length; i++) {
					elementoPai.incluirFilho(filhos[i].toElemento());
				}
			}
	
			return elementoPai;
		}
	
		return null;
	}
	
	
	public String toString() {
	
		if (isVazio()) {
			return "<" + nomeElemento_ + "/>";
		}
	
		StringBuffer buf = new StringBuffer();
	
		buf.append("<" + nomeElemento_ + ">");
	
		if (isValorado()) {
			buf.append(valorElemento_);
		}
	
		ElementoParser[] filhos = getFilhos();
		if (isContainer() && (filhos != null)) {
			for (int i = 0; i < filhos.length; i++) {
				ElementoParser filho = filhos[i];
				buf.append(filho.toString());
			}
		}
	
		buf.append("</" + nomeElemento_ + ">");
	
		return buf.toString();
	}
}
