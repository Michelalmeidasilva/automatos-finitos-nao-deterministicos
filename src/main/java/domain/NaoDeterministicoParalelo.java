package main.java.domain;

import main.java.exceptions.IsNotBelongOnLanguage;

import java.util.Set;
import java.util.TreeSet;

public class NaoDeterministicoParalelo {
  private String  fitaDeEntrada;               //  Σ é um alfabeto (de símbolos) de entrada
  private int estadoInicial ;                  //  q0 é um elemento distinguido de Q: estado inicial
  private int aceitacao[];
  private int [][][] transicao  ;
  private boolean debug;

  public NaoDeterministicoParalelo(int [][][] transicao, int[] estadosAceitacao, int estadoInicial ){
    this.aceitacao = estadosAceitacao;
    this.estadoInicial = estadoInicial  ;
    this.transicao = transicao;
  };

  public NaoDeterministicoParalelo(int [][][] transicao, int[] estadosAceitacao ){
    this.aceitacao = estadosAceitacao;
    this.estadoInicial = 0 ;
    this.transicao = transicao;
  };

  public boolean execucao(String fitaDeEntrada)   {
    this.fitaDeEntrada = fitaDeEntrada;
    int [] estados = leitura();
    if(verificaAceitacao(estados))
      return true;
    else
      return false;
  }

  public boolean execucao(String fitaDeEntrada, boolean  debug)   {
    this.debug = debug;
    return execucao(fitaDeEntrada);
  }

  public int[] leitura(){
    int posicaoAtual = 0;
    int [] estados = {estadoInicial};
    while(posicaoAtual < fitaDeEntrada.length()){
      if(debug)
        imprimeCI(fitaDeEntrada, estados, posicaoAtual);
      int elemento = Integer.parseInt(fitaDeEntrada.substring(posicaoAtual, posicaoAtual +1 ));
      estados = controleFinito(estados, elemento);
      posicaoAtual ++;
    }
    int posicaoFinal = posicaoAtual;
    if(debug)
      imprimeCI(fitaDeEntrada, estados, posicaoFinal);
    return estados;
  }

  private int [] controleFinito(int [] estados, int elemento){
    int[] novosEstados = new int[]{};
    for (int i: estados){
      int [] destinoTransicao = transicao[i][elemento];
      novosEstados = uniao(novosEstados, destinoTransicao);
      for (int j = 0; j < novosEstados.length; j++) {
        System.out.print(" " + novosEstados[j] + " ");
      }
      System.out.println();
      estados = novosEstados;
    }
    if(estados.length == 0 )
      return null;
    return estados;
  }

  private int[] uniao(int[] estados, int[] novosEstados) {
    Set<Integer> uniao = new TreeSet<>();
    for (int i: estados)    uniao.add(i);
    for (int i: novosEstados)   uniao.add(i);
    int[] ret = new int[uniao.size()];
    int j =0 ;
    for(int i: uniao) ret[j++]   = i;
    return ret;
  }

  private void imprimeCI(String entrada,int []estado,  int posicao) {
    System.out.print(entrada.substring(0, posicao) +  "[");
    for (int i = 0; i<  estado.length; i ++){
      System.out.print("q"+ estado[i] );
      if (i < estado.length - 1)  System.out.print(",");
    }
    System.out.println("]" + entrada.substring(posicao));
  }

  private boolean verificaAceitacao(int[] estados) {
    if(estados == null) return false;
    for (int i : estados){
      for(int j : aceitacao){
        if(i == j) return true;
      }
    }
    return false;
  }

  public int[][] convertToDFA(){
    return null;
  }

}
