package com.indracompany.treinamento.util.xml;

import java.util.Collection;
import java.util.Map;

/**
 * Nome:          ArvoreAux                                                 <br>
 * Projeto:       ATF (Sefin/PB)                                            <br>
 * Objetivo(s):                                                             <br>
 *     Determina obriga��es para classes que desejarem fazer                <br>
 *     uso da classe "Arvore".                                              <br>
 *                                                                          <br>
 * @Implementador(es) Andr� Figueiredo                                      <br>
 * @Especificador     Andr� Luna                                            <br>
 * @author            Andr� Figueiredo                                      <br>
 * @version 1.0                                                             <br>
 */
public interface ArvoreAux {

    /**
     * Este flag indica que se deseja algo relacionado com a raiz. Este flag
     * pode possuir v�rias utilidades. A mais comum delas pode ser vista no
     * m�todo obterRegistros, onde ele quer dizer que quer o registro
     * raiz.
     */
    public static final int FLAG_RAIZ = 0;

    /**
     * Este flag indica que se deseja algo relacionado com as folhas. Este flag
     * pode possuir v�rias utilidades. A mais comum delas pode ser vista no
     * m�todo obterRegistros, onde ele quer dizer que quer os registros
     * folhas.
     */
    public static final int FLAG_FOLHAS = 1;

    /**
     * Objetivo:                                                            <br>
     *      Obter todos os registros de "conjunto" que atendem a uma        <br>
     *      determinada condi��o. Inicialmente, este m�todo foi elaborado   <br>
     *      para obter: ora o registro identificado por "item"; ora os      <br>
     *      registros que est�o associados a "item" (subordinantes ou       <br>
     *      subordinados, dependendo da situa��o)                           <br>
     *                                                                      <br>
     *      OBSERVA��ES:                                                    <br>
     *      - O comportamento deste m�todo pode ser configurado atrav�s do  <br>
     *      uso do par�metro "flag". Em "Arvore", por exemplo, � sugerido   <br>
     *      comportamento para os valores "FLAG_RAIZ" (obt�m o item         <br>
     *      identificado por "item"); e "FLAG_FOLHAS" (obt�m os itens       <br>
     *      associados a "pItem", isto �, seus filhos)                      <br>
     *      - Aten��o: o caso em que "item" possa assumir o valor Nulo      <br>
     *      merece cuidado especial na implementa��o!                       <br>
     *      - Para observar um exemplo de utiliza��o desta estrutura,       <br>
     *      verifique "ElementoOrgHierarquiaAux.obterRegistros"             <br>
     *                                                                      <br>
     * @param String item Valor que os registros estar�o relacionados.      <br>
     * @param Collection conjunto Conjunto que cont�m os registros que ser�o<br>
     * retornados.                                                          <br>
     * @param Integer flag Flag que define se os dados ser�o                <br>
     * raiz (FLAG_RAIZ) ou folhas (FLAG_FOLHAS).                            <br>
     *                                                                      <br>
     * @return Collection Registros de conjunto que possuem liga��o com     <br>
     * item.                                                                <br>
     */
    public Collection obterRegistros(String item, Collection conjunto,
                                     Integer flag);

    /**
     * Objetivo:                                                            <br>
     *      Montar elemento "raiz" que ser� utilizado para compor elemento  <br>
     *      "arvore" representando �rvore hier�rquica de dados              <br>
     *      OBSERVA��ES:                                                    <br>
     *      - O comportamento deste m�todo pode ser configurado atrav�s do  <br>
     *      uso do par�metro "flag". Inicialmente, n�o foi pensado em       <br>
     *      situa��o em que isto possa ser �til, por�m, fica aqui dispon�vel<br>
     *      para extens�es futuras                                          <br>
     *                                                                      <br>
     * @param Map conjunto Conjunto que cont�m o registro da raiz.          <br>
     * @param Integer flag Flag que define se os dados ser�o                <br>
     * raiz (FLAG_RAIZ) ou folhas (FLAG_FOLHAS).                            <br>
     *                                                                      <br>
     * @return Elemento Elemento com a estrutura de uma raiz.               <br>
     */
    public Elemento montarRaiz(Map conjunto, Integer flag);
    
