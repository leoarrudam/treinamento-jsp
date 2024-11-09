package com.indracompany.treinamento.util.xml;

import java.util.Collection;
import java.util.Map;

/**
 * Nome:          Arvore                                                    <br>
 * Projeto:       ATF (Sefin/PB)                                            <br>
 * Objetivo(s):                                                             <br>
 *     Fornece mecanismos de manipula��o de �rvore                          <br>
 *     (objeto da classe elemento) hier�rquica de dados                     <br>
 *     Muito embora a estrutura reflita o pattern                           <br>
 *     "Composite", esta classe apenas auxilia o trabalho                   <br>
 *     de implementa��o, visto que o Elemento j� realiza                    <br>
 *     isto (implementa o Composite).                                       <br>
 *                                                                          <br>
 * @Implementador(es) Andr� Figueiredo                                      <br>
 * @Especificador     Andr� Luna                                            <br>
 * @author            Andr� Figueiredo                                      <br>
 * @version 1.0                                                             <br>
 */
public class Arvore {

    /**
     * Valor que uma raiz vazia ir� assumir. Esse valor � utilizado em ra�zes
     * cuja �rvore n�o possui uma raiz central. Por exemplo, em uma �rvore que
     * cont�m todos os pa�ses do mundo, sua raiz ter� esse valor, pois n�o
     * existe nenhum dado que possa ser usado na ra�z que fa�a com que todos os
     * pa�ses sejam folhas dessa raiz.
     */
    public static final String VALOR_RAIZ_VAZIA = "---";

    /**
     * Nome do elemento que representa uma �rvore.
     */
    public static final String ELEM_ARVORE = "arvore";

    /**
     * Nome do elemento que representa as sub�rvores de uma �rvore.
     */
    public static final String ELEM_SUBARVORE = "subArvores";

    /**
     * Nome do elemento que representa a raiz de uma �rvore.
     */
    public static final String ELEM_RAIZ = "raiz";

    /**
     * Nome do elemento que contens itens preenchidos.
     */
    public static final String ELEM_PREENCHIDOS = "preenchidos";

    /**
     * Nome do elemento que contens um iten preenchido.
     */
    public static final String ELEM_NO_PREENCHIDO = "noPreenchido";

    /**
     * Funcionalidade:                                                      <br>
     *      1. Se a Raiz n�o for nula.                                      <br>
     *      1.1. Inclui a raiz na lista.                                    <br>
     *                                                                      <br>
     *      2. Se cont�m alguma sub�rvore.                                  <br>
     *      2.1. Para cada �rvore de sub�rvore chamar arvoreToLista passando<br>
     *          - arvore da vez                                             <br>
     *          - lista (parametro de entrada)                              <br>
     *                                                                      <br>
     * @param Elemento arvore �rvore que ser� transformada em lista.        <br>
     * @param Elemento lista Lista que conter� os dados da �rvore.          <br>
     */
    public static void arvoreToLista(Elemento arvore, Elemento lista){

        if (arvore != null && !ehRaizNula(arvore.getFilho(ELEM_RAIZ))) {
            lista.incluirFilho(arvore.getFilho(ELEM_RAIZ));
        }

        if (arvore != null && arvore.contemFilho(ELEM_SUBARVORE)) {

            Elemento subArvore = arvore.getFilho(ELEM_SUBARVORE);
            Elemento[] subArvores = subArvore.getFilhos(ELEM_ARVORE);

            for (int i = 0; i < subArvores.length; i++){
                arvoreToLista(subArvores[i], lista);
            }
        }// fim if (arvore.contemFilho(ELEM_SUBARVORE)){
    }// arvoreToLista()

