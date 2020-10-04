package main.java.domain;

import java.util.Set;
import java.util.TreeSet;

public class ENFA {

    private boolean debug = false;
    private int[] aceitacao;
    private int estadoInicial;
    private int[][][] transicao;
    private int[][] transicaoVazia;
    private String mapeamentoEntrada;

    public ENFA(int[] aceitacao, int estadoInicial, int [][][] transicao, int [][] transicaoVazia ,  String mapeamentoEntrada,boolean debug ) {
        this.aceitacao = aceitacao;
        this.estadoInicial = estadoInicial;
        this.transicao= transicao;
        this.transicaoVazia = transicaoVazia;
        this.mapeamentoEntrada = mapeamentoEntrada;
        this.debug = true;
    }

    public boolean executar(String entrada) {
        System.out.println("---------------");
        System.out.println(mapeamentoEntrada);
        System.out.println("---------------");

        int posicao = 0;
        int[] estados = eclose(new int[]{estadoInicial});
        while (posicao < entrada.length()) {
            if (debug) {
                imprimeCI(entrada, estados, posicao);
            }
            String elemento = entrada.substring(posicao, posicao + 1);

            int[] novosEstados = new int[]{};
            for (int i : estados) {
                int iElemento = mapeamentoEntrada.indexOf(elemento);
                if (iElemento == -1) {
                    return false;
                }
                int[] destinoTransicao = transicao[i][iElemento];

                novosEstados = uniao(novosEstados, destinoTransicao);
                novosEstados = eclose(novosEstados);
            }
            estados = novosEstados;
            if (estados.length == 0) {
                break;
            }
            posicao++;
        }
        if (debug) {
            imprimeCI(entrada, estados, posicao);
        }
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


}