    /**
     * Objetivo:                                                            <br>
     *      Montar elemento "raiz" que ser� utilizado para compor elemento  <br>
     *      "arvore" representando �rvore hier�rquica de dados              <br>
     *      OBSERVA��ES:                                                    <br>
     *      - O comportamento deste m�todo pode ser configurado atrav�s do  <br>
     *      uso do par�metro "flag". Inicialmente, n�o foi pensado em       <br>
     *      situa��o em que isto possa ser �til, por�m, fica aqui dispon�vel<br>
     *      para extens�es futuras                                          <br>
     *                                                                      <br>
     * @param Map conjunto Conjunto que cont�m o registro da raiz.          <br>
     * @param Integer flag Flag que define se os dados ser�o                <br>
     * raiz (FLAG_RAIZ) ou folhas (FLAG_FOLHAS).                            <br>
     *                                                                      <br>
     * @return Elemento Elemento com a estrutura de uma raiz.               <br>
     */
    public Elemento montarRaizBasica(Map conjunto, Integer flag);

    /**
     * Objetivo:                                                            <br>
     *      Obter identificador de raiz de �rvore.                          <br>
     *                                                                      <br>
     *      OBSERVA��ES:                                                    <br>
     *      - O comportamento deste m�todo pode ser configurado atrav�s do  <br>
     *      uso do par�metro "flag". Em "Arvore", por exemplo, � sugerido   <br>
     *      comportamento para os valores "FLAG_RAIZ" (obt�m o item         <br>
     *      identificado por "item"); e "FLAG_FOLHA" (obt�m item associado a<br>
     *      "item", isto �, seu filho)                                      <br>
     *      - Para observar um exemplo de utiliza��o desta estrutura,       <br>
     *      verifique "ElementoOrgHierarquiaAux.obterIdRaiz".               <br>
     *                                                                      <br>
     * @param Map conjunto Conjunto que cont�m o registro da raiz.          <br>
     * @param Integer flag Flag que define se os dados ser�o                <br>
     * raiz (FLAG_RAIZ) ou folhas (FLAG_FOLHAS).                            <br>
     *                                                                      <br>
     * @return String Valor do identificador da raiz.                       <br>
     */
    public String obterIdRaiz(Map conjunto, Integer flag);

    /**
     * Objetivo:                                                            <br>
     *      Obter identificador de raiz de �rvore.                          <br>
     *                                                                      <br>
     *      OBSERVA��ES:                                                    <br>
     *      - A inten��o �: dada uma raiz de �rvore, obter o identificador  <br>
     *      utilizado na constru��o (observe a descri��o do m�todo          <br>
     *      "montarRaiz") da �rvore.                                        <br>
     *      - O comportamento deste m�todo pode ser configurado atrav�s do  <br>
     *      uso do par�metro "flag". Em "Arvore", por exemplo, � sugerido   <br>
     *      comportamento para os valores "FLAG_RAIZ" (obt�m o item         <br>
     *      identificado por "item"); e "FLAG_FOLHA" (obt�m item associado a<br>
     *      "item", isto �, seu filho)                                      <br>
     *      - Cuidado para o caso que a raiz se4ja nula.                    <br>
     *      - Para observar um exemplo de utiliza��o desta estrutura,       <br>
     *      verifique "ElementoOrgHierarquiaAux.obterIdRaiz".               <br>
     *                                                                      <br>
     * @param Elemento raiz Elemento raiz que cont�m os dados.              <br>
     * @param Integer flag Flag que define se os dados ser�o                <br>
     * raiz (FLAG_RAIZ) ou folhas (FLAG_FOLHAS).                            <br>
     *                                                                      <br>
     * @return String Valor do identificador da raiz.                       <br>
     */
    public String obterIdRaiz(Elemento raiz, Integer flag);

}// ArvoreAux