    /**
     * Funcionalidade:                                                      <br>
     *      1. - Criar elemento "arvore"                                    <br>
     *      2. - Se "pIdRaiz" n�o estiver preenchido                        <br>
     *      2.1 - Criar elemento raiz nula                                  <br>
     *                                                                      <br>
     *      3. - Sen�o                                                      <br>
     *      3.1 - Acionar arvoreAux.obterRegistros passando                 <br>
     *      (item, conjunto, flag), armazenando o resultado em              <br>
     *      vlRegistro                                                      <br>
     *      Obs.:                                                           <br>
     *      - "item" corresponde a "idRaiz"                                 <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "flag" deve conter valor ArvoreAux.FLAG_RAIZ                  <br>
     *      3.2 - Acionar pArvoreAux.montarRaiz passando (conjunto, flag)   <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "vlRegistro"                         <br>
     *      - "flag" deve conter valor ArvoreAux.FLAG_RAIZ                  <br>
     *      3.3 - Fazer vlIdRaiz = idRaiz                                   <br>
     *                                                                      <br>
     *      4. - Adicionar vlRaiz a vlArvore                                <br>
     *                                                                      <br>
     *      5. - Acionar Arvore.obterSubarvores, passando                   <br>
     *      (conjunto, idRaiz, arvoreAux), armazenando o                    <br>
     *      resultado em vlSubarvores                                       <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "idRaiz" corresponde a "vlIdRaiz"                             <br>
     *      - "arvoreAux" corresponde a "arvoreAux"                         <br>
     *                                                                      <br>
     *      6. - Se vlSubarvores contiver algum item                        <br>
     *      6.1 - Adicionar vlSubarvores a vlArvore                         <br>
     *                                                                      <br>
     *      7. - Retornar vlArvore.                                         <br>
     *                                                                      <br>
     * @param Collection conjunto Objeto que implementa a interface         <br>
     * Collection e que cont�m todos objetos que implementam a              <br>
     * interface Map.                                                       <br>
     * @param String idRaiz Valor da raiz central da �rvore.                <br>
     * @param ArvoreAux arvoreAux Objeto encarregado de tratar a parte da   <br>
     * �rvore que � inerente � l�gica de neg�cio.                           <br>
     *                                                                      <br>
     * @return Elemento (�rvore hier�rquica):                               <br>
     * <!Elemento arvore (raiz, subarvores?)>                               <br>
     * <!Elemento subarvores (arvore*)>                                     <br>
     * <!Elemento raiz (VALOR_RAIZ_VAZIA | DEFINIDO_PELO_USUARIO)>          <br>
     * Obs.: VALOR_RAIZ_VAZIA � usado para uma raiz nula.                   <br>
     *       DEFINIDO_PELO_USUARIO � dado pelo objeto ArvoreAux.            <br>
     */
    public static Elemento conjuntoToArvore(Collection conjunto, String idRaiz,
                                            ArvoreAux arvoreAux) {

        Elemento elemArvore = new Elemento(ELEM_ARVORE);
        Elemento elemRaiz = new Elemento(ELEM_RAIZ);
        Collection registros = null;
        Elemento elemSubarvores = null;

        // Se idRaiz n�o vier, a raiz da �rvore ser� nula.
        if (idRaiz == null) {
            elemRaiz.setValor(VALOR_RAIZ_VAZIA);
        } else {

            // Obtem a raiz da �rvore.
            registros = arvoreAux.obterRegistros(idRaiz, conjunto,
                    new Integer(ArvoreAux.FLAG_RAIZ));

            if ((registros == null) || (registros.isEmpty())) {
                return elemArvore;
            }

            // Obtem o elemento que representa a raiz da �rvore.
            elemRaiz = arvoreAux.montarRaiz((Map) registros.toArray()[0],
                    new Integer(ArvoreAux.FLAG_RAIZ));
        }

        // Inclui a raiz na arvore.
        elemArvore.incluirFilho(elemRaiz);

        // Obtem todas as sub�rvores.
        elemSubarvores = obterSubarvores(conjunto, idRaiz, arvoreAux);

        // Se existe o elemento sub�rvores, ele ser� adicionado.
        if (elemSubarvores != null) {
            elemArvore.incluirFilho(elemSubarvores);
        }

        return elemArvore;
    }// conjuntoToArvore()
    
    
    /**
     * Funcionalidade:                                                      <br>
     *      1. - Criar elemento "arvore"                                    <br>
     *      2. - Se "pIdRaiz" n�o estiver preenchido                        <br>
     *      2.1 - Criar elemento raiz nula                                  <br>
     *                                                                      <br>
     *      3. - Sen�o                                                      <br>
     *      3.1 - Acionar arvoreAux.obterRegistros passando                 <br>
     *      (item, conjunto, flag), armazenando o resultado em              <br>
     *      vlRegistro                                                      <br>
     *      Obs.:                                                           <br>
     *      - "item" corresponde a "idRaiz"                                 <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "flag" deve conter valor ArvoreAux.FLAG_RAIZ                  <br>
     *      3.2 - Acionar pArvoreAux.montarRaiz passando (conjunto, flag)   <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "vlRegistro"                         <br>
     *      - "flag" deve conter valor ArvoreAux.FLAG_RAIZ                  <br>
     *      3.3 - Fazer vlIdRaiz = idRaiz                                   <br>
     *                                                                      <br>
     *      4. - Adicionar vlRaiz a vlArvore                                <br>
     *                                                                      <br>
     *      5. - Acionar Arvore.obterSubarvores, passando                   <br>
     *      (conjunto, idRaiz, arvoreAux), armazenando o                    <br>
     *      resultado em vlSubarvores                                       <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "idRaiz" corresponde a "vlIdRaiz"                             <br>
     *      - "arvoreAux" corresponde a "arvoreAux"                         <br>
     *                                                                      <br>
     *      6. - Se vlSubarvores contiver algum item                        <br>
     *      6.1 - Adicionar vlSubarvores a vlArvore                         <br>
     *                                                                      <br>
     *      7. - Retornar vlArvore.                                         <br>
     *                                                                      <br>
     * @param Collection conjunto Objeto que implementa a interface         <br>
     * Collection e que cont�m todos objetos que implementam a              <br>
     * interface Map.                                                       <br>
     * @param String idRaiz Valor da raiz central da �rvore.                <br>
     * @param ArvoreAux arvoreAux Objeto encarregado de tratar a parte da   <br>
     * �rvore que � inerente � l�gica de neg�cio.                           <br>
     *                                                                      <br>
     * @return Elemento (�rvore hier�rquica):                               <br>
     * <!Elemento arvore (raiz, subarvores?)>                               <br>
     * <!Elemento subarvores (arvore*)>                                     <br>
     * <!Elemento raiz (VALOR_RAIZ_VAZIA | DEFINIDO_PELO_USUARIO)>          <br>
     * Obs.: VALOR_RAIZ_VAZIA � usado para uma raiz nula.                   <br>
     *       DEFINIDO_PELO_USUARIO � dado pelo objeto ArvoreAux.            <br>
     */
    public static Elemento conjuntoToArvoreBasica(Collection conjunto, String idRaiz,
                                            ArvoreAux arvoreAux) {

        Elemento elemArvore = new Elemento(ELEM_ARVORE);
        Elemento elemRaiz = new Elemento(ELEM_RAIZ);
        Collection registros = null;
        Elemento elemSubarvores = null;

        // Se idRaiz n�o vier, a raiz da �rvore ser� nula.
        if (idRaiz == null) {
            elemRaiz.setValor(VALOR_RAIZ_VAZIA);
        } else {

            // Obtem a raiz da �rvore.
            registros = arvoreAux.obterRegistros(idRaiz, conjunto,
                    new Integer(ArvoreAux.FLAG_RAIZ));

            if ((registros == null) || (registros.isEmpty())) {
                return elemArvore;
            }

            // Obtem o elemento que representa a raiz da �rvore.
            elemRaiz = arvoreAux.montarRaizBasica((Map) registros.toArray()[0],
                    new Integer(ArvoreAux.FLAG_RAIZ));
        }

        // Inclui a raiz na arvore.
        elemArvore.incluirFilho(elemRaiz);

        // Obtem todas as sub�rvores.
        elemSubarvores = obterSubarvoresBasicas(conjunto, idRaiz, arvoreAux);

        // Se existe o elemento sub�rvores, ele ser� adicionado.
        if (elemSubarvores != null) {
            elemArvore.incluirFilho(elemSubarvores);
        }

        return elemArvore;
    }// conjuntoToArvore()

