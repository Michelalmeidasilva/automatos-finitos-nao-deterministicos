package main.java.domain;

import java.util.Set;
import java.util.TreeSet;

public class NaoDeterministicoEspontaneo {
  protected String  fitaDeEntrada;               // Σ é um alfabeto (de símbolos) de entrada
  protected int estadoInicial ;                  //  q0 é um elemento distinguido de Q: estado inicial
  int [][][] transicao  ;
  int [] aceitacao;
  int [][] transicaoVazia;

  public NaoDeterministicoEspontaneo(int [][][] transicao, int[] estadosAceitacao, int[][] transicaoVazia){
    this.aceitacao = new int[]{0};
    this.estadoInicial = 0;
    this.transicao = transicao;
    this.transicaoVazia = transicaoVazia;

  };
  public boolean execucao(String fitaDeEntrada){
    this.fitaDeEntrada = fitaDeEntrada;
    return controleFinito();
  }

  private boolean controleFinito(){
    String entrada = fitaDeEntrada;
    int posicao =0 ;
    int [] estados =eclose(new int[]{estadoInicial});

    while(posicao < entrada.length()){
      imprimeCI(entrada, estados, posicao);
      int elemento = Integer.parseInt(entrada.substring(posicao, posicao +1 ));

      int[] novosEstados = new int[]{};
      for (int i: estados){
        if(elemento == -1){
          return false;
        }
        int [] destinoTransicao = transicao[i][elemento];
        novosEstados = uniao(novosEstados, destinoTransicao);
        novosEstados= eclose(novosEstados);
      }
      estados = novosEstados;
      if(estados.length == 0 ){
        break;
      }
      posicao ++;
    }
    imprimeCI(entrada, estados, posicao);

    if(aceita(estados)){
      System.out.println("aceita");
      return true;
    }else {
      System.out.println("rejeita");
      return false;
    }
  }

  private int[] eclose(int[] estados) {
    int[] eclosevec = estados;
    System.out.println("Eclose novamente");
    for(int i : estados){
      System.out.println(transicaoVazia[i]);
      int [] ecloseAux= transicaoVazia[i];

      int [] ecloseAux2 =eclose(ecloseAux);
      eclosevec = uniao(eclosevec, ecloseAux);
      eclosevec = uniao(eclosevec, ecloseAux2);
    }
    return eclosevec;
  }

  private int[] uniao(int[] estados, int[] novosEstados) {
    Set<Integer> uniao = new TreeSet<>();
    System.out.println("Uniao");

    for (int i: estados) {
      uniao.add(i);
    }
    for(int i: novosEstados) {
      uniao.add(i);
      System.out.println(uniao);

    }

    int[] ret = new int[uniao.size()];
    int j = 0 ;

    for(int  i: uniao) {
      ret[j++] = i;
    }

    return ret;
  }


  private void imprimeCI(String entrada,int []estado,  int posicao) {
    System.out.print(entrada.substring(0, posicao) );
    System.out.print("[" );
    for (int i = 0; i<  estado.length; i ++){
      System.out.print("q"+ estado[i] );
      if (i < estado.length - 1) {
        System.out.print(",");
      }
    }
    System.out.print("]");
    System.out.println(entrada.substring(posicao));
  }

  private boolean aceita(int[] estados) {
    if(estados == null) return false;
    for (int i : estados){
      for(int j : aceitacao){
        if(i == j){
          return true;
        }
      }
    }
    return false ;
  }

  public int[][] convertToDFA(){
    return null;
  }

}


