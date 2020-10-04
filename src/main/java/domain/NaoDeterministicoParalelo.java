package main.java.domain;

import java.util.Set;
import java.util.TreeSet;

public class NaoDeterministicoParalelo {
  private String  fitaDeEntrada;               //  Σ é um alfabeto (de símbolos) de entrada
  private int estadoInicial ;                  //  q0 é um elemento distinguido de Q: estado inicial
  private int aceitacao[];
  private int [][][] transicao  ;

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

  public boolean execucao(String fitaDeEntrada){
    int posicao =0;
    this.fitaDeEntrada = fitaDeEntrada;
    return controleFinito(posicao);
  }

  private boolean controleFinito(int posicao){
    int [] estados = {estadoInicial};
    while(posicao < fitaDeEntrada.length()){
      imprimeCI(fitaDeEntrada, estados, posicao);
      int elemento = Integer.parseInt(fitaDeEntrada.substring(posicao, posicao +1 ));
      int[] novosEstados = new int[]{};

      for (int i: estados){
        int [] destinoTransicao = transicao[i][elemento];
        novosEstados = uniao(novosEstados, destinoTransicao);
        estados = novosEstados;
      }

      if(estados.length == 0 )
        break;

      posicao ++;
    }
    imprimeCI(fitaDeEntrada, estados, posicao);

    if(verificaAceitacao(estados))
      return true;
    else
      return false;
  }

  private int[] uniao(int[] estados, int[] novosEstados) {
    Set<Integer> uniao = new TreeSet<>();

    for (int i: estados)    uniao.add(i);
    for(int i: novosEstados)   uniao.add(i);

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
        if(i == j){
          return true;
        }
      }
    }
    return false;
  }

  public int[][] convertToDFA(){
    return null;
  }

}