    /**
     * Funcionalidade:                                                      <br>
     *      1. - Criar elemento "subarvores", armazenando-o em vlSubarvores <br>
     *                                                                      <br>
     *      2. - Acionar arvoreAux.obterRegistros passando                  <br>
     *      (item, conjunto, flag), armazenando o resultado em              <br>
     *      vlRegistros                                                     <br>
     *      Obs.:                                                           <br>
     *      - "item" corresponde a "idRaiz"                                 <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "flag" deve conter valor "ArvoreAux.FLAG_FOLHAS"              <br>
     *                                                                      <br>
     *      3. - Se "vlRegistros" n�o for vazio                             <br>
     *      3.1 - Para cada registro (vlRegistro) de vlRegistros:           <br>
     *      (cria outro conjunto contendo apenas o registro selecionado!)   <br>
     *      3.1.1 - Criar elemento "arvore", armazenando-o em vlArvore      <br>
     *      3.1.2 - Adicionar vlArvore a vlSubarvores                       <br>
     *      3.1.3 - Acionar arvoreAux.montarRaiz passando (conjunto, flag), <br>
     *      armazenando o resultado em vlRaiz                               <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "vlRegistro"                         <br>
     *      - "flag" deve conter valor "ArvoreAux.FLAG_FOLHAS"              <br>
     *      3.1.4 - Adicionar vlRaiz a vlArvore                             <br>
     *      3.1.5 - Acionar arvoreAux.obterIdRaiz passando (conjunto, flag),<br>
     *      armazenando o resultado em vlIdRaiz                             <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "pConjunto"                          <br>
     *      - "flag" deve conter valor "ArvoreAux.FLAG_FOLHAS"              <br>
     *      3.1.6 - Acionar Arvore.obterSubarvores, passando                <br>
     *      (conjunto, idRaiz, arvoreAux), armazenando o                    <br>
     *      resultado em vlSubarvoresInternas                               <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "idRaiz" corresponde a "vlIdRaiz"                             <br>
     *      - "arvoreAux" corresponde a "arvoreAux"                         <br>
     *      3.1.7 - Se vlSubarvoresInternas contiver algum item             <br>
     *      3.1.7.1 - Adicionar vlSubarvoresInternas a vlArvore             <br>
     *                                                                      <br>
     *      4. - Retorna vlSubarvores.                                      <br>
     *                                                                      <br>
     * @param Collection conjunto Objeto que implementa a interface         <br>
     * Collection e que cont�m todos objetos que implementam a              <br>
     * interface Map.                                                       <br>
     * @param String idRaiz Valor da raiz central da �rvore.                <br>
     * @param ArvoreAux arvoreAux Objeto encarregado de tratar a parte da   <br>
     * �rvore que � inerente � l�gica de neg�cio.                           <br>
     *                                                                      <br>
     * @return Elemento (sub�rvores de �rvore hier�rquica):                 <br>
     * <!Elemento subarvores (arvore*)>                                     <br>
     * <!Elemento arvore (raiz, subarvores?)>                               <br>
     * <!Elemento raiz (DEFINIDO_PELO_USUARIO)>.                            <br>
     */
    private static Elemento obterSubarvores(Collection conjunto, String idRaiz,
                                            ArvoreAux arvoreAux){

        Elemento elemSubarvores = new Elemento(ELEM_SUBARVORE);
        Collection registros = null;
        Map[] registrosAsArray = null;

        registros = arvoreAux.obterRegistros(idRaiz, conjunto,
                new Integer(ArvoreAux.FLAG_FOLHAS));

        // Se n�o possui Registros, retorna-os vazio.
        if ((registros == null) || (registros.isEmpty())) {
            return elemSubarvores;
        }

        // Coverte array de Objetos em array de Maps.
        registrosAsArray = new Map[registros.toArray().length];
        System.arraycopy(registros.toArray(), 0, registrosAsArray, 0,
                         registros.toArray().length);

        for (int i = 0; i < registrosAsArray.length; i++) {
            Elemento elemArvore = new Elemento(ELEM_ARVORE);
            elemSubarvores.incluirFilho(elemArvore);

            Elemento elemRaiz = arvoreAux.montarRaiz(registrosAsArray[i],
                    new Integer(ArvoreAux.FLAG_FOLHAS));
            elemArvore.incluirFilho(elemRaiz);

            String idRaizAtual = arvoreAux.obterIdRaiz(registrosAsArray[i],
                    new Integer(ArvoreAux.FLAG_FOLHAS));

            Elemento elemSubarvoresInternas =
                    obterSubarvores(conjunto, idRaizAtual, arvoreAux);

            if (elemSubarvoresInternas != null) {
                elemArvore.incluirFilho(elemSubarvoresInternas);
            }
        }// fim for (int i = 0; i < elemRegistros.length; i++) {

        return elemSubarvores;
    }// obterSubarvores()
    
