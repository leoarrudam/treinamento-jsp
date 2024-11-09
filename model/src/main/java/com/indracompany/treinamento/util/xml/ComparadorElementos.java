package com.indracompany.treinamento.util.xml;

//import .Elemento;
import java.util.Comparator;

/**
 * Nome:          ComparadorItensAIDF                                       <BR>
 * Objetivo(s):                                                             <BR>
 *     Comparar os elementos da estrutura.                                  <BR>
 *                                                                          <BR>
 * @Implementador Clovis de Oliveira Junior                                 <BR>
 * @author        Clovis de Oliveira Junior                                 <BR>
 * @version 1.0                                                             <BR>
 */
public class ComparadorElementos implements Comparator, CompararElementos {

    /**
     * Indica que o primeiro valor � maior que o outro.
     */
    private static final int MAIOR = 1;

    /**
     * Indica que os valores s�o iguais.
     */
    private static final int IGUAIS = 0;

    /**
     * Indica que o primeiro valor � menor que o segundo.
     */
    private static final int MENOR = -1;

    private String filhos[];

    /**
     * Construtor default
     */
    public ComparadorElementos() {

        super();

    } // ComparadorElementos()

    /**
     * <p><b>Objetivo:</b>
     *      Criar um comparador de Elementos.</p>
     *
     * <p><b>Funcionamento:</b>
     *      Cria um comparador que far� compara��es entre os filhos
     *      especificados nos elementos que forem passados como par�metro.</p>
     *
     * <p><b>Implementador:</b>
     *      Clovis de Oliveira Junior.</p>
     *
     * @param filhos Os filhos que devem ser comparados em ordem de prioridade.
     */
    public ComparadorElementos(String filhos[]) {
        this.filhos = filhos;
    }

    /**
     * <B> Objetivo: </B>                                                   <BR>
     *          Comparar os elementos para a ordena��o.                     <BR>
     *                                                                      <BR>
     * <B> Funcionamento: </B>                                              <BR>
     *          Para cada filho chama o m�todo comparar. Se forem iguais passa
     *          para o pr�ximo. Como est�o em ordem de prioridade, se forem
     *          diferentes retorna o resultado obtido.                      <br>
     *                                                                      <BR>
     * <B> Implementador(es): </B>                                          <BR>
     *          Clovis de Oliveira Junior
     *
     * @param obj1 <code>Object</code> sendo inserido na estrutura.
     * @param obj2 <code>Object</code> atual da estrutura.
     *
     * @return <code>int</code> resultado da compara��o.
     */
    public int compare(Object obj1, Object obj2) {

        Elemento e1 = (Elemento) obj1;
        Elemento e2 = (Elemento) obj2;

        for (int i = 0; i<filhos.length; i++) {

            int result = comparaElementos(e1, e2, filhos[i]);
            if (result != IGUAIS) {
                return result;
            }
        } // for

        return IGUAIS;

    } // compare

    /**
     * <B> Objetivo: </B>                                                   <BR>
     *          Comparar o valor de um filho de dois elementos.             <BR>
     *                                                                      <BR>
     * <B> Funcionamento: </B>                                              <BR>
     *          Compara um filho espec�fico para dois elementos mesmo que ele
     *          n�o esteja presente nos dois elementos.                     <br>
     *                                                                      <BR>
     * <B> Implementador(es): </B>                                          <BR>
     *          Clovis de Oliveira Junior
     *
     * @param obj1 <code>Object</code> sendo inserido na estrutura.
     * @param obj2 <code>Object</code> atual da estrutura.
     *
     * @return <code>int</code> resultado da compara��o.
     */
    private int comparaElementos(Elemento e1, Elemento e2, String filho) {
    	
        String valor1 = (e1.getNome().equals(filho)) ? e1.getValor() 
        											 : e1.getValor(filho);
        
        String valor2 = (e2.getNome().equals(filho)) ? e2.getValor() 
        											 :e2.getValor(filho);

        if ((valor1 != null) && (valor2 == null)) {
            return MAIOR;
        }
        if ((valor1 == null) && (valor2 != null)) {
            return MENOR;
        }
        if ((valor1 == null) && (valor2 == null)) {
            return IGUAIS;
        }
        return valor1.compareTo(valor2);

    } // comparaElementos

    /**
     * Verificar de dois elementos s�o EXATAMENTE iguais.
     *
     * @param elemA <code>Elemento</code> a ser comparado com elemB
     * @param elemB <code>Elemento</code> a ser comparado com elemA
     *
     * @return <code>boolean</code> caso a condi��o seja satisfeita
     */
    public boolean iguais(Elemento elemA, Elemento elemB, boolean exato){

        if (iguais(elemA, elemB) && (iguais(elemB, elemA))) {

            return true;

        } else {

            return false;

        } // if

    } // iguais()

    /**
     * Verificar de dois elementos s�o iguais
     *
     * @param elemA <code>Elemento</code> a ser comparado com elemB
     * @param elemB <code>Elemento</code> a ser comparado com elemA
     *
     * @return <code>boolean</code> caso a condi��o seja satisfeita
     */
    public boolean iguais(Elemento elemA, Elemento elemB) {

        // Compara os elementos
        if (elemA == elemB) {

            return true;

        } if (elemA == null) {

            return false;

        } if (elemB == null) {

            return false;

        } // if

        // Comparar os nomes
        String nomeA = elemA.getNome();
        String nomeB = elemB.getNome();

        if (!nomeA.equals(nomeB)) {

            return false;

        } // if

        // Comparar os valores
        String valorA = elemA.getValor();
        String valorB = elemB.getValor();
        Elemento[] filhosA = elemA.getFilhos();
        Elemento[] filhosB = elemB.getFilhos();

        if ((valorA == null) && (valorB == null)) {

            return iguais(filhosA, filhosB);

        } else if ((valorA != null) && (valorB == null)) {

            return false;

        } else if ((valorA == null) && (valorB != null)) {

            return false;

        } else if (valorA.equals(valorB)) {

            return true;

        } else {

            return false;

        } // if

    } // iguais()

    /**
     * Verificar se dois arrays de Elementos s�o iguais.
     *
     * @param filhosA <code>Elemento[]</code> que ser� comparado
     * @param filhosB <code>Elemento[]</code> que ser� comparado
     *
     * @return <code>boolean</code> caso a condi��o seja satisfeita
     */
    public boolean iguais(Elemento[] filhosA, Elemento[] filhosB){

        if ((filhosA.length == 0) && (filhosB.length == 0)) {

            return true;

        } // if

        for (int i = 0; i < filhosA.length; i++) {

            if (!possuiFilho(filhosA[i], filhosB)) {

                return false;

            } // if

        } // for

        return true;

    } // iguais()

    /**
     * Verificar se o elemento filho � igual a algum dos Elementos do array
     * filhos.
     *
     * @param filho <code>Elemento</code> quem ser� procurado
     * @param filhos <code>Elemento[]</code> onde ser� procurado
     *
     * @return <code>boolean</code> verdadeiro caso a condi��o seja satisfeita
     */
    public boolean possuiFilho(Elemento filho, Elemento[] filhos){

        for (int i = 0; i < filhos.length; i++) {

            if (iguais(filho, filhos[i])) {

                return true;

            } // if

        } // for

        return false;

    } // possuiFilho()

} // ComparadorElementos
