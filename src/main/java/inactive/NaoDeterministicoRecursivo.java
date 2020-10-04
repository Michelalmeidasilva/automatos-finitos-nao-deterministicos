package main.java.inactive;

//http://bezensek.com/blog/2015/04/12/non-deterministic-finite-state-machine-implementation-in-c-number/
//https://github.com/kkdai/nfa/blob/master/nfa.go
public class NaoDeterministicoRecursivo extends AutomatoFinito {
  protected String  fitaDeEntrada;               // Σ é um alfabeto (de símbolos) de entrada
  protected int estadoInicial ;                  //  q0 é um elemento distinguido de Q: estado inicial
   int [][][] transicao  ;
  int aceitacao[];

  public NaoDeterministicoRecursivo(int [][][] transicao, int[] estadosAceitacao ){
    this.aceitacao = estadosAceitacao;
    this.estadoInicial = 0;
    this.transicao = transicao;
  };

  public void execucao(String fitaDeEntrada){
    this.fitaDeEntrada = fitaDeEntrada;
    controleFinito();
  }

  private void controleFinito(){
    String entrada = fitaDeEntrada;
    int [] estados ={estadoInicial};
    int [] estadosFinais = testa(entrada, estados, 0);
    if(aceita(estadosFinais)){
      System.out.println("aceita");
    }else {
      System.out.println("rejeita");
    }
  }

  //bactrack em busca em profundidade
  private int[] testa(String entrada, int[] estados, int posicao) {
    if(posicao == entrada.length()){  //chegou no final
      imprimeCI(entrada, estados[0], posicao);

      if(aceita(estados)){
        return estados;
      }
      System.out.println("<<backtrack>> Fim da cadeia");
      return null;
    }
    int elemento = Integer.parseInt(entrada.substring(posicao, posicao+1));
    for (int i : estados){
      imprimeCI(entrada, i, posicao);
      int[] novosEstados= transicao[i][elemento];
      if(novosEstados.length == 0){
        imprimeCI(entrada, i, posicao);
        System.out.println("<<backTrack>> sem transicoes");
        return null;
      }
      int[] transicoes = testa(entrada, novosEstados, posicao + 1);
      if(transicoes != null) return transicoes ;
    }
    return null;
  }

  private void imprimeCI(String entrada,int estado,  int posicao) {
    System.out.println(entrada.substring(0, posicao) + "[q" +  estado+ "]" + entrada.substring(posicao));
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