    /**
     * Funcionalidade:                                                      <br>
     *      1. - Criar elemento "subarvores", armazenando-o em vlSubarvores <br>
     *                                                                      <br>
     *      2. - Acionar arvoreAux.obterRegistros passando                  <br>
     *      (item, conjunto, flag), armazenando o resultado em              <br>
     *      vlRegistros                                                     <br>
     *      Obs.:                                                           <br>
     *      - "item" corresponde a "idRaiz"                                 <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "flag" deve conter valor "ArvoreAux.FLAG_FOLHAS"              <br>
     *                                                                      <br>
     *      3. - Se "vlRegistros" n�o for vazio                             <br>
     *      3.1 - Para cada registro (vlRegistro) de vlRegistros:           <br>
     *      (cria outro conjunto contendo apenas o registro selecionado!)   <br>
     *      3.1.1 - Criar elemento "arvore", armazenando-o em vlArvore      <br>
     *      3.1.2 - Adicionar vlArvore a vlSubarvores                       <br>
     *      3.1.3 - Acionar arvoreAux.montarRaiz passando (conjunto, flag), <br>
     *      armazenando o resultado em vlRaiz                               <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "vlRegistro"                         <br>
     *      - "flag" deve conter valor "ArvoreAux.FLAG_FOLHAS"              <br>
     *      3.1.4 - Adicionar vlRaiz a vlArvore                             <br>
     *      3.1.5 - Acionar arvoreAux.obterIdRaiz passando (conjunto, flag),<br>
     *      armazenando o resultado em vlIdRaiz                             <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "pConjunto"                          <br>
     *      - "flag" deve conter valor "ArvoreAux.FLAG_FOLHAS"              <br>
     *      3.1.6 - Acionar Arvore.obterSubarvores, passando                <br>
     *      (conjunto, idRaiz, arvoreAux), armazenando o                    <br>
     *      resultado em vlSubarvoresInternas                               <br>
     *      Obs.:                                                           <br>
     *      - "conjunto" corresponde a "conjunto"                           <br>
     *      - "idRaiz" corresponde a "vlIdRaiz"                             <br>
     *      - "arvoreAux" corresponde a "arvoreAux"                         <br>
     *      3.1.7 - Se vlSubarvoresInternas contiver algum item             <br>
     *      3.1.7.1 - Adicionar vlSubarvoresInternas a vlArvore             <br>
     *                                                                      <br>
     *      4. - Retorna vlSubarvores.                                      <br>
     *                                                                      <br>
     * @param Collection conjunto Objeto que implementa a interface         <br>
     * Collection e que cont�m todos objetos que implementam a              <br>
     * interface Map.                                                       <br>
     * @param String idRaiz Valor da raiz central da �rvore.                <br>
     * @param ArvoreAux arvoreAux Objeto encarregado de tratar a parte da   <br>
     * �rvore que � inerente � l�gica de neg�cio.                           <br>
     *                                                                      <br>
     * @return Elemento (sub�rvores de �rvore hier�rquica):                 <br>
     * <!Elemento subarvores (arvore*)>                                     <br>
     * <!Elemento arvore (raiz, subarvores?)>                               <br>
     * <!Elemento raiz (DEFINIDO_PELO_USUARIO)>.                            <br>
     */
    private static Elemento obterSubarvoresBasicas(Collection conjunto, String idRaiz,
                                            ArvoreAux arvoreAux){

        Elemento elemSubarvores = new Elemento(ELEM_SUBARVORE);
        Collection registros = null;
        Map[] registrosAsArray = null;

        registros = arvoreAux.obterRegistros(idRaiz, conjunto,
                new Integer(ArvoreAux.FLAG_FOLHAS));

        // Se n�o possui Registros, retorna-os vazio.
        if ((registros == null) || (registros.isEmpty())) {
            return elemSubarvores;
        }

        // Coverte array de Objetos em array de Maps.
        registrosAsArray = new Map[registros.toArray().length];
        System.arraycopy(registros.toArray(), 0, registrosAsArray, 0,
                         registros.toArray().length);

        for (int i = 0; i < registrosAsArray.length; i++) {
            Elemento elemArvore = new Elemento(ELEM_ARVORE);
            elemSubarvores.incluirFilho(elemArvore);

            Elemento elemRaiz = arvoreAux.montarRaizBasica(registrosAsArray[i],
                    new Integer(ArvoreAux.FLAG_FOLHAS));
            elemArvore.incluirFilho(elemRaiz);

            String idRaizAtual = arvoreAux.obterIdRaiz(registrosAsArray[i],
                    new Integer(ArvoreAux.FLAG_FOLHAS));

            Elemento elemSubarvoresInternas =
                    obterSubarvoresBasicas(conjunto, idRaizAtual, arvoreAux);

            if (elemSubarvoresInternas != null) {
                elemArvore.incluirFilho(elemSubarvoresInternas);
            }
        }// fim for (int i = 0; i < elemRegistros.length; i++) {

        return elemSubarvores;
    }// obterSubarvores()

