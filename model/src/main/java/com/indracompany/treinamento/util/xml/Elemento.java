package com.indracompany.treinamento.util.xml;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
 

/**
 * Representa um elemento de um documento xml.
 *
 * <p>
 *   Exemplo de um XML que poder� ser representado pela classe Elemento
 *   < Pessoa >                                                             <br>
 *      < nome > Jose     < /nome >                                         <br>
 *      < cpf >  33333333 < /cpf >                                          <br>
 *      < dependentes >                                                     <br>
 *           < nome > Joao < /nome >                                        <br>
 *           < nome > Pedro < /nome >                                       <br>
 *      < /dependentes >                                                    <br>
 *  </ Pessoa >                                                             <br>
 *                                                                          <br>
 * Para criar esse XML atrav�s do Elemento deve ser feitos os passos abaixo:<br>
 *   Elemento e = new Elemento("Pessoa"); //Elemento raiz                   <br>
 *   e.incluirFilho("nome","Jose");                                         <br>
 *   e.incluirFilho("cpf","33333333");                                      <br>
 *                                                                          <br>
 *   Elemento ef = e.incluirFilho("dependentes"); //Cria um Elemento filho  <br>
 *   ef.incluirFilho("nome","Joao");                                        <br>
 *   ef.incluirFilho("nome","Pedro");                                       <br>
 *                                                                          <br>
 * @author: Politec
 *
 * <b> Hist�rico: <b> <br>
 *  06/05/2003 Renan Guerra
 *             Cria��o do m�todo clone() que retorna uma nova refer�ncia do
 *             object atual.
 *  26/11/2002 Nazareno Duarte
 *             Alterados os m�todos converterStringToXXXX para verificar se
 *             o valor do elemento e <code>null</code>, e neste caso n�o gerar
 *             exce��o, apenas retornar null.
 */
