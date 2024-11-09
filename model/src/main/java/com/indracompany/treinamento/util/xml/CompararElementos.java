package com.indracompany.treinamento.util.xml;

public interface CompararElementos {

    /**
     * Verificar se dois arrays de Elementos s�o iguais.
     *
     * @param filhosA <code>Elemento[]</code> que ser� comparado
     * @param filhosB <code>Elemento[]</code> que ser� comparado
     *
     * @return <code>boolean</code> caso a condi��o seja satisfeita
     */
    boolean iguais(Elemento[] filhosA, Elemento[] filhosB);

    /**
     * Verificar de dois elementos s�o EXATAMENTE iguais.
     *
     * @param elemA <code>Elemento</code> a ser comparado com elemB
     * @param elemB <code>Elemento</code> a ser comparado com elemA
     *
     * @return <code>boolean</code> caso a condi��o seja satisfeita
     */
    boolean iguais(Elemento elemA, Elemento elemB, boolean exato);

    /**
     * Verificar de dois elementos s�o iguais
     *
     * @param elemA <code>Elemento</code> a ser comparado com elemB
     * @param elemB <code>Elemento</code> a ser comparado com elemA
     *
     * @return <code>boolean</code> caso a condi��o seja satisfeita
     */
    boolean iguais(Elemento elemA, Elemento elemB);

    /**
     * Verificar se o elemento filho � igual a algum dos Elementos do array
     * filhos.
     *
     * @param filho <code>Elemento</code> quem ser� procurado
     * @param filhos <code>Elemento[]</code> onde ser� procurado
     *
     * @return <code>boolean</code> verdadeiro caso a condi��o seja satisfeita
     */
    boolean possuiFilho(Elemento filho, Elemento[] filhos);

} // CompararElementos