    /**
     * Funcionalidade:                                                      <br>
     *      1. - Obter item "raiz" (de pArvore), armazenando-o em vlRaiz    <br>
     *                                                                      <br>
     *      2. - Acionar pArvoreAux.obterIdRaiz passando (raiz, flag),      <br>
     *      armazenando o resultado em vlIdRaiz                             <br>
     *      Obs.:                                                           <br>
     *      - "raiz" corresponde a vlRaiz                                   <br>
     *      - "flag" deve conter valor "RAIZ"                               <br>
     *                                                                      <br>
     *      3. - Se vlIdRaiz for igual a pIdRaiz                            <br>
     *      3.1 - Retornar pArvore                                          <br>
     *                                                                      <br>
     *      4. - Sen�o                                                      <br>
     *      4.1 - Obter o item "subarvores" de "pArvore", armazenando-o em  <br>
     *      vlSubarvores                                                    <br>
     *      4.2 - Fazer vlArvoreResultado = Nulo                            <br>
     *      4.3 - Enquanto [ houver item "arvore" (armazenado em            <br>
     *      "vlSubarvore") em vlSubarvores ] E [vlArvoreResultado for Nulo] <br>
     *      4.3.1 - Acionar Arvore.obterArvore passando                     <br>
     *      (arvore, idRaiz, arvoreAux), armazenando o resultado            <br>
     *      em vlArvoreResultado                                            <br>
     *      Obs.:                                                           <br>
     *      - arvore corresponde a vlSubarvore                              <br>
     *      - idRaiz corresponde a pIdRaiz                                  <br>
     *      - arvoreAux corresponde a pArvoreAux                            <br>
     *                                                                      <br>
     *      4.4 - Retornar vlArvoreResultado.                               <br>
     *                                                                      <br>
     * @param Elemento arvore �rvore que cont�m a sub�rvore desejada.       <br>
     * @param String idRaiz Valor da raiz da �rvore desejada.               <br>
     * @param ArvoreAux arvoreAux Objeto encarregado de tratar a parte da   <br>
     * �rvore que � inerente � l�gica de neg�cio.                           <br>
     *                                                                      <br>
     * @return Elemento (�rvore hier�rquica):                               <br>
     * <!Elemento arvore (raiz, subarvores?)>                               <br>
     * <!Elemento subarvores (arvore*)>                                     <br>
     * <!Elemento raiz (VALOR_RAIZ_VAZIA | DEFINIDO_PELO_USUARIO)>          <br>
     * Obs.: VALOR_RAIZ_VAZIA � usado para uma raiz nula.                   <br>
     *       DEFINIDO_PELO_USUARIO � dado pelo objeto ArvoreAux.            <br>
     */
    public static Elemento obterArvore(Elemento arvore, String idRaiz,
            ArvoreAux arvoreAux) {
        Elemento elemRaiz = arvore.getFilho(ELEM_RAIZ);
        Elemento[] elemSubarvores = new Elemento[0];

        String idRaizArvore = arvoreAux.obterIdRaiz(elemRaiz,
                new Integer(ArvoreAux.FLAG_RAIZ));
        if (idRaiz == null) {
            return (ehRaizNula(elemRaiz)) ? arvore : null;
        }

        if ((idRaizArvore != null) && (idRaiz.equals(idRaizArvore))) {
            return arvore;
        }

        if (elemRaiz.getFilho(ELEM_SUBARVORE) != null) {
            elemSubarvores =
                    elemRaiz.getFilho(ELEM_SUBARVORE).getFilhos(ELEM_ARVORE);
        }

        for (int i = 0; i < elemSubarvores.length; i++) {
            Elemento elemArvoreResultado =
                    Arvore.obterArvore(elemSubarvores[i], idRaiz, arvoreAux);
            if (elemArvoreResultado != null) {
                return elemArvoreResultado;
            }
        }// fim for (int i = 0; i < elemSubarvores.length; i++) {

        return null;
    }// obterArvore()

