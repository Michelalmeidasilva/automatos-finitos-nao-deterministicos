package main.java.domain;

import main.java.exceptions.IsNotBelongOnLanguage;
import main.java.utils.IOValidator;

import java.util.Set;
import java.util.TreeSet;

public class ENFA {

    private boolean debug = false;
    private int[] aceitacao;
    private int estadoInicial;
    private int[][][] transicao;
    private int[][] transicaoVazia;
    private String mapeamentoEntrada;
    private String fitaDeEntrada;

    /**
     * Construtor padrão passando todos os parametros para um automato finito nao deterministico com transição espontanea
     * @param aceitacao
     * @param estadoInicial
     * @param transicao
     * @param transicaoVazia
     * @param mapeamentoEntrada
     */
    public ENFA(int[] aceitacao, int estadoInicial, int [][][] transicao, int [][] transicaoVazia ,  String mapeamentoEntrada ) {
        this.aceitacao = aceitacao;
        this.estadoInicial = estadoInicial;
        this.transicao= transicao;
        this.transicaoVazia = transicaoVazia;
        this.mapeamentoEntrada = mapeamentoEntrada;
    }

    /**
     *  Construtor com conversão de matriz int para char para um automato nao deterministico com transição espontanea
     * @param aceitacao
     * @param estadoInicial
     * @param transicao
     * @param transicaoVazia
     * @param mapeamentoEntrada
     */
    public ENFA(char[] aceitacao, char estadoInicial, char [][][] transicao, char [][] transicaoVazia ,  String mapeamentoEntrada ) {
        IOValidator validator = new IOValidator();
        this.aceitacao = validator.convertArrayCharToArrayInt(aceitacao);
        this.transicao = validator.convertMatrizCharToInt(transicao);
        this.transicaoVazia = validator.convertMatrizCharToInt(transicaoVazia);
        this.mapeamentoEntrada = mapeamentoEntrada;
        this.estadoInicial = estadoInicial;
    }

    /**
     *  Construtor para automato nao deterministico
     *  este nao possui transição espontanea
     * @param aceitacao
     * @param estadoInicial
     * @param transicao
     * @param mapeamentoEntrada
     */
    public ENFA(char[] aceitacao, char estadoInicial, char [][][] transicao, String mapeamentoEntrada ) {
        IOValidator validator = new IOValidator();
        this.aceitacao = validator.convertArrayCharToArrayInt(aceitacao);
        this.transicao = validator.convertMatrizCharToInt(transicao);
        this.mapeamentoEntrada = mapeamentoEntrada;
        this.estadoInicial = estadoInicial;
    }

    private int[] leitura(String entrada) throws IsNotBelongOnLanguage {
        int posicao = 0;
        int[] estados = eclose(new int[]{estadoInicial});
        while (posicao < entrada.length()) {
            if (debug) imprimeCI(entrada, estados, posicao);

            String elemento = entrada.substring(posicao, posicao + 1);

            estados = controleFinito(estados, elemento);
            if (estados.length == 0) {
                break;
            }
            posicao++;
        }
        if (debug) {
            imprimeCI(entrada, estados, posicao);
        }
        return estados;
    }


    private int [] controleFinito(int [] estados, String elemento) throws IsNotBelongOnLanguage {
        int[] novosEstados = new int[]{};
        for (int i : estados) {
            int iElemento = mapeamentoEntrada.indexOf(elemento);
            if (iElemento == -1) {
                throw new IsNotBelongOnLanguage( "elemento:{" + elemento + "} Nao pertence ao alfabeto" );
            }
            int[] destinoTransicao = transicao[i][iElemento];
            novosEstados = uniao(novosEstados, destinoTransicao);
            novosEstados = eclose(novosEstados);
        }
        estados = novosEstados;
        return estados;
    }


    /** A aplicação é executada a partir da entrada podendo dar a exception da entrada nao pertencer ao alfabeto
     * @param entrada
     * @return
     * @throws IsNotBelongOnLanguage
     */
    public boolean executar(String entrada) throws IsNotBelongOnLanguage {
        int [] estados = leitura(entrada);
        if (aceita(estados)) {
            return true;
        } else {
            return false;
        }
    }

    private int[] eclose(int[] estados) {
        int[] eclose = estados;
        for (int i : estados) {
            int[] ecloseAux = transicaoVazia[i];
            int[] ecloseAux2 = eclose(ecloseAux);
            eclose = uniao(eclose, ecloseAux);
            eclose = uniao(eclose, ecloseAux2);
        }
        return eclose;
    }

    private int[] uniao(int[] estados, int[] novosEstados) {
        Set<Integer> uniao = new TreeSet<Integer>();
        for (int i : estados) {
            uniao.add(i);
        }
        for (int i : novosEstados) {
            uniao.add(i);
        }
        int[] ret = new int[uniao.size()];
        int j = 0;
        for (int i : uniao) {
            ret[j++] = i;
        }
        return ret;
    }


    private boolean aceita(int[] estados) {
        for (int i : estados) {
            for (int j : aceitacao) {
                if (i == j) {
                    return true;
                }
            }
        }
        return false;
    }

    private void imprimeCI(String entrada, int[] estados, int posicao) {
        System.out.print(entrada.substring(0, posicao) + "{");
        for (int i = 0; i < estados.length; i++) {
            System.out.print("q" + estados[i]);
            if (i < estados.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("}" + entrada.substring(posicao));
    }

    /** Parametro para mostrar os estados executados a aplicação
     * @param debug
     */
    public void  setDebug(boolean debug){
        this.debug = debug;
    }

    /**Metodo para converter um automato finito nao deterministico para um automato deterministico
     * @param enfa
     */
    public void convertToDfa(ENFA enfa){

    }
}