public class Elemento implements Serializable, Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5488980353522790642L;
	
	private String nome_;
    private Object valor_; // v2.0
    private List filhos_;

    private static final int    TAM_INICIAL_FILHOS = 1;

    private static final char   INICIO_TAG_CHAR    = '<';
    private static final char   BARRA_CHAR         = '/';
    private static final char   FIM_TAG_CHAR       = '>';
    private static final String INICIO_TAG         = "" + INICIO_TAG_CHAR;
    private static final String BARRA              = "" + BARRA_CHAR;
    private static final String FIM_TAG            = "" + FIM_TAG_CHAR;

    private static final String STRING_VAZIA       = "";
    private static final String NOME_NULO          = "Recebi um nome nulo.";
    private static final String NOME_STRING_VAZIA  = "Recebi a String vazia.";
    
    
    /**
     * Added default constructor to fix converting task used by
     * Redisson to serialize Json object.
     */
    public Elemento() {}

    /**
     * Cria um novo Elemento sem valor. Recebe uma String no par�metro a qual
     * representa o nome do Elemento.                                       <br>
     *
     * Caso o nome passado seja "Pessoa", o seguinte elemento
     * sera criado:                                                         <br>
     *                                                                      <br>
     * < Pessoa >  < /Pessoa >                                              <br>
     *
     * @param nome String nome do elemento XML.
     */
    public Elemento(String nome) {

        if (nome == null) {
            throw new RuntimeException(NOME_NULO);
        } else if (nome.trim().equals(STRING_VAZIA))  {
            throw new RuntimeException(NOME_STRING_VAZIA);
        } else {
            nome_ = nome;
        } // if else

    } // Elemento()

    /**
     * Cria um Elemento XML contendo valor. Recebe como par�metro o nome e o
     * valor do novo elemento XML. Caso o nome passado seja Nome, e o valor seja
     * Joao, o seguinte elemento � criado : <br>
     *                                                                      <br>
     * < Nome > Joao < /Nome >
     *                                                                      <br>
     * @param nome String nome da raiz do XML.                              <br>
     * @param valor String valor de um elemento.                            <br>
     */
    public Elemento(String nome, String valor) {

        this(nome);

        valor_ = valor;

    } // Elemento()

    /**
     * <b> Implementador: </b> <br>
     *              Nazareno Duarte <br>
     *                                                                      <br>
     * <b> Data: </b> <br>
     *              18/Dez/2002 <br>
     *                                                                      <br>
     * <b> Objetivo: </b> <br>
     *              Cria um Elemento XML contendo valor.
     *                                                                      <br>
     * <b> Funcionamento: </b> <br>
     *              Recebe como par�metro o nome do elemento e o valor do novo
     *              elemento XML. Caso o nome passado seja Nome, e o valor seja
     *              Joao, o seguinte elemento � criado : <br>
     *                  < Nome > Joao < /Nome >
     *                                                                      <br>
     * @param nome String nome do elemento XML.                             <br>
     * @param valor Object armazenado pelo elemento.                        <br>
     *                                                                      <br>
     */
    public Elemento(String nome, Object valor) {

        this(nome);

        valor_ = valor;

    } // Elemento()

    /**
     * Criar a estrutura que armazenar� os filhos do <code>Elemento</code>.
     */
    private void criarFilhos() {

        filhos_ = new ArrayList(TAM_INICIAL_FILHOS);

    } // criarFilhos()

    /**
     * Retornar a estrutura que armazena os filhos do <code>Elemento</code>.
     *
     * @return <code>List</code> o valor obtido
     */
    private List obterFilhos() {

        return filhos_;

    } // obterFilhos()

    /**
     * Retornar a quantidade de filhos que o este <code>Elemento</code> possui.
     *
     * @return <code>int</code> o valor obtido
     */
    private int obterTamanhoFilhos() {

        if (filhos_ == null) {

            return 0;

        } else {

            return filhos_.size();

        } // if else

    } // obterTamanhoFilhos()

    /**
     * Adicionar um filho a lista de filhos do elemento atual
     *
     * @param filho <code>Elemento</code> o filho que ser� inserido
     */
    public void adicionarFilho(Elemento filho) {

        if (filhos_ == null)  {

            criarFilhos();

        } // if

        filhos_.add(filho);

    } // adicionarFilho()

    /**
     * <b> Implementador: </b> <br>
     *              Nazareno Duarte <br>
     *                                                                      <br>
     * <b> Data: </b> <br>
     *              11/Out/2002 <br>
     *                                                                      <br>
     * <b> Objetivo: </b> <br>
     *              Inclui elementos no elemento de onde o m�todo e 'invocado.
     *                                                                      <br>
     * <b> Funcionamento: </b> <br>
     *              . Verificar se existem elementos a serem inseridos. <br>
     *              . Para cada elemento recebido no array <br>
     *              . Se o elemento atual for diferente de null, chamar o m�todo
     *                incluirFilho passando o elemento atual do array. <br>
     *                                                                      <br>
     * @param filhos Elemento elemento filho a ser incluido.
     *                                                                      <br>
     */
    public void incluirFilhos(Elemento[] filhos) {

        if ((filhos != null) && (filhos.length > 0)) {

            for (int i = 0; i < filhos.length; i++) {

                if (filhos[i] != null) {

                    incluirFilho(filhos[i]);

                } // if

            } // for

        } // if

    } // incluirFilhos()

    /**
     * <b> Implementador: </b> <br>
     *              Nazareno Duarte <br>
     *                                                                      <br>
     * <b> Data: </b> <br>
     *              11/Out/2002 <br>
     *                                                                      <br>
     * <b> Objetivo: </b> <br>
     *              Inclui elementos no elemento de onde o m�todo e 'invocado.
     *                                                                      <br>
     * <b> Funcionamento: </b> <br>
     *              . Verificar se existem elementos a serem inseridos. <br>
     *              . Para cada elemento recebido no Iterador <br>
     *              . Chamar o m�todo incluirFilho passando o elemento atual
     *                do array. <br>
     *                                                                      <br>
     * @param filhos Collection com os filhos a serem incluidos.
     *                                                                      <br>
     */
    public void incluirFilhos(Collection filhos) {

        if (filhos != null) {

            Elemento filho;
            Iterator iterador = filhos.iterator();

            while(iterador.hasNext()) {

                filho = (Elemento) iterador.next();

                if (filho != null) {

                    incluirFilho(filho);

                } // if

            } // while

        } // if

    } // incluirFilhos()

    /**
     * <b> Implementador: </b> <br>
     *              Nazareno Duarte <br>
     *                                                                      <br>
     * <b> Data: </b> <br>
     *              23/Dez/2002 <br>
     *                                                                      <br>
     * <b> Objetivo: </b> <br>
     *              Inclui elementos no elemento de onde o m�todo � invocado.
     *                                                                      <br>
     * <b> Funcionamento: </b> <br>
     *              . Para cada String recebido no array <br>
     *              . Incluir um filho com o nome recebido. <br>
     *                                                                      <br>
     * @param nomes String[] com os nomes do filhos a incluir.
     *                                                                      <br>
     */
    public void incluirFilhos(String[] nomes) {

        if ((nomes != null) && (nomes.length > 0)) {

            for (int i = 0; i < nomes.length; i++) {

                if (nomes[i] != null) {

                    incluirFilho(nomes[i]);

                } // if

            } // for

        } // if

    } // incluirFilhos()

    /**
     * Inclui um elemento no elemento de onde o m�todo �invocado.
     *                                                                      <br>
     * Considere a sequencia de comandos a seguir:
     *                                                                      <br>
     * Elemento E1 = new Elemento("Pessoa");
     *                                                                      <br>
     * Elemento E2 = new Elemento("Nome", "Joao");
     *                                                                      <br>
     * E1.incluirFilho(E2); // comando que inclui o elemento E2 no elemento E1.
     *                                                                      <br>
     * o resultado do XML final � o seguinte:
     *                                                                      <br>
     * < Pessoa >
     *     < Nome > Joao < /Nome >
     * < /Pessoa >
     *                                                                      <br>
     * @param filho Elemento elemento filho a ser incluido.
     */
    public Elemento incluirFilho(Elemento filho) {

        if (filho == null) {

            throw new RuntimeException("Nao posso incluir um filho nulo.");

        } else if (filho.getNome().indexOf('.') != -1) {

            throw new RuntimeException(
                    "O elemento n�o pode ter um '.' no nome: "
                    + filho.getNome());

        } // if else if

        adicionarFilho(filho);

        return filho;

    } // incluirFilho()

    /**
     * Inclui um Elemento filho sem valor no elemento de onde o m�todo �
     * invocado.
     *                                                                      <br>
     * Considere a sequencia de comandos a seguir:
     *                                                                      <br>
     * Elemento E1 = new Elemento("Pessoa");
     *                                                                      <br>
     * E1.incluirFilho("Nome"); // comando que inclui o elemento sem valor Nome
     * no elemento E1.
     *                                                                      <br>
     * o resultado do XML final � o seguinte:
     *                                                                      <br>
     * < Pessoa >                                                           <br>
     *    < Nome > < /Nome >                                                <br>
     * < /Pessoa >                                                          <br>
     *                                                                      <br>
     *
     * @param nomeFilho String nome do filho a ser incluido.
     * @return Elemento com filho incluido.
     */
    public Elemento incluirFilho(String nomeFilho) {

        int pos = nomeFilho.indexOf('.');

        if (pos == -1) {

            Elemento novo = new Elemento(nomeFilho);

            return incluirFilho(novo);

        } else {

            Elemento pai  = getFilhoCriando(nomeFilho.substring(0, pos));
            Elemento novo = pai.incluirFilho(nomeFilho.substring(pos + 1));

            return novo;

        } // if else

    } // incluirFilho()

    /**
     * Inclui um Elemento filho com valor no elemento de onde o m�todo �
     * invocado.
     * Este m�todo recebe como par�metro o nome, no formato String e o valor em
     * inteiro do novo filho a ser inclu�do.
     *                                                                      <br>
     * Considere a seq��ncia de comandos a seguir:
     *                                                                      <br>
     * Elemento E1 = new Elemento("Pessoa");
     *                                                                      <br>
     * E1.incluirFilho("Idade","30");  // comando que inclui o elemento sem
     *                                 // valor Nome no elemento E1.
     *                                                                      <br>
     * o resultado do XML final � o seguinte:
     *                                                                      <br>
     * < Pessoa >
     *    < Idade > 30 < /Idade >
     * < /Pessoa >
     *                                                                      <br>
     *
     * @param nomeFilho String nome do filho a ser incluido.
     * @param nomeFilho int valor em inteiro do filho a ser incluido.
     */
    public Elemento incluirFilho(String nomeFilho, int valorFilho) {

        return setValor(nomeFilho, String.valueOf(valorFilho));

    } // incluirFilho()

    /**
     * Inclui um Elemento filho com valor no elemento de onde o m�todo �
     * invocado.
     * Este m�todo recebe como par�metro o nome, no formato String e o valor em
     * long do novo filho a ser incluido.
     *                                                                      <br>
     * @param nomeFilho String nome do filho a ser incluido.
     * @param nomeFilho long valor em long do filho a ser incluido.
     */
    public Elemento incluirFilho(String nomeFilho, long valorFilho) {

        return setValor(nomeFilho, String.valueOf(valorFilho));

    } // incluirFilho()

    /**
     * Inclui um Elemento filho com valor no elemento de onde o m�todo e
     * 'invocado.
     * Este m�todo recebe como par�metro o nome, no formato String e o valor em
     * float do novo filho a ser incluido.
     *                                                                      <br>
     * Considere a sequencia de comandos a seguir:
     *                                                                      <br>
     * Elemento E1 = new Elemento("Mercadoria");
     *                                                                      <br>
     * E1.incluirFilho("Preco","27,9"); // comando que inclui o elemento sem
     *                                  // valor Nome no elemento E1.
     *                                                                      <br>
     * o resultado do XML final � o seguinte:
     *                                                                      <br>
     * < Mercadoria >
     *    < Preco > 27,9 < /Preco >                                         <br>
     * < /Mercadoria >
     *                                                                      <br>
     * @param nomeFilho String nome do filho a ser incluido.
     * @param nomeFilho float valor em float do filho a ser incluido.
     */
    public Elemento incluirFilho(String nomeFilho, float valorFilho) {

        return setValor(nomeFilho, String.valueOf(valorFilho));

    } // incluirFilho()

    /**
     * Inclui um Elemento filho com valor no elemento de onde o m�todo �
     * invocado.
     * Este m�todo recebe como par�metro o nome, no formato String e o valor em
     * double do novo filho a ser incluido.
     *                                                                      <br>
     * @param nomeFilho String nome do filho a ser incluido.
     * @param valorFilho double valor em double do filho a ser incluido.
     *
     */
    public Elemento incluirFilho(String nomeFilho, double valorFilho) {

        return setValor(nomeFilho, String.valueOf(valorFilho));

    } // incluirFilho()

    /**
     * Inclui um Elemento filho com valor no elemento de onde o m�todo e
     * 'invocado.
     * Este m�todo recebe como par�metro o nome e o valor em inteiro do novo
     * filho a ser incluido, ambos no formato String. <br>
     *                                                                      <br>
     * Considere a sequencia de comandos a seguir:
     *                                                                      <br>
     * Elemento E1 = new Elemento("Pessoa");                                <br>
     * E1.incluirFilho("Nome","Joao");   // comando que inclui o elemento sem
     *                                   // valor Nome no elemento E1.
     *                                                                      <br>
     * o resultado do XML final � o seguinte:                              <br>
     *                                                                      <br>
     * < Pessoa >
     *    < Nome > Joao < /Nome >                                           <br>
     * < /Pessoa >                                                          <br>
     *                                                                      <br>
     * @param nomeFilho String nome do filho a ser incluido.
     * @param valorFilho int valor em inteiro do filho a ser incluido.
     * @return Elemento, elemento filho que foi criado e inserido.
     */
    public Elemento incluirFilho(String nomeFilho, String valorFilho) {

        return setValor(nomeFilho, valorFilho);

    } // incluirFilho()

    /**
     * m�todo que recebe como par�metro o nome de um elemento e verifica se ha
     * um elemento com este nome no elemento que invocou o m�todo.
     *                                                                      <br>
     * O valor true �retornado se existe um elemento com o nome passado no
     * par�metro, e false caso contrario.
     *                                                                      <br>
     *
     * Por exemplo, considere o elemento abaixo:                            <br>
     *
     *    < Pessoa >                                                        <br>
     *      < Nome > Joao < /Nome >                                         <br>
     *   < /Pessoa >                                                        <br>
     *
     * boolean A = Pessoa.contemFilho("Nome");                              <br>
     * boolean B = Pessoa.contemFilho("Idade");                             <br>
     *
     * o valor de A sera true, pois existe um elemento chamado Nome no
     *  elemento Pessoa. <br>
     * o valor de B sera false, pois nao existe um elemento chamado Idade no
     *  elemento Pessoa. <br>
     * @param nomeProcurado String nome do elemento filho  procurado.
     * @return boolean true se existir um elemento filho com o nome procurado e
     *  false caso contrario.
     */
    public boolean contemFilho(String nomeProcurado) {

        return (this.getFilho(nomeProcurado) != null);

    } // contemFilho()

    /**
     * <B>Objetivo</B>
     *        Este m�todo verifica se um Elemento cont�m filhos ou n�o      <br>
     *                                                                      <br>
     * Por exemplo, considere o elemento abaixo:                            <br>
     *                                                                      <br>
     *    < Pessoa >                                                        <br>
     *      < Nome > Joao < /Nome >                                         <br>
     *      < Nome > Maria < /Nome >                                        <br>
     *      < Nome > Jose < /Nome >                                         <br>
     *      < Cidade > Brasilia < /Cidade >                                 <br>
     *   < /Pessoa >                                                        <br>
     *                                                                      <br>
     * Pessoa tem filhos                                                    <br>
     * Nome n�o tem filhos                                                  <br>
     *                                                                      <br>
     * @return boolean true se existir pelo menos um elemento filho         <br>
     * e false caso contrario.                                              <br>
     */
    public boolean contemFilhos() {

        List filhos = obterFilhos();

        return ((filhos != null) && (filhos.size() > 0));

    } // contemFilhos()

    /**
     * Exclui um determinado filho. O filho a ser excluido � encontrado atrav�s
     * de seu nome.
     * Caso exista mais de um elemento com o mesmo nome, apenas o primeiro
     * elemento encontrado � excluido.
     *                                                                      <br>
     * @param nomeCampo nome do Elemento filho que se deseja excluir.
     */
    public void excluir(String nomeElemento) {

        int tamFilhos = obterTamanhoFilhos();

        if (tamFilhos > 0) {

            int pos = nomeElemento.indexOf('.');

            if (pos == -1) {

                Elemento filho;

                for (int i = 0; i < tamFilhos; i++) {

                    filho = (Elemento) obterFilhos().get(i);

                    if (filho.getNome().equalsIgnoreCase(nomeElemento)) {

                        obterFilhos().remove(i);

                        return;

                    } // if

                } // if

            } else {

                Elemento pai = getFilho(nomeElemento.substring(0, pos));

                if (pai != null) {

                    pai.excluir(nomeElemento.substring(pos + 1));

                } // if

            } // if else

        } // if else

    } // excluir()

    /**
     * Exclui um determinado filho. O filho a ser excluido � encontrado atraves
     * de sua refer�ncia. Caso exista um elemento retorna true, caso contr�rio
     * false.
     *                                                                      <br>
     * @param Elemento filho Elemento filho que se deseja excluir.
     */
    public boolean excluir(Elemento filho) {

        if ((filho != null) && (obterTamanhoFilhos() > 0)) {

            return obterFilhos().remove(filho);

        } else {

            return false;

        } // if

    } // excluir()

     /**
     * <b> Implementador: </b> <br>
     *              Nazareno Duarte <br>
     *                                                                      <br>
     * <b> Data: </b> <br>
     *              11/Out/2002 <br>
     *                                                                      <br>
     * <b> Objetivo: </b> <br>
     *              Exclui elementos do elemento de onde o m�todo e 'invocado.
     *                                                                      <br>
     * <b> Funcionamento: </b> <br>
     *              . Verificar se existem elementos a serem exclu�dos. <br>
     *              . Para cada elemento recebido no array <br>
     *              . Se o elemento atual for diferente de null, chamar o m�todo
     *                excluir passando o elemento atual do array. <br>
     *                                                                      <br>
     * @param filho Elemento elemento filho a ser incluido.
     *                                                                      <br>
     */
    public void excluir(Elemento[] filhos) {

        if ((filhos != null) && (filhos.length > 0)) {

            for (int i = 0; i < filhos.length; i++) {

                if (filhos[i] != null) {

                    excluir(filhos[i]);

                } // if

            } // for

        } // if else if

    } // excluir()

    /**
     * <b> Implementador: </b> <br>
     *              Nazareno Duarte <br>
     *                                                                      <br>
     * <b> Data: </b> <br>
     *              23/Dez/2002 <br>
     *                                                                      <br>
     * <b> Objetivo: </b> <br>
     *              Exclui elementos, com nomes recebidos no par�mtro, do
     *              elemento de onde o m�todo � invocado.
     *                                                                      <br>
     * <b> Funcionamento: </b> <br>
     *              . Para cada String recebido no array <br>
     *              . Excluir um filho com o nome recebido. <br>
     *                                                                      <br>
     * @param nomes String[] com os nomes do filhos a excluir.
     *                                                                      <br>
     */
    public void excluir(String[] nomes) {

        if ((nomes != null) && (nomes.length > 0)) {

            for (int i = 0; i < nomes.length; i++) {

                if (nomes[i] != null) {

                    excluir(nomes[i]);

                } // if

            } // for

        } // if else if

    } // excluir()

   /**
     * Modifica ou seta o valor do elemento. Caso o elemento nao possua valor,
     * uma RuntimeException � lancada.
     *                                                                      <br>
     * @param valor novo valor a ser setado para o elemento.
     */
    public void setValor(String valor) {

        if (obterTamanhoFilhos() > 0) {

            throw new RuntimeException("N�o � poss�vel incluir um valor "
                    + "diretamente em um elemento que j� possui filhos");

        } // if

        valor_ = valor;

    } // setValor()

    /**
     * Modifica ou seta o valor de um elemento filho. Caso o elemento filho nao
     * exista ou nao possua valor uma RuntimeException � lancada.
     * @return Elemento retorna o elemento filho cujo valor foi modificado.
     * @param nomeCampo nome do elemento filho que se deseja modificar o valor
     * @param valor novo valor a ser setado para o elemento filho.
     */
    public Elemento setValor(String nomeCampo, String valor) {

        Elemento e = getFilhoCriando(nomeCampo);
        e.setValor(valor);

        return e;

    } // setValor();

    /**
     * m�todo que recebe como par�metro o nome de um elemento e verifica se ha
     * um elemento com este nome no elemento que invocou o m�todo. <br>
     * Um objeto Elemento filho �retornado caso seja encontrado um elemento
     * filho com o nome passado no par�metro. Caso nao exista, o valor null �
     * retornado. <br>
     * Caso existam mais de um elemento filho com o nome procurado, apenas o
     * primeiro encontrado sera retornado. <br>
     *                                                                      <br>
     * Por exemplo, considere o elemento abaixo:                            <br>
     *                                                                      <br>
     * <pre>
     *    < Pessoa >
     *      < Nome > Joao < /Nome >
     *   < /Pessoa >
     * </pre>
     *                                                                      <br>
     * Elemento filho1 = Pessoa.getFilho("Nome");                           <br>
     * Elemento filho2 = Pessoa.getFilho("Idade");                          <br>
     *                                                                      <br>
     * O filho1 sera setado com o elemento Nome de valor Joao , pois existe um
     *   elemento chamado Nome no elemento Pessoa. <br>
     * O filho2 sera setado com null, pois nao existe um elemento chamado Idade
     *   no elemento Pessoa. <br>
     *                                                                      <br>
     * @param nomeElemento String nome do elemento filho  procurado. <br>
     * @return Elemento o elemento filho caso exista um elemento filho com o
     *  nome procurado, e null caso contrario. <br>
     */
    public Elemento getFilho(String nomeElemento) {

        int tamFilhos = obterTamanhoFilhos();

        if (tamFilhos > 0) {

            int pos = nomeElemento.indexOf('.');

            String nomeE = nomeElemento;
            String resto = null;

            if (pos != -1) {
                nomeE = nomeElemento.substring(0, pos);
                resto = nomeElemento.substring(pos + 1);
            } // if

            for (int i = 0; i < tamFilhos; i++) {

                Elemento filho = (Elemento) obterFilhos().get(i);
                String nomeFilho = filho.getNome();

                if (nomeFilho.equalsIgnoreCase(nomeE)) {

                    if (resto == null) {

                        return filho;

                    } else {

                        return filho.getFilho(resto);

                    } // if else

                } // if

            } // for

        } // if

        return null;

    } // getFilho()

    /**
     * m�todo que recebe como par�metro o nome de um elemento e verifica se
     * existem elementos com este nome no elemento que invocou o m�todo. <br>
     * Um array de Elementos filhos � retornado caso sejam encontrados
     * elementos filhos com o nome passado no par�metro. Caso nao exista, um
     * array de Elemento, com tamanho 0 (zero), � retornado. <br>
     *                                                                      <br>
     * Por exemplo, considere o elemento abaixo: <br>
     *                                                                      <br>
     * <pre>
         < Pessoa>
            < Nome>Joao< /Nome>
            < Nome>Maria< /Nome>
            < Nome>Jose< /Nome>
            < filhos>
                < Nome>Joao< /Nome>
                < Nome>Maria< /Nome>
                < Nome>Jose< /Nome>
            < /filhos>
         < /Pessoa>
       </pre>                                                               <br>
     *                                                                      <br>
     * Elemento[] filhos1 = Pessoa.getFilhos("Nome");                       <br>
     * Elemento[] filhos2 = Pessoa.getFilhos("Idade");                      <br>
     *                                                                      <br>
     * <b> Altera�ao: </b> <br>
     *          Nazareno Duarte <br>
     *          22/08/2002 <br>
     *          <br>
     * O nome pode ser composto por pontos:
     *     Ex.:
     *          Elemento[] filhos1 = Pessoa.getFilhos("pessoa.filhos.nome");<br>
     *                                                                      <br>
     * filhos1 sera setado com um array de tres objetos Elemento , pois existem
     *  elementos chamados Nome no elemento Pessoa. <br>
     * filhos2 sera setado com um array de tamanho zero, pois nao existe nenhum
     *  elemento chamado Idade no elemento Pessoa. <br>
     *                                                                      <br>
     * @param nomeElemento String nome do elemento filho  procurado. <br>
     * @return Elemento[] array contendo os objetos Elemento filhos encontrados,
     *  e um array de tamanho zero, caso nao exista nenhum filho com o nome
     *  procurado. <br>
     */
    public Elemento[] getFilhos(String nomeElemento) {

        int tamFilhos = obterTamanhoFilhos();

        // Validar a solicita��o
        if ((nomeElemento == null) || (tamFilhos == 0)) {

            return new Elemento[0];

        } // if

        // Posicionar no elemento adequado
        int pos = nomeElemento.indexOf('.');

        if (pos != -1) {

            String nomeProximoElemento = nomeElemento.substring(0, pos);
            String complementoNome = nomeElemento.substring(pos + 1);

            Elemento proximoElemento = getFilho(nomeProximoElemento);

            if (proximoElemento != null) {

                return proximoElemento.getFilhos(complementoNome);

            } else {

                return new Elemento[0];

            } // if else

        } else {

            // Recuperar elementos filhos
            ArrayList encontrados = new ArrayList(0);

            for (int i = 0; i < tamFilhos; i++) {

                Elemento filho = (Elemento) obterFilhos().get(i);
                String nomeFilho = filho.getNome();

                if (nomeElemento.equalsIgnoreCase(nomeFilho)) {

                    encontrados.add(filho);

                } // if

            } // if

            Elemento [] filhosEncontrados = new Elemento[encontrados.size()];

            encontrados.toArray(filhosEncontrados);

            return filhosEncontrados;

        } // if else

    } // getFilhos()

    /**
     * Este m�todo retorna um array de objetos Elemento contendo todos os
     * elementos filhos do elemento que esta invocando o m�todo.
     *  <br>                                                                <br>
     * Por exemplo, considere o elemento abaixo:                            <br>
     *                                                                      <br>
     *    < Pessoa >                                                        <br>
     *      < Nome > Joao < /Nome >                                         <br>
     *      < Nome > Maria < /Nome >                                        <br>
     *      < Nome > Jose < /Nome >                                         <br>
     *      < Cidade > Brasilia < /Cidade >                                 <br>
     *   < /Pessoa >                                                        <br>
     *                                                                      <br>
     * Elemento[] filhos1 = Pessoa.getFilhos();                             <br>
     *                                                                      <br>
     * filhos1 sera setado com um array de quatro objetos Elemento , pois contem
     *  quatro elementos filhos.
     * @return Elemento[] array contendo todos os objetos Elemento filhos
     *                  - null caso o elemento nao possua filhos.
     */
    public Elemento[] getFilhos() {

        int tamFilho = obterTamanhoFilhos();

        if (tamFilho == 0) {

            return new Elemento[0];

        } // if

        Elemento [] filhosEncontrados = new Elemento[tamFilho];

        obterFilhos().toArray(filhosEncontrados);

        return filhosEncontrados;

    } // getFilhos()

    /**
     * <B>Objetivo</B> <br>
     *        Este m�todo retorna um array de objetos Elemento contendo todos
     *        os elementos filhos do elemento que esta invocando o m�todo.  <br>
     *                                                                      <br>
     * Por exemplo, considere o elemento abaixo:                            <br>
     *                                                                      <br>
     *    < Pessoa >                                                        <br>
     *      < Nome > Joao < /Nome >                                         <br>
     *      < Nome > Maria < /Nome >                                        <br>
     *      < Nome > Jose < /Nome >                                         <br>
     *      < Cidade > Brasilia < /Cidade >                                 <br>
     *   < /Pessoa >                                                        <br>
     *                                                                      <br>
     * Elemento[] filhos1 = Pessoa.getFilhos();                             <br>
     *                                                                      <br>
     * filhos1 sera setado com um array de quatro objetos Elemento ,        <br>
     * pois contem quatro elementos filhos.                                 <br>
     * @return Elemento[] array contendo todos os objetos Elemento filhos   <br>
     *                  - null caso o elemento nao possua filhos.           <br>
     *                                                                      <br>
     */
    public Elemento[] getFilhosNotNull() {

        int tamFilhos = obterTamanhoFilhos();

        if (tamFilhos == 0) {

            return new Elemento[0];

        } // if

        ArrayList encontrados = new ArrayList(0);

        for (int i = 0; i < tamFilhos; i++) {

            Elemento filho = (Elemento) obterFilhos().get(i);

            if (!filho.contemFilhos()) {
                if (((filho.getValor() != null)
                    && (filho.getValor().trim().equals("")))
                    || (filho.getValor() ==null)) {
                    continue;
                } // if
            } // if

            encontrados.add(filho);

        } // for

        Elemento [] filhosEncontrados = new Elemento[encontrados.size()];

        encontrados.toArray(filhosEncontrados);

        return filhosEncontrados;

    } // getFilhosNotNull()

    //--------------------------------------------------------------------------
    /**
     * m�todo que recebe como par�metro o nome de um elemento e verifica se
     * existem elementos com este nome no elemento que invocou o m�todo. <br>
     * Uma cole��o de Elementos filhos � retornada caso sejam encontrados
     * elementos filhos com o nome passado no par�metro. Caso nao exista, uma
     * cole��o vazia � retornada, � retornado. <br>
     *                                                                      <br>
     * Por exemplo, considere o elemento abaixo: <br>
     *                                                                      <br>
     * <pre>
         < Pessoa>
            < Nome>Joao< /Nome>
            < Nome>Maria< /Nome>
            < Nome>Jose< /Nome>
            < filhos>
                < Nome>Joao< /Nome>
                < Nome>Maria< /Nome>
                < Nome>Jose< /Nome>
            < /filhos>
         < /Pessoa>
       </pre>                                                               <br>
     *                                                                      <br>
     * Collection filhos1 = Pessoa.getFilhos("Nome");                       <br>
     * Collection filhos2 = Pessoa.getFilhos("Idade");                      <br>
     *                                                                      <br>
     * <b> Altera�ao: </b> <br>
     *          Nazareno Duarte <br>
     *          22/08/2002 <br>
     *          <br>
     * O nome pode ser composto por pontos:
     *     Ex.:
     *          Collection filhos1 = Pessoa.getFilhos("pessoa.filhos.nome");<br>
     *                                                                      <br>
     * filhos1 sera setado com uma colecao de tres objetos Elemento, pois
     *  existem elementos chamados Nome no elemento Pessoa. <br>
     * filhos2 sera setado com null, pois nao existe nenhum elemento chamado
     *  Idade no elemento Pessoa. <br>
     *                                                                      <br>
     * @param nomeElemento String nome do elemento filho  procurado. <br>
     * @return Collection contendo os objetos Elemento filhos encontrados,
     *  uma cole��o vazia caso nao exista nenhum filho com o nome procurado. <br>
     */
    public Collection getColecao(String nomeElemento) {

        return Arrays.asList(getFilhos(nomeElemento));

    } // getColecao()

    /**
     * Este m�todo retorna um Cole��o de Elementos contendo todos os elementos
     *  filhos do elemento que esta invocando o m�todo. <br>                                                                <br>
     * Por exemplo, considere o elemento abaixo:                            <br>
     *                                                                      <br>
     *    < Pessoa >                                                        <br>
     *      < Nome > Joao < /Nome >                                         <br>
     *      < Nome > Maria < /Nome >                                        <br>
     *      < Nome > Jose < /Nome >                                         <br>
     *      < Cidade > Brasilia < /Cidade >                                 <br>
     *   < /Pessoa >                                                        <br>
     *                                                                      <br>
     * Collection filhos1 = Pessoa.getFilhos();                             <br>
     *                                                                      <br>
     * filhos1 sera setado com uma cole��o de quatro objetos Elemento , pois
     *  contem quatro elementos filhos.
     * @return Collection contendo todos os objetos Elemento filhos
     *                  - Uma cole��o vazia caso o elemento nao possua filhos.
     */
    public Collection getColecao() {

        List encontrados = new ArrayList(0);

        int tamFilho = obterTamanhoFilhos();

        for (int i = 0; i < tamFilho; i++) {

            Elemento filho = (Elemento) obterFilhos().get(i);

            encontrados.add(filho);

        } // for

        return encontrados;

    } // getColecao()

    /**
     * Este m�todo retorna um iterador sobre os Elementos filhos do elemento
     * atual. <br>
     *
     * @return um Iterator <br>
     */
    public Iterator iterador(String nomeElemento) {

        return getColecao(nomeElemento).iterator();

    } // iterador()

    /**
     * Este m�todo retorna um iterador sobre os Elementos filhos do elemento
     * atual. <br>
     *
     * @return um Iterator <br>
     */
    public Iterator iterador() {

        return getColecao().iterator();

    } // iterador()

    //--------------------------------------------------------------------------

    /**
     * Altera o nome do elemento.
     *                                                                      <br>
     * @param nome o nome do elemento.
     */
    public void setNome(String nome) {

        this.nome_ = nome;

    } // setNome()

    /**
     * Retorna o nome do Elemento setado no momento de sua instanciacao.
     *                                                                      <br>
     * @return java.lang.String - nome do Elemento setado no momento de sua
     * instancia��o.
     */
    public String getNome() {

        return nome_;

    } // getNome()

    /**
     * Retorna o valor de um elemento filho Elemento, caso o elemento nao
     * possua valor, ou nao exista, sera retornado null.. <br>
     *                                                                      <br>
     * @param nomeCampo nome do Elemento filho cujo valor � desejado. <br>
     * @return String - valor do elemento filho ou null caso o filho nao exista
     *  ou nao possua valor. <br>
     */
    public String getValor(String nomeCampo) {

        Elemento e = getFilho(nomeCampo);

        if (e == null) {

            return null;

        } else {

            return e.getValor();

        } // if

    } // getValor()

    /**
     * m�todo que retorna o valor de um elemento filho no formato String.
     * <br>
     * Caso o tamanho do valor do elemento filho nao seja maior que o valor
     * minimo permitido, uma excecao ElementException � lancada.
     * <br>
     * Este m�todo � util em situacoes onde se deseja validar o tamanho minimo
     * de um campo para consultas SQL usando LIKE.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @param tamMinimo tamanho minimo permitido.
     * @param cdErroConv codigo de erro para a conversao.
     * @return Float objeto contendo o valor em float do elemento filho
     *  procurado.
     */
    public String getValorMaiorQuePermitido(String nomeElemento, int tamMinimo,
            int cdErroConv) {

        if (getValor(nomeElemento).length() < tamMinimo) {

            throw new ElementException();

        } else {

            return getValor(nomeElemento);

        } // if else

    } // getValorMaiorQuePermitido()

    /**
     * m�todo que retorna o valor de um elemento filho no formato String. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho. <br>
     *                                                                      <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return String valor do elemento filho procurado.
     */
    public String getValorNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return getValor(nomeElemento);

    } // getValorNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato String. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho. <br>
     *                                                                      <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return String valor do elemento filho procurado.
     */
    public String getValorNotNull(String nomeElemento, int cdErroNull) {

        existeFilho(nomeElemento, cdErroNull);

        return getValor(nomeElemento);

    } // getValorNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato String. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho, verificando tambem se ele � vazio.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return String valor do elemento filho procurado.
     */
    public String getValorNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return getValor(nomeElemento);

    } // getValorNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato String. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio, uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho, verificando tambem se ele � vazio.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @param cdErroNullouVazio o codigo da mensagem de erro que sera gerada no
     *  caso onde elemento desejado seja nulo ou vazio.
     * @return String valor do elemento filho procurado.
     */
    public String getValorNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return getValor(nomeElemento);

    } // getValorNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     *                                                                      <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *   procurado.
     */
    public Integer getInt(String nomeElemento, int cdErroConv) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToInt(nomeElemento, cdErroConv);

        } // if else

    } // getInt()

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *  procurado. <br>
     */
    public Integer getIntNotNull(String nomeElemento, int cdErroNull,
            int cdErroConv) {

        existeFilho(nomeElemento, cdErroNull);

        return converterStringToInt(nomeElemento, cdErroConv);

    } // getIntNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *  procurado.
     */
    public Integer getIntNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio, int cdErroConv) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return converterStringToInt(nomeElemento, cdErroConv);

    } // getIntNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato float.
     * Caso o valor do elemento filho nao seja um valor float, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Float objeto contendo o valor em float do elemento filho
     *  procurado.
     */
    public Float getFloat(String nomeElemento, int cdErroConv) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToFloat(nomeElemento, cdErroConv);

        } // if else

    } // getFloat()

    /**
     * m�todo que retorna o valor de um elemento filho no formato float.
     * Caso o valor do elemento filho nao seja um valor float, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Float objeto contendo o valor em float do elemento filho
     *  procurado.
     */
    public Float getFloatNotNull(String nomeElemento, int cdErroNull,
            int cdErroConv) {

        existeFilho(nomeElemento, cdErroNull);

        return converterStringToFloat(nomeElemento, cdErroConv);

    } // getFloatNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato float.
     * Caso o valor do elemento filho nao seja um valor float, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Float objeto contendo o valor em float do elemento filho
     *  procurado.
     */
    public Float getFloatNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio, int cdErroConv) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return converterStringToFloat(nomeElemento, cdErroConv);

    } // getFloatNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato long.
     * Caso o valor do elemento filho nao seja um valor long, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Long objeto contendo o valor em long do elemento filho procurado.
     */
    public Long getLong(String nomeElemento, int cdErroConv) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToLong(nomeElemento, cdErroConv);

        } // if

    } // getLong()

    /**
     * m�todo que retorna o valor de um elemento filho no formato long.
     * Caso o valor do elemento filho nao seja um valor long, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Long objeto contendo o valor em long do elemento filho procurado.
     */
    public Long getLongNotNull(String nomeElemento, int cdErroNull,
            int cdErroConv) {

        existeFilho(nomeElemento, cdErroNull);

        return converterStringToLong(nomeElemento,cdErroConv);

    } // getLongNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato long.
     * Caso o valor do elemento filho nao seja um valor long, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Long objeto contendo o valor em long do elemento filho procurado.
     */
    public Long getLongNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio, int cdErroConv) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return converterStringToLong(nomeElemento, cdErroConv);

    } // getLongNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato double.
     * Caso o valor do elemento filho nao seja um valor double, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Double objeto contendo o valor em double do elemento filho
     *  procurado.
     */
    public Double getDouble(String nomeElemento, int cdErroConv) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToDouble(nomeElemento,cdErroConv);

        } // if else

    } // getDouble()

    /**
     * m�todo que retorna o valor de um elemento filho no formato double.
     * Caso o valor do elemento filho nao seja um valor double, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Double objeto contendo o valor em double do elemento filho
     *  procurado.
     */
    public Double getDoubleNotNull(String nomeElemento, int cdErroNull,
            int cdErroConv) {

        existeFilho(nomeElemento, cdErroNull);

        return converterStringToDouble(nomeElemento,cdErroConv);

    } // getDoubleNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato double.
     * Caso o valor do elemento filho nao seja um valor double, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Double objeto contendo o valor em double do elemento filho
     *  procurado.
     */
    public Double getDoubleNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio, int cdErroConv) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return converterStringToDouble(nomeElemento,cdErroConv);

    } // getDoubleNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Date.
     * Caso o valor do elemento filho nao seja um valor date, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Date objeto contendo o valor do elemento filho procurado.
     */
    public java.util.Date getDate(String nomeElemento, int cdErroConv) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToDate(getValor(nomeElemento),cdErroConv);

        } // if else

    } // getDate()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Date.
     * Caso o valor do elemento filho nao seja um valor Date, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Date objeto contendo o valor em Date do elemento filho procurado.
     */
    public java.util.Date getDateNotNull(String nomeElemento,
            int cdErroNull, int cdErroConv) {

        existeFilho(nomeElemento, cdErroNull);

        return converterStringToDate(getValor(nomeElemento),cdErroConv);

    } // getDateNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Date.
     * Caso o valor do elemento filho nao seja um valor Date, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Date objeto contendo o valor em Date do elemento filho procurado.
     */
    public java.util.Date getDateNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio, int cdErroConv) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return converterStringToDate(getValor(nomeElemento),cdErroConv);

    } // getDateNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Timestamp.
     * Caso o valor do elemento filho nao seja um valor timestamp, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Timestamp objeto contendo o valor do elemento filho procurado.
     */
    public java.sql.Timestamp getTimestamp(String nomeElemento, int cdErroConv) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToTimestamp(
                            getValor(nomeElemento), cdErroConv);

        } // if else

    } // getTimestamp()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Timestamp.
     * Caso o valor do elemento filho nao seja um valor timestamp ou nulo, uma
     * excecao ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Timestamp objeto contendo o valor do elemento filho procurado.
     */
    public java.sql.Timestamp getTimestampNotNull(String nomeElemento,
            int cdErroNull, int cdErroConv) {

        existeFilho(nomeElemento, cdErroNull);

        return converterStringToTimestamp(getValor(nomeElemento), cdErroConv);

    } // getTimestampNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Timestamp.
     * Caso o valor do elemento filho nao seja um valor Timestamp, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Timestamp objeto contendo o valor do elemento filho procurado.
     */
    public java.sql.Timestamp getTimestampNotNullouVazio(String nomeElemento,
            int cdErroNullouVazio, int cdErroConv) {

        existeFilhoComValor(nomeElemento, cdErroNullouVazio);

        return converterStringToTimestamp(getValor(nomeElemento), cdErroConv);

    } // getTimestampNotNullouVazio()

    ///////////////////////////////////////////////////////////////////////////
    // Implementa��o antiga:
    ///////////////////////////////////////////////////////////////////////////

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *  procurado.
     */
    public Integer getInt(String nomeElemento) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToInt(nomeElemento);

        } // if else

    } // getInt()

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *  procurado.
     */
    public Integer getIntNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return converterStringToInt(nomeElemento);

    } // getIntNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *  procurado.
     */
    public Integer getIntNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return converterStringToInt(nomeElemento);

    } // getIntNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato float.
     * Caso o valor do elemento filho nao seja um valor float, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Float objeto contendo o valor em float do elemento filho
     * procurado.
     */
    public Float getFloat(String nomeElemento) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToFloat(nomeElemento);

        } // if else

    } // getFloat()

    /**
     * m�todo que retorna o valor de um elemento filho no formato float.
     * Caso o valor do elemento filho nao seja um valor float, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Float objeto contendo o valor em float do elemento filho
     *  procurado.
     */
    public Float getFloatNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return converterStringToFloat(nomeElemento);

    } // getFloatNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato float.
     * Caso o valor do elemento filho nao seja um valor float, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Float objeto contendo o valor em float do elemento filho
     *  procurado.
     */
    public Float getFloatNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return converterStringToFloat(nomeElemento);

    } // getFloatNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato long.
     * Caso o valor do elemento filho nao seja um valor long, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Long objeto contendo o valor em long do elemento filho procurado.
     */
    public Long getLong(String nomeElemento) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToLong(nomeElemento);

        } // if else

    } // getLong()

    /**
     * m�todo que retorna o valor de um elemento filho no formato long.
     * Caso o valor do elemento filho nao seja um valor long, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Long objeto contendo o valor em long do elemento filho procurado.
     */
    public Long getLongNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return converterStringToLong(nomeElemento);

    } // getLongNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato long.
     * Caso o valor do elemento filho nao seja um valor long, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Long objeto contendo o valor em long do elemento filho procurado.
     */
    public Long getLongNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return converterStringToLong(nomeElemento);

    } // getLongNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato double.
     * Caso o valor do elemento filho nao seja um valor double, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Double objeto contendo o valor em double do elemento filho
     * procurado.
     */
    public Double getDouble(String nomeElemento) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else  {

            return converterStringToDouble(nomeElemento);

        } // if else

    } // getDouble()
    
    /**
	 * <b>Objetivo:</b> <BR>
	 * Retornar o valor de um elemento filho no formato Double. Ele � capaz de
	 * retornar o valor em double do elemento com pontos flutuantes aplicados.<BR>
	 * <b>Funcionamento:</b> <BR>
	 * Este metodo recebe o nome do filho cujo valor se deseja
	 * converter. Este valor pode estar no formato double ou com 
	 * ponto flutuante aplicado. Utilizando regex, o metodo trata essas ocorrencias e
	 * retorna o valor em double.<BR>
	 * <b>Implementador:</b> <BR>
	 * George Lima <BR>
	 * <BR>
	 * 
	 * @param nomeElemento
	 *            elemento filho cujo valor � desejado.
	 * @return Double objeto contendo o valor em double do elemento filho
	 *         procurado.
	 * 
	 */
	public Double getDoublePontoFlutuante(String nomeElemento) {

		if (!contemFilho(nomeElemento)) {

			return null;

		} else {

			return converterStringToDoubleRegex(nomeElemento);

		} // if else

	} // getDoublePontoFlutuante()

    /**
     * m�todo que retorna o valor de um elemento filho no formato double.
     * Caso o valor do elemento filho nao seja um valor double, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Double objeto contendo o valor em double do elemento filho
     * procurado.
     */
    public Double getDoubleNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return converterStringToDouble(nomeElemento);

    } // getDateNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato double.
     * Caso o valor do elemento filho nao seja um valor double, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Double objeto contendo o valor em double do elemento filho
     * procurado.
     */
    public Double getDoubleNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return converterStringToDouble(nomeElemento);

    } // getDoubleNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Date.
     * Caso o valor do elemento filho nao seja um valor date, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Date objeto contendo o valor do elemento filho procurado.
     */
    public java.util.Date getDate(String nomeElemento) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToDate(getValor(nomeElemento));

        } // if else

    } // getDate()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Date.
     * Caso o valor do elemento filho nao seja um valor Date, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Date objeto contendo o valor em Date do elemento filho procurado.
     */
    public java.util.Date getDateNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return converterStringToDate(getValor(nomeElemento));

    } // getDateNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Date.
     * Caso o valor do elemento filho nao seja um valor Date, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele � vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Date objeto contendo o valor em Date do elemento filho procurado.
     */
    public java.util.Date getDateNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return converterStringToDate(getValor(nomeElemento));

    } // getDateNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Timestamp.
     * Caso o valor do elemento filho nao seja um valor timestamp, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Timestamp objeto contendo o valor do elemento filho procurado.
     */
    public java.sql.Timestamp getTimestamp(String nomeElemento) {

        if (!contemFilho(nomeElemento)) {

            return null;

        } else {

            return converterStringToTimestamp(getValor(nomeElemento));

        } // if else

    } // getTimestamp()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Timestamp.
     * Caso o valor do elemento filho nao seja um valor Date, uma excecao
     * ElementException � lancada. <br>
     * Caso o elemento filho procurado nao exista, uma excecao ElementException
     * tambem � lancada. <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * de um elemento filho e do tipo do seu valor. <br>
     *                                                                      <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Timestamp objeto contendo o valor do filho procurado.
     */
    public java.sql.Timestamp getTimestampNotNull(String nomeElemento) {

        existeFilho(nomeElemento);

        return converterStringToTimestamp(getValor(nomeElemento));

    } // getTimestampNotNull()

    /**
     * m�todo que retorna o valor de um elemento filho no formato Timestamp.
     * Caso o valor do elemento filho nao seja um valor Date, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista ou seja vazio uma excecao
     * ElementException � lancada.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * da existencia de um elemento filho e do tipo do seu valor, verificando
     * tambem se ele �  vazio. <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Timestamp objeto contendo o valor do filho procurado.
     */
    public java.sql.Timestamp getTimestampNotNullouVazio(String nomeElemento) {

        existeFilhoComValor(nomeElemento);

        return converterStringToTimestamp(getValor(nomeElemento));

    } // getTimestampNotNullouVazio()

    /**
     * m�todo que retorna o valor de um elemento filho no formato inteiro.
     * Caso o valor do elemento filho nao seja um valor inteiro, uma excecao
     * ElementException � lancada.
     * <br>
     * Caso o elemento filho procurado nao exista, o valor null � retornado.
     * <br>
     * Este m�todo � util em situacoes onde se deseja forcar a obrigatoriedade
     * do tipo do valor de um elemento filho.
     * <br>
     * @param nomeElemento elemento filho cujo valor � desejado.
     * @return Integer objeto contendo o valor em inteiro do elemento filho
     *  procurado.
     */
    public String getMask(String nomeElemento, String mascara) {

        Elemento e = getFilho(nomeElemento);

        if (e == null) {

            return null;

        } else {

            return converterStringToMask(nomeElemento, mascara);

        } // if else

    } // getMask()

    /**
     * m�todo que recebe como par�metro o nome de um elemento e verifica se ha
     * um elemento com este nome no elemento que invocou o m�todo. <br>
     * O valor true �retornado se existe um elemento com o nome passado no
     * par�metro, e false caso contrario. <br>
     *                                                                      <br>
     * Por exemplo, considere o elemento abaixo:                            <br>
     *                                                                      <br>
     *    < Pessoa >                                                        <br>
     *      < Nome > Joao < /Nome >                                         <br>
     *   < /Pessoa >                                                        <br>
     *                                                                      <br>
     * Elemento filho = Pessoa.contemFilho("Nome");                         <br>
     * boolean B = Pessoa.contemFilho("Idade");                             <br>
     *                                                                      <br>
     * o valor de A sera true, pois existe um elemento chamado Nome no elemento
     * Pessoa.                                                              <br>
     * o valor de B sera false, pois nao existe um elemento chamado Idade no
     * elemento Pessoa.                                                     <br>
     *                                                                      <br>
     * @param nomeProcurado String nome do elemento filho  procurado.
     * @return boolean true se existir um elemento filho com o nome procurado
     *  e false caso contrario.
     */
    public String elementoToString(Elemento elemento) {

        return elemento.toString();

    } // elementoToString()

    /**
     * Transforma um representa��o em String de um documento XML para sua
     * correspondente estrutura de objetos Elemento. <br>
     * A String passada no par�metro deve representar a estrutura padrao de um
     * documento xml, como por exemplo a String abaixo: <br>
     * "<Pessoa><nome>joao</nome></Pessoa>"
     * <br>
     * @return Elemento apontando para estrutura de objetos Elemento.
     * @param xml string que representa uma estrutura XML.
     */
    public static Elemento stringToElemento(String xml) {

        ElementoParser elementoParser = new ElementoParser(new StringBuilder(
        		xml.trim()));

        return elementoParser.toElemento();

    } // stringToElemento()

    /**
     * Transforma um representa��o em String de um documento XML para sua
     * correspondente estrutura de objetos Elemento. <br>
     * A String passada no par�metro deve representar a estrutura padrao de um
     * documento xml, como por exemplo a String abaixo: <br>
     * "<Pessoa><nome>joao</nome></Pessoa>"
     * <br>
     * @return Elemento apontando para estrutura de objetos Elemento.
     * @param xml string que representa uma estrutura XML.
     */
    public static Elemento stringToElemento(StringBuilder xml) {

        ElementoParser elementoParser = new ElementoParser( xml );

        return elementoParser.toElemento();

    } // stringToElemento()    
    
    /**
     * @implementador Joackson Ferreira Lins                                <br>
     *                                                                      <br>
     * <B>Objetivo:</B>                                                     <br>
     *          Excluir todos os filhos do elemento com este nome.
     * 
     * @param nomeElemento O nome dos filhos do elemento q deseja excluir
     */
    public void excluirTodos(String nomeElemento) {

    	List lstFilhos = obterFilhos();
    	if (lstFilhos != null) {

    		lstFilhos.removeAll(getColecao(nomeElemento));

    	}

    } // excluirTodos()

    /**
     * Retorna um objeto Elemento no formato de uma String a qual representa a
     * estrutura XML padrao. <br>
     * A String retornada representa a estrutura padrao de um
     * documento xml, como por exemplo a String abaixo: <br>
     * "<Pessoa><nome>joao</nome></Pessoa>"
     * <br>
     * @return String representacao da estrutura XML em formato de String.
     */
    public String toString() {

        StringBuilder buf = new StringBuilder();

        toString(buf);

        return buf.toString();

    } // toString()

    /* --------------------- m�todos Privados Auxiliares -------------------- */

    // m�todo auxiliar para a transformacao de um objeto Elemento em uma String.
    private void toString(StringBuilder buf) {

        int tamFilhos = obterTamanhoFilhos();

        if ((valor_ == null) && (tamFilhos == 0)) {

            buf.append(INICIO_TAG).append(nome_).append(BARRA).append(FIM_TAG);

            return;

        } // if

        buf.append(INICIO_TAG).append(nome_).append(FIM_TAG);

        if (valor_ != null) {

            buf.append(valor_);

        } // if

        for (int i = 0; i < tamFilhos; i++) {

            Elemento filho = (Elemento) obterFilhos().get(i);

            filho.toString(buf);

        } // for

        buf.append(INICIO_TAG).append(BARRA).append(nome_).append(FIM_TAG);

    } // toString()

    /**
     * Auxiliar na busca de um Elemento filho.
     *
     * @param nomeElemento <code>String</code> identifica o filho
     *
     * @return <code>Elemento</code> o filho obtido.
     */
    private Elemento getFilhoCriando(String nomeElemento) {

        int pos = nomeElemento.indexOf('.');

        String nomeE = nomeElemento;
        String resto = null;

        if (pos != -1) {

            nomeE = nomeElemento.substring(0, pos);
            resto = nomeElemento.substring(pos + 1);

        } // if

        Elemento filho;
        String nomeFilho;

        // Existem filhos?
        int tamFilhos = obterTamanhoFilhos();

        // Procurar
        for (int i = 0; i < tamFilhos; i++) {

            filho     = (Elemento) obterFilhos().get(i);
            nomeFilho = filho.getNome();

            if (nomeFilho.equalsIgnoreCase(nomeE)) {
                if (resto == null) {

                    return filho;

                } else {

                    return filho.getFilhoCriando(resto);

                } // if else
            } // if

        } //for

        filho = new Elemento(nomeE);

        adicionarFilho(filho);

        if (resto != null) {

          return filho.getFilhoCriando(resto);

        } else {

            return filho;

        } // if else

    } // getFilhoCriando()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Float. Todos os valores dos elementos sao armazenados como String.
     */
    private Float converterStringToFloat(String nomeElemento) {

        try {
            String valor = getValor(nomeElemento);

            if (valor != null) {

                return new Float(Float.parseFloat(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

            throw new ElementException("*O valor " + getValor(nomeElemento)
                    + " do elemento " + nomeElemento
                    + " nao � um valor float$");

        } // try catch

    } // converterStringToString()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Float.
     * todos os valores dos elementos sao armazenados como String.
     */
    private Float converterStringToFloat(String nomeElemento, int cdErroConv) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return new Float(Float.parseFloat(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

          throw new ElementException();

        }

    } // converterStringToFloat()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Long. Todos os valores dos elementos sao armazenados como String.
     */
    private Long converterStringToLong(String nomeElemento) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return new Long(Long.parseLong(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

            throw new ElementException("*O valor " + getValor(nomeElemento)
                    + " do elemento " + nomeElemento
                    + " nao � um valor long$");

        } // try catch

    } // converterStringToLong()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Long. Todos os valores dos elementos sao armazenados como String.
     */
    private Long converterStringToLong(String nomeElemento, int cdErroConv) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return new Long(Long.parseLong(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

          throw new ElementException();

        } // try catch

    } // converterStringToLong()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Integer. Todos os valores dos elementos sao armazenados como String.
     */
    private Integer converterStringToInt(String nomeElemento) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return Integer.valueOf(Integer.parseInt(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

            throw new ElementException("*O valor " + getValor(nomeElemento)
                    + " do elemento " + nomeElemento
                    + " nao � um valor inteiro$");

        } // try catch

    } // converterStringToInt()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Integer.
     * todos os valores dos elementos sao armazenados como String.
     */
    private Integer converterStringToInt(String nomeElemento, int cdErroConv) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return Integer.valueOf(Integer.parseInt(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

            throw new ElementException();

        } // try catch

    } // converterStringToInt()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Date. Todos os valores dos elementos sao armazenados como String.
     */
    private java.util.Date converterStringToDate(String valor) {

        //  SimpleDateFormat dateFormat = new SimpleDateFormat();
        //
        //  try {

        if (valor != null) {

            return java.sql.Date.valueOf(valor); // dateFormat.parse(valor);

        } else {

            return null;

        } // if

        //  } catch (ParseException pe) {
        //
        //      throw new ElementException("*O valor do elemento nao pode ser "
        //      + "convertido para java.util.Date$");
        //
        //  }

    } // converterStringToDate()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Date. Todos os valores dos elementos sao armazenados como String.
     */
    private java.util.Date converterStringToDate(String valor, int cdErroConv) {

        //  SimpleDateFormat dateFormat = new SimpleDateFormat();
        //
        //  try {

        if (valor != null) {

            return java.sql.Date.valueOf(valor); // dateFormat.parse(valor);

        } else {

            return null;

        } // if

        //  } catch (ParseException pe) {
        //
        //      throw new ElementException(cdErroConv);
        //
        //  }
    } // converterStringToDate()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Date. Todos os valores dos elementos sao armazenados como String.
     */
    private java.sql.Timestamp converterStringToTimestamp(String valor) {

        try {

            if (valor != null) {

                return java.sql.Timestamp.valueOf(valor);

            } else {

                return null;

            } // if

        } catch (IllegalArgumentException pe) {

            throw new ElementException("*O valor do elemento nao pode ser "
                    + "convertido para java.sql.Timestamp$");

        }

    } // converterStringToTimestamp()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Date. Todos os valores dos elementos sao armazenados como String.
     */
    private java.sql.Timestamp converterStringToTimestamp(String valor,
            int cdErroConv) {

        try {

            if (valor != null) {

                return java.sql.Timestamp.valueOf(valor);

            } else {

                return null;

            } // if

        } catch (IllegalArgumentException pe) {

            throw new ElementException();

        } // try catch

    } // convterStringtoTimestamp

    /*
     * M�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Double. Todos os valores dos elementos sao armazenados como String.
     */
    private Double converterStringToDouble(String nomeElemento) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return Double.valueOf(Double.parseDouble(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

            throw new ElementException("*O valor " + getValor(nomeElemento)
                    + " do elemento " + nomeElemento
                    + " nao � um valor double$");
        }

    } // converterStringToDouble()
    
    /*
	 * M�todo auxiliar utilizado na conversao do valor de um elemento de String
	 * para Double utilizando regez. Este m�todo � capaz de tratar strings
	 * numericas com mascaras e sem mascaras. Todos os valores dos elementos sao
	 * armazenados como String.
	 */
	private Double converterStringToDoubleRegex(String nomeElemento) {

		try {

			String valor = getValor(nomeElemento);

			if (valor != null) {

				valor = valor.replaceAll("[^\\d-,\\.]++", "");
				if (valor.matches(".+\\.\\d+,\\d+$"))
					return Double.valueOf(valor.replaceAll("\\.", "")
							.replaceAll(",", "."));
				if (valor.matches(".+,\\d+\\.\\d+$"))
					return Double.valueOf(valor.replaceAll(",", ""));
				return Double.valueOf(valor.replaceAll(",", "."));

			} else {

				return null;

			} // if

		} catch (NumberFormatException nf) {

			throw new ElementException("*O valor " + getValor(nomeElemento)
					+ " do elemento " + nomeElemento
					+ " nao � um valor double$");
		}

	} // converterStringToDouble()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * para Double. Todos os valores dos elementos sao armazenados como String.
     */
    private Double converterStringToDouble(String nomeElemento, int cdErroConv) {

        try {

            String valor = getValor(nomeElemento);

            if (valor != null) {

                return Double.valueOf(Double.parseDouble(valor));

            } else {

                return null;

            } // if

        } catch (NumberFormatException nf) {

            throw new ElementException();

        } // try catch

    } // converterStringToDouble()

    /*
     * m�todo auxiliar utilizado na conversao do valor de um elemento de String
     * vindo do banco de dados (NNNNNNN,NN) para Currency (N.NNN.NNN,NN).
     * Todos os valores dos elementos sao armazenados como String.
     */
    private String converterStringToMask(String nomeElemento, String mascara) {

        String valor = null;

        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();

        //try {
        df.applyPattern(mascara);   // "#,##0.00"
        valor = df.format(Double.parseDouble(getValor(nomeElemento)));

        //} catch (NumberFormatException nfe) {
        //    throw new ElementException();
        //}

        return valor;

    } // converterStringToMask()

    ///////////////////////////////////////////////////////////////////////////
    // Novos m�todos para manipula��o de objetos gen�ricos como elementos
    // Elemento v2.0
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Retorna o valor de um elemento filho Elemento sem nenhum casting
     *                                                                      <br>
     * @param nomeCampo nome do Elemento filho cujo valor � desejado.
     * @return Object - valor do elemento filho ou null caso o filho nao exista
     *  ou nao possua valor.
     */
    public Object getObjeto(String nomeCampo) { // v2.0

        Elemento e = getFilho(nomeCampo);

        if (e == null) {

            return null;

        } else {

            return e.getObject();

        } // if else

    } // getObject()

    /**
     * Inclui um Elemento filho com valor no elemento de onde o m�todo �
     * invocado. <br>
     * Este m�todo recebe como par�metro o nome e o valor em inteiro do novo
     * filho a ser incluido, sendo o valor no formato Objeto <br>
     *                                                                      <br>
     * @param nomeFilho String nome do filho a ser incluido.
     * @param valorFilho Object valor a ser incluido.
     * @return Elemento, elemento filho que foi criado e inserido.
     */
    public Elemento incluirFilho(String nomeFilho, Object valorFilho) { // v2.0

        return setValor(nomeFilho, valorFilho);

    } // incluirFilho()

    /**
     * Retorna o valor do Elemento, caso o elemento nao possua valor, sera
     * retornado null.
     *                                                                      <br>
     * @return java.lang.Object - valor do Elemento.
     */
    public Object getObject() { // v2.0

        return valor_;

    } // getObject

    /**
     * Retorna o valor do Elemento, caso o elemento nao possua valor, sera
     * retornado null.
     *                                                                      <br>
     * @return java.lang.String - valor do Elemento.
     */
    public String getValor() { // v2.0

        if (valor_ != null) {

            return valor_.toString();

        } else {

            return null;

        } // if else

    } // getValor()

    /**
     * Modifica ou seta o valor de um elemento filho. Caso o elemento filho nao
     * exista ou nao possua valor uma RuntimeException � lancada. <br>
     *                                                                      <br>
     * @return Elemento retorna o elemento filho cujo valor foi modificado.
     * @param nomeCampo nome do elemento filho que se deseja modificar o valor
     * @param valor novo valor a ser setado para o elemento filho.
     */
    public Elemento setValor(String nomeCampo, Object valor) { // v2.0

        Elemento e = getFilhoCriando(nomeCampo);

        e.setValor(valor);

        return e;

    } // setValor()

    /**
     * Modifica ou seta o valor do elemento. Caso o elemento nao possua valor,
     * uma RuntimeException � lancada.
     * @param valor novo valor a ser setado para o elemento.
     */
    public void setValor(Object valor) { // v2.0

        if (obterTamanhoFilhos() > 0) {
            throw new RuntimeException("N�o � poss�vel incluir um valor "
                + "diretamente em um elemento que j� possui filhos");
        } // if

        valor_ = valor;

    } // setValor()

    ///////////////////////////////////////////////////////////////////////////
    // Fim dos m�todos da vers�o 2.0
    ///////////////////////////////////////////////////////////////////////////

    /**
     * <b> Implementador: </b> Nazareno Duarte <br>
     * <b> Data: </b> 11/Out/2002 <br>
     * <b> Objetivo: </b> <br>
     *              Verificar se um elemento filho existe. Lan�ar uma exce��o
     *              do tipo ElementoException caso ele n�o exista. <br>
     *                                                                      <br>
     * @param nomeElemento o nome do elemento que ser� verificado <br>
     * @throws ElementoException caso o elemento n�o exista <br>
     */
    private void existeFilho(String nomeElemento) throws ElementException {

        if (!contemFilho(nomeElemento)) {

            String msg = "*Elemento " + nomeElemento + " nao foi encontrado$";

            throw new ElementException(msg);

        } // if

    } // existeFilho()

    /**
     * <b> Implementador: </b> Nazareno Duarte <br>
     * <b> Data: </b> 11/Out/2002 <br>
     * <b> Objetivo: </b> <br>
     *     Verificar se um elemento filho existe. Lan�ar uma exce��o do tipo
     *     ElementoException caso ele n�o exista. <br>
     *                                                                      <br>
     * @param nomeElemento o nome do elemento que ser� verificado <br>
     * @throws ElementoException caso o elemento n�o exista <br>
     */
    private void filhoPossuiValor(String nomeElemento) throws ElementException {

        if (getValor(nomeElemento).trim().equals("")) {

            String msg = "*Elemento " + nomeElemento + " eh vazio$";

            throw new ElementException(msg);

        } // if

    } // filhoPossuiValor()

    /**
     * <b> Implementador: </b> Nazareno Duarte <br>
     * <b> Data: </b> 11/Out/2002 <br>
     * <b> Objetivo: </b>
     *     Verificar o valor de um filho pode ser recuperado. Lan�ar uma exce��o
     *     do tipo ElementoException caso ele n�o exista. br>
     *                                                                      <br>
     * @param nomeElemento o nome do elemento que ser� verificado <br>
     * @throws ElementoException caso o elemento n�o exista <br>
     */
    private void existeFilhoComValor(String nomeElemento)
            throws ElementException {

        existeFilho(nomeElemento);
        filhoPossuiValor(nomeElemento);

    } // existeFilhoComValor()

    /**
     * <b> Implementador: </b> Nazareno Duarte <br>
     * <b> Data: </b> 11/Out/2002 <br>
     * <b> Objetivo: </b> <br>
     *              Verificar se um elemento filho existe. Lan�ar uma exce��o
     *              do tipo ElementoException caso ele n�o exista. <br>
     *                                                                      <br>
     * @param nomeElemento o nome do elemento que ser� verificado <br>
     * @param cdErro c�digo da exce��o gerada caso o filho n�o exista <br>
     * @throws ElementoException caso o elemento n�o exista <br>
     */
    private void existeFilho(String nomeElemento, int cdErro)
            throws ElementException {

        if (!contemFilho(nomeElemento)) {

            throw new ElementException();

        } // if

    } // existeFilho()

    /**
     * <b> Implementador: </b> Nazareno Duarte <br>
     * <b> Data: </b> 11/Out/2002 <br>
     * <b> Objetivo: </b> <br>
     *     Verificar se um elemento filho existe. Lan�ar uma exce��o do tipo
     *     ElementoException caso ele n�o exista. <br>
     *                                                                      <br>
     * @param nomeElemento o nome do elemento que ser� verificado <br>
     * @param cdErro c�digo da exce��o gerada caso o valor n�o exista <br>
     * @throws ElementoException caso o elemento n�o exista <br>
     */
    private void filhoPossuiValor(String nomeElemento, int cdErro)
            throws ElementException {

        String valor = getValor(nomeElemento);

        if ((valor == null) || (valor.trim().equals(""))) {

            throw new ElementException();

        } // if

    } // filhoPossuiValor()

    /**
     * <b> Implementador: </b> Nazareno Duarte <br>
     * <b> Data: </b> 11/Out/2002 <br>
     * <b> Objetivo: </b>
     *     Verificar o valor de um filho pode ser recuperado. Lan�ar uma exce��o
     *     do tipo ElementoException caso ele n�o exista. br>
     *                                                                      <br>
     * @param nomeElemento o nome do elemento que ser� verificado <br>
     * @throws ElementoException caso o elemento n�o exista <br>
     */
    private void existeFilhoComValor(String nomeElemento, int cdErro)
            throws ElementException {

        existeFilho(nomeElemento, cdErro);
        filhoPossuiValor(nomeElemento, cdErro);

    } // existeFilhoComValor()

    /**
     * <b> Implementador: </b> Renan Pinheiro do Egypto Guerra <br>
     * <b> Data: </b> 06/05/2003 <br>
     * <b> Objetivo: </b>
     *     Criar uma copia do elemento em questao <br>
     * @throws CloneNotSupportedException <br>
     */
    public Object clone() throws CloneNotSupportedException {

        return null;
    } // clone()

    /**
     * Criar uma <code>String</code> a partir do Elemento, identando.
     *
     * @return <code>String</code> o valor obtido
     */
    public String toFormatedString() {

        return toFormatedString(" ");

    } // toFormatedString()

    /**
     * Criar uma <code>String</code> a partir do Elemento, identando.
     *
     * @param identador <code>String</code> usado para formatar o resultado
     *
     * @return <code>String</code> o valor obtido
     */
    public String toFormatedString(String identador) {

        StringBuilder resultado = new StringBuilder("");
        StringBuilder identacao = new StringBuilder(identador);

        identacao.append(" ");

        resultado.append(identacao).append("<").append(getNome());

        int tamFilhos = obterTamanhoFilhos();

        if (tamFilhos == 0) {

            if (getValor() == null) {

                resultado.append("/>\n");

            } else {

                resultado.append(">").append(getValor())
                        .append("</").append(getNome()).append(">\n");

            } // if else

        } else {

            resultado.append(">\n");

            for (int i = 0; i < tamFilhos; i++) {

                Elemento filho = (Elemento) obterFilhos().get(i);

                resultado.append(filho.toFormatedString(identacao.toString()));

             } // for

            resultado.append(identacao)
                    .append("</").append(getNome()).append(">\n");


        } // if else

        return resultado.toString();

    } // toFormatedString()
    
} // Elemento