    /**
     * OBJETIVO:                                                            <br>
     * Obter "ra�zes" das �rvores que est�o acima da que possui raiz        <br>
     * identificada por pIdRaiz.                                            <br>
     *                                                                      <br>
     * Funcionalidade:                                                      <br>
     *  1. - Obter item "raiz" (de pArvore), armazenando-o em vlRaiz        <br>
     *                                                                      <br>
     *  2. - Acionar pArvoreAux.obterIdRaiz passando (raiz, flag),          <br>
     *  armazenando o resultado em vlIdRaiz                                 <br>
     *                                                                      <br>
     *  Obs.:                                                               <br>
     *  - "raiz" corresponde a vlRaiz                                       <br>
     *  - "flag" deve conter valor "RAIZ"                                   <br>
     *                                                                      <br>
     *  3. - Se vlIdRaiz for igual a pIdRaiz                                <br>
     *  3.1 - Adicionar vlRaiz a pLista                                     <br>
     *                                                                      <br>
     * 4. - Sen�o                                                           <br>
     * 4.1 - Obter o item "subarvores" de "pArvore", armazenando-o em       <br>
     * vlSubarvores                                                         <br>
     * 4.2 - Fazer vlParar = Falso                                          <br>
     * 4.3 - Enquanto [ houver item "arvore" (armazenado em "vlSubarvore")  <br>
     * em vlSubarvores ] E [ vlParar for igual a Falso ]                    <br>
     * 4.3.1 - Acionar Arvore.obterSuperiores passando                      <br>
     * (arvore, idRaiz, arvoreAux, lista)                                   <br>
     *                                                                      <br>
     * Obs.:                                                                <br>
     * - "arvore" corresponde a vlSubarvore                                 <br>
     * - "idRaiz" corresponde a pIdRaiz                                     <br>
     * - "arvoreAux" corresponde a pArvoreAux                               <br>
     * - "lista" corresponde a pLista                                       <br>
     *                                                                      <br>
     * 4.3.2 - Se pLista contiver algum item "raiz"                         <br>
     * (isto quer dizer que a raiz desejada foi encontrada)                 <br>
     *                                                                      <br>
     * 4.3.2.1 - Fazer vlParar = Verdadeiro                                 <br>
     * 4.3.2.2 - Adicionar vlRaiz a pLista.                                 <br>
     *                                                                      <br>
     * @param Elemento arvore �rvore que cont�m a sub�rvore desejada.       <br>
     * @param String idRaiz Valor da raiz da �rvore desejada.               <br>
     * @param ArvoreAux arvoreAux Objeto encarregado de tratar a parte da   <br>
     * �rvore que � inerente � l�gica de neg�cio.                           <br>
     * @param Elemento lista Lista que conter� os dados da �rvore.          <br>
     *                                                                      <br>
     */
    public static void obterSuperiores(Elemento arvore, String idRaiz,
            ArvoreAux arvoreAux, Elemento lista) {
        Elemento elemRaiz = arvore.getFilho(ELEM_RAIZ);
        String idRaizArvore = arvoreAux.obterIdRaiz(elemRaiz,
                new Integer(ArvoreAux.FLAG_RAIZ));

        if (lista == null) {
            lista = new Elemento("lista");
        }

        if (idRaiz == null) {
            if (ehRaizNula(elemRaiz)) {
                lista.incluirFilho(elemRaiz);
            }
        }

        if ((idRaizArvore != null) && (idRaiz.equals(idRaizArvore))) {
            lista.incluirFilho(elemRaiz);
        } else {
            Elemento[] elemSubarvores = new Elemento[0];
            if (elemRaiz.getFilho(ELEM_SUBARVORE) != null) {
                elemSubarvores = elemRaiz.getFilho(
                        ELEM_SUBARVORE).getFilhos(ELEM_ARVORE);
            }

            for (int i = 0; i < elemSubarvores.length; i++) {
                Arvore.obterSuperiores(elemSubarvores[i], idRaiz, arvoreAux,
                                       lista);
                if (lista.getFilhos(ELEM_RAIZ).length > 0) {
                    lista.incluirFilho(elemRaiz);
                    break;
                }
            }// fim for (int i = 0; i < elemSubarvores.length; i++) {
        }// fim if - else
    }// obterSuperiores()

