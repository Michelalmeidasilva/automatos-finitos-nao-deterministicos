package main.java.domain;

import main.java.exceptions.IsNotBelongOnLanguage;
import main.java.utils.IOValidator;

import java.util.Set;
import java.util.TreeSet;

/// tem-se um autômato finito não determinístico (AFN), se cada par (estado, simbolo) é uma transição para um ou mais estados de transição
public class NaoDeterministicoParalelo {
  private String  fitaDeEntrada;               //  Σ é um alfabeto (de símbolos) de entrada
  private int estadoInicial ;                  //  q0 é um elemento distinguido de Q: estado inicial
  private int aceitacao[];
  private int [][][] transicao  ;
  private boolean debug;
  public int posicaoAtual = 0;

  public NaoDeterministicoParalelo(int [][][] transicao, int[] estadosAceitacao, int estadoInicial ){
    this.aceitacao = estadosAceitacao;
    this.estadoInicial = estadoInicial  ;
    this.transicao = transicao;
  };
  public NaoDeterministicoParalelo(char [][][] transicao, char[] estadosAceitacao, char estadoInicial ){
    IOValidator validator = new IOValidator();
    this.aceitacao = validator.convertArrayCharToArrayInt(estadosAceitacao);
    this.estadoInicial = estadoInicial;
    this.transicao = validator.convertMatriz3DCharToInt(transicao, debug) ;
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
    posicaoAtual = 0;
    int [] estados = {estadoInicial};

    if(debug)   imprimeCI(fitaDeEntrada, estados, posicaoAtual );

    while(posicaoAtual < fitaDeEntrada.length()){
      int elemento  = -1;

      try {
        elemento = Integer.parseInt(fitaDeEntrada.substring(posicaoAtual, posicaoAtual +1 ));
      }catch  (NumberFormatException e){
        elemento = fitaDeEntrada.charAt(posicaoAtual) - 96;
        System.out.println(elemento);
      }

      estados = controleFinito(estados, elemento);
      posicaoAtual ++;
      if(debug) {
        System.out.print(" elemento " + elemento + ":  ");
        imprimeCI(fitaDeEntrada, estados, posicaoAtual );
      }
    }
    return estados;
  }

  private int [] controleFinito(int [] estados, int elemento){
    int[] novosEstados = new int[]{};
    for (int i: estados){
      int [] destinoTransicao = transicao[i][elemento];
      novosEstados = uniao(novosEstados, destinoTransicao);
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
    System.out.print(entrada.substring(0, posicao) + "[");
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