    /**
     * OBJETIVO:                                                            <br>
     * Obter os nomes dos itens preenchidos num dado elemento de estrutura  <br>
     * qualquer.                                                            <br>
     *                                                                      <br>
     * Funcionalidade:                                                      <br>
     *  .                                 <br>
     *                                                                      <br>
     * @param Elemento elemento Elemento de estrutura qualquer.             <br>
     * @param Elemento preenchidos Elemento com estrutura descrita abaixo:  <br>
     * <!Elemento preenchidos (noPreenchido*)>.                             <br>
     *                                                                      <br>
     */
    public static void obterItensPreenchidos(Elemento elemento,
                                             Elemento preenchidos) {
        Elemento elemPreenchidos = new Elemento(ELEM_PREENCHIDOS);
        Elemento[] filhos = null;

        if (elemento == null) {
            return;
        }

        filhos = elemento.getFilhos();

        if (filhos.length == 0) {
            if (elemento.getValor() != null) {
                elemPreenchidos.incluirFilho(ELEM_NO_PREENCHIDO,
                                             elemento.getNome());
            }
        } else {
            for (int i = 0; i < filhos.length; i++) {
                Arvore.obterItensPreenchidos(filhos[i], preenchidos);
            }
        }// fim if - else (filhos.length == 0) {
    }// obterItensPreenchidos()

    /**
     * Informa se uma determinada raiz � nula ou n�o.                       <br>
     * Para ser nula, uma raiz deve:                                        <br>
     *      - Ser, ela pr�pria, null OU                                     <br>
     *      - N�o possuir nenhum filho OU                                   <br>
     *      - Possuir um valor diferente de null E egual a VALOR_RAIZ_VAZIA <br>
     *                                                                      <br>
     * @param Elemento raiz Raiz a ser testada.                             <br>
     *                                                                      <br>
     * @return true - � raiz nula; false - n�o � raiz nula.                 <br>
     */
    public static boolean ehRaizNula(Elemento raiz) {
        boolean retorno = false;

        retorno = ((raiz == null)
                || (raiz.getFilhos().length == 0)
                || ((raiz.getValor() != null)
                    && (raiz.getValor().equals(VALOR_RAIZ_VAZIA))));

        return retorno;
    }// ehRaizNula()
}// Arvore
